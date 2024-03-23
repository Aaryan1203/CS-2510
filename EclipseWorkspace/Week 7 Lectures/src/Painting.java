import tester.Tester;
//represents a list of paintings
interface ILoPainting {
  //count the paintings in this ILoPainting
  int count();
  //get the paintings in this ILoPainting that are painted by the artist with
  //the given name
  ILoPainting getByArtistName(String name);
  //sort this list alphabetically by title
  ILoPainting sort();
  //insert a Painting into this *sorted* list
  //resulting in a sorted list
  ILoPainting insert(Painting p);
}
//represents the empty list of paintings
class MtLoPainting implements ILoPainting {
  //count the paintings in this MtLoPainting
  public int count() {
    return 0;
  }
  //get the paintings in this MtLoPainting that are painted by the artist with
  //the given name
  public ILoPainting getByArtistName(String name) {
    return this;
  }
  //sort this list alphabetically by title
  public ILoPainting sort() {
    return this;
  }
  //insert a Painting into this *sorted* list
  //resulting in a sorted list
  public ILoPainting insert(Painting p) {
    return new ConsLoPainting(p, this);
  }
}
//represents a non-empty list of paintings
class ConsLoPainting implements ILoPainting {
  Painting first;
  ILoPainting rest;
  ConsLoPainting(Painting first, ILoPainting rest) {
    this.first = first;
    this.rest = rest;
  }
  /* fields: 
   *  this.first ... Painting
   *  this.rest ... ILoPainting
   * methods:
   *  this.count() ... int
   *  this.getByArtistName(String) ... ILoPainting
   *  this.sort() ... ILoPainting
   *  this.insert(Painting) ... ILoPainting
   * methods for fields:
   *  this.first.samePainting(Painting) ... boolean
   *  this.first.checkArtistName(String) ... boolean
   *  this.first.titleComesBefore(Painting) ... boolean
   *  this.rest.count() ... int
   *  this.rest.getByArtistName(String) ... ILoPainting
   *  this.rest.sort() ... ILoPainting
   *  this.rest.insert(Painting) ... ILoPainting
   */
  //count the paintings in this ConsLoPainting
  public int count() {
    return 1 + this.rest.count();
  }
  //get the paintings in this ConsLoPainting that are painted by the artist 

  //the given name
  public ILoPainting getByArtistName(String name) {
    if (this.first.checkArtistName(name)) {
      return new ConsLoPainting(this.first, 
          this.rest.getByArtistName(name));
    }
    else {
      return this.rest.getByArtistName(name);
    }
  }
  //sort this list alphabetically by title
  public ILoPainting sort() {
    return this.rest.sort().insert(this.first);
  }
  //insert a Painting into this *sorted* list
  //resulting in a sorted list
  public ILoPainting insert(Painting p) {
    if (this.first.titleComesBefore(p)) {
      return new ConsLoPainting(this.first, this.rest.insert(p));
    }
    else {
      return new ConsLoPainting(p, this);
    }
  }
}
//a class represent a painting
class Painting {
  Artist artist;
  String title;
  double value; // in millions of dollars
  int year;
  Painting(Artist artist, String title, double value, int year) {
    this.artist = artist;
    this.title = title;
    this.value = value;
    this.year = year;
    //assign this painting to this.artist
    this.artist.addPainting(this);
  }
  /* fields:
   *  this.artist ... Artist
   *  this.title ... String
   *  this.value ... double
   *  this.year ... int
   * methods:
   *   this.samePainting(Painting) ... boolean
   *   this.checkArtistName(String) ... boolean
   *   this.titleComesBefore(Painting) ... boolean
   * methods for fields:
   *   this.artist.checkName(String) ... boolean
   *   this.artist.sameArtist(Artist) ... boolean
   */
  //is this Painting the same as the given one?
  boolean samePainting(Painting p) {
    /* fields of p:
     *   p.artist ... Artist
     *   p.title ... String
     *   p.value ... int
     *   p.year ... int
     *  methods of p: 
     *   p.titleComesBefore(Painting)
     *   p.samePainting(Painting)
     *   p.checkArtistName(String)
     *  methods of fields of p:
     *   p.artist.sameArtist(Artist) ... boolean
     *   p.artist.checkName(String) ... boolean
     */
    return this.artist.sameArtist(p.artist) &&
        this.title.equals(p.title) &&
        this.value == p.value &&
        this.year == p.year;
  }
  //does the artist of this painting have the given name?
  boolean checkArtistName(String name) {
    return this.artist.checkName(name);
  }
  //does the title of this painting come before the title of the given one 
  boolean titleComesBefore(Painting p) {
    /* fields of p:
     *   p.artist
     *   p.title
     *   p.value
     *   p.year
     *  methods of p: 
     *   p.titleComesBefore(Painting)
     *   p.samePainting(Painting)
     *   p.checkArtistName(String)
     *  methods of fields of p:
     *   p.artist.sameArtist(Artist) ... boolean
     *   p.artist.checkName(String) ... boolean
     */
    return this.title.compareTo(p.title) < 0;
  }
}
class Artist { 
  String name;
  int yob;
  ILoPainting paintings;
  
