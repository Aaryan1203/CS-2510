import java.util.ArrayList;
import java.util.Random;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

/*
 * Enhancements: 
 * 1. Added a start button
 * 2. Added a border around every cell for a graphic enhancement
 * 3. Added a score feature
 * 4. Added a timer
 * 5. Added an End game Button
 * 6. Displays the score when you win, lose, or end the game
 * 7. Change the game from light mode to dark mode
 * 8. Not sure if this counts as an enhancement:
 *    Added a border around the start and end buttons for a graphic enhancement
 */

// Class that represents the world
class FloodItWorld extends World {

  // All the cells of the game
  ArrayList<Cell> board;
  ArrayList<Cell> flooded;
  int size;
  int numCol;
  int cellSize;
  int worldSize;
  Cell clicked;
  Random rand;
  int score;
  int totalClicks;
  int time;
  int index;
  boolean startMode;
  boolean lightMode;


  // Constructor that determines the size and number of Colors
  FloodItWorld(int size, int numCol) {
    this.size = size;
    this.numCol = numCol;
    this.rand = new Random();
    if (size < 1 || numCol < 2 || numCol > 10) {
      throw new IllegalArgumentException(
          "Make sure the size is greater than 0 and the number of colors is between 2 and 10");
    }
    this.worldSize = 500;
    this.index = 0;
    this.cellSize = worldSize / size;
    this.totalClicks = (size * numCol) / 3;
    this.time = 0;
    this.flooded = new ArrayList<Cell>();
    this.startMode = true;
    this.lightMode = true;
    this.score = 0;
    this.board = this.makeBoard();
    this.clicked = this.board.get(0);
  }

  // Constructor that takes in a random number for testing
  FloodItWorld(int size, int numCol, Random rand) {
    this.size = size;
    this.numCol = numCol;
    if (size < 1 || numCol < 2 || numCol > 10) {
      throw new IllegalArgumentException(
          "Make sure the size is greater than 0 and the number of colors is between 2 and 10");
    }
    this.worldSize = 500;
    this.index = 0;
    this.cellSize = worldSize / size;
    this.totalClicks = (size * numCol) / 3;
    this.time = 0;
    this.flooded = new ArrayList<Cell>();
    this.startMode = true;
    this.lightMode = true;
    this.score = 0;
    this.rand = rand;
    this.board = this.makeBoard();
    this.clicked = this.board.get(0);
  }

  // Generating a random color
  ArrayList<Color> randomColor(int numCol) {
    ArrayList<Color> colors = new ArrayList<Color>();
    for (int i = 0; i < numCol; i++) {
      int r = this.rand.nextInt(256);
      int g = this.rand.nextInt(256);
      int b = this.rand.nextInt(256);
      colors.add(new Color(r, g, b));
    }

    return colors;
  }

