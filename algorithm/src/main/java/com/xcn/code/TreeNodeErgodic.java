package com.xcn.code;

import com.xcn.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            if (!stack.empty()) {
                TreeNode treeroot = stack.pop();
                list.add(treeroot.val);
                p = treeroot.right;
            }
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
        TreeNode p = root;
        TreeNode pre = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                TreeNode treeroot = stack.peek().right;
                if (treeroot == null || treeroot == pre) {
                    p = stack.pop();
                    list.add(p.val);
                    pre = p;
                    p = null;
                } else {
                    p = treeroot;
                }
            }
        }
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
