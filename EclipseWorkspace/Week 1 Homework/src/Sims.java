// An interface that is either Study, Socialize, or Exercise
interface IMode {

}
// A class to represent studying

class Study implements IMode {
  String subject;
  boolean examReview;

  Study(String subject, boolean examReview) {
    this.subject = subject;
    this.examReview = examReview;
  }
}
// A class to represent socializing

class Socialize implements IMode {
  String description;
  int friends;

  Socialize(String description, int friends) {
    this.description = description;
    this.friends = friends;
  }
}
// A class to represent Exercising

class Exercise implements IMode {
  String name;
  boolean aerobic;

  Exercise(String name, boolean aerobic) {
    this.name = name;
    this.aerobic = aerobic;
  }
}
// An interface that is either a Classroom, Gym, or Student center

interface IPlace {

}
// A class that represents a Classroom

class Classroom implements IPlace {
  String name;
  int roomCapacity;
  int occupantCount;

  Classroom(String name, int roomCapacity, int occupantCount) {
    this.name = name;
    this.roomCapacity = roomCapacity;
    this.occupantCount = occupantCount;
  }
}
// A class that represents a Gym

class Gym implements IPlace {
  String name;
  int exerciseMachines;
  int patrons;
  boolean open;

  Gym(String name, int exerciseMachines, int patrons, boolean open) {
    this.name = name;
    this.exerciseMachines = exerciseMachines;
    this.patrons = patrons;
    this.open = open;
  }
}
// A class that represents a Student Center

class StudentCenter implements IPlace {
  String name;
  boolean open;

  public StudentCenter(String name, boolean open) {
    this.name = name;
    this.open = open;
  }
}
// A class that represents a student

class SimStudent {
  String name;
  IMode mode;
  IPlace location;
  double gpa;
  String major;

  SimStudent(String name, IMode mode, IPlace location, double gpa, String major) {
    this.name = name;
    this.mode = mode;
    this.location = location;
    this.gpa = gpa;
    this.major = major;
  }
}

class ExamplesSims {
  IPlace shillman105 = new Classroom("Shillman 105", 115, 86);
  IPlace marino = new Gym("Marino Recreation Center", 78, 47, true);
  IPlace curry = new StudentCenter("Curry Student Center", false);
  IPlace isec = new Classroom("ISEC 102", 300, 189);
  IPlace iv = new Gym("International Village Gym", 55, 24, false);
  IPlace ell = new StudentCenter("Ell Student Center", true);
  IMode study = new Study("Fundies", true);
  IMode socialize = new Socialize("Playground", 5);
  IMode exercise = new Exercise("Treadmill", true);
  SimStudent student1 = new SimStudent("Aaryan", this.study, this.curry, 3.5, "Computer Science");
  SimStudent student2 = new SimStudent("Aarav", this.exercise, this.iv, 3.7, "Computer Science");
  SimStudent student3 = new SimStudent("Sri", this.study, this.shillman105, 2.8, "Data Science");
  SimStudent student4 = new SimStudent("Harish", this.socialize, this.marino, 3.2, "Economics");
}