  // Generating the initial board with its adjacent cells
  // Effect: Assigning the neighboring cells to their respective cells
  ArrayList<Cell> makeBoard() {
    this.board = new ArrayList<Cell>();
    ArrayList<Color> colors = randomColor(this.numCol);

    // Creating the board
    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        Color color = colors.get(this.rand.nextInt(this.numCol));
        Cell cell = new Cell(col, row, color);
        this.board.add(cell);
      }
    }

    // Connecting the adjacent cells
    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        int index = row * this.size + col;
        Cell cell = this.board.get(index);

        if (col > 0) {
          cell.left = this.board.get(index - 1);
        }

        if (row > 0) {
          cell.top = this.board.get(index - this.size);
        }

        if (col < this.size - 1) {
          cell.right = this.board.get(index + 1);
        }

        if (row < this.size - 1) {
          cell.bottom = this.board.get(index + this.size);
        }
      }
    }

    Cell first = this.board.get(0);
    first.floodCell(this.flooded);
    this.flood(first);
    return this.board;
  }

  // Determines which Cell is clicked
  // Effect: Assigns the clicked feel with the cell that is clicked
  // Determines if the user clicked the end game button
  public void onMousePressed(Posn posn) {
    // Checking if the user clicked the start game button
    if (this.startMode) {
      if (posn.x >= this.worldSize / 2 - this.cellSize * 2.5 && posn.x <= this.worldSize / 2 
          + this.cellSize * 2.5 && posn.y >= this.worldSize / 2 - this.cellSize 
          && posn.y <= this.worldSize / 2 + this.cellSize) {
        this.startMode = false;
      }
    }

    // Checking which cell the user clicked
    if (!this.startMode) {
      for (Cell cell : this.board) {
        if (posn.x >= cell.x * this.cellSize && posn.x < (cell.x + 1) * this.cellSize 
            && posn.y >= cell.y * this.cellSize && posn.y < (cell.y + 1) * this.cellSize) {
          this.clicked = cell;
          break;
        }
      }

      // Checking if the user clicked the end game button
      if (posn.x >= this.worldSize - this.worldSize / 6 - this.worldSize / 12 
          && posn.x <= this.worldSize - this.worldSize / 6 + this.worldSize / 12
          && posn.y >= this.worldSize + this.worldSize / 8 - this.worldSize / 16 
          && posn.y <= this.worldSize + this.worldSize / 8 + this.worldSize / 16) {
        this.endOfWorld("");
      }

      // Checking if the user clicked the switch mode button
      if (posn.x >= this.worldSize / 2 - this.worldSize / 3 - this.worldSize / 10
          && posn.x <= this.worldSize / 2 - this.worldSize / 3 + this.worldSize / 10
          && posn.y >= this.worldSize + this.worldSize / 8 - this.worldSize / 16
          && posn.y <= this.worldSize + this.worldSize / 8 + this.worldSize / 16) {
        this.lightMode = !this.lightMode;
      }

      // Flooding the game
      if (!this.startMode && !this.clicked.sameColor(this.board.get(0).color)) {
        this.score++;

        if (this.clicked.equals(this.board.get(0))) {
          this.score = 0;
        }

        this.index = 0;
        this.flood(this.clicked);
      }
    }
  }

  // Determines if all of the cells are the same color
  public boolean allSameColor() {
    Color color = this.board.get(0).color;
    for (Cell cell : board) {
      if (!cell.sameColor(color)) {
        return false;
      }
    }
    return true;
  }

  // Effect: Handles ticking of the clock and updating the world if needed
  // Floods the game
  // Determines if the game is over and returns lastScene
  public void onTick() {

    if (!this.startMode) {
      this.time += 1;
    }

    Cell cell = this.flooded.get(this.index);
    cell.changeColor(this.clicked);

    if (cell.isFlooded(cell.right)) {
      cell.right.changeColor(this.clicked);
    }
    if (cell.isFlooded(cell.bottom)) {
      cell.bottom.changeColor(this.clicked);
    }

    if (this.index < this.flooded.size() - 1) {
      this.index++;
    }
  }

  // Effect: Floods the game board with the color of the given cell
  void flood(Cell clicked) {
    Color color = clicked.color;
    for (Cell cell : board) {
      if (cell.flooded) {
        if (cell.x < this.size - 1 && (cell.right.sameColor(color))) {
          cell.right.floodCell(this.flooded);
        }
        if (cell.y < this.size - 1 && (cell.bottom.sameColor(color))) {
          cell.bottom.floodCell(this.flooded);
        }
        if (cell.x > 0 && (cell.left.sameColor(color))) {
          cell.left.floodCell(this.flooded);
        }
        if (cell.y > 0 && (cell.top.sameColor(color))) {
          cell.top.floodCell(this.flooded);
        }
      }
    }
  }

  // Effect: Resets the game
  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      this.score = 0;
      this.time = 0;
      this.board = this.makeBoard();
      this.clicked = this.board.get(0);
    }
  }

  // Draws the game
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.worldSize, this.worldSize);

    // Extra Credit: Start game button
    if (this.startMode) {
      WorldImage startRect = new RectangleImage(this.worldSize / 2, this.worldSize / 5, 
          OutlineMode.SOLID, Color.green.darker());
      WorldImage startText = new TextImage("Click to start!", this.worldSize / 25, Color.white);
      WorldImage startShadow1 = new RectangleImage(this.worldSize / 2, 
          this.worldSize / 100, OutlineMode.SOLID, Color.green.darker().darker());
      WorldImage startShadow2 = new RectangleImage(this.worldSize / 100, 
          this.worldSize / 5, OutlineMode.SOLID, Color.green.darker().darker());

      scene.placeImageXY(startRect, this.worldSize / 2, this.worldSize / 2);
      scene.placeImageXY(startText, this.worldSize / 2, this.worldSize / 2);
      scene.placeImageXY(startShadow1, this.worldSize / 2, 
          this.worldSize / 2 + this.worldSize / 10);
      scene.placeImageXY(startShadow2, this.worldSize / 2 + this.worldSize / 4, 
          this.worldSize / 2);
      scene.placeImageXY(startShadow1, this.worldSize / 2, 
          this.worldSize / 2 - this.worldSize / 10);
      scene.placeImageXY(startShadow2, this.worldSize / 2 - this.worldSize / 4, 
          this.worldSize / 2);
      return scene;
    }

    // The cells on the board
    for (Cell cell : this.board) {
      int x = cell.x * this.cellSize;
      int y = cell.y * this.cellSize;

      // Lighten the cell color
      Color lighterColor = cell.color.brighter();
      WorldImage cellBackground;
      WorldImage cellImage;

      if (this.lightMode) {
        // Extra Credit Attempt: Border Effect
        // Create the cell background with a lighter color
        cellBackground = new RectangleImage(this.cellSize, this.cellSize,
            OutlineMode.SOLID, lighterColor);
        // Create the cell with the original color
        cellImage = new RectangleImage(this.cellSize - this.cellSize / 4, 
            this.cellSize - this.cellSize / 4,
            OutlineMode.SOLID, cell.color);
      }

      else {
        cellBackground = new RectangleImage(this.cellSize, this.cellSize,
            OutlineMode.SOLID, cell.color.darker());
        // Create the cell with the original color
        cellImage = new RectangleImage(this.cellSize - this.cellSize / 4, 
            this.cellSize - this.cellSize / 4,
            OutlineMode.SOLID, cell.color.darker().darker());
      }

      // Place the cell background and cell image to create a border effect
      scene.placeImageXY(cellBackground, x + this.cellSize / 2, y + this.cellSize / 2);
      scene.placeImageXY(cellImage, x + this.cellSize / 2, y + this.cellSize / 2);
    }

    // Extra credit: Switch from light mode to dark mode
    WorldImage lightMode = new RectangleImage(this.worldSize / 5, this.worldSize / 8, 
        OutlineMode.SOLID, Color.gray.brighter());
    WorldImage lightText = new TextImage("Dark mode", this.worldSize / 35, Color.black);
    WorldImage darkMode = new RectangleImage(this.worldSize / 5, this.worldSize / 8, 
        OutlineMode.SOLID, Color.black.brighter());
    WorldImage darkText = new TextImage("Light mode", this.worldSize / 35, Color.white);


    // Extra Credit: Score and Timer
    WorldImage score = new TextImage("Tries: " + this.score + " / "
        + this.totalClicks, this.worldSize / 25, Color.black);
    WorldImage time = new TextImage("Time: " + this.time / 4500 + ":"
        + this.addZero(this.time / 75) + " minutes", this.worldSize / 25, Color.black);

    // Extra Credit: End game button
    WorldImage endRect = new RectangleImage(this.worldSize / 6, this.worldSize / 8, 
        OutlineMode.SOLID, Color.red.darker());
    WorldImage endText = new TextImage("End Game", this.worldSize / 35, Color.white);

    WorldImage endShadow1 = new RectangleImage(this.worldSize / 6, 
        this.worldSize / 100, OutlineMode.SOLID, Color.red.darker().darker());
    WorldImage endShadow2 = new RectangleImage(this.worldSize / 100, 
        this.worldSize / 8, OutlineMode.SOLID, Color.red.darker().darker());

    scene.placeImageXY(score, this.worldSize / 2, this.worldSize + this.worldSize / 6);
    scene.placeImageXY(time, this.worldSize / 2, this.worldSize + this.worldSize / 11);
    scene.placeImageXY(endRect, this.worldSize - this.worldSize / 6, 
        this.worldSize + this.worldSize / 8);
    scene.placeImageXY(endText, this.worldSize - this.worldSize / 6, 
        this.worldSize + this.worldSize / 8);
    scene.placeImageXY(endShadow1,  this.worldSize - this.worldSize / 6, 
        this.worldSize + this.worldSize / 8 - this.worldSize / 16);
    scene.placeImageXY(endShadow1,  this.worldSize - this.worldSize / 6, 
        this.worldSize + this.worldSize / 8 + this.worldSize / 16);
    scene.placeImageXY(endShadow2, this.worldSize - this.worldSize / 6 + this.worldSize / 12, 
        this.worldSize + this.worldSize / 8);
    scene.placeImageXY(endShadow2, this.worldSize - this.worldSize / 6 - this.worldSize / 12, 
        this.worldSize + this.worldSize / 8);

    if (this.lightMode) {
      scene.placeImageXY(lightMode, this.worldSize / 2 - this.worldSize / 3, 
          this.worldSize + this.worldSize / 8);
      scene.placeImageXY(lightText, this.worldSize / 2 - this.worldSize / 3, 
          this.worldSize + this.worldSize / 8);
    }

    if (!this.lightMode) {
      scene.placeImageXY(darkMode, this.worldSize / 2 - this.worldSize / 3, 
          this.worldSize + this.worldSize / 8);
      scene.placeImageXY(darkText, this.worldSize / 2 - this.worldSize / 3, 
          this.worldSize + this.worldSize / 8);
    }

    // When the user loses
    if (this.score > this.totalClicks) {
      WorldImage youLose = new TextImage("You Lose!", this.worldSize / 35, Color.black);
      scene.placeImageXY(youLose, this.worldSize / 2, this.worldSize + this.worldSize / 20);
    }

    // When the user wins
    if (this.allSameColor()) {
      WorldImage youWin = new TextImage("You Win!", this.worldSize / 35, Color.black);
      scene.placeImageXY(youWin, this.worldSize / 2, this.worldSize + this.worldSize / 20);
    }

    return scene;
  }

  // Adds a 0 before the number if it is less than 10 (for the timer)
  public String addZero(int time) {
    if (time % 60 < 10) {
      return "0" + time % 60;
    }
    else {
      return "" + time % 60;
    }
  }
}


