interface ILoStudent {
  
}

class MtLoStudent implements ILoStudent {
  
}

class ConsLoStudent implements ILoStudent {
  Student first;
  ILoStudent rest;
  
  ConsLoStudent(Student first, ILoStudent rest) {
    this.first = first;
    this.rest = rest;
  }
}

interface ILoCourse {
  
}

class MtLoCourse implements ILoCourse {
  
}

class ConsLoCourse implements ILoCourse {
  Course first;
  ILoCourse rest;
  
  ConsLoCourse(Course first, ILoCourse rest) {
    this.first = first;
    this.rest = rest;
  }
}

class Student {
  String firstName;
  String lastName;
  ILoCourse courses;
  
  Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.courses = new MtLoCourse();
  }
}

class Course {
  String departmentName;
  int courseNumber;
  String instructor;
  ILoStudent enrollment;
  
  Course(String departmentName, int courseNumber, String instructor) {
    this.departmentName = departmentName;
    this.courseNumber = courseNumber;
    this.instructor = instructor;
    this.enrollment = new MtLoStudent();
  }
}

class ExamplesCourse {
  
}