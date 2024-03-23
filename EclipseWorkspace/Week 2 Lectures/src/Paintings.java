
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
  ILoPainting list4 = new ConsLoPainting(this.last, this.list4);
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
  //this.mt.getByArtistName("Da Vinci") --> this.mt
  //this.list3.getByArtistName("Banksy") --> this.mt
  //this.list3.getByArtistName("Da Vinci") --> this.list1
  //this.list4.getByArtistName("Da Vinci") --> new ConsLoPainting(this.last,
  //                                             new ConsLoPainting(this.mona, 
  //this.mt))
//this.mt.sort() --> this.mt
//this.mt.insert(this.mona) --> this.list1
//this.list2.sort() --> (this.mona, this.sunflowers)
//this.list1.insert(this.sunflowers) --> (this.mona, this.sunflowers)
//this.list1.insert(this.last) --> (this.last, this.mona)
}
