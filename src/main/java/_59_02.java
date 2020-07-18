import java.util.ArrayDeque;
import java.util.Deque;

public class _59_02<T extends Comparable<T>> {

    private class Data {
        protected T number;
        protected int index;
        protected Data(T n,int i) {
            number = n;
            index = i;
        }

        @Override
        public String toString() {
            return index + ":" + number;
        }
    }

    private int curIndex;
    private Deque<Data> major = new ArrayDeque<>();
    private Deque<Data> maxHelper = new ArrayDeque<>();
    private Deque<Data> minHelper = new ArrayDeque<>();

    public void addLast(T number) {
        while(!maxHelper.isEmpty() && number.compareTo(maxHelper.peek().number) >= 0) {
            maxHelper.removeLast();
        }
        while(!minHelper.isEmpty() && number.compareTo(minHelper.peek().number) < 0) {
            minHelper.removeLast();
        }
        Data data = new Data(number,curIndex);
        major.add(data);
        maxHelper.add(data);
        minHelper.add(data);
        curIndex ++;
    }

    public Data removeFirst() {
        if (major.isEmpty()) {
            throw new RuntimeException("Empty");
        }
        int index = major.peek().index;
        if (maxHelper.peek().index == index) {
            maxHelper.removeFirst();
        }
        if (minHelper.peek().index == index) {
            minHelper.removeFirst();
        }
        return major.removeFirst();
    }

    public T getMax() {
        if (maxHelper.isEmpty()) {
            throw new RuntimeException("Empty");
        }
        return maxHelper.peek().number;
    }

    public T getMin() {
        if (minHelper.isEmpty()) {
            throw new RuntimeException("Empty");
        }
        return minHelper.peek().number;
    }

    public static void main(String[] args) {
        _59_02<Integer> t = new _59_02<>();
        int[] arr = new int[]{2,6,4,2,4,2,5,1};
        for (int ele : arr) {
            t.addLast(ele);
        }
        System.out.println("max->"+t.getMax());
        System.out.println("min->"+t.getMin());
        System.out.println(t.removeFirst());
        t.addLast(10);
        System.out.println("max->"+t.getMax());
        System.out.println("min->"+t.getMin());
    }

}
