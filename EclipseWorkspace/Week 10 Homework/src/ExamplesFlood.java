import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

//Examples and tests for FloodIt
class ExamplesFlood {
  int worldSize = 500;
  int size = 10;
  int numCol = 10;
  FloodItWorld world = new FloodItWorld(this.size, this.numCol);
  FloodItWorld worldRandom = new FloodItWorld(2, 2, new Random(5));
  FloodItWorld worldRandom2 = new FloodItWorld(3, 3, new Random(10));
  FloodItWorld game = new FloodItWorld(10, 5, new Random(1));

  // Tests the bigBang
  void testBigBang(Tester t) {
    this.world.bigBang(this.worldSize, this.worldSize + worldSize / 4, 0.01);
  }

  // Tests the sameColor method
  void testSameColor(Tester t) {
    Color c1 = new Color(1, 2, 3);
    Color c2 = new Color(10, 20, 30);
    Color c3 = new Color(1, 2, 3);
    Cell cell1 = new Cell(1, 3, c1);
    Cell cell2 = new Cell(10, 3, c2);
    Cell cell3 = new Cell(1, 30, c3);
    t.checkExpect(cell1.sameColor(c3), true);
    t.checkExpect(cell2.sameColor(c3), false);
    t.checkExpect(cell3.sameColor(c1), true);
    t.checkExpect(cell1.sameColor(c2), false);
    t.checkExpect(cell2.sameColor(c2), true);
  }

  // Tests the changeColor method
  void testChangeColor(Tester t) {
    Cell cell1 = new Cell(1, 2, Color.red);
    Cell cell2 = new Cell(10, 2, Color.green);
    Cell cell3 = new Cell(1, 2, Color.blue);
    Cell cell4 = new Cell(10, 2, Color.white);
    t.checkExpect(cell1.color, Color.red);
    t.checkExpect(cell2.color, Color.green);

    cell1.changeColor(cell3);
    cell2.changeColor(cell4);

    t.checkExpect(cell1.color, Color.blue);
    t.checkExpect(cell2.color, Color.white);
  }

  // Tests the isFLooded method
  void testIsFlooded(Tester t) {
    Cell cell1 = new Cell(1, 2, Color.red);
    Cell cell2 = new Cell(1, 2, Color.gray);
    t.checkExpect(cell1.isFlooded(cell2), false);

    cell2.flooded = true;
    t.checkExpect(cell1.isFlooded(cell2), true);

    cell1.left = cell2;
    t.checkExpect(cell1.isFlooded(cell1.left), true);
  }

  // Tests the isFloodedHelp method
  void testIfFloodedHelp(Tester t) {
    Cell cell1 = new Cell(1, 2, Color.red);
    Cell cell2 = new Cell(10, 2, Color.green);
    ArrayList<Cell> list = new ArrayList<Cell>(Arrays.asList(cell1));
    MtCell mt = new MtCell();
    t.checkExpect(cell1.isFloodedHelp(), false);
    t.checkExpect(cell2.isFloodedHelp(), false);

    cell1.floodCell(list);
    t.checkExpect(cell1.isFloodedHelp(), true);
    t.checkExpect(mt.isFloodedHelp(), false);
  }

  // Tests the floodCell method
  void testFloodCell(Tester t) {
    Cell cell1 = new Cell(1, 2, Color.red);
    Cell cell2 = new Cell(10, 2, Color.green);
    ArrayList<Cell> list = new ArrayList<Cell>(Arrays.asList(cell1));
    t.checkExpect(cell1.flooded, false);
    t.checkExpect(list.size(), 1);
    t.checkExpect(cell2.flooded, false);
    cell2.floodCell(list);
    t.checkExpect(list.size(), 2);
    t.checkExpect(cell1.flooded, false);
    t.checkExpect(cell2.flooded, true);
  }

