//import java.util.*;
//
//import tester.Tester;
//
//class ArrayUtils {
//
//  int findValue(ArrayList<ArrayList<Integer>> matrix) {
//
//    // finds the largest numbers in each row
//    ArrayList<Integer> largestNumbersInEachRow = new ArrayList<Integer>();
//
//    for (int row = 0; row < matrix.size(); row++) {
//      int sizeOfRow = matrix.get(row).size();
//
//      int maxSoFar = matrix.get(row).get(0);
//
//      for (int col = 1; col < sizeOfRow; col++) {
//        int curNum = matrix.get(row).get(col);
//
//        if (curNum > maxSoFar) {
//          maxSoFar = curNum;
//        }
//      }
//      largestNumbersInEachRow.add(maxSoFar);
//    }
//
//    int sizeOfCol = matrix.get(0).size();
//    int numRows = matrix.size();
//    int 
//    for (int col = 0; col < sizeOfCol; col++) {
//
//      int minSoFar = matrix.get(0).get(col);
//
//      for (int row = 1; row < numRows; row++) {
//        int curNum = matrix.get(row).get(col);
//
//        if (curNum < minSoFar) {
//          minSoFar = curNum;
//        }
//      }
//
//      if(largestNumbersInEachRow.contains(minSoFar)) {
//        return minSoFar;
//      }
//    }
//
//    return -1;
//  }
//}
//
//
//class ExamplesArrays {
//  void testFindValue(Tester t) {
//    ArrayUtils u = new ArrayUtils();
//
//    ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(2, 2, 3));
//    ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(1, 2, 2));
//    ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(3, 4, 1));
//
//    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>(Arrays.asList(row1, row2, row3));
//    t.checkExpect(u.findValue(matrix), 2);
//
//  }
//}