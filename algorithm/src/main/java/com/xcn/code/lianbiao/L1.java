package com.xcn.code.lianbiao;

import com.xcn.bean.Node;

/**
 * 给定两个可能有环也可能无环的两个链表h1和h2,请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如不相交，则返回NUll
 * 若两个链表的长度之和为N，shi时间复杂度要求为O(n),空间复杂度要求为O(1)
 */
public class L1 {

    public static void main(String[] args) {
        // 简单顶一下两个node
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        // 找到第一个入环节点
        Node loop1 = getNode(n1);
        Node loop2 = getNode(n2);

        Node result = null;
        if (loop1 == null && loop2 == null) {
            result = noLoop(n1, n2);
        } else if (loop1 != null && loop2 != null) {
            result = bothLoop(n1, n2, loop1, loop2);
        } else {
            // 1个 有环，一个无环 不可能相交。
            result = null;
        }
    }

    /**
     * 两个链表都有环，返回第一个相交节点。有三种情况
     * 1. 虽然都有环，但是没有相交
     * 2. 环外相交，即入环节点相同
     * 3. 环内相交，即入环节点不同。
     *
     * @param n1
     * @param n2
     * @param loop1
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node n1, Node n2, Node loop1, Node loop2) {
        Node n = n1;
        Node nn = n2;
        if (loop1 == loop2) {
            // 情况2
            // 转换成 无环链表找相交节点
            int size = 0;
            while (n != loop1) {
                size++;
                n = n.getNextNode();
            }
            while (nn != loop2) {
                size--;
                nn = nn.getNextNode();
            }
            n = size > 0 ? n : nn;
            nn = size > 0 ? nn : n;
            size = Math.abs(size);

            while (n != nn) {
                if (size-- > 0) {
                    n = n.getNextNode();
                    continue;
                }
                n = n.getNextNode();
                nn = nn.getNextNode();
            }
            return nn;
        } else {
            // 情况1，3
            n = loop1.getNextNode();
            while (n != loop1) {
                if (n == loop2) {
                    return loop1;
                }
                n = n.getNextNode();
            }
        }
        return null;
    }

    /**
     * 给定两个链表都无环，返回第一个相交节点，如果不想交，返回null。
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Node noLoop(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return null;
        }

        if (n1 == n2) {
            return n1;
        }

        int size = 0;
        Node n = n1;
        Node nn = n2;
        while (n != null) {
            n = n.getNextNode();
            size++;
        }

        while (nn != null) {
            nn = nn.getNextNode();
            size--;
        }
        // 两个链表最后的节点位置不相等，则表示没有相交
        if (n != nn) {
            return null;
        }

        n = size > 0 ? n1 : n2;
        nn = size > 0 ? n2 : n1;
        size = Math.abs(size);

        while (n != nn) {
            if (size-- > 0) {
                n = n.getNextNode();
                continue;
            }
            n = n.getNextNode();
            nn = nn.getNextNode();
        }
        return n;
    }


    /**
     * 找到链表的第一个入环点
     *
     * @param node
     * @return
     */
    public static Node getNode(Node node) {
        if (node == null
                || node.getNextNode() == null
                || node.getNextNode().getNextNode() == null) {
            return null;
        }
        // 快慢指针
        Node n1 = node.getNextNode();
        Node n2 = node.getNextNode().getNextNode();

        while (n1 != n2) {
            if (n2 == null
                    || n2.getNextNode() == null
                    || n2.getNextNode().getNextNode() == null
                    ) {
                return null;
            }
            n1 = n1.getNextNode();
            n2 = n2.getNextNode().getNextNode();
        }

        // 追击问题找入环节点
        n2 = node;
        while (n1 != n2) {
            n1 = n1.getNextNode();
            n2 = n2.getNextNode();
        }
        return n1;
    }

}
