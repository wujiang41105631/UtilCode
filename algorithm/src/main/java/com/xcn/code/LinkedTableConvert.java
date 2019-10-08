package com.xcn.code;

import com.xcn.bean.Node;

/**
 * 单向链表反转
 *
 * @author: xupeng.guo
 * @date: 2019/3/19
 * @description
 */
public class LinkedTableConvert {

    /**
     * 递归
     *
     * @param node
     * @return
     */
    public static Node convert(Node node) {
        if (!node.hasNext()) {
            return node;
        }
        Node n1 = convert(node.getNextNode());
        node.getNextNode().setNextNode(node);
        node.setNextNode(null);
        return n1;
    }


    /**
     * 循环
     *
     * @param head
     * @return
     */
    public static Node convert2(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNextNode() == null) {
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        //下一个节点指针
        Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.getNextNode();//nextNode 指向下一个节点
            curNode.setNextNode(preNode);//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }
        return preNode;
    }

    public static void main(String[] args) {
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        n1.setNextNode(n2);
        n2.setNextNode(n3);
        n3.setNextNode(n4);
        printf(n1);
        System.out.println("---------");
        n1 = LinkedTableConvert.convert2(n1);
        printf(n1);
    }

    static void printf(Node n) {
        Node tempN = n;
        do {
            System.out.println(tempN);
        } while ((tempN = tempN.getNextNode()) != null);
    }
}
