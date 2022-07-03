package com.xcn.bean.graph;



import java.util.List;

public class GraphNode {

    public int value;

    public int in; // 入度

    public int out;// 出度

    public List<GraphNode> nexts;

    public List<Edge> edges;
}
