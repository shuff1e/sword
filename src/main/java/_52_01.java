// Q：两个链表的第一个公共节点

// A：使用2个栈，存储链表节点

import java.util.ArrayDeque;
import java.util.Deque;

public class _52_01 {

    public static ListNode getFirstCommonNode(ListNode node1,ListNode node2) {
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();

        ListNode temp1 = node1;
        ListNode temp2 = node2;

        ListNode result = null;


        while (temp1 != null) {
            stack1.push(temp1);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            stack2.push(temp2);
            temp2 = temp2.next;
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            temp1 = stack1.pop();
            temp2 = stack2.pop();
            if (temp1 == temp2) {
                result = temp1;
            } else {
                break;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = head1;

        ListNode result = getFirstCommonNode(head1,head2);
        System.out.println(result);


    }
}

class ListNode {
    int value;
    ListNode next;
    public ListNode(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
