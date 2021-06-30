package com.github.noodzhan.main.structure.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树的工具包
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/27 11:09 上午
 */
public class TreeUtil {
    public static List<TreeNode> convertTree(List<TreeNode> list) {
        LinkedList<TreeNode> result = new LinkedList<>();
        for (TreeNode daoEntity : list) {
            //找到所有的孩子
            for (TreeNode entity : list) {
                if (daoEntity.getId().equals(entity.getPid())) {
                    daoEntity.getChild().add(entity);
                }
            }
        }
        //找到所有的子树（根节点），构造结果
        for (TreeNode treeNode : list) {
            if (treeNode.getPid() == null) {
                result.add(treeNode);
            }
        }

        return result;

    }


    public static List<TreeNode> convertTreeByRecursive(List<TreeNode> list){
        //找到所有的跟
        List<TreeNode> root = list.stream().filter(e -> e.getPid() == null).collect(Collectors.toList());
        //递归找所有的子节点
        for (TreeNode treeNode : root) {
            findChildrenNode(treeNode, list);
        }
        return root;
    }

    /**
     * 递归找到所有的子节点
     * @param treeNode 当前节点
     * @param list 树
     * @return
     */
    private static TreeNode findChildrenNode(TreeNode treeNode,List<TreeNode> list){
        List<TreeNode> childNode = list.stream().filter(e->treeNode.getId().equals(e.getPid())).collect(Collectors.toList());
        treeNode.setChild(childNode);
        childNode.stream().forEach(e -> findChildrenNode(e, list));
        return treeNode;
    }


    public static void main(String[] args) {
        List<TreeNode> treeEntities = TreeUtil.convertTree(Arrays.asList(new TreeNode(1l, "1", null), new TreeNode(2l, "2", null)
                , new TreeNode(3l, "1-1", 1l), new TreeNode(4l, "1-1-1", 3l)));
        System.out.println(treeEntities);


        List<TreeNode> treeNodes = TreeUtil.convertTreeByRecursive(Arrays.asList(new TreeNode(1l, "1", null), new TreeNode(2l, "2", null)
                , new TreeNode(3l, "1-1", 1l), new TreeNode(4l, "1-1-1", 3l),new TreeNode(5l,"2-1",2l),new TreeNode(6l,"2-2",2l)));

        System.out.println(treeNodes);

    }

}
