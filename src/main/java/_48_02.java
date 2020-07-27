// 最长不含重复字符的子字符串
// 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子
// 字符串的长度。假设字符串中只包含从'a'到'z'的字符。

// 例如字符串"arabcacfr"中，最长的不含重复字符的子字符串是"acfr"，长度为4

import java.util.HashMap;
import java.util.Map;

// A:给定arr
// 假设以arr[i]为结尾的，不包含重复字符的子串长度为f(i)
// f(i) = f(i-1) + 1 如果f(i-1)结尾的最长子串不包含arr[i]
// f(i) = i - index(arr[i]) 如果f(i-1)结尾的最长子串包含arr[i]

// 使用非递归的方法
public class _48_02 {

    static int[] position = new int[26];
    static int curLength = 0;
    static int maxLength = 0;

    public static int longestSubstringWithoutDuplication(String str) {
        for (int i = 0;i<position.length;i++) {
            position[i] = -1;
        }
        for (int i = 0;i<str.length();i++) {
            int index = i - 1 - curLength;
            if (position[str.charAt(i) - 'a'] > index) {
                curLength = i - position[str.charAt(i) - 'a'];
            } else {
                curLength = curLength + 1;
            }
            position[str.charAt(i) - 'a'] = i;

            maxLength = Math.max(maxLength,curLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String str = "arabcacfrghjh";
        int length = longestSubstringWithoutDuplication(str);
        System.out.println(length);
    }
}
