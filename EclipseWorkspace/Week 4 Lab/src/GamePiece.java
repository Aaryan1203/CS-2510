import tester.*;

//represents an IGamePiece
interface IGamePiece {

  //gets the value of a tile
  int getValue();

  //merges two game pieces together
  IGamePiece merge(IGamePiece other);

  //checks if the game piece is valid
  boolean isValid();
}

//represents a base tile
class BaseTile implements IGamePiece {
  int value;

  BaseTile(int value) { 
    this.value = value; 
  }

  /*
   * Fields : 
   *  this.value ... int
   *  
   * Methods:
   *  this.getValue() ... int
   *  this.merge(IGamePiece) ... IGamePiece
   *  this.isValid() ... boolean
   * methods of fields:
   */

  //gets the value of this tile
  public int getValue() { 
    return this.value;
  }

  //merges two game pieces together
  public IGamePiece merge(IGamePiece other) {

    /*
     * Fields : 
     *  
     * Methods:
     *  other.getValue() ... int
     *  other.merge(IGamePiece) ... IGamePiece
     *  other.isValid() ... boolean
     * methods of fields:
     * 
     */

    return this;
  }

  //determines if this base tile is valid
  public boolean isValid() {
    return true;
  }
}

//represents a merged tile
class MergeTile implements IGamePiece {
  IGamePiece piece1;
  IGamePiece piece2;

  MergeTile(IGamePiece piece1, IGamePiece piece2) {
    this.piece1 = piece1;
    this.piece2 = piece2;

    /*
     * Fields : 
     *  this.piece1 ... IGamePiece
     *  this.piece2 ... IGamePiece
     * Methods:
     *  this.getValue() ... int
     *  this.merge(IGamePiece) ... IGamePiece
     *  this.isValid() ... boolean
     * methods of fields:
     *  piece1.getValue() ... int
     *  piece1.merge(IGamePiece) ... IGamePiece
     *  piece2.getValue() ... int
     *  piece2.merge(IGamePiece) ... IGamePiece
     *  piece1.isValid() ... boolean
     *  piece2.isValid() ... boolean
     */

  }

  //gets the value of a merged tile
  public int getValue() {
    return this.piece1.getValue() + this.piece2.getValue();
  }

  //merges two game pieces together
  public IGamePiece merge(IGamePiece other) {

    /*
     * Fields : 
     *  
     * Methods:
     *  other.getValue() ... int
     *  other.merge(IGamePiece) ... IGamePiece
     *  other.isValid() ... boolean
     * methods of fields:
     * 
     */

    return new MergeTile(this, other);
  }

  //determines if this merged tile is valid
  public boolean isValid() {
    return this.piece1.getValue() == this.piece2.getValue();
  }
}

class ExamplesGamePiece {

  IGamePiece four = new MergeTile(new BaseTile(2), new BaseTile(2));
  IGamePiece eight = new MergeTile(this.four, this.four);
  IGamePiece twelve = new MergeTile(this.eight, this.four);

  //tests the getValue method
  boolean testGetValue(Tester t) {
    return t.checkExpect(four.getValue(), 4);
  }

  //tests the merge method
  boolean testMerge(Tester t) {
    return t.checkExpect(this.four.merge(this.four), this.eight)
        && t.checkExpect(this.eight.merge(this.four), this.twelve);
  }

  //tests the isValid method
  boolean testValid(Tester t) {
    return t.checkExpect(this.four.isValid(), true)
        && t.checkExpect(this.eight.isValid(), true)
        && t.checkExpect(this.twelve.isValid(), false);
  }
}