  // Tests the Exceptions for the Cell methods
  void testExceptions(Tester t) {
    Cell cell1 = new Cell(1, 2, Color.red);
    MtCell mt = new MtCell();
    ArrayList<Cell> list = new ArrayList<Cell>(Arrays.asList(cell1));
    t.checkException(new RuntimeException(
        "Cannot determines if an empty cell is a specific color"), 
        mt, "sameColor", Color.red);
    t.checkException(new RuntimeException("Cannot check if an empty cell is flooded"), 
        mt, "isFlooded", mt);
    t.checkException(new RuntimeException("Cannot flood an emtpy cell"), 
        mt, "floodCell", list);
  }

  // Tests the Constructor Exceptions
  void testConstructorException(Tester t) {
    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", 0, -1);

    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", 0, 1);

    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", -1, 0);

    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", 1, 21);
    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", 1, 21, new Random(5));
    t.checkConstructorException(new IllegalArgumentException(
        "Make sure the size is greater than 0 and the number of colors is between 2 and 10"),
        "FloodItWorld", 1, 0, new Random(3));
  }

  // Tests the randomColors method
  void testRandomColors(Tester t) {
    t.checkExpect(this.worldRandom.randomColor(3), new ArrayList<Color>(Arrays.asList(
        new Color(178, 192, 71), new Color(88, 194, 214), new Color(56, 118, 235))));

    t.checkExpect(this.worldRandom.randomColor(4), new ArrayList<Color>(Arrays.asList(
        new Color(187, 226, 81), new Color(56, 83, 226), new Color(6, 112, 81), 
        new Color(100, 168, 254))));

    t.checkExpect(this.worldRandom.randomColor(1), new ArrayList<Color>(Arrays.asList(
        new Color(170, 225, 51))));

    t.checkExpect(this.worldRandom.randomColor(2), new ArrayList<Color>(Arrays.asList(
        new Color(72, 170, 63), new Color(15, 151, 125))));
  }

  // Tests the makeBoard Method
  void testMakeBoard(Tester t) {
    FloodItWorld world = new FloodItWorld(3, 2);
    FloodItWorld worldRandom = new FloodItWorld(3, 2, new Random(5));
    ArrayList<Cell> board = world.board;
    Cell topLeft = board.get(0);
    Cell topRight = board.get(2);
    Cell bottomLeft = board.get(6);
    Cell bottomRight = board.get(8);
    t.checkExpect(world.makeBoard().size(), 9);
    t.checkExpect(worldRandom.makeBoard(), new FloodItWorld(3, 2, new Random(5)).makeBoard());
    t.checkExpect(worldRandom.makeBoard().get(0).color, new Color(254, 170, 225));

    // Check the adjacent cells
    t.checkExpect(topLeft.right, board.get(1));
    t.checkExpect(topLeft.top, new MtCell());
    t.checkExpect(topLeft.bottom, board.get(3));
    t.checkExpect(topLeft.left, new MtCell());
    t.checkExpect(topRight.left, board.get(1));
    t.checkExpect(topRight.right, new MtCell());
    t.checkExpect(topRight.top, new MtCell());
    t.checkExpect(topRight.bottom, board.get(5));
    t.checkExpect(bottomLeft.bottom, new MtCell());
    t.checkExpect(bottomLeft.top, board.get(3));
    t.checkExpect(bottomLeft.right, board.get(7));
    t.checkExpect(bottomLeft.left, new MtCell());
    t.checkExpect(bottomRight.left,  board.get(7));
    t.checkExpect(bottomRight.right, new MtCell());
    t.checkExpect(bottomRight.bottom, new MtCell());
    t.checkExpect(bottomRight.top, board.get(5));
    t.checkExpect(topLeft.right, topRight.left);

    // Checking if the initial cells are flooded
    FloodItWorld worldRandom1 = new FloodItWorld(10, 5, new Random(11));
    t.checkExpect(worldRandom1.board.get(0).flooded, true);
    t.checkExpect(worldRandom1.board.get(1).flooded, false);
    t.checkExpect(worldRandom1.board.get(10).flooded, true);
    t.checkExpect(worldRandom1.board.get(11).flooded, false);
    t.checkExpect(worldRandom1.board.get(63).flooded, false);
  }

