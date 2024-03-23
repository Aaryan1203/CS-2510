import java.awt.Color;
import java.util.ArrayList;

// Represents an empty cell
class MtCell implements ICell {

  // Determines if this color is the same as the given color
  public boolean sameColor(Color color) {
    throw new RuntimeException("Cannot determines if an empty cell is a specific color");
  }

  //Changes the color of this cell to the given color
  public void changeColor(Cell clicked) {
    return ;
  }

  // Determines if the given ICell is flooded
  public boolean isFlooded(ICell cell) {
    throw new RuntimeException("Cannot check if an empty cell is flooded");
  }

  // Determines if this cell is flooded
  public boolean isFloodedHelp() {
    return false;
  }

  // Floods the given cell by adding it to flooded and 
  // changing its flooded field to true
  public void floodCell(ArrayList<Cell> flooded) {
    throw new RuntimeException("Cannot flood an emtpy cell");
  }
}