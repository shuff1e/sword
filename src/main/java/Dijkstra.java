public class Dijkstra {
    // adjMatrix为临接矩阵，-1表示不通
    public int[] getShortestPaths(int[][] adjMatrix) {
        // 结果
        int[] result = new int[adjMatrix.length];

        // 1.标记节点是否访问过，节点分为两类，一类是访问过的S，一类是未访问的U
        boolean[] used = new boolean[adjMatrix.length];

        // 2.初始化
        used[0] = true;
        for (int i = 0;i<adjMatrix.length;i++) {
            result[i] = adjMatrix[0][i];
        }

        //
        for (int i = 0;i<adjMatrix.length;i++) {
            // 3.首先找到0节点到未访问集合U中的最短路径
            int min = Integer.MAX_VALUE;
            int k = 0;
            for (int j = 1;j<adjMatrix.length;j++) {
                if (!used[j] && adjMatrix[i][j] != -1) {
                    if (result[j] < min) {
                        min = result[j];
                        k = j;
                    }
                }
            }
            // 4.将k节点加入已访问集合S
            used[k] = true;

            // 5.对于所有K能达到的节点，更新其最短路径的值
            for (int j = 1;j<adjMatrix.length;j++) {
                // 对于未访问集合中的节点
                if (!used[j]) {
                    // 更新
                    if (adjMatrix[k][j]!= -1 &&(result[j] > min + adjMatrix[k][j] || result[j] == -1)) {
                        result[j] = adjMatrix[k][j] + min;
                    }
                }
            }

        }
        return result;
    }
    public static void main(String[] args) {
        Dijkstra test = new Dijkstra();
        int[][] adjMatrix = {{0,6,3,-1,-1,-1},
                {6,0,2,5,-1,-1},
                {3,2,0,3,4,-1},
                {-1,5,3,0,2,3},
                {-1,-1,4,2,0,5},
                {-1,-1,-1,3,5,0}};
        int[] result = test.getShortestPaths(adjMatrix);
        System.out.println("顶点0到图中所有顶点之间的最短距离为：");
        for(int i = 0;i < result.length;i++)
            System.out.print(result[i]+" ");
    }
}
