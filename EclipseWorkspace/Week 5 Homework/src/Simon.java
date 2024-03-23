import java.awt.Color;
import tester.Tester;
import javalib.funworld.*;
import javalib.worldimages.*;
import java.util.Random;

// Represents a game of SimonSays
class SimonWorld extends World {

  ILoButton answerKey;
  ILoButton clicked;
  boolean inputMode;
  boolean computerMode;
  int index;
  Random random = new Random();

  //Buttons
  Button red = new Button(Color.red.darker().darker(), 300, 200);
  Button blue = new Button(Color.blue.darker().darker(), 200, 200);
  Button green = new Button(Color.green.darker().darker(), 300, 300);
  Button yellow = new Button(Color.yellow.darker().darker(), 200, 300);

  //List of four buttons
  ILoButton buttons = new ConsLoButton(red, 
      new ConsLoButton(blue, 
          new ConsLoButton(green, 
              new ConsLoButton(yellow, new MtLoButton())))); 

  //empty constructor
  public SimonWorld() {
    this.answerKey = new ConsLoButton(this.randomButtons(), new MtLoButton());
    this.clicked = new MtLoButton();
    this.inputMode = false;
    this.computerMode = true;
    this.index = 0;

  }

  //constructor which takes in fields
  public SimonWorld(ILoButton answerKey, ILoButton clicked, boolean inputMode, 
      boolean computerMode, int index) {

    this.answerKey = answerKey;
    this.clicked = clicked;
    this.inputMode = inputMode;
    this.computerMode = computerMode;
    this.index = index;
  }

  //constructor for testing
  public SimonWorld(ILoButton answerKey, ILoButton clicked, boolean inputMode, 
      boolean computerMode, int index, Random rand) {
    this(answerKey, clicked, inputMode, computerMode, index);
    this.random = rand;
  }

  /*
   * Fields:
   *  this.answerKey ... ILoButton
   *  this.clicked ... ILoButton
   *  this.inputMode ... Boolean
   *  this.computerMode ... boolean
   *  this.index ... int
   * Methods:
   *  this.checkLocation() ... String
   *  this.randomButtons() ... Button
   *  this.listButtons() ... ILoButton
   *  this.makeScene() ... WorldScene
   *  this.onTick() ... Word
   *  this.onMouseClicked() ... SimonWorld
   * Methods of Fields:
   *  this.clicked.drawList(WorldScene, boolean) ... WorldScene
   *  this.clicked.add(Button) ... ILoButton
   *  this.clicked.drawIndex(WorldScene, int, boolean) ... WorldScene
   *  this.clicked.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
   *  this.clicked.length() ... boolean
   *  this.clicked.sameList(ILoButton) ... boolean
   *  this.clicked.sameListHelp(ILoButton) ... boolean
   *  this.clicked.sameFirstButton(Button) ... boolean
   *  this.answerKey.drawList(WorldScene, boolean) ... WorldScene
   *  this.answerKey.add(Button) ... ILoButton
   *  this.answerKey.drawIndex(WorldScene, int, boolean) ... WorldScene
   *  this.answerKey.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
   *  this.answerKey.length() ... boolean
   *  this.answerKey.sameList(ILoButton) ... boolean
   *  this.answerKey.sameListHelp(ILoButton) ... boolean
   *  this.answerKey.sameFirstButton(Button) ... boolean
   */

  //Generating random numbers and assigning them to colors
  public Button randomButtons() {

    int randomInt = this.random.nextInt(4);

    if (randomInt == 0) {
      return new Button(Color.red, 300, 200);
    }

    if (randomInt == 1) {
      return new Button(Color.blue, 200, 200);
    }

    if (randomInt == 2) {
      return new Button(Color.green, 300, 300);
    }

    else {
      return new Button(Color.yellow, 200, 300);
    }

  }


  // Draw the current state of the world
  public WorldScene makeScene() {
    //represents the background of the game
    WorldScene background = new WorldScene(500, 500); 
    WorldScene buttons = this.buttons.drawList(background, false);

    if (this.computerMode) {      
      return this.answerKey.drawIndex(buttons, index, true);

    }

    else {
      return buttons;
    }

  }

