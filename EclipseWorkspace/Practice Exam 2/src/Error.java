
import java.util.ArrayList;
import java.util.Arrays;
import tester.Tester;

// represents a roster of students for a sports team
class Roster {
  // names of the students
  ArrayList<String> names;

  // create a roster from a given list of names
  Roster(ArrayList<String> names) {
    this.names = new ArrayList<String>(names);
  }

  // adds a name to this Roster's 
  // list of names
  void addName(String name) {
    ArrayList<String> newList = new ArrayList<String>(this.names);
    newList.add(name);
    this.names = newList;
  }

  // returns the list of students in 
  // this Roster
  ArrayList<String> getNames() {
    return new ArrayList<String>(this.names);
  }
}

// examples class!
class ExamplesRoster { 
  ArrayList<String> defaultRoster; 
  Roster volleyball;
  Roster tennis;
  Roster swimming;
  Roster figureSkating;

  void initData() {
    this.defaultRoster = new ArrayList<String>(Arrays.asList("Edward"));

    this.volleyball = new Roster(this.defaultRoster); 
    this.tennis = new Roster(this.defaultRoster); 
    this.swimming = new Roster(this.defaultRoster); 
    this.figureSkating = new Roster(this.defaultRoster); 

  }
  // tests the addName method
  void testAddName(Tester t) {
    this.initData();

    t.checkExpect(this.volleyball.names.size(), 1);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

    this.volleyball.addName("Karen");

    t.checkExpect(this.volleyball.names.size(), 2);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the two tests below fail?
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

  }

  // tests the getNames method
  void testGetNames(Tester t) {
    this.initData();

    t.checkExpect(this.figureSkating.getNames().size(), 1);
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));

    ArrayList<String> fgList = this.figureSkating.getNames();
    fgList.add("Karen");

    t.checkExpect(fgList.size(), 2);
    t.checkExpect(fgList, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the three tests below fail?
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
  }
}
