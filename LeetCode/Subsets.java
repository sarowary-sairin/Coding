/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
import LCLibrary.*;
import java.util.*;

class Solution {
    public static void swap(int[] S, int a, int b) {
        int temp = S[a];
        S[a] = S[b];
        S[b] = temp;
    }

    public static int partition(int[] S, int start, int end, int pivot) {
        int left = start;
        int right = end;
        while(left <= right) {
            while(S[left] < pivot) {
                left++;
            }
            while(S[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(S, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static void quickSort(int[] S, int start, int end) {
        if(start >= end) {
            return;
        }
        int pivot = S[start + (end - start) / 2];
        int index = partition(S, start, end, pivot);
        quickSort(S, start, index - 1);
        quickSort(S, index, end);
    }

    public void quickSort(int[] S) {
        quickSort(S, 0, S.length - 1);
    }

    // time complexity : O(2^N)
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
        if(S == null || S.length == 0) {
            return subsets;
        }
        //Arrays.sort(S);
        quickSort(S);
        ArrayList<Integer> empty = new ArrayList<Integer>();
        subsets.add(empty);
        for(int i = 0; i < S.length; i++) {
            int size = subsets.size();
            for(int j = 0; j < size; j++) {
                ArrayList<Integer> subset = new ArrayList<Integer>(subsets.get(j));
                subset.add(S[i]);
                subsets.add(subset);
            }
        }
        return subsets;
    }
}

/*
    Second Round
*/
class Solution2 {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(S == null || S.length == 0) return result;
        ArrayList<Integer> first = new ArrayList<Integer>();
        result.add(first);
        Arrays.sort(S);
        for(int i = 0; i < S.length; i++) {
            int size = result.size();
            for(int j = 0; j < size; j++) {
                ArrayList<Integer> list = new ArrayList<Integer>(result.get(j));
                list.add(S[i]);
                result.add(list);
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] S = {4, 1, 0};
        Output.printLevelLists(solution.subsets(S));
    }
}