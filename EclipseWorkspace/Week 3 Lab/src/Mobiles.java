import java.awt.Color;
import tester.Tester;

//An interface that represents mobile phones
interface IMobile {
  
  //computes the total weight of the mobile
  int totalWeight();
  
  //computes the height of the mobile
  int totalHeight();
  
}

//a simple mobile phone
class Simple implements IMobile {
  int length;
  int weight;
  Color color;
  
  Simple(int length, int weight, Color color) {
    this.length = length;
    this.weight = weight;
    this.color = color;
  }
  
  /*
   * Fields:
   *  this.length ... int
   *  this.weight ... int
   *  this.color ... Color
   * Methods:
   *  this.totalWright() ... int
   *  this.totalHeight() ... int
   * Methods of fields:
   * 
   */
  
  //computes total weight of the simple mobile
  public int totalWeight() {
    return this.weight;
  }

  //computes the total height of the simple milbile
  public int totalHeight() {
    return this.length + this.weight / 10;
  } 
  
}

//a complex mobile phone
class Complex implements IMobile {
  int length;
  int leftside;
  int rightside;
  IMobile left;
  IMobile right;
  
  Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
    this.length = length;
    this.leftside = leftside;
    this.rightside = rightside;
    this.left = left;
    this.right = right;
  }
  
  /*
   * Fields:
   *  this.length ... int
   *  this.leftside ... int
   *  this.rightside ... int
   *  this.left ... IMobile
   *  this.right ... IMobile
   * Methods:
   *  this.totalWeight() ... int
   *  this.totalHeight() ... int
   * Methods of fields:
   *  this.right.totalWeight() ... int
   *  this.left.totalWeight() ... int
   *  this.right.totalHeight() ... int
   *  this.left.totalHeight() ... int
   */
  
  //computes total weight of the complex mobile
  public int totalWeight() {
    return this.left.totalWeight() + this.right.totalWeight();
  }

  //computes the total height of the complex mobile
  public int totalHeight() {
    return this.length + this.left.totalHeight() + this.right.totalHeight();
  }
  
}

//examples of IMobiles
class ExamplesMobiles {
  
  //simple examples for example complex
  IMobile thirtySixBlue = new Simple(1, 36, Color.blue);
  IMobile twelveRed = new Simple(1, 12, Color.red);
  IMobile thirySixRed = new Simple(2, 36, Color.red);
  IMobile sixtyGreen = new Simple(1, 60, Color.green);
  
  //complex examples for example complex
  IMobile complexOne = new Complex(2, 5, 3, this.thirySixRed, this.sixtyGreen);
  IMobile complexTwo = new Complex(2, 8, 1, this.twelveRed, this.complexOne);
  
  //complex examples for complex3
  IMobile complexThree = new Complex(3, 2, 3, this.complexOne, this.complexTwo);
  IMobile complexFour = new Complex(4, 5, 6, this.complexThree, this.complexTwo);
  
  //given examples
  IMobile exampleSimple = new Simple(2, 20, Color.blue);
  IMobile exampleComplex = new Complex(1, 9, 3, this.thirtySixBlue, this.complexTwo);
  IMobile example3 = new Complex(2, 5, 10, this.complexFour, this.complexThree);
  
  boolean testMobiles(Tester t) {
    return t.checkExpect(this.exampleComplex.totalWeight(), 144)
        && t.checkExpect(this.example3.totalWeight(), 516)
        && t.checkExpect(this.twelveRed.totalWeight(), 12)
        && t.checkExpect(this.exampleComplex.totalHeight(), 12)
        && t.checkExpect(this.exampleSimple.totalHeight(), 4);
  }
}


