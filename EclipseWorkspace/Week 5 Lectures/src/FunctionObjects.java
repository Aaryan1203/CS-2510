
import tester.Tester;

//the templates and tests in this file still need some work
//represents a list of paintings
interface ILoPainting {

  //is this ILoPainting the same as the given ILoPainting?
  boolean sameLoPainting(ILoPainting that);

  //is this ILoPainting the same as the given MtLoPainting?
  boolean sameMtLoPainting(MtLoPainting that);

  //is this ILoPainting the same as the given ConLoPainting?
  boolean sameConsLoPainting(ConsLoPainting that);

  //sort this list of painting by the given comparator
  ILoPainting sort(IPaintingCompare comp);

  //inserts the given painting into this *sorted* list
  ILoPainting insert(Painting p, IPaintingCompare comp);

  //filter this ILoPainting by the given predicate
  ILoPainting filter(IPred<Painting> pred);

  //does this ILoPainting have at least one painting that passes the given 
  //predicate?
  boolean ormap(IPaintingPred pred);

  //get the best painting based on the given comparator
  Painting getBest(IPaintingCompare comp);

  //accumulator: keeps track of the best painting so far
  Painting getBestAcc(IPaintingCompare comp, Painting acc);
}

//represents an empty list of painting
class MtLoPainting implements ILoPainting {

  //sort this empty list of painting by the given comparator
  public ILoPainting sort(IPaintingCompare comp) {
    return this;
  }

  //inserts the given painting into this *sorted* list
  public ILoPainting insert(Painting p, IPaintingCompare comp) {
    return new ConsLoPainting(p, this);
  }

  //is this MtLoPainting the same as the given ILoPainting?
  public boolean sameLoPainting(ILoPainting that) {
    return that.sameMtLoPainting(this);
  }

  //is this MtLoPainting the same as the given MtLoPainting?
  public boolean sameMtLoPainting(MtLoPainting that) {
    return true;
  }

  //is this MtLoPainting the same as the given ConsLoPainting?
  public boolean sameConsLoPainting(ConsLoPainting that) {
    return false;
  }

  //filter this MtLoPainting by the given predicate
  public ILoPainting filter(IPred<Painting> pred) {
    return this;
  }

  //does this MtLoPainting have at least one painting that passes the given 
  //predicate?
  public boolean ormap(IPaintingPred pred) {
    return false;
  }

  //get the best painting based on the given comparator
  public Painting getBest(IPaintingCompare comp) {
    throw new RuntimeException("no painting to return");
  }

  //accumulator: keeps track of the best painting so far
  public Painting getBestAcc(IPaintingCompare comp, Painting acc) {
    return acc;
  }
}

