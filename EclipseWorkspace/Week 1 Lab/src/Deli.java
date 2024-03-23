class Deli{
  Soup soup;
  Salad salad;
  Sandwich sandwich;
  
  Deli(){
    
  }
  
}

class Soup{
  boolean veg;
  
  Soup(boolean veg){
    this.veg = veg;
  }
}

class Salad{
  boolean veg;
  String dressing;
  
  Salad(boolean veg, String dressing){
    this.veg = veg;
    this.dressing = dressing;
  }
}

class Sandwich{
  String bread;
  String filling1;
  String filling2;
  
  Sandwich(String bread, String filling1, String filling2){
    this.bread = bread;
    this.filling1 = filling1;
    this.filling2 = filling2;
  }
}

class ExampleDeli{
  Soup soup1 = new Soup(true);
  Soup soup2 = new Soup(false);
  
  Salad salad1 = new Salad(true, "ranch");
  Salad salad2 = new Salad(false, "yogurt");
  
  Sandwich s1 = new Sandwich("Wheat", "Jelly", "Peanut Butter");
  Sandwich s2 = new Sandwich("Plain", "Ham", "Cheese");
}