  //handles ticking of the clock and updating the world if needed
  public World onTick() {
    if (inputMode) {
      if (clicked.empty()) {
        return new SimonWorld(answerKey, clicked, false, true, index);
      }

      else {
        return new SimonWorld(answerKey, clicked, true, false, index);

      }
    }

    else {
      if (answerKey.empty()) {
        return new SimonWorld(answerKey, clicked, true, false, 0);
      }

      else {
        Button cur = this.randomButtons();
        this.answerKey.add(cur);
        return new SimonWorld(answerKey, clicked, false, true, index + 1);
      }
    }
  }

  //Returns the final scene with the given message displayed
  public WorldScene lastScene(String msg) {
    WorldScene background = new WorldScene(500, 500);
    WorldImage end = new TextImage(msg, 30, Color.black);
    return background.placeImageXY(end, 250, 250);
  }


  //handles mouse clicks and is given the mouse location
  public World onMouseClicked(Posn pos) {

    /*
     * Fields: none
     * Methods:
     *  pos.x ... int
     *  pos.y ... int
     */

    if (this.checkLocation(pos).equals("Blue")) {
      this.clicked = this.clicked.add(new Button(Color.blue, 200, 200));
      if (this.clicked.sameList(answerKey)) {
        return new SimonWorld(this.answerKey, this.clicked, false, true, index); 
      }
      else {

        return this.endOfWorld("Game over in " + this.answerKey.length() + " try");
      }
    }

    if (this.checkLocation(pos).equals("Red")) {
      this.clicked = this.clicked.add(new Button(Color.red, 300, 200));
      if (this.clicked.sameList(answerKey)) {
        return new SimonWorld(this.answerKey, this.clicked, false, true, index); 
      }
      else {

        return this.endOfWorld("Game over in " + this.answerKey.length() + " try");
      }
    }

    if (this.checkLocation(pos).equals("Yellow")) {
      this.clicked = this.clicked.add(new Button(Color.yellow, 200, 300));
      if (this.clicked.sameList(answerKey)) {
        return new SimonWorld(this.answerKey, this.clicked, false, true, index); 
      }
      else {

        return this.endOfWorld("Game over in " + this.answerKey.length() + " try");
      }
    }

    if (this.checkLocation(pos).equals("Green")) {
      this.clicked = this.clicked.add(new Button(Color.green, 300, 300));
      if (this.clicked.sameList(answerKey)) {
        return new SimonWorld(this.answerKey, this.clicked, false, true, index);
      }
      else {
        return this.endOfWorld("Game over in " + this.answerKey.length() + " try");
      }
    }

    else {
      return this;
    }
  }


  //determines which button the mouse clicked
  String checkLocation(Posn pos) {

    /*
     * Fields: none
     * Methods:
     *  pos.x ... int
     *  pos.y ... int
     */

    if (pos.x < 250 && pos.x > 150 && pos.y < 250 && pos.y > 150) {
      return "Blue";
    }
    else if (pos.x < 350 && pos.x > 250 && pos.y < 250 && pos.y > 150) {
      return "Red";
    }
    else if (pos.x < 250 && pos.x > 150 && pos.y < 350 && pos.y > 250) {
      return "Yellow";
    }
    else if (pos.x < 350 && pos.x > 250 && pos.y < 350 && pos.y > 250) {
      return "Green";
    }
    else {
      return this.checkLocation(pos);
    }
  }
} 

// Represents a list of buttons
interface ILoButton {

  //draws a list of buttons on the given background
  WorldScene drawList(WorldScene background, boolean lit);

  //adds the given button to the list of buttons
  ILoButton add(Button next);

  //draws the first button in the list of buttons
  WorldScene drawIndex(WorldScene background, int index, boolean lit);

  //determines if we are at the correct index
  //Acc: keeps track of the current index
  WorldScene drawIndexAcc(int current, int index, 
      WorldScene background, boolean lit);

  //determines the length of a list of buttons
  int length();

