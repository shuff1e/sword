public class RankNode
{
    public int left_size = 0;
    public RankNode left,right;
    public int data;

    public RankNode(int data) {
        this.data = data;
    }

    public void insert(int d) {
        if (d<=data) {
            if (left == null) {
                left = new RankNode(d);
            } else {
                left.insert(d);
            }
            left_size ++;
        } else {
            if (right == null) {
                right = new RankNode(d);
            } else {
                right.insert(d);
            }
        }
    }

    public int getRank(int d) {
        if (d == data) {
            return left_size;
        } else if (d <data) {
            if (left == null) {
                return 0;
            } else {
                return left.getRank(d);
            }
        } else {
            int right_rank = right == null?0:right.getRank(d);
            return left_size + 1 + right_rank;
        }
    }
}