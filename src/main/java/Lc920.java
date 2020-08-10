import java.util.Arrays;

// 你的音乐播放器里有 N 首不同的歌，在旅途中，你的旅伴想要听 L 首歌（不一定不同，即，允许歌曲重复）。请你为她按如下规则创建一个播放列表：
//
//每首歌至少播放一次。
//一首歌只有在其他 K 首歌播放完之后才能再次播放。
//返回可以满足要求的播放列表的数量。由于答案可能非常大，请返回它模 10^9 + 7 的结果。
//
//
//输入：N = 3, L = 3, K = 1
//输出：6
//解释：有 6 种可能的播放列表。[1, 2, 3]，[1, 3, 2]，[2, 1, 3]，[2, 3, 1]，[3, 1, 2]，[3, 2, 1].
//
//
//输入：N = 2, L = 3, K = 0
//输出：6
//解释：有 6 种可能的播放列表。[1, 1, 2]，[1, 2, 1]，[2, 1, 1]，[2, 2, 1]，[2, 1, 2]，[1, 2, 2]
//
//
//输入：N = 2, L = 3, K = 1
//输出：2
//解释：有 2 种可能的播放列表。[1, 2, 1]，[2, 1, 2]

// 令 dp[i][j] 为播放列表长度为 i 包含恰好 j 首不同歌曲的数量。我们需要计算 dp[L][N]，看上去可以通过 dp 来解决。
//
// 考虑 dp[i][j]。最后一首歌，我们可以播放没有播放过的歌也可以是播放过的。
// 如果未播放过的，那么就是 dp[i-1][j-1] * (N-(j-1) 种选择方法。
// 如果不是，那么就是选择之前的一首歌，dp[i-1][j] * max(j-K, 0)（j 首歌，最近的 K 首不可以播放）。

public class Lc920 {
    public static int numMusicPlaylists(int N, int L, int K) {
        int MOD = 1_000_000_007;

        long[][] dp = new long[L+1][N+1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; ++i)
            for (int j = 1; j <= N; ++j) {
                dp[i][j] += dp[i-1][j-1] * (N-j+1);
                dp[i][j] += dp[i-1][j] * Math.max(j-K, 0);
                dp[i][j] %= MOD;
            }

        for (long[] ele : dp) {
            System.out.println(Arrays.toString(ele));
        }

        return (int) dp[L][N];
    }

    public static void main(String[] args) {
        System.out.println(numMusicPlaylists(3,3,1));
    }
}
