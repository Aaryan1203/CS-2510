
import tester.Tester;

class Person {
  String name;
  int phone;
  Person(String name, int phone) {
    this.name = name;
    this.phone = phone;
  }

  // Returns true when the given person has the same name and phone number as this person
  boolean samePerson(Person that) {
    return this.name.equals(that.name) && this.phone == that.phone;
  }

  // Returns true when this person has the same name as a given String
  boolean sameName(String name) {
    return this.name.equals(name);
  }

  // Returns the number of this person when they have the same name as a given String
  int phoneOf(String name) {
    if (this.name.equals(name)) {
      return this.phone;
    }

    else {
      throw new RuntimeException("The given name does not match this person's name");
    }
  }

  //EFFECT: changes the phone number of a person
  public void changePhone(int newNum) {
    this.phone = newNum;
  }
}

interface ILoPerson {
  // Returns true if this list contains a person with the given name
  boolean contains(String name);

  //find the number of the person with the given name
  int find(String name);

  //EFFECT: change the number of the person with the given name
  void changeNum(String name, int newNum);

  //EFFECT: removes the person with the given name
  void removePerson(String name);

  //EFFECT: removes the person with the given name
  //acc: keeps track of the previous ConsLoPerson before the given name
  void removeHelp(String name, ConsLoPerson prev);

}
class MtLoPerson implements ILoPerson {
  // Returns true if this empty list contains a person with the given name
  public boolean contains(String name) { return false; }

  public int find(String name) {
    return -1;
  }

  //EFFECT: change the number of the person with the given name
  public void changeNum(String name, int newNum) {
    return ;
  }

  //removes the person with the given name
  public void removePerson(String name) {
    return ;
  }

  //EFFECT: removes the person with the given name
  //acc: keeps track of the previous ConsLoPerson before the given name
  public void removeHelp(String name, ConsLoPerson prev) {
    return ;
  }
}

class ConsLoPerson implements ILoPerson {
  Person first;
  ILoPerson rest;
  ConsLoPerson(Person first, ILoPerson rest) {
    this.first = first;
    this.rest = rest;
  }
  // Returns true if this non-empty list contains a person with the given name
  public boolean contains(String name) {
    return this.first.sameName(name) || this.rest.contains(name);
  }

  public int find(String name) {
    if (this.first.sameName(name)) {
      return this.first.phoneOf(name);
    }
    else {
      return this.rest.find(name);
    }
  }

  //EFFECT: change the number of the person with the given name
  public void changeNum(String name, int newNum) {
    if (this.first.sameName(name)) {
      this.first.changePhone(newNum);
    }

    else {
      this.rest.changeNum(name, newNum);
    }
  }

  //Effect removes the person with the given name
  public void removePerson(String name) {
    if (this.first.sameName(name)) {
      
    }
    
    else {
    this.rest.removeHelp(name, this);
    }
  }

  //EFFECT: removes the person with the given name
  //acc: keeps track of the previous ConsLoPerson before the given name
  public void removeHelp(String name, ConsLoPerson prev) {
    if (this.first.sameName(name)) {
      prev.rest = this.rest;
    }

    else {
      this.rest.removePerson(name);
    }
  }
}

class ExamplePhoneLists {
  Person anne, bob, clyde, dana, eric, frank, gail, henry, irene, jenny, alice1, alice2, alice3;

  ILoPerson friends, family, work;

  void initData() {

    this.anne = new Person("Anne", 1234);
    this.bob = new Person("Bob", 3456);
    this.clyde = new Person("Clyde", 6789);
    this.dana = new Person("Dana", 1357);
    this.eric = new Person("Eric", 12469);
    this.frank = new Person("Frank", 7294);
    this.gail = new Person("Gail", 9345);
    this.henry = new Person("Henry", 8602);
    this.irene = new Person("Irene", 91302);
    this.jenny = new Person("Jenny", 8675309);

    this.alice1 = new Person("Alice", 12345);
    this.alice2 = new Person("Alice", 12345);
    this.alice3 = alice1;


    this.friends =
        new ConsLoPerson(this.anne, new ConsLoPerson(this.clyde,
            new ConsLoPerson(this.gail, new 
                ConsLoPerson(this.frank,
                    new ConsLoPerson(this.jenny, new 
                        MtLoPerson())))));
    this.family =
        new ConsLoPerson(this.anne, new ConsLoPerson(this.dana,
            new ConsLoPerson(this.frank, new 
                MtLoPerson())));
    this.work =
        new ConsLoPerson(this.bob, new ConsLoPerson(this.clyde,
            new ConsLoPerson(this.dana, new 
                ConsLoPerson(this.eric,
                    new ConsLoPerson(this.henry, new 
                        ConsLoPerson(this.irene,
                            new MtLoPerson()))))));
  }

  void testContains(Tester t) {
    this.initData();
    t.checkExpect(new MtLoPerson().contains("Anne"), false);
    t.checkExpect(this.friends.contains("Gail"), true);
    t.checkExpect(this.family.contains("Clyde"), false);
  }

  void testChange(Tester t) {
    this.initData();
    this.friends.changeNum("Frank", 1234);
    t.checkExpect(this.friends.find("Frank"), 1234);
    t.checkExpect(this.family.find("Frank"), 1234);
    t.checkExpect(this.frank.phone, 1234);
  }

  void testSame(Tester t) {
    this.initData();
    t.checkExpect(this.alice1.samePerson(this.alice2), true);
    t.checkExpect(this.alice1.samePerson(this.alice3), true);
    t.checkExpect(this.alice1.equals(this.alice2), false);
    t.checkExpect(this.alice1.equals(this.alice3), true);
    this.alice1.phone = 54321;
    t.checkExpect(this.alice1.samePerson(this.alice2), false);
    t.checkExpect(this.alice1.samePerson(this.alice3), true);
    t.checkExpect(this.alice1.equals(this.alice2), false);
    t.checkExpect(this.alice1.equals(this.alice3), true);
  }

  void testRemove(Tester t) {
    this.initData();
    t.checkExpect(this.friends.contains("Frank"), true);
    t.checkExpect(this.family.contains("Frank"), true);
    this.friends.removePerson("Frank");
    t.checkExpect(this.friends.contains("Frank"), false);
    t.checkExpect(this.family.contains("Frank"), true);
    
    this.initData();
    t.checkExpect(this.friends.contains("Anne"), true);
    t.checkExpect(this.family.contains("Anne"), true);
    this.friends.removePerson("Anne");
    t.checkExpect(this.friends.contains("Anne"), false);
    t.checkExpect(this.family.contains("Anne"), true);
    
    
  }
}
