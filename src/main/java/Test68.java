import com.sun.org.apache.regexp.internal.RE;

import java.util.*;
public class Test68
{
    public static boolean getPath(BinaryTree root,BinaryTree target,Deque<BinaryTree> queue)
    {
        if (root==null||target==null)
        {
            return false;
        }
        queue.addLast(root);
        if (root.data==target.data)
        {
            return true;
        }
        boolean flag=getPath(root.left,target,queue);
        if (!flag)
        {
            flag=getPath(root.right,target,queue);
        }
        if (!flag)
        {
            queue.removeLast();
        }
        return flag;
    }
    public static BinaryTree findCommon(BinaryTree root,BinaryTree target1,BinaryTree target2)
    {
        Deque<BinaryTree> queue1=new ArrayDeque<>();
        Deque<BinaryTree> queue2=new ArrayDeque<>();
        getPath(root,target1,queue1);
        getPath(root,target2,queue2);
        BinaryTree result=null;
        while ((!queue1.isEmpty())&&(!queue2.isEmpty()))
        {
            if (queue1.peekFirst().data==queue2.peekFirst().data)
            {
                result=queue1.removeFirst();
                queue2.removeFirst();
            }
            else
            {
                queue1.removeFirst();
                queue2.removeFirst();
            }
        }
        return result;
    }

    public static BinaryTree findCommonNode2(BinaryTree root,BinaryTree target1,BinaryTree target2) {
        Result result = helper(root,target1,target2);
        System.out.println(result.findAll);
        if (result.findAll) {
            return result.node;
        }
        return null;
    }

    private static class Result {
        boolean findAll;
        boolean findOne;
        BinaryTree node;
        public Result(boolean findAll,boolean findOne,BinaryTree node) {
            this.findAll = findAll;
            this.findOne = findOne;
            this.node = node;
        }
    }

    public static Result helper(BinaryTree root,BinaryTree target1,BinaryTree target2) {
        //
        // 返回是否全部发现，是否发现一个节点，发现的节点
        if (root == null) {
            return new Result(false,false,null);
        }
        Result leftResult = helper(root.left,target1,target2);
        Result rightResult = helper(root.right,target1,target2);
        if (leftResult.findAll) {
            return leftResult;
        } else if (rightResult.findAll) {
            return rightResult;
        } else if (leftResult.findOne && rightResult.findOne) {
            return new Result(true,true,root);
        } else if (leftResult.findOne) {
            return leftResult;
        } else if (rightResult.findOne) {
            return rightResult;
        } else if (root.data == target1.data || root.data == target2.data){
            return new Result(false,true,root);
        } else {
            return new Result(false,false,null);
        }
    }

    public static void main(String[] args)
    {
        BinaryTree node1=new BinaryTree(8);
        BinaryTree left=new BinaryTree(10);
        BinaryTree right=new BinaryTree(11);
        node1.left=left;
        node1.right=right;
        BinaryTree temp=new BinaryTree(9);
        left.left=temp;
        temp=new BinaryTree(2);
        left.right=temp;
        left=new BinaryTree(4);
        right=new BinaryTree(7);
        temp.left=left;
        temp.right=right;
//        System.out.println(findCommon(
//                node1,new BinaryTree(9),new BinaryTree(110)).data);
//        System.out.println(findCommonNode2(node1,new BinaryTree(9),new BinaryTree(111)).data);
    }
}
