import java.util.Stack;

// 一个单向链表
// 要求相邻节点的值如果重复，则进行消除

// 1.输入 abbcb，返回acb
// 输入abba，返回null

// 2.abbcb，返回acb
// 输入abba，返回aba

public class List_1 {
    // 1
    public static ListNode process(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        Stack<ListNode> stack = new Stack<>();

        ListNode head = root;
        stack.push(head);
        head = head.next;

        while(head != null) {
            if (head.value == stack.peek().value) {
                stack.pop();
                head = head.next;
            } else {
                stack.push(head);
                head = head.next;
            }
        }

        ListNode next = null;

        while (!stack.isEmpty()) {
            head = stack.pop();
            head.next = next;
            next = head;
        }
        return next;

    }

    // 2
    public static ListNode process2(ListNode root) {
        ListNode head = root;
        while (head != null && head.next != null) {
            if (head.value == head.next.value) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
//        head = null;

        ListNode result = process2(head);
        while (result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }
}