  Artist(String n, int yob) {
    this.name = n;
    this.yob = yob;
    this.paintings = new MtLoPainting();
  }
  /* fields:
   *  this.name ... String
   *  this.yob ... int
   *  this.painting ... Painting
   * methods:
   *  this.sameArtist(Artist) ... boolean
   *  this.checkName(String) ... boolean 
   * methods of Fields:
   *  this.painting.samePainting(Painting) ... boolean
   */
  //is this artist the same as the given one?
  boolean sameArtist(Artist that) {
    /* fields of that:
     *  that.name ... String
     *  that.yob ... int
     *  that.painting ... Painting
     * methods of that:
     *  that.sameArtist(Artist) ... boolean 
     *  that.checkName(String) ... boolean
     */
    return this.name.equals(that.name) &&
        this.yob == that.yob; //&&
    //this.painting.samePainting(that.painting);
  }
  //is the name of this artist the same as the given name
  boolean checkName(String name) {
    return this.name.equals(name);
  }

  //EFFECT: set this artist's paintings to the given one
  void addPainting(Painting p) {
    this.paintings = new ConsLoPainting(p, this.paintings);
  }

}

class ExamplesPaintings {
  Artist monet;
  Painting waterlillies;
  Painting poppies;

  //EFFECT: resets the examples to the original values
  void initData() {
    this.monet = new Artist("Monet", 1840);
    this.waterlillies = new Painting(this.monet, "Waterlilies", 50, 1910);
    this.poppies = new Painting(this.monet, "Poppies",25, 1890);
  }

  boolean testCycle(Tester t) {
    this.initData();
    boolean initialConditions = t.checkExpect(this.monet.paintings, null);
    this.monet.addPainting(this.waterlillies);
    boolean finalCondition = t.checkExpect(this.monet.paintings, this.waterlillies);
    return initialConditions && finalCondition;
  }

  boolean testCycle2(Tester t) {
    this.initData();
    boolean initialConditions = t.checkExpect(this.monet.paintings, null);
    this.monet.addPainting(this.poppies);
    boolean finalCondition = t.checkExpect(this.monet.paintings, this.poppies);
    return initialConditions && finalCondition;
  }

  boolean testSameness(Tester t) {
    return t.checkExpect(this.waterlillies.samePainting(this.waterlillies), true);
  }
}



class Counter {
  int val;

  Counter() {
    this(0);
  }

  Counter(int initialVal) {
    this.val = initialVal;
  }

  int get() {
    int ans = this.val;
    this.val = this.val + 1;
    return ans;
  }
}

class ExamplesCounter {
  boolean testCounter(Tester t) {
    Counter c1 = new Counter();
    Counter c2 = new Counter(2);

    return t.checkExpect(c1.get(), 0)
        && t.checkExpect(c2.get(), 2)
        && t.checkExpect(c1.get() == c1.get(), false)
        && t.checkExpect(c2.get() == c1.get(), true)
        && t.checkExpect(c2.get() == c1.get(), true)
        && t.checkExpect(c1.get() == c1.get(), false)
        && t.checkExpect(c2.get() == c1.get(), false);
  }
}









