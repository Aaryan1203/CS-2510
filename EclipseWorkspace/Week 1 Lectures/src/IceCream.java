
interface IIcecream{
  
}

class EmptyServing implements IIcecream{
  boolean cone;
  
  EmptyServing(boolean cone){
    this.cone = cone;
  }
}

class Scooped implements IIcecream{
  IIcecream more;
  String flavor;
  
  Scooped(IIcecream more, String flavor){
    this.more = more;
    this.flavor = flavor;
  }
}

class ExamplesIcecream{
  IIcecream sugarCone = new EmptyServing(true);
  IIcecream scoop1 = new Scooped(this.sugarCone, "Vanilla");
  IIcecream scoop2 = new Scooped(this.scoop1, "Chocolate");
}