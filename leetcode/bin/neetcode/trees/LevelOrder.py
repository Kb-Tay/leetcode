class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        # Use a queue to do the bfs
        queue = []
        queue.append(root.val)
        ansList = []

        while len(queue) > 0:
            temp = []
            ans = []
            while (len(queue) > 0): 
              node = queue.pop(0)
              ans.append(node.val)
              if node.left is not None:
                temp.add(node.left)

              if node.right is not None:
                temp.add(node.right)

            ansList.append(ans.copy());           

        return ansList 
        

        