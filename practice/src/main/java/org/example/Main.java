package org.example;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    int[][] inputs = {{2, 7, 11, 15}, {3, 2, 4}, {3, 3}};
    int[] targets = {9, 6, 6};
    int[][] outputs = {{0, 1}, {1, 2}, {0, 1}};

    for (int i = 0; i < inputs.length; i++) {
      System.out.printf("Inputs: %s, Target: %s, Expected Result: %s\n", Arrays.toString(inputs[i]), targets[i], Arrays.toString(outputs[i]));
      System.out.printf("Given result: %s\n", Arrays.toString(getTwoSum(inputs[i], targets[i])));
    }

  }

  public static int[] getTwoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    int i = 0;

    for (int num : nums) {
      if (map.containsKey(target - num)) {
        return new int[]{i, map.get(target - num)};
      }
      if (!map.containsKey(num)) {
        map.put(num, i);
      }
      i++;
    }


    return new int[]{};
  }
}
