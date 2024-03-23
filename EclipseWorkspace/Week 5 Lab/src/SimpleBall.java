import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import tester.Tester;

/**
 * This program produces a single ball moving on screen. Pressing the space 
 * creates a new ball at a random position.
 *
 */

//this class represents a single ball
class SimpleBall {
  int x;
  int y;
  int radius;
  int speedX;
  int speedY;
  Color color;

  SimpleBall(int x,int y,int radius,Color color) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
  }

  //Return a new ball created by moving this ball by its speed.
  SimpleBall move(int minX,int maxX,int minY,int maxY) {   
    return new SimpleBall(this.x + 1,this.y + 1,this.radius,this.color);
  }


  //draw this ball as a circular image in the provided scene
  WorldScene draw(WorldScene scene) {
    return scene.placeImageXY(new CircleImage(this.radius,OutlineMode.SOLID,this.color),this.x,this.y);
  }
}


//the World. 
//Look at the image library documentation.

//This particular example uses the "funworld" version of the library (see imports above). 

class BallWorld extends World {

  //the world's state includes a ball
  SimpleBall ball;


  BallWorld(){
    this(new SimpleBall(30,50,30,Color.RED));

  }

  BallWorld(SimpleBall b) {
    this.ball = b;

  }

  /**
   * This method must be overridden. It provides the scene that must be rendered. 
   */
  //draws the ball onto the empty scene
  public WorldScene makeScene() {
    //a good idea is to start from an "empty" scene, and then fill it with everything we wish to draw
    WorldScene scene = this.getEmptyScene();

    return this.ball.draw(scene);
  }

  /**
   * This method is called at every tick of the animation. 
   * Code that changes the state of the world based on time should be here
   */
  //change the world by creating a new world state that has the ball at its next position
  public World onTick() {
    return new BallWorld(this.ball.move(0, this.getEmptyScene().width,0,this.getEmptyScene().height));
  }

  /**
   * Key pressed listener. Use this method to add key events to the game
   */
  // create a new ball if the space bar is pressed, and return the resulting world
  public World onKeyReleased(String key) {
    Random r = new Random();
    if (key.equals(" ")) {    
      return new BallWorld(new SimpleBall(r.nextInt(200),r.nextInt(200),r.nextInt(30),Color.BLACK));
    }
    else {
      return this;
    }
  }

}

//the "test", that is the runner of our game.
class ExamplesBallWorld {
  //add examples and tests

  boolean testGo(Tester t) {
    //create my world. This will initialize everything
    BallWorld myWorld = new BallWorld();

    //Start the game with a big bang. The third argument is the number of seconds between ticks. So currently the game
    //progresses at 200 ticks/second. This is probably higher than what is supported by the frame refresh rate by 
    //the image library, which is why the animation looks slower than it should.
    return myWorld.bigBang(500,500,0.005);
  }
}
