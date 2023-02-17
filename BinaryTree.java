import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Node{
    int val;
    Node left;
    Node right;
    public Node(int val) {
        this.val = val;
    }
}
public class BinaryTree { 

   /* 
    `2 
   3   4
 6    12 31
  20
    */  
    public static void main(String[] args) {
        Node root = new Node(2);
        Node aNode = new Node(3);
        Node bNode = new Node(4);
        Node cNode = new Node(6);
        Node dNode = new Node(20);
        Node eNode = new Node(12);
        Node fNode = new Node(31);
        root.left = aNode;
        root.right = bNode;
        aNode.left = cNode;
        cNode.right = dNode;
        bNode.left = eNode;
        bNode.right = fNode;

        System.out.println(dfs(root));
        System.out.println(dfsIterative(root));

        System.out.println(bfs(root));

        System.out.println(containsValueDFS(root,20)); //true
        System.out.println(containsValueDFS(root,40)); //false
        System.out.println(containsValueBFS(root,20)); //true
        System.out.println(containsValueBFS(root,40)); //false
        System.out.println(containsValueBFS(root,31)); //true
        System.out.println(containsValueBFS(root,6));//true

        System.out.println(treeSum(root)); //78
        System.out.println(treeMinBFS(root)); // 2
        System.out.println(treeMinDFS(root)); // 2

        System.out.println(treeRootToLeafSumMax(root)); // 37

        System.out.println(traverse(root, "IN")); //[6, 20, 3, 2, 12, 4, 31]
        System.out.println(traverse(root, "PRE")); // [2, 3, 6, 20, 4, 12, 31]
        System.out.println(traverse(root, "POST")); //[20, 6, 3, 12, 31, 4, 2]
        System.out.println(levelOrder(root)); //[[2], [3, 4], [6, 12, 31], [20]]
        System.out.println(zigzagLevelOrder(root)); //[[2], [4, 3], [31, 12, 6], [20]]

        System.out.println(rightSideView(root)); //[2, 4, 31, 20]

    }
 
    private static List<Integer> traverse(Node root, String type) {
        List<Integer> result = new ArrayList<>();
        if (type.equals("IN"))
            inOrderDFS(root, result);

        if (type.equals("PRE"))
            preOrderDFS(root, result);
        if (type.equals("POST"))
            postOrderDFS(root, result);

        return result;
    }

    private static void inOrderDFS(Node root, List<Integer> path) {
        if(root == null) return;
        
        inOrderDFS(root.left, path);
        path.add(root.val);
        inOrderDFS(root.right, path);
    }

    private static void preOrderDFS(Node root, List<Integer> path) {
        if(root == null) return;
       
        path.add(root.val);
        preOrderDFS(root.left, path);
        preOrderDFS(root.right, path);
    }

    private static void postOrderDFS(Node root, List<Integer> path) {
        if(root == null) return;
        
        postOrderDFS(root.left, path);
        postOrderDFS(root.right, path);
        path.add(root.val);
    }

    private static  List<List<Integer>> levelOrder(Node root) {
        return bfs(root, false);
    }

    private static  List<List<Integer>> zigzagLevelOrder(Node root) {
        return bfs(root, true);
    }

    private static List<Integer> rightSideView(Node root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node right = null;
            while (size > 0) {
                Node current = q.poll();
                right = current;
                if (current.left != null)
                    q.offer(current.left);
                if (current.right != null)
                    q.offer(current.right);
                size--;
            }
            result.add(right.val);

        }
        return result;
    }

    private static List<List<Integer>> bfs(Node root, boolean zigzag) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null)
            return result;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean inOrder = true;
        

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            while (size > 0) {
                Node current = q.poll();
                if(zigzag && !inOrder) {
                    level.add(0,current.val);

                } else {
                    level.add(current.val);
                }
                if (current.left != null)
                    q.offer(current.left);
                if (current.right != null)
                    q.offer(current.right);
                size--;
            }
            inOrder ^= inOrder;
            result.add(level);

        }
        return result;
    }


    private static int treeRootToLeafSumMax(Node root) {
        if(root == null) return 0;
        int leftMax = treeRootToLeafSumMax(root.left);
        int rightMax = treeRootToLeafSumMax(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }
    private static int treeMinDFS(Node root) {
        if(root == null) return Integer.MAX_VALUE;
        int childMin = Math.min(treeMinDFS(root.left),treeMinDFS(root.right));

        return Math.min(root.val, childMin);
    }

    private static int treeMinBFS(Node root) {
        int min = Integer.MAX_VALUE;

        if(root == null) return min;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            Node current = q.poll();
            min = Math.min(current.val, min);
            if(current.left != null)  q.offer(current.left);
            if(current.right != null) q.offer(current.right);
        }
        return min;
    }
    private static int treeSum(Node root) {
        if(root == null) return 0;
        return root.val + treeSum(root.left) + treeSum(root.right);

    } 


    private static boolean containsValueBFS(Node root, int target) {
        if(root == null) return false;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.val == target) return true;
            if(current.left != null)  q.offer(current.left);

            if(current.right != null) q.offer(current.right);

        }
        return false;
    }
    private static boolean containsValueDFS(Node root, int target) {
        if(root == null) return false;
        if(root.val == target) return true;
        return containsValueDFS(root.left, target) || containsValueDFS(root.right, target);
    }

    private static List<Integer> bfs(Node root) {
        if(root == null) return null;
        List<Integer> result = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            Node current = q.poll();
            result.add(current.val);
            if(current.left != null)  q.offer(current.left);

            if(current.right != null) q.offer(current.right);

        }
        return result;
    }

    private static List<Integer> dfsIterative(Node root) {
        if(root == null) return null;
        List<Integer> result = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()) {
            Node current = s.pop();
            result.add(current.val);
            if(current.right != null) s.push(current.right);

            if(current.left != null) s.push(current.left);
        }
        return result;
    }
    private static List<Integer> dfs(Node root) {
        if(root == null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        dfsHelper(root, result);
        return result;
    }

    private static void dfsHelper(Node root, List<Integer> result) {
        if(root == null) return;
        result.add(root.val);
        dfsHelper(root.left, result);
        dfsHelper(root.right, result);
    }

}