//represents a non-empty list of painting
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
   *  this.getByArtistName(String) ... ILoPainting
   *  add all of the same... methods
   * methods for fields:
   *  this.first.samePainting(Painting) ... boolean
   *  this.first.checkArtistName(String) ... boolean
   *  this.first.titleComesBefore(Painting) ... boolean  
   *  this.rest.getByArtistName(String) ... ILoPainting
   *  add all of the this.rest.same...
   */

  //sort this list by the given comparator
  public ILoPainting sort(IPaintingCompare comp) {
    return this.rest.sort(comp).insert(this.first, comp);
  }

  //inserts the given painting into this *sorted* list
  public ILoPainting insert(Painting p, IPaintingCompare comp) {
    if (comp.compare(this.first, p) < 0) {
      return new ConsLoPainting(this.first, this.rest.insert(p, comp));
    }
    else {
      return new ConsLoPainting(p, this);
    }
  }

  //is this ConsLoPainting the same as the given ILoPainting?
  public boolean sameLoPainting(ILoPainting that) {
    return that.sameConsLoPainting(this);
  }

  //is this ConsLoPainting the same as the given MtLoPainting?
  public boolean sameMtLoPainting(MtLoPainting that) {
    return false;
  }

  //is this ConsLoPainting the same as the given ConsLoPainting?
  public boolean sameConsLoPainting(ConsLoPainting that) {

    /* everything in the class template, plus:
     * fields of that:
     *   that.first ... Painting
     *   that.rest ... ILoPainting
     * methods of that:
     *   *all of the methods in ILoPainting can be called by that
     * methods for fields of that:
     *   *all of the methods in Painting can be called by that.first*
     *   *all of the methods in ILoPainting can be called by that.rest*    
     * 
     */

    return this.first.samePainting(that.first) &&
        this.rest.sameLoPainting(that.rest);
  }

  //filter this ConsLoPainting by the given predicate
  public ILoPainting filter(IPred<Painting> pred) {
    if (pred.apply(this.first)) {
      return new ConsLoPainting(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //does this ConsLoPainting have at least one painting that passes the given 
  //predicate?
  public boolean ormap(IPaintingPred pred) {
    return pred.apply(this.first) ||
        this.rest.ormap(pred);
  }

  @Override
  public Painting getBest(IPaintingCompare comp) {
    return this.rest.getBestAcc(comp, this.first);
  }

  @Override
  public Painting getBestAcc(IPaintingCompare comp, Painting acc) {
    if (comp.compare(this.first, acc) < 0) {
      return this.rest.getBestAcc(comp, this.first);
    }
    else {
      return this.rest.getBestAcc(comp, acc);
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
  int titleComesBefore(Painting p) {
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
    return this.title.compareTo(p.title);
  }
}

class Artist { 
  String name;
  int yob;
  Artist(String n, int yob) {
    this.name = n;
    this.yob = yob;
  }
  /* fields:
   *  this.name ... String
   *  this.yob ... int
   * methods:
   *  this.sameArtist(Artist) ... boolean
   *  this.checkName(String) ... boolean 
   */

  //is this artist the same as the given one?
  boolean sameArtist(Artist that) {
    /* fields of that:
     *  that.name ... String
     *  that.yob ... int
     * methods of that:
     *  that.sameArtist(Artist) ... boolean 
     *  that.checkName(String) ... boolean
     */
    return this.name.equals(that.name) &&
        this.yob == that.yob;
  }

  //is the name of this artist the same as the given name
  boolean checkName(String name) {
    return this.name.equals(name);
  }
}

interface IPaintingPred {
  //asks a question about a given painting
  boolean apply(Painting p);
}

class AndPredicate implements IPaintingPred {
  IPaintingPred left;
  IPaintingPred right;
  AndPredicate(IPaintingPred left, IPaintingPred right) {
    this.left = left;
    this.right = right;
  }

  /* fields: 
   *  this.left ... IPaintingPred
   *  this.right ... IPaintingPred
   * methods:
   *  this.apply(Painting) ... boolean
   * methods for fields:
   *    this.left.apply(Painting) ... boolean
   *    this.right.apply(Painting) ... boolean
   * 
   */

  @Override
  public boolean apply(Painting p) {
    return this.left.apply(p) && this.right.apply(p);
  }
}

class ByArtistName implements IPaintingPred {
  String name;
  ByArtistName(String name) {
    this.name = name;
  }

  //is the given Painting painted by the artist with this.name?
  public boolean apply(Painting p) {
    /* We are relaxing the rule about accessing the fields of a parameter 
if it's not the same as this
     * fields of p:
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
    return p.checkArtistName(this.name);
  }
}

class Before1900 implements IPaintingPred{
  //is the given Painting painted before 1900?
  public boolean apply(Painting p) {
    /* We are relaxing the rule about accessing the fields of a parameter 
if it's not the same as this

     * fields of p:
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

    return p.year < 1900;
  }
}

class FlipPred implements IPaintingPred {
  IPaintingPred pred;
  FlipPred(IPaintingPred pred) {
    this.pred = pred;
  }

  @Override
  public boolean apply(Painting p) {
    return !this.pred.apply(p);
  }
}

interface IPaintingCompare {

  //determine which painting comes first
  //returns: a negative int if p1 comes before p2
  //         a positive int if p1 comes after p2
  //         a 0 is p1 is the same as p2

  int compare(Painting p1, Painting p2);
}

class CompareTitles implements IPaintingCompare {

  //does p1's title come before p2's title alphabetically?
  public int compare(Painting p1, Painting p2) {
    return p1.titleComesBefore(p2); 
  }
}

class CompareYears implements IPaintingCompare {
  //does p1's year come before p2's year?
  public int compare(Painting p1, Painting p2) {
    // relaxing the rule against accessing fields of parameters that are 
    //not the same as this
    return p1.year - p2.year;
  }
}

class FlipCompare implements IPaintingCompare {
  IPaintingCompare comp;
  FlipCompare(IPaintingCompare comp) {
    this.comp = comp;
  }
  //flip the result of comp.compare
  public int compare(Painting p1, Painting p2) {
    return comp.compare(p1, p2) * -1;
  }
}



class ExamplesPaintings {
  Artist daVinci = new Artist("Da Vinci", 1452);
  Artist banksy = new Artist("Banksy", 1975); //estimated yob
  Artist monet = new Artist("Monet", 1840);
  Painting mona = new Painting(this.daVinci, "Mona Lisa", 10, 1503);
  Painting last = new Painting(this.daVinci, "The Last Supper", 11, 1480);
  Painting sunflowers = new Painting(new Artist("Van Gogh", 1853), 
      "sunflowers", 9, 1889);
  Painting waterlilies = new Painting(this.monet, "Water Lilies", 20, 1915);
  Painting showMe = new Painting(this.banksy, "Show me the Monet", 6, 2005);
  Painting balloon = new Painting(this.banksy, "Balloon Girl", 25, 2002);
  ILoPainting mt = new MtLoPainting();
  ILoPainting list1 = new ConsLoPainting(this.mona, this.mt);
  ILoPainting list2 = new ConsLoPainting(this.sunflowers, this.list1);
  ILoPainting list3 = new ConsLoPainting(this.waterlilies, this.list2);
  ILoPainting list4 = new ConsLoPainting(this.last, this.list3);
  ILoPainting list5 = new ConsLoPainting(this.last, this.list3);
  IPaintingPred byArtistAndBefore1900 = new AndPredicate(new 
      ByArtistName("Monet"), new Before1900());
  IPaintingPred andPred2 = new AndPredicate(this.byArtistAndBefore1900, new 
      Before1900());
  IList<Painting> Paintings = new ConsList<Painting>(this.waterlilies,
      new ConsList<Painting>(this.mona, new MtList<Painting>()));
  boolean testSameness(Tester t) {
    return t.checkExpect(this.list4.equals(this.list4), true) &&
        t.checkExpect(this.list4.equals(this.list5), false) &&
        t.checkExpect(this.list4.sameLoPainting(this.list5), true);
    
    //add tests for all of the helpers as well as the empty list
  }
  boolean testFilter(Tester t) {
    return //t.checkExpect(this.list3.filter(new Before1900()), this.list2) 
        //&&
        t.checkExpect(this.list3.filter(new ByArtistGen("Da Vinci")), this.list1);// &&
    // t.checkExpect(this.list3.filter(new 
    //   ByArtistName("Banksy")), this.mt);
  }

  boolean testSort(Tester t) {
    return t.checkExpect(this.mt.sort(new CompareTitles()), this.mt) &&
        t.checkExpect(this.list2.sort(new CompareYears()), 
            new ConsLoPainting(this.mona, new 
                ConsLoPainting(this.sunflowers, this.mt)));
  }

  boolean testMap(Tester t) {
    return t.checkExpect(this.Paintings.map(new PaintingToArtist()), 
        new ConsList<Artist>(this.monet, new ConsList<Artist>(this.daVinci, new MtList<Artist>())));
  }
  // tests in shorthand: 
  //this.daVinci.checkName("Da Vinci") --> true
  //this.daVinci.checkName("Banksy") --> false
  //this.daVinci.sameArtist(this.monet) --> false
  //this.daVinci.sameArtist(this.daVinci) --> true
  //this.mona.samePainting(this.last) --> false
  //this.mona.samePainting(this.mona) --> true
  //this.mona.checkArtistName("Da Vinci") --> true
  //this.mona.checkArtistName("Monet") --> false
  //this.balloon.titleComesBefore(this.waterlilies) --> true
  //this.waterlilies.titleComesBefore(this.balloon) --> false
  //this.mt.count() --> 0
  //this.list2.count() --> 2
  //this.list3.count() --> 3
  //this.mt.getByDaVinci() --> this.mt
  //this.list3.getByDaVinci() --> this.list1
  //this.list4.getByDaVinci() --> new ConsLoPainting(this.last,
  //new ConsLoPainting(this.mona, this.mt))        
}
