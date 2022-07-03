package com.xcn.code.graph;

import com.xcn.bean.graph.GraphNode;

import java.util.*;

/**
 * 图算法
 */
public class GraphTest {

    /**
     * 广度优先遍历
     * @param gn
     */
    public static void bfs(GraphNode gn){
        if(gn == null){
            return;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> set = new HashSet<>();
        queue.add(gn);
        set.add(gn);
        while(!queue.isEmpty()){
            GraphNode cur = queue.poll();
            System.out.println(cur.value);
            if(cur.nexts!=null){
                for (GraphNode next : cur.nexts) {
                    if(set.add(next)){
                        queue.add(next);
                    }
                }
            }
        }

    }

    /**
     * 深度优先
     * @param gn
     */
    public static void  dfs(GraphNode gn){
        if(gn == null){
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        Set<GraphNode> set = new HashSet<>();
        stack.push(gn);
        set.add(gn);
        System.out.println(gn.value);
        while(!stack.isEmpty()){
            GraphNode tmp = stack.pop();
            if(tmp.nexts == null || tmp.nexts.size() == 0){
                continue;
            }
            for (GraphNode next : tmp.nexts) {
                if(set.add(next)){
                    stack.push(tmp);
                    stack.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }


    /**
     * 拓扑排序：
     *  前提： 有向无环图
     *  1。 先找到 入度为0的点拿到。并消除其的出度，然后循环该步骤
     * @param gn
     */
    public static void sort(GraphNode gn){

    }

    /**
     * 最小生成树
     */
    public static void t1(){

    }
}
