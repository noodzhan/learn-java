package com.github.noodzhan.main.structure.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 持久层id
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/27 11:07 上午
 */
public class TreeNode extends DaoEntity {

    public TreeNode(Long id, String label, Long pid) {
        super(id, label, pid);
    }

    private List<TreeNode> child = new LinkedList<>();

    public List<TreeNode> getChild() {
        return child;
    }

    public void setChild(List<TreeNode> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "child=" + child +
                '}';
    }
}
