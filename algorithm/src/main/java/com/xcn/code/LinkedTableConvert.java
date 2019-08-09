package com.xcn.code;

import com.xcn.bean.Node;

/**
 * 单向链表反转
 * @author: xupeng.guo
 * @date: 2019/3/19
 * @description
 */
public class LinkedTableConvert {

    public static Node convert(Node node){
        if(!node.hasNext()){
            return node;
        }
        Node n1 = convert(node.getNextNode());
        node.getNextNode().setNextNode(node);
        node.setNextNode(null);
        return n1;
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
        n1 = LinkedTableConvert.convert(n1);
        printf(n1);
    }

    static void printf(Node n) {
        Node tempN = n;
        do {
            System.out.println(tempN);
        } while ((tempN = tempN.getNextNode()) != null) ;
    }
}
