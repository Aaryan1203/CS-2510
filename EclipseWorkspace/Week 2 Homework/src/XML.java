import tester.Tester;

//represents a list of XML's
interface ILoXML {

  //checks the length of the ILoXML
  int contentLength();

  //checks if the List has a tag with the given name
  boolean hasTag(String tagName);

  //checks if the List has an attribute with the given name
  boolean hasAttribute(String attName);

  //renders the xml as a simple string
  String renderAsString();
}

//represents an empty list of XML's
class MtLoXML implements ILoXML {
  /*
   * Fields:
   *  
   * Methods:
   *  this.checkLength() ... int
   *  this.hasTag() ... boolean
   *  this.hasAttribute() ... boolean
   *  this.renderAsString() ... boolean
   * Methods of fields:
   * 
   */

  //checks the length of the empty list
  public int contentLength() {
    return 0;
  }

  //checks if the empty list has a tag with the given name
  public boolean hasTag(String name) {
    return false;
  }

  //checks if the empty list has an attribute with the given name
  public boolean hasAttribute(String attName) {
    return false;
  }

  //renders the empty list of XML's to a string
  public String renderAsString() {
    return "";
  }

}

//represents a non-empty list of XML's
class ConsLoXML implements ILoXML {
  IXML first;
  ILoXML rest;

  ConsLoXML(IXML first, ILoXML rest) {
    this.first = first;
    this.rest = rest;
  }

  //checks the length of a non-empty list
  public int contentLength() {
    return first.contentLength() + rest.contentLength();
  }

  //checks with the non-empty list has a tag with the given name
  public boolean hasTag(String name) {
    return this.first.hasTag(name)
        || this.rest.hasTag(name);
  }

  //checks with the non-empty list has an attribute with the given name
  public boolean hasAttribute(String attName) {
    return this.first.hasAttribute(attName)
        || this.rest.hasAttribute(attName);
  }

  //renders the non-empty list of XML's to a string
  public String renderAsString() {
    return this.first.renderAsString() + this.rest.renderAsString();
  }

  /*
   * Fields:
   *  this.first ... IXML
   *  this.rest ... ILoXML
   * Methods:
   *  this.rest.contentLength() ... int
   *  this.rest.hasTag() ... boolean
   *  this.rest.hasAttribute() ... boolean
   *  this.rest.renderAsString() ... boolean
   * Methods of fields:
   *  this.first.contentLength() ... int
   *  this.first.hasTag(String) ... boolean
   *  this.first.hasAttribute(String) ... boolean
   *  this.first.renderAsString() ... String
   */

}

//represents an XML
interface IXML {

  //computes the length of the content in an XML document
  int contentLength();

  //determines if a piece of XML contains a Tag with the given name
  boolean hasTag(String tagName);

  //determines if a piece of XML contains an Attribute with the given name
  boolean hasAttribute(String attName);

  //turns the xml into a string
  String renderAsString();

}

//represents an XML with plain text 
class Plaintext implements IXML {
  String txt;

  Plaintext(String txt) {
    this.txt = txt;
  }

  /*
   * Fields:
   *  this.txt ... String
   * Methods:
   *  this.contentLength() ... int
   *  this.hasTag(String) ... boolean
   *  this.hasAttribute(String) ... boolean
   *  this.renderAsString() ... String
   * Methods of fields:
   * 
   */

  //determines the length of plain text
  public int contentLength() {
    return this.txt.length();
  }

  //determines if the plain text contains a Tag with the given name
  public boolean hasTag(String tagName) {
    return false;
  }

  //determines if the plain text contains an Attribute with the given name
  public boolean hasAttribute(String attName) {
    return false;
  }

  //turns the xml into a string
  public String renderAsString() {
    return this.txt;
  }

}

//represents an XML with no tags
class Untagged implements IXML {
  ILoXML content;

  Untagged(ILoXML content) {
    this.content = content;
  }

  /*
   * Fields:
   *  this.content ... ILoXML
   * Methods:
   *  this.contactLength() ... int
   *  this.hasTag(String) ... boolean
   *  this.hasAttribute(String) ... boolean
   *  this.renderAsString() ... String
   * Methods of fields:
   *  this.content.contentLength() ... int
   *  this.content.hasTag(String) ... boolean
   *  this.content.hasAttribute(String) ... boolean
   *  this.content.renderAsString() ... String
   */

  //determines the length of text in the Untagged xml
  public int contentLength() {
    return this.content.contentLength();
  }

