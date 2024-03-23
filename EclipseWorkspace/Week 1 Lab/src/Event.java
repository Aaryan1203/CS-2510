class Event {
  String title;
  String location;
  Date date;
  Person person;
  
  Event(){
    
  }
  
   Event(String title, Date date, String location, Person person) {
    this.title = title;
    this.date = date;
    this.location = location;
    this.person = person;
  }
}

class Date {
  int day;
  String month;
  int year;
  
  Date(int day, String month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
}

class examplesEvents {
  Date d1 = new Date(3, "December", 2022);
  Date d2 = new Date(4, "October", 2021);
  Date d3 = new Date(23, "February", 2023);
  
  Address addtim = new Address("Boston", "MA");
  Address addkate = new Address("Warwick", "RI");
  Address addrebecca = new Address("Nashau", "NH");
  
  Person tim = new Person("Tim", 23, "Male", addtim);
  Person kate = new Person("Kate", 22, "Female", addkate);
  Person rebecca = new Person("Rebecca", 31, "Non-binary", addrebecca);
  
  Event event1 = new Event("Party", d1, "Boston", tim);
  Event event2 = new Event("Birthday", d2, "Las Vegas", kate);
  Event event3 = new Event("Convert", d3, "New Jersey", rebecca);

}