  //determines if this list of buttons is the same as the given list of buttons
  boolean sameList(ILoButton that);

  //helps determines if this list of buttons is the same as the given list of buttons
  boolean sameListHelp(ILoButton that);

  //determines if the first of this button is the same as the given button
  boolean sameFirstButton(Button b);

  //determines if the list is empty
  boolean empty();
} 

// Represents an empty list of buttons
class MtLoButton implements ILoButton {

  /*
   * Fields:
   *  this.first ... Button
   *  this.rest ... ILoButton
   * Methods:
   *  this.drawList(WorldScene, boolean) ... WorldScene
   *  this.add(Button) ... ILoButton
   *  this.drawIndex(WorldScene, int, boolean) ... WorldScene
   *  this.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
   *  this.length() ... boolean
   *  this.sameList(ILoButton) ... boolean
   *  this.sameListHelp(ILoButton) ... boolean
   *  this.sameFirstButton(Button) ... boolean
   * Methods of Fields:
   *  None
   */

  //draws all of the buttons in the empty list on the background
  public WorldScene drawList(WorldScene background, boolean lit) {
    return background;
  }

  //adds the given button to the empty list of buttons
  public ILoButton add(Button next) {

    /*
     * Fields: none
     * Methods:
     *  next.drawButton(Color) ... WorldImage
     *  next.drawDark() ... WorldImage
     *  next.draw(WorldScene, boolean) ... WorldScene
     *  next.sameButton(Button) ... boolean
     */

    return new ConsLoButton(next, this);
  }

  //draws the first button of the empty list on the background
  public WorldScene drawIndex(WorldScene background, int index, boolean lit) {
    return background;
  }

  //determines if we are at the correct index
  public WorldScene drawIndexAcc(int current, int index,
      WorldScene background, boolean lit) {

    return background;
  }

  //determines the length of an empty list of buttons
  public int length() {
    return 0;
  }

  //determines if this list of buttons is the same as the given list of buttons
  public boolean sameList(ILoButton that) {

    /*
     * Fields: none
     * Methods: 
     *  that.drawList(WorldScene, boolean) ... WorldScene
     *  that.add(Button) ... ILoButton
     *  that.sameButtons(Button) ... boolean
     *  that.drawIndex(WorldScene, int, boolean) ... WorldScene
     *  that.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
     *  that.length() ... int
     *  that.sameList(ILoButton) ... boolean
     *  that.sameListHelp(ILoButton) ... boolean
     *  that.sameFirstButton(Button) ... boolean
     */

    return that.length() == 0;
  }

  //helps determines if this list of buttons is the same as the given list of buttons
  public boolean sameListHelp(ILoButton that) {

    /*
     * Fields: none
     * Methods: 
     *  that.drawList(WorldScene, boolean) ... WorldScene
     *  that.add(Button) ... ILoButton
     *  that.sameButtons(Button) ... boolean
     *  that.drawIndex(WorldScene, int, boolean) ... WorldScene
     *  that.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
     *  that.length() ... int
     *  that.sameList(ILoButton) ... boolean
     *  that.sameListHelp(ILoButton) ... boolean
     *  that.sameFirstButton(Button) ... boolean
     */

    return false;
  }

  //checks if the first button in this list is the same as the given button
  public boolean sameFirstButton(Button b) {

    /*
     * Fields: none
     * Methods:
     *  b.drawButton(Color) ... WorldImage
     *  b.drawDark() ... WorldImage
     *  b.draw(WorldScene, boolean) ... WorldScene
     *  b.sameButton(Button) ... boolean
     */

    return false;
  }


  //determines if the list is empty
  public boolean empty() {
    return true;
  }

} 

// Represents a non-empty list of buttons
class ConsLoButton implements ILoButton {
  Button first;
  ILoButton rest;

