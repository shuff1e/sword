// 面试题48：最长不含重复字符的子字符串
// 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子
// 字符串的长度。假设字符串中只包含从'a'到'z'的字符。

// 例如字符串"arabcacfr"中，最长的不含重复字符的子字符串是"acfr"，长度为4

// A:给定arr
// 假设以arr[i]为结尾的，不包含重复字符的子串长度为f(i)
// f(i) = f(i-1) + 1 如果f(i-1)结尾的最长子串不包含arr[i]
// f(i) = i - index(arr[i]) 如果f(i-1)结尾的最长子串包含arr[i]

import java.util.HashMap;
import java.util.Map;

// 递归函数需要返回以arr[i-1]结尾的长度，以及是否包含arr[i]，以及arr[i]的index
public class _48_01 {

    static Map<Character,Integer> map = new HashMap<>();
    static int maxLength = 0;

    public static int Process(String str,int index) {
        if (index == 0) {
            map.put(str.charAt(index),index);
            return 1;
        }

        int length = Process(str,index-1);

        // 返回给上一层的结果
        int resultLength = 0;

        char c = str.charAt(index);

        if (map.containsKey(c)) {
            int parentIndex = map.get(c);
            // 如果当前的char在子字符串中
            if (parentIndex > index-1-length) {
                resultLength = index - parentIndex;
            } else {
                resultLength = length + 1;
            }

        } else {
            resultLength = length + 1;
        }
        map.put(c,index);
        maxLength = Math.max(maxLength,resultLength);
        return resultLength;
    }

    public static void main(String[] args) {
        String str = "arabcacfr";
        int length = Process(str,str.length()-1);
        System.out.println(maxLength);
    }
}
