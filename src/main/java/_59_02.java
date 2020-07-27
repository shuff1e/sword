// 队列的最大值
// 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，
// 如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个
// 滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}，


// A:实现一个队列，能在O1时间内，得到队列的最大值和最小值

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

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
        while(!maxHelper.isEmpty() && number.compareTo(maxHelper.peekLast().number) >= 0) {
            maxHelper.removeLast();
        }
        while(!minHelper.isEmpty() && number.compareTo(minHelper.peekLast().number) < 0) {
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

    public void print() {
        this.maxHelper.forEach(ele -> System.out.println(ele.number));
        System.out.println("---");
    }

    public static void main(String[] args) {
        _59_02<Integer> t = new _59_02<>();
//        int[] arr = new int[]{2,6,4,2,4,2,5,1};
        int[] arr = new int[]{2,3,4,2,6,2,5,1};
        for (int i = 0;i<3;i++) {
            t.addLast(arr[i]);
        }

        for (int i = 3;i<arr.length;i++) {
            t.removeFirst();
            t.addLast(arr[i]);
//            t.print();
            System.out.println(t.getMax());
        }
//        System.out.println("max->"+t.getMax());
//        System.out.println("min->"+t.getMin());
//        System.out.println(t.removeFirst());
//        t.addLast(10);
//        System.out.println("max->"+t.getMax());
//        System.out.println("min->"+t.getMin());
    }

}