  //determines if the xml contains a Tag with the given name
  public boolean hasTag(String tagName) {
    return this.content.hasTag(tagName);
  }

  //determines if the xml contains an attribute with the given name
  public boolean hasAttribute(String attName) {
    return this.content.hasAttribute(attName);
  }

  //turns the Untagged XML into a string
  public String renderAsString() {
    return this.content.renderAsString();
  }

}

//represents an XML with tags
class Tagged implements IXML {
  Tag tag;
  ILoXML content;

  Tagged(Tag tag, ILoXML content) {
    this.tag = tag;
    this.content = content;
  }

  /*
   * Fields:
   *  this.tag ... Tag
   *  this.content ... ILoXML
   * Methods:
   *  this.contentLength() ... int
   *  this.hasTag(String) ... boolean
   *  this.hasAttribute(String) ... boolean
   *  this.renderAsString() ... String
   * Methods of fields:
   *  this.content.contentLength() ... int
   *  this.content.hasTag(String) ... boolean
   *  this.content.hasAttribute(String) ... boolean
   *  this.content.renderAsString() ... String
   */

  //determines the length of the text in the Tagged xml
  public int contentLength() {
    return this.content.contentLength();
  }

  //determines if the xml contains a Tag with the given name
  public boolean hasTag(String tagName) {
    return this.tag.sameTag(tagName)
        || this.content.hasTag(tagName);
  }

  //determines if the xml contains an Attribute with the given name
  public boolean hasAttribute(String attName) {
    return this.tag.sameAttribute(attName)
        || this.content.hasAttribute(attName);
  }

  //renders the tagged xml as a string
  public String renderAsString() {
    return this.content.renderAsString();
  }
}

//represents a tag for an XML
class Tag {
  String name;
  ILoAtt atts;

  Tag(String name, ILoAtt atts) {
    this.name = name;
    this.atts = atts;
  }

  /*
   * Fields:
   *  this.name ... String
   *  this.atts ... ILoXML
   * Methods:
   *  this.sameTag() ... boolean
   * Methods of fields:
   *  this.atts.sameAttribute(String) ... boolean
   */

  //compares this tag with the given tag
  boolean sameTag(String tagName) {
    return this.name == tagName;
  }

  //
  boolean sameAttribute(String attName) {
    return this.atts.sameAttrtibute(attName);
  }

}

//represents a list of Attributes
interface ILoAtt {

  //checks if the given attribute is in the list of attributes
  boolean sameAttrtibute(String attName);

}

//represents an empty list of attributes
class MtLoAtt implements ILoAtt {

  /*
   * Fields:
   *  
   * Methods:
   *  this.sameAttribute(String) ... boolean
   * Methods of fields:
   * 
   */

  //checks if the given attribute is in the empty list
  public boolean sameAttrtibute(String attName) {
    return false;
  }


}

//represents a non-empty list of attributes
class ConsLoAtt implements ILoAtt {
  Att first;
  ILoAtt rest;

  ConsLoAtt(Att first, ILoAtt rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields:
   *  this.first ... Att
   *  this.rest ... ILoAtt
   * Methods:
   *  this.sameAttribute(String) ... boolean
   * Methods of fields:
   *  this.first.sameAttribute(String) ... boolean
   */

  //checks if the given attribute is in the non-empty list
  public boolean sameAttrtibute(String attName) {
    return this.first.sameAttribute(attName)
        || this.rest.sameAttrtibute(attName);
  }


}

//represents an attribute
class Att {
  String name;
  String value;

  Att(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /*
   * Fields:
   *  this.name ... String
   *  this.value ... String
   * Methods:
   *  this.sameAttribute(String) ... boolean
   * Methods of fields:
   * 
   */

  //determines if this attribute is the same as the given attribute
  boolean sameAttribute(String attName) {
    return this.name == attName;
  }
}

//examples of XML's
class ExamplesXML {


  //plain texts
  IXML xml = new Plaintext("XML");
  IXML iAm = new Plaintext("I am ");
  IXML exclimationMark = new Plaintext("!");
  IXML x = new Plaintext("X");
  IXML ml = new Plaintext("ML");

  //empty lists
  ILoAtt emptyAtt = new MtLoAtt();
  ILoXML emptyXML = new MtLoXML();

  //Attributes
  Att volume30 = new Att("volume", "30db");
  Att duration5 = new Att("duration", "5sec");

  //<yell>
  Tag yell = new Tag("yell", this.emptyAtt);

