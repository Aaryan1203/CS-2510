class Person {
  String name;
  int age;
  String gender;
  Address address;
  
   Person() {
  }
  
   Person(String name, int age, String gender, Address address) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.address = address;
  }
}

class Address {
  String city;
  String state;
  
  Address(String city, String state) {
    this.city = city;
    this.state = state;
  }
}

class ExamplesPerson {
  Address addtim = new Address("Boston", "MA");
  Address addkate = new Address("Warwick", "RI");
  Address addrebecca = new Address("Nashau", "NH");
  
  Person tim = new Person("Tim", 23, "Male", addtim);
  Person kate = new Person("Kate", 22, "Female", addkate);
  Person rebecca = new Person("Rebecca", 31, "Non-binary", addrebecca);
}