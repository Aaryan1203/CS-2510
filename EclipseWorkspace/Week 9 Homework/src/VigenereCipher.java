import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

// class that represents a Vigenere
class Vigenere {
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<ArrayList<Character>> table;

  //constructor that initializes the table to the Vigenere table
  Vigenere() {
    this.table = this.initVigenere();
  }

  // Initializes the Vigenere table
  ArrayList<ArrayList<Character>> initVigenere() {
    ArrayList<ArrayList<Character>> vigenereTable = new ArrayList<ArrayList<Character>>();

    for (int i = 0; i < this.alphabet.size(); i = i + 1) {
      ArrayList<Character> row = new ArrayList<Character>();

      for (int j = 0; j < this.alphabet.size(); j = j + 1) {
        row.add(this.alphabet.get((j + i) % this.alphabet.size()));
      }

      vigenereTable.add(row);
    }

    return vigenereTable;
  }

  //codes the given message and keyword based on if it needs to be encoded or decoded
  String code(String message, String keyword, Boolean encode) {
    String key = repeatKeyword(keyword, message.length());
    String code = "";

    if (encode) {
      for (int i = 0; i < message.length(); i = i + 1) {
        int row = this.alphabet.indexOf(message.charAt(i));
        int col = this.alphabet.indexOf(key.charAt(i));
        code += table.get(row).get(col);
      }

      return code;
    }

    else {
      for (int i = 0; i < message.length(); i = i + 1) {
        int row = this.alphabet.indexOf(key.charAt(i));
        int col = table.get(row).indexOf(message.charAt(i));
        code += this.alphabet.get(col);
      }

      return code;
    }
  }

  // Encodes the given message using the given keyword
  String encode(String message, String keyword) {
    return code(message, keyword, true);
  }

  // Decodes the given message using the given keyword
  String decode(String message, String keyword) {
    return code(message, keyword, false);

  }

  // Repeats the given keyword until it is the same length as the given message
  String repeatKeyword(String keyword, int length) {

    String word = "";
    for (int i = 0; i < length; i = i + 1) {
      word += keyword.charAt(i % keyword.length());
    }

    return word;
  }
}

//examples of Vigenere
class ExamplesVigenere {

  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  Vigenere init = new Vigenere();
  ArrayList<ArrayList<Character>> table;
  ArrayList<ArrayList<Character>> vigenereTable = new ArrayList<ArrayList<Character>>();

  //tests the initVigenere method
  void testInitVigenere(Tester t) {

    for (int i = 0; i < this.alphabet.size(); i = i + 1) {
      ArrayList<Character> row = new ArrayList<Character>();

      for (int j = 0; j < this.alphabet.size(); j = j + 1) {
        row.add(this.alphabet.get((j + i) % this.alphabet.size()));
      }

      this.vigenereTable.add(row);
    }

    t.checkExpect(this.init.table.size(), 26);
    t.checkExpect(this.vigenereTable.get(0), this.alphabet);
    t.checkExpect(this.vigenereTable.get(1), new ArrayList<Character>(Arrays.asList(
        'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
        't', 'u', 'v', 'w', 'x', 'y', 'z', 'a')));

    t.checkExpect(this.vigenereTable.get(7), new ArrayList<Character>(Arrays.asList(
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 
        'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g')));

    t.checkExpect(this.vigenereTable.get(25), new ArrayList<Character>(Arrays.asList(
        'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y')));

    t.checkExpect(this.vigenereTable.get(0).get(3), 'd');
    t.checkExpect(this.vigenereTable.get(4).get(7), 'l');
    t.checkExpect(this.vigenereTable.get(2).get(3), 'f');
    t.checkExpect(this.vigenereTable.get(8).get(22), 'e');
    t.checkExpect(this.vigenereTable.get(1).get(25), 'a');

  }

  //tests the code method
  void testCode(Tester t) {
    t.checkExpect(this.init.code("thanksgiving", "happy", true), "ahpcizgxkgug");
    t.checkExpect(this.init.code("hello", "bob", true), "ismmc");
    t.checkExpect(this.init.code("christmas", "hello", true), "jlctgaqld");
    t.checkExpect(this.init.code("easter", "fundies", true), "jufwmv");
    t.checkExpect(this.init.code("halloween", "red", true), "yeocszviq");
    t.checkExpect(this.init.code("hello", "hello", true), "oiwwc");
    t.checkExpect(this.init.code("ahpcizgxkgug", "happy", false), "thanksgiving");
    t.checkExpect(this.init.code("ismmc", "bob", false), "hello");
    t.checkExpect(this.init.code("jlctgaqld", "hello", false), "christmas");
    t.checkExpect(this.init.code("jufwmv", "fundies", false), "easter");
    t.checkExpect(this.init.code("yeocszviq", "red", false), "halloween");
    t.checkExpect(this.init.code("", "hello", true), "");
    t.checkExpect(this.init.code("", "hello", false), "");
  }

  //tests the encode method
  void testEncode(Tester t) {
    t.checkExpect(this.init.encode("thanksgiving", "happy"), "ahpcizgxkgug");
    t.checkExpect(this.init.encode("hello", "bob"), "ismmc");
    t.checkExpect(this.init.encode("christmas", "hello"), "jlctgaqld");
    t.checkExpect(this.init.encode("easter", "fundies"), "jufwmv");
    t.checkExpect(this.init.encode("halloween", "red"), "yeocszviq");
    t.checkExpect(this.init.encode("hello", "hello"), "oiwwc");
    t.checkExpect(this.init.encode("", "happy"), "");
  }

  //tests the decode method
  void testDecode(Tester t) {
    t.checkExpect(this.init.decode("ahpcizgxkgug", "happy"), "thanksgiving");
    t.checkExpect(this.init.decode("ismmc", "bob"), "hello");
    t.checkExpect(this.init.decode("jlctgaqld", "hello"), "christmas");
    t.checkExpect(this.init.decode("jufwmv", "fundies"), "easter");
    t.checkExpect(this.init.decode("yeocszviq", "red"), "halloween");
    t.checkExpect(this.init.decode("oiwwc", "hello"), "hello");
    t.checkExpect(this.init.decode("", "happy"), "");
  }

  //tests the repeatKeyword method
  void testRepeatKeyword(Tester t) {
    t.checkExpect(this.init.repeatKeyword("hello", 10), "hellohello");
    t.checkExpect(this.init.repeatKeyword("hello", 11), "hellohelloh");
    t.checkExpect(this.init.repeatKeyword("bob", 10), "bobbobbobb");
    t.checkExpect(this.init.repeatKeyword("fundies", 3), "fun");
    t.checkExpect(this.init.repeatKeyword("hi", 7), "hihihih");
    t.checkExpect(this.init.repeatKeyword("thanksgiving", 20), "thanksgivingthanksgi");
    t.checkExpect(this.init.repeatKeyword("thanksgiving", 0), "");
  }
}


