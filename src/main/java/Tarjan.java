// 求解有向图强连通分量的线性时间算法

// 如果两个顶点可以相互通达，则称两个顶点强连通
// 如果有向图G的每两个顶点都强连通，称G是一个强连通图
// 强连通子图，称为强连通分量

/*
u 表示当前被搜索的节点
DFN[u] 表示节点u被搜索的次序
low[u] 表示节点i能回溯到的最早位于栈中的节点的搜索次序，即DFN[u]的值

1. 深度优先搜索
2.1 节点v不在栈中，继续对v深度优先搜索
2.2 节点v在栈中，当前节点u能回溯到的最早节点，是low[u]和DFN[v]中的最小值
3. 回溯过程中，v能达到的节点，u肯定也能达到，所以low[u]是low[u]和low[v]中最小的
4.
在该连通图中有且仅有一个节点u的DFN值和low值相等
假设有两个节点的DFN值和low值相等，由于这两个节点的DFN值一定不相同(DFN值的定义就是深度遍历时被访问的先后次序)，
所以两个的low值也绝对不相等。
由于位于同一个连通图中，所以两个节点必定相互可达，那么两者的low值一定会被另外一个所影响（要看谁的low值更小），
所以不可能存在两对DFN值和low值相等的节点。
5. 所以我们在回溯的过程中就能够通过判断节点的low值和DFN值是否相等来判断是否已经找到一个子连通图。
由于该连通图中的DFN值和low值相等的节点是该连通图中第一个被访问到的节点，
又根据栈的特性，则该节点在最里面。所以能够通过不停的弹栈，
直到弹出该DFN值和low值相同的节点来弹出该连通图中所有的节点。

tarjan(u){
　　DFN[u]=Low[u]=++Index // 为节点u设定次序编号和Low初值
　　Stack.push(u)   // 将节点u压入栈中
　　for each (u, v) in E // 枚举每一条边
　　　　if (v is not visted) // 如果节点v未被访问过
　　　　　　　　tarjan(v) // 继续向下找
　　　　　　　　Low[u] = min(Low[u], Low[v])
　　　　else if (v in S) // 如果节点u还在栈内
　　　　　　　　Low[u] = min(Low[u], DFN[v])
　　if (DFN[u] == Low[u]) // 如果节点u是强连通分量的根
　　repeat v = S.pop  // 将v退栈，为该强连通分量中一个顶点
　　print v
　　until (u== v)
}
*/


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tarjan {
    static int size = 8;
    // 访问的char数组
    static char[] arr = new char[size];
    // 临接矩阵
    static int[][] matrix;
    //
    static int index;
    // 标记节点是否已经访问过
    static boolean[] flags = new boolean[arr.length];
    // 用于push访问的index
    static Stack<Integer> stack = new Stack<>();
    // 记录访问的次序
    static int[] dfn = new int[arr.length];
    // 记录节点能回溯到的最早的节点
    static int[] low = new int[arr.length];
    // 结果
    static List<LinkedList<Character>> result = new LinkedList<>();

    // i表示访问到哪个节点
    public static void tarjan(int i) {
        dfn[i] = index;
        low[i] = index;
        stack.push(i);
        flags[i] = true;
        index ++;
        for (int j = 0;j<arr.length;j++) {
            if (matrix[i][j] != 0) {
                if (!flags[j]) {
                    tarjan(j);
                    low[i] = Math.min(low[i],low[j]);
                } else if (stack.contains(j)) {
                    low[i] = Math.min(low[i],dfn[j]);
                }
            }
        }

        if (dfn[i] == low[i]) {
            LinkedList<Character> list = new LinkedList<>();
            int temp;
            do {
                temp = stack.pop();
                list.add(arr[temp]);
            } while(i != temp);
            result.add(list);
        }
    }

    public static void main(String[] args) {
        arr = new char[]{'1','2','3','4','5','6','7','8'};
        matrix = new int[][]{
                {0,1,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,0,1,1,0,0,0},
                {1,0,0,0,0,1,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,0,0,0,0},
        };
        tarjan(0);
        for (LinkedList<Character> list : result) {
            for (char c : list) {
                System.out.println(c);
            }
            System.out.println("---");
        }
    }
}