  //<italic>
  Tag italic = new Tag("italic", this.emptyAtt);

  //<yell volume="30db">
  Tag yellVolume30 = new Tag("yell", new ConsLoAtt(this.volume30, this.emptyAtt));

  //<yell volume="30db" duration="5sec">
  Tag yellVolume30Duration5 = new Tag("yell", new ConsLoAtt(this.volume30, 
      new ConsLoAtt(this.duration5, emptyAtt)));

  //<yell>XML</yell>
  IXML yellXML = new Tagged(this.yell, new ConsLoXML(this.xml, this.emptyXML));

  //<italic>X</italic>
  IXML italicX = new Tagged(this.italic, new ConsLoXML(this.x, this.emptyXML));

  //<yell><italic>X</italic>ML</yell>
  IXML yellItalicXML = new Tagged(this.yell, new ConsLoXML(this.italicX, 
      new ConsLoXML(this.ml, this.emptyXML)));

  //<yell volume="30db"><italic>X</italic>ML</yell>
  IXML yellVol30ItalicXML = new Tagged(this.yellVolume30, 
      new ConsLoXML(this.italicX, new ConsLoXML(this.ml, this.emptyXML)));

  //<yell volume="30db" duration="5sec"><italic>X</italic>ML</yell>
  IXML yellVol30Dur5ItalicXML = new Tagged(this.yellVolume30Duration5, 
      new ConsLoXML(this.italicX, new ConsLoXML(this.ml, this.emptyXML)));

  //I am XML!
  IXML xml1 = new Plaintext("I am XML!");

  //I am <yell>XML</yell>!
  IXML xml2 = new Untagged(new ConsLoXML(this.iAm, new ConsLoXML(this.yellXML, 
      new ConsLoXML(this.exclimationMark, this.emptyXML))));

  //I am <yell><italic>X</italic>ML</yell>!
  IXML xml3 = new Untagged(new ConsLoXML(iAm, new ConsLoXML(this.yellItalicXML, 
      new ConsLoXML(this.exclimationMark, this.emptyXML))));

  //I am <yell volume="30db"><italic>X</italic>ML</yell>!
  IXML xml4 = new Untagged(new ConsLoXML(iAm, new ConsLoXML(this.yellVol30ItalicXML, 
      new ConsLoXML(this.exclimationMark, this.emptyXML))));

  //I am <yell volume="30db" duration="5sec"><italic>X</italic>ML</yell>!
  IXML xml5 = new Untagged(new ConsLoXML(iAm, 
      new ConsLoXML(this.yellVol30Dur5ItalicXML, 
          new ConsLoXML(this.exclimationMark, this.emptyXML))));

  //testing the contentLength method
  boolean testContent(Tester t) {
    return t.checkExpect(this.xml1.contentLength(), 9)
        && t.checkExpect(this.exclimationMark.contentLength(), 1)
        && t.checkExpect(this.yellXML.contentLength(), 3)
        && t.checkExpect(this.italicX.contentLength(), 1)
        && t.checkExpect(this.xml2.contentLength(), 9);
  }

  //testing the hasTag method
  boolean testTag(Tester t) {

    return t.checkExpect(this.xml.hasTag("yell"), false)
        && t.checkExpect(this.xml2.hasTag("yell"), true)
        && t.checkExpect(this.xml2.hasTag("italic"), false)
        && t.checkExpect(this.xml3.hasTag("italic"), true);
  }

  //testing the hasAttribute method
  boolean testAttribute(Tester t) {
    return t.checkExpect(this.xml.hasAttribute("volume"), false)
        && t.checkExpect(this.xml3.hasAttribute("volume"), false)
        && t.checkExpect(this.xml4.hasAttribute("volume"), true)
        && t.checkExpect(this.xml4.hasAttribute("duration"), false)
        && t.checkExpect(this.xml5.hasAttribute("duration"), true);
  }

  //testing the renderAsString method
  boolean testString(Tester t) {
    return t.checkExpect(this.xml.renderAsString(), "XML")
        && t.checkExpect(this.exclimationMark.renderAsString(), "!")
        && t.checkExpect(this.yellItalicXML.renderAsString(), "XML")
        && t.checkExpect(this.yellVol30Dur5ItalicXML.renderAsString(), "XML")
        && t.checkExpect(this.xml3.renderAsString(), "I am XML!")
        && t.checkExpect(this.xml5.renderAsString(), "I am XML!")
        && t.checkExpect(this.emptyXML.renderAsString(), "");

  }

}