  // Tests the addZero method
  void testSeconds(Tester t) {
    t.checkExpect(this.world.addZero(5), "05");
    t.checkExpect(this.world.addZero(6), "06");
    t.checkExpect(this.world.addZero(10), "10");
    t.checkExpect(this.world.addZero(35), "35");
  }

  // Test the makeScene method
  void testMakeScene(Tester t) {
    FloodItWorld testWorld = new FloodItWorld(10, 10);
    WorldScene startScene = new WorldScene(500, 500);
    WorldImage startRect = new RectangleImage(250, 100, OutlineMode.SOLID, Color.green.darker());
    WorldImage startText = new TextImage("Click to start!", 20, Color.white);
    WorldImage shadow1 = new RectangleImage(50 * 5, 50 / 10, OutlineMode.SOLID, 
        Color.green.darker().darker());
    WorldImage shadow2 = new RectangleImage(50 / 10, 50 * 2, OutlineMode.SOLID, 
        Color.green.darker().darker());
    startScene.placeImageXY(startRect, 250, 250);
    startScene.placeImageXY(startText, 250, 250);
    startScene.placeImageXY(shadow1, 250, 300);
    startScene.placeImageXY(shadow2, 375, 250);
    startScene.placeImageXY(shadow1, 250, 200);
    startScene.placeImageXY(shadow2, 125, 250);

    t.checkExpect(testWorld.makeScene(), startScene);

    WorldScene expectedScene = new WorldScene(500, 500);
    WorldImage score = new TextImage("Tries: 0 / 33", 20, Color.black);
    WorldImage time = new TextImage("Time: 0:00 minutes", 20, Color.black);
    testWorld.onMousePressed(new Posn(250, 250));
    testWorld.onTick();

    for (Cell cell : testWorld.board) {
      int x = cell.x * 50;
      int y = cell.y * 50;
      Color lighterColor = cell.color.brighter();
      WorldImage cellBackground = new RectangleImage(50, 50,
          OutlineMode.SOLID, lighterColor);
      WorldImage cellImage = new RectangleImage(38, 
          38, OutlineMode.SOLID, cell.color);
      expectedScene.placeImageXY(cellBackground, x + 25, y + 25);
      expectedScene.placeImageXY(cellImage, x + 25, y + 25);
      expectedScene.placeImageXY(score, 250, 583);
      expectedScene.placeImageXY(time, 250, 545);
    }

    // Compare actual scene to the expected scene
    t.checkExpect(testWorld.makeScene(), expectedScene);
  }

  //Test the OnTick method
  void testOnTick(Tester t) {
    FloodItWorld testWorld = new FloodItWorld(10, 10);

    // Store the initial states
    int initialWorldTime = testWorld.time;
    int initialGameTime = game.time;

    // Call the onTick method for each world instance
    testWorld.onTick();
    game.onTick();
    t.checkExpect(testWorld.startMode, true);
    t.checkExpect(game.startMode, true);


    // Starting the game
    testWorld.onMousePressed(new Posn(250, 250));
    game.onMousePressed(new Posn(250, 250));
    t.checkExpect(testWorld.startMode, false);
    t.checkExpect(game.startMode, false);

    // Calling onTick again
    testWorld.onTick();
    game.onTick();

    // Check that the time has increased by 1 for each world instance
    t.checkExpect(testWorld.time, initialWorldTime + 1);
    t.checkExpect(game.time, initialGameTime + 1);
  }

