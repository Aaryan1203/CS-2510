import java.awt.Color;
import java.util.ArrayList;

// Represents a single square of the game area
class Cell implements ICell {

  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  Color color;
  boolean flooded;


  // The four adjacent cells to this one
  ICell left;
  ICell top;
  ICell right;
  ICell bottom;

  // Constructor that initializes all of the given fields
  Cell(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.flooded = false;
    this.left = new MtCell();
    this.right = new MtCell();
    this.top = new MtCell();
    this.bottom = new MtCell();
  }

  // Determines if this color is the same as the given color
  public boolean sameColor(Color color) {
    return this.color.equals(color);
  }

  // Effect: Changes the color of this cell to the given color
  public void changeColor(Cell clicked) {
    this.color = clicked.color;
  }

  // Determines if the given ICell is flooded
  public boolean isFlooded(ICell cell) {
    return cell.isFloodedHelp();
  }

  // Determines if this cell is flooded
  public boolean isFloodedHelp() {
    return this.flooded;
  }

  // Effect: Floods the given cell by adding it to flooded and 
  // changing its flooded field to true
  public void floodCell(ArrayList<Cell> flooded) {
    flooded.add(this);
    this.flooded = true;
  }
}