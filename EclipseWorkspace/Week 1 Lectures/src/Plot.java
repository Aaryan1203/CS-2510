
interface IPlot{

}

class Ending implements IPlot{
  String description;

  Ending(String d){
    this.description = d;
  }
}

class Event implements IPlot{
  String character;
  String description;
  IPlot left;
  IPlot right;

  Event(String c, String d, IPlot l, IPlot r){
    this.character = c;
    this.description = d;
    this.left = l;
    this.right = r;
  }
}

class ExamplesPlot{
  IPlot ending = new Ending("The End");
  IPlot shrek = new Event("Shrek", "Meets Donkey", ending, ending);
  
}