import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

class ExamMaxDiff {

  public int maxDiff(ArrayList<Integer> ints) {
    int min = ints.get(0);
    for (int num : ints) {
      if (num < min) {
        min = num;
      }
    }
    int minIndex = ints.indexOf(min);
    int max = min;
    while (minIndex < ints.size()) {
      int newNum = ints.get(minIndex);
      if (newNum > max) {
        max = newNum;
      }
      minIndex ++;
    }
    return max - min;
  }
  
  public int maxDeffSecond(ArrayList<Integer> ints) {
    int minSoFar = ints.get(0);
    int maxDifference = 0;
    
    for (int num : ints) {
      int currDeff = num - minSoFar;
      if (num < minSoFar) {
        minSoFar = num;
      }
      
      if (currDeff > maxDifference) {
        maxDifference = currDeff;
      }
    }
    
    
    return maxDifference;
  }

}

class ExamplesMaxDiff {
  ExamMaxDiff exam = new ExamMaxDiff();
  void testMax(Tester t) {
    t.checkExpect(exam.maxDiff(new ArrayList<Integer>(Arrays.asList(11, 2, 6, 4, 10, 7))), 8);
    t.checkExpect(exam.maxDiff(new ArrayList<Integer>(Arrays.asList(11, 2, 6, 4, 10, 7, 11))), 9);
    t.checkExpect(exam.maxDiff(new ArrayList<Integer>(Arrays.asList(9, 8, 7, 6, 5))), 0);

  }
}