  ConsLoButton(Button first, ILoButton rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields:
   *  this.first ... Button
   *  this.rest ... ILoButton
   * Methods:
   *  this.drawList(WorldScene, boolean) ... WorldScene
   *  this.add(Button) ... ILoButton
   *  this.sameButtons(Button) ... boolean
   *  this.drawIndex(WorldScene, int, boolean) ... WorldScene
   *  this.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
   *  this.length() ... int
   *  this.sameList(ILoButton) ... boolean
   *  this.sameListHelp(ILoButton) ... boolean
   *  this.sameFirstButton(Button) ... boolean
   * Methods of Fields:
   *  this.first.drawButton(Color) ... WorldImage
   *  this.first.drawDark() ... WorldImage
   *  this.first.draw(WorldScene, boolean) ... WorldScene
   *  this.first.sameButton(Button) ... boolean
   *  this.rest.drawList(WorldScene, boolean) ... WorldScene
   *  this.rest.add(Button) ... ILoButton
   *  this.rest.sameButtons(Button) ... boolean
   *  this.rest.drawIndex(WorldScene, int, boolean) ... WorldScene
   *  this.rest.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
   *  this.rest.length() ... int
   *  this.rest.sameList(ILoButton) ... boolean
   *  this.rest.sameListHelp(ILoButton) ... boolean
   *  this.rest.sameFirstButton(Button) ... boolean
   */

  //draws all the buttons in the non-empty list on the given background
  public WorldScene drawList(WorldScene background, boolean lit) {
    return this.rest.drawList(this.first.draw(background, lit), lit);

  }

  //adds a button to the non-empty list of buttons
  public ILoButton add(Button next) {

    /*
     * Fields: none
     * Methods:
     *  next.drawButton(Color) ... WorldImage
     *  next.drawDark() ... WorldImage
     *  next.draw(WorldScene, boolean) ... WorldScene
     *  next.sameButton(Button) ... boolean
     */

    return new ConsLoButton(this.first, this.rest.add(next));
  }

  //draws the first button of the non-empty list on the background
  public WorldScene drawIndex(WorldScene background, int index, boolean lit) {
    return this.drawIndexAcc(0, index, background, lit);  
  }

  //determines if we are at the correct index
  public WorldScene drawIndexAcc(int current, int index, 
      WorldScene background, boolean lit) {

    if (current == index) {
      return this.first.draw(background, lit);
    }
    else {
      return this.rest.drawIndexAcc(current + 1, index, background, lit);
    }
  }

  //determines the length of a non-empty list of buttons
  public int length() {
    return 1 + this.rest.length();
  }

  //determines if this list of buttons is the same as the given list of buttons
  public boolean sameList(ILoButton that) {

    /*
     * Fields: none
     * Methods: 
     *  that.drawList(WorldScene, boolean) ... WorldScene
     *  that.add(Button) ... ILoButton
     *  that.sameButtons(Button) ... boolean
     *  that.drawIndex(WorldScene, int, boolean) ... WorldScene
     *  that.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
     *  that.length() ... int
     *  that.sameList(ILoButton) ... boolean
     *  that.sameListHelp(ILoButton) ... boolean
     *  that.sameFirstButton(Button) ... boolean
     */

    return that.sameFirstButton(this.first)
        && that.sameListHelp(this.rest);
  }

  //helps determine if the list of buttons is the same as the given list of buttons
  public boolean sameListHelp(ILoButton that) {

    /*
     * Fields: none
     * Methods: 
     *  that.drawList(WorldScene, boolean) ... WorldScene
     *  that.add(Button) ... ILoButton
     *  that.sameButtons(Button) ... boolean
     *  that.drawIndex(WorldScene, int, boolean) ... WorldScene
     *  that.drawIndexAcc(int, int, WorldScene, boolean) ... WorldScene
     *  that.length() ... int
     *  that.sameList(ILoButton) ... boolean
     *  that.sameListHelp(ILoButton) ... boolean
     *  that.sameFirstButton(Button) ... boolean
     */

    return that.sameList(this.rest);
  }

  //checks if the first button in this list is the same as the given button
  public boolean sameFirstButton(Button b) {

    /*
     * Fields: none
     * Methods:
     *  b.drawButton(Color) ... WorldImage
     *  b.drawDark() ... WorldImage
     *  b.draw(WorldScene, boolean) ... WorldScene
     *  b.sameButton(Button) ... boolean
     */

    return this.first.sameButton(b);
  }

  //determines if the list is empty
  public boolean empty() {
    return false;
  }
}

// Represents one of the four buttons you can click
class Button {
  Color color;
  int x;
  int y;

