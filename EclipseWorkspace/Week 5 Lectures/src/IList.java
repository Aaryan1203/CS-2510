
interface IList<T> {
  //filters this list by the given predicate
  IList<T> filter(IPred<T> pred);
  
  //maps the given function on to every item in the list
  <U> IList<U> map(IFunc<T, U> func);
}

class MtList<T> implements IList<T> {

  @Override
  public IList<T> filter(IPred<T> pred) {
    return this;
  }

  @Override
  public <U> IList<U> map(IFunc<T, U> func) {
    return new MtList<U>();
  }
  
}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public IList<T> filter(IPred<T> pred) {
    if (pred.apply(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  public <U> IList<U> map(IFunc<T, U> func) {
    return new ConsList<U>(func.apply(this.first), this.rest.map(func));
  }
  
}

class ExmaplesLists {
  IList<String> mtStrings = new MtList<String>();
  IList<String> list1String = new ConsList<String>("a", this.mtStrings);
  
  IList<Integer> mtInts = new MtList<Integer>();
  IList<Integer> list1Ints = new ConsList<Integer>(2, this.mtInts);
}