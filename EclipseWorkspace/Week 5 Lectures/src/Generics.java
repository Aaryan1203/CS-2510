interface IPred<T> {
  //asks a question about the given T
  boolean apply(T t);
}


class ByArtistGen implements IPred<Painting> {
  String name;
  
  ByArtistGen(String name) {
    this.name = name;
  }
  
  //is the painting painted by an artist with this.name
  public boolean apply(Painting t) {
    return t.checkArtistName(this.name);
  }
  
}

interface ICompare<T> {
  //compare the given objects to determine which comes first
  int compare(T t1, T t2);
}

class CompareByTitleGen implements ICompare<Painting> {

  //compares the two paintings to see which comes first
  public int compare(Painting t1, Painting t2) {
    return t1.title.compareTo(t2.title);
  }
  
}

interface IFunc<X, Y> {
  //applies operation to the x and produces a y
  Y apply(X x);
}

class PaintingToArtist implements IFunc<Painting, Artist> {

  public Artist apply(Painting x) {
    return x.artist;
  }
  
}