  Button(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }

  /*
   * Fields:
   *  this.color ... Color
   *  this.x ... int
   *  this.y ... int
   * Methods:
   *  this.drawButton(Color) ... WorldImage
   *  this.drawDark() ... WorldImage
   *  this.draw(WorldScene, boolean) ... WorldScene
   *  this.sameButton(Button) ... boolean
   * Methods of Fields:
   *  none
   */

  //draws the button with the given color
  WorldImage drawButton(Color color) {
    return new RectangleImage(100, 100, OutlineMode.SOLID, color);
  }

  //draw this button dark
  WorldImage drawDark() {
    return this.drawButton(this.color.darker().darker());
  }

  //draws a single button on the background
  WorldScene draw(WorldScene background, boolean lit) {

    if (lit) {
      return background.placeImageXY(this.drawButton(this.color), this.x, this.y);
    }

    else {
      return background.placeImageXY(this.drawDark(), this.x, this.y);
    }

  }

  //checks if this button is the same as the given button
  boolean sameButton(Button clickedButton) {
    return this.color == clickedButton.color
        && this.x == clickedButton.x
        && this.y == clickedButton.y;
  }
}

//examples
class ExamplesSimon {

  Button red = new Button(Color.red, 300, 200);
  Button blue = new Button(Color.blue, 200, 200);
  Button green = new Button(Color.green, 300, 300);
  Button yellow = new Button(Color.yellow, 200, 300);
  ILoButton mtButton = new MtLoButton();

  Random rand = new Random(5); 

  //tests the random seed
  boolean testRandom(Tester t) {
    return t.checkExpect(this.rand.nextInt(10), 7)
        && t.checkExpect(this.rand.nextInt(10), 2)
        && t.checkExpect(this.rand.nextInt(10), 4)
        && t.checkExpect(this.rand.nextInt(10), 4)
        && t.checkExpect(this.rand.nextInt(10), 6);
  }

  //examples and tests for length
  ILoButton b = new ConsLoButton(this.red, this.mtButton);
  ILoButton b1 = new ConsLoButton(this.red, new ConsLoButton(this.blue,
      this.mtButton));
  ILoButton b2 = new ConsLoButton(this.red, new ConsLoButton(this.blue, 
      new ConsLoButton(this.yellow, this.mtButton)));

  boolean testLength(Tester t) {
    return t.checkExpect(this.b1.length(), 2)
        && t.checkExpect(this.mtButton.length(), 0)
        && t.checkExpect(this.b2.length(), 3)
        && t.checkExpect(this.b.length(), 1);
  }

  //examples and test for sameIndividual
  Button red2 = new Button(Color.red, 300, 200);

  boolean testSameIndividual(Tester t) {
    return t.checkExpect(this.red.sameButton(this.blue), false)
        && t.checkExpect(this.red.sameButton(this.red2), true);
  }

  //tests the drawButton method
  boolean testDrawButton(Tester t) {
    return t.checkExpect(this.red.drawButton(Color.red), 
        new RectangleImage(100, 100, OutlineMode.SOLID, Color.red))
        && t.checkExpect(this.green.drawButton(Color.green), 
            new RectangleImage(100, 100, OutlineMode.SOLID, Color.green))
        && t.checkExpect(this.blue.drawButton(Color.blue), 
            new RectangleImage(100, 100, OutlineMode.SOLID, Color.blue));
  }

  //tests the drawDark method
  boolean testDrawDark(Tester t) {
    return t.checkExpect(this.red.drawDark(), 
        new RectangleImage(100, 100, OutlineMode.SOLID, Color.red.darker().darker()))
        && t.checkExpect(this.green.drawDark(), 
            new RectangleImage(100, 100, OutlineMode.SOLID, Color.green.darker().darker()))
        && t.checkExpect(this.blue.drawDark(), 
            new RectangleImage(100, 100, OutlineMode.SOLID, Color.blue.darker().darker()));
  }

