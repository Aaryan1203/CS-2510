interface ILoDocument {
  ILoString sources();
}


class MtLoDocument implements ILoDocument {

  public ILoString sources() {
    return new MtLoString();
  }

}


class ConsLoDocument implements ILoDocument {
  IDocument first;
  ILoDocument rest;
  
  ConsLoDocument(IDocument first, ILoDocument rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoString sources() {
    return this.first.sources().append(this.rest.sources());
  }
}


interface IDocument {
  ILoString sources();
  
}

class Book implements IDocument {
  Author author;
  String title;
  ILoDocument documents;
  String URL;


  Book(Author author, String title, ILoDocument documents, String uRL) {
    this.author = author;
    this.title = title;
    this.documents = documents;
    URL = uRL;
  }

  
  public ILoString sources() {
    return new ConsLoString(this.author.format() + "." + "\"" + this.title + "\"", this.documents.sources());
  }
}



class Wiki implements IDocument {
  String author;
  String title;
  ILoDocument documents;
  String publisher;

  Wiki(String author, String title, ILoDocument documents, String publisher) {
    this.author = author;
    this.title = title;
    this.documents = documents;
    this.publisher = publisher;
  }

  public ILoString sources() {
    return this.documents.sources();
  }

}

class Author {
  String firstName;
  String lastName;
  int yob;
  
  Author(String firstName, String lastName, int yob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yob = yob;
  }
  
  String format() {
    return this.lastName + "," + this.firstName;
  }
}