  // Test the onMousePressed method
  void testOnMousePressed(Tester t) {
    FloodItWorld testWorld = new FloodItWorld(10, 5, new Random(1));

    // Testing to see if it changed from StartMode to gameMode
    t.checkExpect(testWorld.startMode, true);

    testWorld.onMousePressed(new Posn(250, 250));
    t.checkExpect(testWorld.startMode, false);

    // Testing to see which cell was clicked
    testWorld.onMousePressed(new Posn(0, 0));
    t.checkExpect(testWorld.clicked, testWorld.board.get(0));

    testWorld.onMousePressed(new Posn(testWorld.cellSize, 0));
    t.checkExpect(testWorld.clicked, testWorld.board.get(1));

    testWorld.onMousePressed(new Posn(0, testWorld.cellSize));
    t.checkExpect(testWorld.clicked, testWorld.board.get(testWorld.size));

    testWorld.onMousePressed(new Posn(testWorld.cellSize, testWorld.cellSize));
    t.checkExpect(testWorld.clicked, testWorld.board.get(testWorld.size + 1));
  }

  //Test the flood method
  void testFlood(Tester t) {
    FloodItWorld testWorld = new FloodItWorld(2, 2, new Random(5));
    testWorld.board.get(0).color = Color.RED;
    testWorld.board.get(1).color = Color.BLUE;
    testWorld.board.get(2).color = Color.BLUE;
    testWorld.board.get(3).color = Color.GREEN;

    testWorld.flood(testWorld.board.get(1));
    t.checkExpect(testWorld.flooded.contains(testWorld.board.get(1)), true);
    t.checkExpect(testWorld.board.get(1).flooded, true);
    t.checkExpect(testWorld.flooded.contains(testWorld.board.get(2)), true);
    t.checkExpect(testWorld.board.get(2).flooded, true);

    FloodItWorld testWorld2 = new FloodItWorld(3, 3, new Random(10));
    testWorld2.board.get(0).color = Color.RED;
    testWorld2.board.get(1).color = Color.BLUE;
    testWorld2.board.get(2).color = Color.GREEN;
    testWorld2.board.get(3).color = Color.BLUE;
    testWorld2.board.get(4).color = Color.YELLOW;
    testWorld2.board.get(5).color = Color.GREEN;
    testWorld2.board.get(6).color = Color.GREEN;
    testWorld2.board.get(7).color = Color.YELLOW;
    testWorld2.board.get(8).color = Color.GREEN;

    testWorld2.flood(testWorld2.board.get(1));
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(1)), true);
    t.checkExpect(testWorld2.board.get(1).flooded, true);
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(3)), true);
    t.checkExpect(testWorld2.board.get(3).flooded, true);
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(4)), false);
    t.checkExpect(testWorld2.board.get(4).flooded, false);
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(5)), false);
    t.checkExpect(testWorld2.board.get(5).flooded, false);

    testWorld2.flood(testWorld2.board.get(4));
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(4)), true);
    t.checkExpect(testWorld2.board.get(4).flooded, true);
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(7)), true);
    t.checkExpect(testWorld2.board.get(7).flooded, true);
    t.checkExpect(testWorld2.flooded.contains(testWorld2.board.get(5)), false);
    t.checkExpect(testWorld2.board.get(5).flooded, false);
  }


  // Test the onKeyEvent method
  void testOnKeyEvent(Tester t) {
    game.score = 5;
    game.time = 30;
    game.onKeyEvent("r");

    t.checkExpect(game.score, 0);
    t.checkExpect(game.time, 0);

    game.onTick();
    game.onKeyEvent("x");
    t.checkExpect(game.score, 0);
  }

  // Test the allSameColor method
  void testAllSameColor(Tester t) {
    FloodItWorld testWorld1 = new FloodItWorld(2, 2, new Random(42));
    FloodItWorld testWorld2 = new FloodItWorld(3, 3, new Random(42));

    // Set all cells to the same color for testing
    for (Cell cell : testWorld1.board) {
      cell.color = Color.RED;
    }
    for (Cell cell : testWorld2.board) {
      cell.color = Color.BLUE;
    }

    t.checkExpect(testWorld1.allSameColor(), true);
    t.checkExpect(testWorld2.allSameColor(), true);
    t.checkExpect(worldRandom.allSameColor(), false);
    t.checkExpect(worldRandom2.allSameColor(), false);
  }
}