  //tests the add method
  boolean testAdd(Tester t) {
    return t.checkExpect(this.mtButton.add(this.red), this.b)
        && t.checkExpect(this.b.add(this.blue), this.b1)
        && t.checkExpect(this.b1.add(this.yellow), this.b2);
  }

  //tests the sameFirstButton method
  boolean testSameFirstButton(Tester t) {
    return t.checkExpect(this.b.sameFirstButton(red), true)
        && t.checkExpect(this.b.sameFirstButton(blue), false)
        && t.checkExpect(this.mtButton.sameFirstButton(red), false)
        && t.checkExpect(this.b2.sameFirstButton(red), true);
  }

  //examples and tests the sameList method
  ILoButton b3 = new ConsLoButton(this.red, this.mtButton);
  ILoButton b4 = new ConsLoButton(this.red, new ConsLoButton(this.blue, this.mtButton));
  ILoButton b5 = new ConsLoButton(this.red, new ConsLoButton(this.blue, 
      new ConsLoButton(this.yellow, this.mtButton)));  

  boolean testSameList(Tester t) {
    return t.checkExpect(this.b.sameList(this.b3), true)
        && t.checkExpect(this.b1.sameList(this.b2), false)
        && t.checkExpect(this.b2.sameList(this.b3), false)
        && t.checkExpect(this.mtButton.sameList(this.b2), false);
  }

  //tests the sameListHelp method
  boolean testSameListHelp(Tester t) {
    return t.checkExpect(this.b.sameListHelp(this.b3), false)
        && t.checkExpect(this.b2.sameListHelp(this.b1), false)
        && t.checkExpect(this.b4.sameListHelp(this.b4), false)
        && t.checkExpect(this.mtButton.sameListHelp(this.b2), false);
  }

  //tests the empty method
  boolean testEmpty(Tester t) {
    return t.checkExpect(this.mtButton.empty(), true)
        && t.checkExpect(this.b1.empty(), false)
        && t.checkExpect(this.b2.empty(), false);
  }

  //SimonWorld for testing with a random seed
  SimonWorld s1 = new SimonWorld(b, b1, true, false, 0, new Random(5));
  SimonWorld s2 = new SimonWorld(b1, b2, true, false, 0, new Random(2));
  SimonWorld s3 = new SimonWorld(b1, b2, true, false, 0, new Random(10));


  //tests the randomButtons method
  boolean testRandomButtons(Tester t) {
    return t.checkExpect(s1.randomButtons(), new Button(Color.green, 300, 300))
        && t.checkExpect(s1.randomButtons(), new Button(Color.red, 300, 200))
        && t.checkExpect(s1.randomButtons(), new Button(Color.red, 300, 200))
        && t.checkExpect(s1.randomButtons(), new Button(Color.green, 300, 300))
        && t.checkExpect(s1.randomButtons(), new Button(Color.blue, 200, 200));
  }

  WorldScene world1 = new WorldScene(500, 500);

