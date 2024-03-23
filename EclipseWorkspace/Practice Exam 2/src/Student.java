import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

class Student {
  String name;
  int age;

  Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  // Does this object equal the given object
  public boolean equals(Object o) {
    if (o instanceof Student) {
      Student other = (Student)o;
      return this.name.equals(other.name) && 
          this.age == other.age;
    }
    else {
      return false;
    }
  }
  
  // Returns the hashCode of this object
  public int hashCode() {
    return this.name.hashCode() * 1000 + this.age;
  }
}


class ExamplesStudent {


  Student kelly = new Student("Kelly", 20);
  Student adam = new Student("Adam", 21);
  ArrayList<Student> students = new
      ArrayList<Student>(Arrays.asList(this.kelly, this.adam));

  void testStudent(Tester t) {
    t.checkExpect(this.students.contains(this.kelly), true);
    // why does the following test below fail?
    t.checkExpect(this.students.contains(new Student("Kelly",
        20)), true);

  }
}