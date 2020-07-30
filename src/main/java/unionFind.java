/*
 function MakeSet(x)
     x.parent := x

 function Find(x)
     if x.parent == x
        return x
     else
        return Find(x.parent)

 function Union(x, y)
     xRoot := Find(x)
     yRoot := Find(y)
     xRoot.parent := yRoot
 */

/*
按秩合并

 function MakeSet(x)
     x.parent := x
     x.rank   := 0

 function Union(x, y)
     xRoot := Find(x)
     yRoot := Find(y)
     if xRoot == yRoot
         return

     // x和y不在同一个集合，合并它们。
     if xRoot.rank < yRoot.rank
         xRoot.parent := yRoot
     else if xRoot.rank > yRoot.rank
         yRoot.parent := xRoot
     else
         yRoot.parent := xRoot
         xRoot.rank := xRoot.rank + 1

 */


/*

 function Find(x)
     if x.parent != x
        x.parent := Find(x.parent)
     return x.parent


 */

/*
每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
在结果列表中，选择字典序最小的名字作为真实名字。

输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
输出：["John(27)","Chris(36)"]

 */

import java.util.HashMap;
import java.util.Map;

class unionFind {
    public static String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> unionMap = new HashMap<>();     //并查集， key(子孙)->value(祖宗)
        for (String name : names) {     //统计频率
            int idx1 = name.indexOf('(');
            int idx2 = name.indexOf(')');
            int frequency = Integer.valueOf(name.substring(idx1 + 1, idx2));
            map.put(name.substring(0, idx1), frequency);
        }
        for (String pair : synonyms) {  //union同义词
            int idx = pair.indexOf(',');
            String name1 = pair.substring(1, idx);
            String name2 = pair.substring(idx + 1, pair.length() - 1);
            while (unionMap.containsKey(name1)) {   //找name1祖宗
                name1 = unionMap.get(name1);
            }
            while (unionMap.containsKey(name2)) {   //找name2祖宗
                name2 = unionMap.get(name2);
            }
            if(!name1.equals(name2)){   //祖宗不同，要合并
                int frequency = map.getOrDefault(name1, 0) + map.getOrDefault(name2, 0);    //出现次数是两者之和
                String trulyName = name1.compareTo(name2) < 0 ? name1 : name2;
                String nickName = name1.compareTo(name2) < 0 ? name2 : name1;
                unionMap.put(nickName, trulyName);      //小名作为大名的分支，即大名是小名的祖宗
                map.remove(nickName);       //更新一下数据
                map.put(trulyName, frequency);
            }
        }
        String[] res = new String[map.size()];
        int index = 0;
        for (String name : map.keySet()) {
            StringBuilder sb = new StringBuilder(name);
            sb.append('(');
            sb.append(map.get(name));
            sb.append(')');
            res[index++] = sb.toString();
        }
        return res;
    }
    public static void main(String[] args) {
        String[] names = new String[]{"John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"};
        String[] synonyms = new String[]{"(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"};
        String[] res = trulyMostPopular(names,synonyms);
        for (String str : res) {
            System.out.println(str);
        }
    }
}

