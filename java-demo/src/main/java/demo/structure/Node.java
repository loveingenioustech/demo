package demo.structure;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-04-25.
 * Java里面没有直接提供树的实现，但是它很容易通过下面的方式来实现。只需要创建一个Node对象，里面包含一个指向叶子节点的ArrayList。
 */
public class Node {
    private String name;
    @NotNull
    private List<Node> children = new ArrayList<Node>();
    private Node parent;

    public Node(String name){
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node child){
        children.add(child);
    }

    public void removeChild(Node child){
        children.remove(child);
    }

    public String toString() {
        return name;
    }
}
