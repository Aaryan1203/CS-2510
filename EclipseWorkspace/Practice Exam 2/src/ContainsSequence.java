import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

class Exam {
  boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence) {
    int sourceIndex = 0;
    int sequenceIndex = 0;
    while( sourceIndex < source.size() && sequenceIndex < sequence.size()) {
      if (source.get(sourceIndex) == (sequence.get(sequenceIndex))) {
        sequenceIndex++;
      }
      
      else {
        sequenceIndex = 0;
      }

      sourceIndex++;
    }
    return sequence.size() == sequenceIndex;
  }
}


class ExamplesSequence {
  Exam exam = new Exam();
  ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
  ArrayList<Integer> ints1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
  ArrayList<Integer> ints2 = new ArrayList<Integer>(Arrays.asList(1, 3));
  ArrayList<Integer> ints3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 6));
  ArrayList<Integer> mt = new ArrayList<Integer>(Arrays.asList());


  void test(Tester t) {
    t.checkExpect(exam.containsSequence(ints1, ints), true);
    t.checkExpect(exam.containsSequence(ints, ints1), false);
    t.checkExpect(exam.containsSequence(ints1, ints2), false);
    t.checkExpect(exam.containsSequence(ints1, ints3), false);
    t.checkExpect(exam.containsSequence(mt, mt), true);
    t.checkExpect(exam.containsSequence(ints, mt), true);
    t.checkExpect(exam.containsSequence(mt, ints), false);
    t.checkExpect(exam.containsSequence(ints, ints), true);
  }
}
