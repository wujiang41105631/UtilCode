package com.xcn.code;

import com.xcn.bean.TreeNode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 遍历
 *
 * @author: xupeng.guo
 * @date: 2019/10/10
 * @description
 */
public class TreeNodeErgodic {


    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    public ArrayList<Integer> preOrder(TreeNode tree) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hh");
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        while (!stack.empty()) {
            TreeNode tn = stack.pop();
            list.add(tn.val);
            if (tn.right != null) {
                stack.push(tn.right);
            }
            if (tn.left != null) {
                stack.push(tn.left);
            }
        }
        return list;
    }



    /**
     * 中序遍历 先遍历左子树，然后访问根结点，最后遍历右子树
     *
     * @param tree
     * @return
     */
    public ArrayList<Integer> innerOrder(TreeNode tree) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = tree;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            TreeNode treeroot = stack.pop();
            list.add(treeroot.val);
            p = treeroot.right;
        }
        return list;
    }

    /**
     * 后序遍历 先遍历左子树，然后访问右子树，最后遍历根结点
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> postOrder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode tn = stack.pop();
            list.add(tn.val);
            if (tn.left != null) {
                stack.push(tn.left);
            }
            if (tn.right != null) {
                stack.push(tn.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public ArrayList<Integer> postOrder2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode tmp = null;
        while (!stack.empty()) {
            tmp = stack.peek();
            if(tmp!= null && root != tmp.left && tmp.right != root){
                stack.push(tmp.left);
            } else if(tmp.right != null && tmp.right != root){
                stack.push(tmp.right);
            }else{
                list.add(stack.pop().val);
                root = tmp;
            }
        }
        Collections.reverse(list);
        return list;
    }

    //层序遍历的非递归实现
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return list;
        }
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode treeroot = queue.poll();

            if(treeroot.left!=null){

                queue.offer(treeroot.left);
            }
            if(treeroot.right!=null){

                queue.offer(treeroot.right);
            }
            list.add(treeroot.val);

        }
        return list;
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    public ArrayList<Integer> preOrderDiGui(TreeNode tree) {
        return preOrderDiGui(tree, new ArrayList<Integer>());
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    private ArrayList<Integer> preOrderDiGui(TreeNode tree, ArrayList<Integer> result) {
        result.add(tree.val);
        if(tree.left!=null){
            preOrderDiGui(tree.left,result);
        }
        if(tree.right!=null){
            preOrderDiGui(tree.right,result);
        }
        return result;
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    public ArrayList<Integer> innerOrderDiGui(TreeNode tree) {
        return innerOrderDiGui(tree, new ArrayList<Integer>());
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    private ArrayList<Integer> innerOrderDiGui(TreeNode tree, ArrayList<Integer> result) {
        if(tree.left!=null){
            innerOrderDiGui(tree.left,result);
        }
        result.add(tree.val);
        if(tree.right!=null){
            innerOrderDiGui(tree.right,result);
        }
        return result;
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    public ArrayList<Integer> postOrderDiGui(TreeNode tree) {
        return postOrderDiGui(tree, new ArrayList<Integer>());
    }

    /**
     * 先序遍历 先根 后左 再右
     *
     * @param tree
     * @return
     */
    private ArrayList<Integer> postOrderDiGui(TreeNode tree, ArrayList<Integer> result) {
        if(tree.left!=null){
            postOrderDiGui(tree.left,result);
        }
        if(tree.right!=null){
            postOrderDiGui(tree.right,result);
        }
        result.add(tree.val);
        return result;
    }

    /**
     * 获取二叉树最大的宽度
     */
    private int weightV2(TreeNode tree){
        if(tree == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        // key = node,value = 节点的层
        Map<TreeNode,Integer> cache = new HashMap<>();
        cache.put(tree,1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            int lastlevel = cache.get(poll);
            if(poll.left!=null){
                queue.add(poll.left);
                cache.put(poll.left,lastlevel+1);
            }
            if(poll.right != null){
                queue.add(poll.right);
                cache.put(poll.right,lastlevel+1);
            }

            if(curLevel == lastlevel){
                curLevelNodes++;
            }else{
                curLevel ++ ;
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 1;
            }
        }
        max = Math.max(max,curLevelNodes);
        return max;
    }


    /**
     * 获取二叉树的宽度
     */
    private int weight(TreeNode tree){
        if(tree == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        int max = 0;
        int curLevelNodeSize = 0;
        TreeNode endNode = null;
        TreeNode curEndNode = tree;
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(poll.left!=null){
                queue.add(poll.left);
                endNode = poll.left;
            }
            if(poll.right != null){
                queue.add(poll.right);
                endNode = poll.right;
            }
            curLevelNodeSize++;
            if(poll == curEndNode){
                max = Math.max(max,curLevelNodeSize);
                curLevelNodeSize = 0;
                curEndNode = endNode;
            }
        }
        max = Math.max(max,curLevelNodeSize);
        return max;
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(0);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(5);
        TreeNode g = new TreeNode(6);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        TreeNodeErgodic t = new TreeNodeErgodic();
        ArrayList<Integer> list = new ArrayList<>();
        list = t.postOrder(a);
        for (Integer item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        list = t.postOrderDiGui(a);
        for (Integer item : list) {
            System.out.print(item + " ");
        }
    }
}
