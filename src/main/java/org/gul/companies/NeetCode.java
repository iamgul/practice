package org.gul.companies;

import java.util.*;

public class NeetCode {


    public static void main(String[] args) {
        //1. Contains Duplicate [lc-217] - Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
        containsDuplicate();
        //2. Valid Anagram  [lc-242] Given two strings s and t, return true if t is an anagram of s, and false otherwise.
        isValidAnagram();
        //3. Two Sum [lc-1] Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.You may assume that each input would have exactly one solution, and you may not use the same element twice.You can return the answer in any order.
        twoSum();
        //4. Group Anagrams [lc-49] Given an array of strings strs, group the anagrams together. You can return the answer in any order.
        groupAnagram();



    }

    private static void groupAnagram() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> strList = groupAnagrams(strs);
        System.out.println(strList);
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            strs[i] = String.valueOf(charArray);
        }
        Arrays.sort(strs);
        List<List<String>> strList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            List<String> listi = new ArrayList<>();
            listi.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (strs[i].equals(strs[j])) {
                    listi.add(strs[j]);
                    i++;
                }
            }
            strList.add(listi);
        }
        return strList;
    }

    private static void twoSum() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        int target = 11;
        int[] indices_aap1 = twoSum_approach1_bf(nums, target); // Bad as tc is O(n^2)
        //approach_2 --> Sort the elements and get the 1st element, subtract it from target , the remaining number will either be present in either left or right side, since its sorted, we can use BinaryTree[bt] to compare with only half parts
        int[] indices_app3 = twoSum_approach3(nums, target);//approach_3 --> Using HashMap DataStructure


    }

    private static int[] twoSum_approach3(int[] nums, int target) {
        //Approach -> to find sum we need to add two num, similarly if we know the target, we can subtract one num and get the other, same technique is used here,
        //1st we subtract each number from target and check if reminder is in HashMap. if reminder is present in HashMap, then we found two numbers so we will return the index
        //If it's not present then we will add that number as key and its value as its index, and when we find, same index(value) will be returned.

        //tc is 0(n), tc of HashMap is 0(1)
        Map<Integer, Integer> mapOfIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int reminder = target - nums[i];
            if(mapOfIndices.containsKey(reminder)){
                return new int[]{i, mapOfIndices.get(reminder)};
            }else {
                mapOfIndices.put(nums[i],i);
            }
        }
        return new int[]{};
    }

    private static int[] twoSum_approach1_bf(int[] nums, int target) {
        int[] returnIndices = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    returnIndices[0] = i;
                    returnIndices[1] = j;
                    break;
                }
            }
        }
        return returnIndices;
    }

    private static void isValidAnagram() {
        String s1 = "peek";
        String s2 = "keep";
        boolean isAnagram =  isValidAnagram(s1, s2);
        System.out.println("Is valid Anagram ---> "+isAnagram);
    }

    private static boolean isValidAnagram(String s, String t) {
        boolean isValidAnagram = true;
        if (s.length() != t.length()) return false;

        char[] charArr1 = s.toCharArray();
        char[] charArr2 = t.toCharArray();

        Arrays.sort(charArr1);
        Arrays.sort(charArr2);

        for (int i = 0; i < charArr1.length; i++) {
            if (charArr1[i] != charArr2[i]) {
                isValidAnagram = false;
                break;
            }
        }
        return isValidAnagram;
    }

    private static void containsDuplicate() {
        /*This can be checked in 3 ways.
        1. bruteforce[B.F.] -> compare each element with all other --> Worst approach as the timeComplexity(tc) will be O(n^2)
        2. Sorting -> Sort the nums and check only one next element since they are sorted ->tc -> O(nlogn) -> tc of sorting integers is O(nlogn)
        3.HashSet -> Optimal approach with tc O(n) since we are checking n times
        */
        int[] nums = new int[]{1, 2, 3, 4};
        boolean containsDuplicate = isDuplicate(nums);
        System.out.println("Contains Duplicate ---> " + containsDuplicate);
    }

    private static boolean isDuplicate(int[] nums) {
        boolean isDuplicate = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

}
