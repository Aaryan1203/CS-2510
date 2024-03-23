import java.util.*;

import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {

  // The original list of characters to be encoded
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Used for testing by giving it a specific random object
  PermutationCode(Random rand) {
    this.rand = rand;
    this.code = this.initEncoder();
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    int size = this.alphabet.size();
    ArrayList<Character> copy = new ArrayList<Character>(this.alphabet);
    ArrayList<Character> encoded = new ArrayList<Character>();

    for (int i = 0; i < size; i = i + 1) {
      Character randomElement = copy.get(rand.nextInt(copy.size()));
      encoded.add(randomElement);
      copy.remove(randomElement);
    }

    return encoded;
  }

  // produce an encoded String from the given String
  String encode(String source) {
    return code(source, this.alphabet, this.code);
  }  

  // produce a decoded String from the given String
  String decode(String code) {
    return code(code, this.code, this.alphabet);
  }

  // codes a string based on different keys and returns the coded string
  String code(String original, ArrayList<Character> first, ArrayList<Character> second) {
    String answer = "";
    for (int i = 0; i < original.length(); i = i + 1) {
      char letter = original.charAt(i);
      int index = first.indexOf(letter);
      answer += second.get(index);
    }

    return answer;  
  }

}

//Example class to test for encode and decode
class ExamplesPermutation {

  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> alphabetCode = 
      new ArrayList<Character>(Arrays.asList(
          'v', 'z', 'e', 'b', 'h', 'n', 't', 'o', 'a', 'c', 
          's', 'u', 'm', 'y', 'd', 'g', 'j', 'r', 'p', 
          'w', 'k', 'i', 'f', 'x', 'q', 'l'));


  ArrayList<Character> encode = 
      new ArrayList<Character>(Arrays.asList(
          'b', 'e', 'a', 'c', 'd'));


  ArrayList<Character> code = new ArrayList<Character>();
  PermutationCode init = new PermutationCode();
  PermutationCode permutation1 = new PermutationCode(this.alphabetCode);
  PermutationCode permutation2 = new PermutationCode(this.encode);
  PermutationCode permutation3 = new PermutationCode(new Random(5));

  // Tests the initEncoder method
  void testInit(Tester t) {
    // Test if the generated code in init has the same size as the alphabet
    t.checkExpect(this.init.code.size(), this.alphabet.size());

    // Test if the generated code in init contains all characters from the alphabet
    for (Character c : this.alphabet) {
      t.checkExpect(this.init.code.contains(c), true);
    }

    // Test if the generated code in init is different from the original alphabet
    t.checkExpect(this.init.code.equals(this.alphabet), false);

    t.checkExpect(this.permutation3.code, new ArrayList<Character>(Arrays.asList(
        'p', 's', 'c', 'o', 'r', 't', 'u', 'i', 'k', 'q', 
        'v', 'e', 'f', 'x', 'n', 'j', 'y', 'w', 'z', 
        'd', 'a', 'b', 'g', 'm', 'l', 'h')));
    t.checkExpect(this.permutation3.code.get(10), 'v');
    t.checkExpect(this.permutation3.code.get(11), 'e');
    t.checkExpect(this.permutation3.code.get(1), 's');
    t.checkExpect(this.permutation3.code.get(25), 'h');
  }


  //tests the encode method
  void testEncode(Tester t) {
    t.checkExpect(this.permutation2.encode("badace"), "ebcbad");
    t.checkExpect(this.permutation1.encode("hello"), "ohuud");
    t.checkExpect(this.permutation1.encode("aaryan"), "vvrqvy");
    t.checkExpect(this.permutation1.encode("hi"), "oa");
    t.checkExpect(this.permutation1.encode("fundies"), "nkybahp");
    t.checkExpect(this.permutation1.encode(""), ""); 
    t.checkExpect(this.permutation1.encode("a"), "v"); 
    t.checkExpect(this.permutation1.encode("aaa"), "vvv"); 
  }

  //tests the decode method
  void testDecode(Tester t) {
    t.checkExpect(this.permutation2.decode("abeedc"), "cabbed");
    t.checkExpect(this.permutation1.decode("hello"), "eczzh");
    t.checkExpect(this.permutation1.decode("aaryan"), "iirnif");
    t.checkExpect(this.permutation1.decode("hi"), "ev");
    t.checkExpect(this.permutation1.decode("fundies"), "wlfovck");
    t.checkExpect(this.permutation1.decode(""), ""); 
    t.checkExpect(this.permutation1.decode("v"), "a"); 
    t.checkExpect(this.permutation1.decode("vvv"), "aaa"); 
  }

  //tests the code method
  void testCode(Tester t) {
    PermutationCode perm = new PermutationCode(new Random(5));
    t.checkExpect(perm.code("badace", this.alphabet, perm.code), "spopcr");
    t.checkExpect(perm.code("hello", this.alphabet, perm.code), "ireen");
    t.checkExpect(perm.code("aaryan", this.alphabet, perm.code), "ppwlpx");
    t.checkExpect(perm.code("hi", this.alphabet, perm.code), "ik");
    t.checkExpect(perm.code("fundies", this.alphabet, perm.code), "taxokrz");
    t.checkExpect(perm.code("", this.alphabet, perm.code), "");
    t.checkExpect(perm.code("badace",perm.code, this.alphabet), "vutucl");
    t.checkExpect(perm.code("hello", perm.code, this.alphabet), "zlyyd");
    t.checkExpect(perm.code("aaryan", perm.code, this.alphabet), "uuequo");
    t.checkExpect(perm.code("hi", perm.code, this.alphabet), "zh");
    t.checkExpect(perm.code("fundies", perm.code, this.alphabet), "mgothlb");
    t.checkExpect(perm.code("", perm.code, this.alphabet), "");
  }
}