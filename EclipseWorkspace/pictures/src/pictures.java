import tester.*;

interface IPicture {
  int getWidth();
  int countShapes();
  int comboDepth();
  IPicture mirror();
  String pictureRecipe(int depth);
}

class Shape implements IPicture {
  String kind;
  int size;

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }

  public int getWidth() {
    return this.size; 
  }

  public int countShapes() {
    return 1;
  }

  public int comboDepth() {
    return 0;
  }

  public IPicture mirror() {
    return this;
  }

  public String pictureRecipe(int depth) {
    return this.kind;
  }
}

class Combo implements IPicture {
  String name;
  IOperation operation;

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }

  public int getWidth() {
    return this.operation.getWidth();
  }

  public int countShapes() {
    return this.operation.countShapes();
  }

  public int comboDepth() {
    return 1 + this.operation.comboDepth();
  }

  public IPicture mirror() {
    return new Combo(this.name, this.operation.mirror());
  }
  
  public String pictureRecipe(int depth) {
    if (depth == 0) {
        return this.name;
    } else {
        return this.operation.pictureRecipe(depth - 1);
    }
  }
}

interface IOperation {
  int getWidth();
  int countShapes();
  int comboDepth();
  IOperation mirror();
  String pictureRecipe(int depth);
}

class Scale implements IOperation {
  IPicture picture;
  
  Scale(IPicture picture) {
    this.picture = picture;
  }
    
  public int getWidth() {
    return 2 * this.picture.getWidth();
  }
  
  public int countShapes() {
    return this.picture.countShapes();
  }
  
  public int comboDepth() {
    return this.picture.comboDepth();
  }
  
  public IOperation mirror() {
    return new Scale(this.picture.mirror());
  }
  
  public String pictureRecipe(int depth) {
    if (depth == 0) {
        return "scale";
    } else {
        return "scale(" + this.picture.pictureRecipe(depth - 1) + ")";
    }
  }
}

class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }
    
  public int getWidth() {
    return this.picture1.getWidth() + this.picture2.getWidth();
  }
    
  public int countShapes() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }
    
  public int comboDepth() {
    return Math.max(this.picture1.comboDepth(), this.picture2.comboDepth());
  }
    
  public IOperation mirror() {
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }
  
  public String pictureRecipe(int depth) {
    if (depth == 0) {
        return "beside";
    } else {
        return "beside(" + this.picture1.pictureRecipe(depth - 1) + ", " 
                         + this.picture2.pictureRecipe(depth - 1) + ")";
    }
  }
}

class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;

  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }
    
  public int getWidth() {
    return Math.max(this.topPicture.getWidth(), this.bottomPicture.getWidth());
  }
    
  public int countShapes() {
    return this.topPicture.countShapes() + this.bottomPicture.countShapes();
  }
  
  public int comboDepth() {
    return Math.max(this.topPicture.comboDepth(), this.bottomPicture.comboDepth());
  }
    
  public IOperation mirror() {
    return new Overlay(this.topPicture.mirror(), this.bottomPicture.mirror());
  }
  
  public String pictureRecipe(int depth) {
    if (depth == 0) {
        return "overlay";
    } else {
        return "overlay(" + this.topPicture.pictureRecipe(depth - 1) + ", " 
                            + this.bottomPicture.pictureRecipe(depth - 1) + ")";
    }
  }
}

class ExamplesPicture {
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IPicture bigCircle = new Combo("big circle", new Scale(circle));
  IPicture squareOnCircle = new Combo("square on circle", new Overlay(square, bigCircle));
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle", 
      new Beside(squareOnCircle, squareOnCircle));

  IPicture scaledSquare = new Combo("scaled square", new Scale(square));
  IPicture circleBesideSquare = new Combo("circle beside square", new Beside(circle, square));
  IPicture overlayCircleOnSquare = new Combo("overlay circle on square", 
      new Overlay(circle, square));
  
  // Test for getWidth
  // Testing getWidth on a single shape
  boolean testGetWidthCircle(Tester t) {
    return circle.getWidth() == 20;
  }

  // Testing getWidth on a combo picture
  boolean testGetWidthDoubledSquareOnCircle(Tester t) {
    return t.checkExpect(doubledSquareOnCircle.getWidth(), 0); // Expected width 
  }

  // Test for countShapes
  // Testing countShapes on a combo picture
  boolean testCountShapesDoubledSquareOnCircle(Tester t) {
    return t.checkExpect(doubledSquareOnCircle.countShapes(), 0); // Expected count 
  }

  // Test for comboDepth
  // Testing comboDepth on a nested combo
  boolean testComboDepthDoubledSquareOnCircle(Tester t) {
    return t.checkExpect(doubledSquareOnCircle.comboDepth(), 0); // Expected depth 
  }

  // Test for mirror
  // Testing mirror on a beside combo
  boolean testMirrorCircleBesideSquare(Tester t) {
    return t.checkExpect(circleBesideSquare.mirror(),new Combo("circle beside square", 
        new Beside(square, circle)));
  }

  // Test for pictureRecipe
  // Testing pictureRecipe at depth 2 for a complex combo
  boolean testPictureRecipeDoubledSquareOnCircleDepth2(Tester t) {
    return t.checkExpect(doubledSquareOnCircle.pictureRecipe(2),"beside(overlay(square, big circle), "
        + "overlay(square, big circle))");
  }
}
