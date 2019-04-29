package com.xcn.bean;

/**
 * @author: xupeng.guo
 * @date: 2019/3/19
 * @description
 */
public class Node {
    private String data;

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    private Node nextNode;

    public Node(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }


}
