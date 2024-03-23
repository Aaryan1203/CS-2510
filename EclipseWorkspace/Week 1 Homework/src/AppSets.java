// An interface that can is either a Folder or an App
interface IAppSet{

}
// A class to represent a Folder

class Folder implements IAppSet {
  String title;

  Folder(String title) {
    this.title = title;
  }
}
// A class to represent an App

class Apps implements IAppSet {
  String appName;
  IAppSet others;

  Apps(String appName, IAppSet others) {
    this.appName = appName;
    this.others = others;
  }
}

class ExamplesSets {
  IAppSet travel = new Folder("Travel");
  IAppSet food = new Folder("Food");
  IAppSet social = new Folder("Social");
  IAppSet t1 = new Apps("Uber", this.travel);
  IAppSet t2 = new Apps("mTicket", this.t1);
  IAppSet t3 = new Apps("Moovit", this.t2);
  IAppSet travelApps = new Apps("Orbitz", this.t3);
  IAppSet f1 = new Apps("Grubhub", this.food);
  IAppSet f2 = new Apps("B. Good", this.f1);
  IAppSet foodApps = new Apps("Gong Cha", this.f2);
  IAppSet s1 = new Apps("Instagram", this.social);
  IAppSet s2 = new Apps("Snapchat", this.s1);
  IAppSet socialApps = new Apps("Twitter", this.s2);


}