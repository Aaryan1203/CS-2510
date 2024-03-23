import java.awt.Color;
import java.util.ArrayList;

//Represents an interface for a cell
interface ICell {

  // Determines if this color is the same as the given color
  public boolean sameColor(Color color);

  //Effect: Changes the color of this cell to the given color
  public void changeColor(Cell clicked);

  // Determines if the given ICell is flooded
  public boolean isFlooded(ICell cell);

  // Determines if this cell is flooded
  public boolean isFloodedHelp();

  // Effect: Floods the given cell by adding it to flooded and 
  // changes its flooded field to true
  public void floodCell(ArrayList<Cell> flooded);
}