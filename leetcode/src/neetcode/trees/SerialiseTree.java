package neetcode.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerialiseTree {
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder(); 
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            
            if (n == null) {
                sb.append("#,");
                continue;
            }

            sb.append(String.format("%d,", n.val));

            q.add(n.left);
            q.add(n.right);
        }

        return sb.substring(0, sb.length() - 1);
    } 

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(","); 

        return levelOrder(strArr, 1, 0);
    } 


    public TreeNode levelOrder(String[] data, int lvl, int i) {
        if (i >= data.length) return null;
       
        if (data[i] == "#") return null;        

        TreeNode n = new TreeNode(Integer.parseInt(data[i]));
        n.left = levelOrder(data, lvl * 2, i + lvl);
        n.right = levelOrder(data, lvl * 2, i + lvl + 1);

        return n;
    }
}
