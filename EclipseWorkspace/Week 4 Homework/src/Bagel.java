import tester.Tester;

//represents a recipe for a bagel
class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double salt;
  double malt;

  //constructor that accepts all the values
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    this.flour = this.checkWeight(flour, water, 
        "The weight of the flour must be the same as weight of the water");
    this.water = water;
    this.malt = this.checkWeight(malt, yeast,
        "The weight of the malt must be the same as weight of the yeast");
    this.yeast = yeast;
    this.salt = this.checkWeight(salt, flour / 20.0 - yeast, 
        "The weight of the salt must be 1/20th the weight of the flour minus the yeast");
  }

  //constructor that accepts only flour and yeast
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, flour / 20 - yeast, yeast);
  }

  //constructor that accepts only flour, yeast, and salt as volumes
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, yeast * 5 / 48, salt / 4.8, yeast * 5 / 48);
  }


  /*
   * Fields:
   *  this.flour ... double
   *  this.water ... double
   *  this.yeast ... double
   *  this.salt ... double
   *  tihs.malt ... double
   * Methods:
   *  this.checkWeight(double, double, String) ... double
   *  this.sameRecipe(BagelRecipe) ... boolean
   * Methods of Fields:
   *  None
   */

  //checks if the weights of the ingredients are the same
  double checkWeight(double ingredientOne, double ingredientTwo, String message) {
    if (Math.abs(ingredientOne - ingredientTwo) < 0.001) {
      return ingredientOne;
    }
    else {
      throw new IllegalArgumentException(message);

    }
  }

  //checks if this recipe is the same as the other recipe
  boolean sameRecipe(BagelRecipe other) {
    return Math.abs(this.flour - other.flour) < 0.001
        && Math.abs(this.water - other.water) < 0.001
        && Math.abs(this.yeast - other.yeast) < 0.001
        && Math.abs(this.salt - other.salt) < 0.001
        && Math.abs(this.malt - other.malt) < 0.001;
  }

}


//represents the examples
class ExamplesBagel {

  BagelRecipe bagelOne = new BagelRecipe(20.0, 20.0, 0.5, 0.5, 0.5); 
  BagelRecipe bagelTwo = new BagelRecipe(40.0, 0.5);
  BagelRecipe bagelThree = new BagelRecipe(20.0, 0.5); 
  BagelRecipe bagelFour = new BagelRecipe(20.0, 20.0); 
  BagelRecipe bagelFive = new BagelRecipe(40.0, 40.0, 0.5, 1.5, 0.5); 
  BagelRecipe bagelSix = new BagelRecipe(5.0, 1.0, 4.6);
  BagelRecipe bagelSeven = new BagelRecipe(5.0, 2.0, 4.1);

  //testing the constructor
  boolean testConstructor(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException(
        "The weight of the malt must be the same as weight of the yeast"), 
        "BagelRecipe", 2.0, 2.0, 3.5, 3.0, 5.0)
        && t.checkConstructorException(new IllegalArgumentException(
            "The weight of the flour must be the same as weight of the water"),
            "BagelRecipe", 2.0, 3.1, 3.5, 3.5, 3.5)
        && t.checkConstructorException(new IllegalArgumentException(
            "The weight of the salt must be 1/20th the weight of the flour minus the yeast"),
            "BagelRecipe", 7.3, 7.3, 3.5, 3.0, 3.5);
  }


  //testing the sameRecipe method
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.bagelOne.sameRecipe(bagelThree), true)
        && t.checkExpect(this.bagelOne.sameRecipe(bagelFour), false)
        && t.checkExpect(this.bagelOne.sameRecipe(bagelFive), false)
        && t.checkExpect(this.bagelTwo.sameRecipe(bagelFive), true)
        && t.checkExpect(this.bagelThree.sameRecipe(bagelFour), false);
  }

  //testing the checkWeight method
  boolean testCheckWeight(Tester t) {
    return t.checkInexact(this.bagelFive.checkWeight(40.0, 40.0, "error"), 40.0, 0.001)
        && t.checkInexact(this.bagelOne.checkWeight(20.0, 20.0, "error"), 20.0, 0.001)
        && t.checkException(new IllegalArgumentException("error"), new BagelRecipe(25.2, 23.6), 
            "checkWeight", 25.2, 23.6, "error");
  }

}