  //tests the makeScene method
  boolean testMakeScene(Tester t) {
    return t.checkExpect(s1.makeScene(), new WorldScene(500, 500)
        .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
            new Color(60, 0, 0)), 300, 200)
        .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
            new Color(0, 0, 60)), 200, 200)
        .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
            new Color(0, 60, 0)), 300, 300)
        .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
            new Color(60, 60, 0)), 200, 300))
        && t.checkExpect(s2.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(60, 0, 0)), 300, 200)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(0, 0, 60)), 200, 200)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(0, 60, 0)), 300, 300)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(60, 60, 0)), 200, 300))
        && t.checkExpect(s3.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(60, 0, 0)), 300, 200)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(0, 0, 60)), 200, 200)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(0, 60, 0)), 300, 300)
            .placeImageXY(new RectangleImage(100, 100, OutlineMode.SOLID, 
                new Color(60, 60, 0)), 200, 300));
  }

  //tests the drawIndex method
  boolean testDrawIndex(Tester t) {
    return t.checkExpect(this.b.drawIndex(background, 0, true), background.placeImageXY(
        this.red.drawButton(Color.red), 300, 200))
        && t.checkExpect(this.b1.drawIndex(background, 0, true), background.placeImageXY(
            this.red.drawButton(Color.red), 300, 200))
        && t.checkExpect(this.b1.drawIndex(background, 1, true), background.placeImageXY(
            this.blue.drawButton(Color.blue), 200, 200))
        && t.checkExpect(this.b2.drawIndex(background, 2, true), background.placeImageXY(
            this.yellow.drawButton(Color.yellow), 200, 300))
        && t.checkExpect(this.b2.drawIndex(background, 1, true), background.placeImageXY(
            this.blue.drawButton(Color.blue), 200, 200));
  }

  //tests the drawIndexAcc method
  boolean testDrawIndexAcc(Tester t) {
    return t.checkExpect(this.b1.drawIndexAcc(1, 1, background, true), background.placeImageXY(
        this.red.drawButton(Color.red), 300, 200))
        && t.checkExpect(this.b2.drawIndexAcc(1, 2, background, true), background.placeImageXY(
            this.blue.drawButton(Color.blue), 200, 200))
        && t.checkExpect(this.b3.drawIndexAcc(0, 0, background, true), background.placeImageXY(
            this.red.drawButton(Color.red), 300, 200))
        && t.checkExpect(this.b2.drawIndexAcc(0, 2, background, true), background.placeImageXY(
            this.yellow.drawButton(Color.yellow), 200, 300));
  }

  //examples and tests for the onTick method
  SimonWorld w1 = new SimonWorld(this.b5, this.b4, true, false, 0);
  SimonWorld w2 = new SimonWorld(this.b5, this.b4, true, false, 1);
  SimonWorld w3 = new SimonWorld(this.b5, this.b4, false, true, 2);
  SimonWorld w4 = new SimonWorld(this.b5, this.b4, false, true, 3);
  SimonWorld w5 = new SimonWorld(this.b2, this.b1, true, false, 0);

  // tests the onTick method
  boolean testOnTick(Tester t) {
    return t.checkExpect(this.w1.onTick(), new SimonWorld(this.b5, this.b4, true, false, 0))
        && t.checkExpect(this.w2.onTick(), new SimonWorld(this.b5, this.b4, true, false, 1))
        && t.checkExpect(this.w3.onTick(), new SimonWorld(this.b5, this.b4, false, true, 3));
  }

  //tests and examples for the checkLocation method
  SimonWorld s = new SimonWorld();

  boolean testChecklocation(Tester t) {
    return t.checkExpect(s.checkLocation(new Posn(200, 200)), "Blue")
        && t.checkExpect(s.checkLocation(new Posn(300, 200)), "Red")
        && t.checkExpect(s.checkLocation(new Posn(200, 300)), "Yellow")
        && t.checkExpect(s.checkLocation(new Posn(300, 300)), "Green");
  }

  //examples of WorldScene and World Images to test
  WorldScene background = new WorldScene(500, 500);
  WorldImage gameOver = new TextImage("Game Over", 30, Color.black);
  WorldImage bye = new TextImage("bye", 30, Color.black);
  WorldImage youLost = new TextImage("You lost", 30, Color.black);

  //test the lastScene() method
  boolean testLastScene(Tester t) {
    return t.checkExpect(this.w1.lastScene("Game Over"), 
        background.placeImageXY(this.gameOver, 250, 250))
        && t.checkExpect(this.w2.lastScene("bye"), 
            background.placeImageXY(this.bye, 250, 250))
        && t.checkExpect(this.w3.lastScene("You lost"), 
            background.placeImageXY(this.youLost, 250, 250));
  }

  //runs the game by creating a world and calling bigBang
  boolean testSimonSays(Tester t) {
    SimonWorld starterWorld = new SimonWorld();
    int sceneSize = 500;
    return starterWorld.bigBang(sceneSize, sceneSize, 1);
  }

}