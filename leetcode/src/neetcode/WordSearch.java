package neetcode;

public class WordSearch {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        boolean isMatch = false;

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (visited[r][c]) {
                    continue;
                }
                isMatch = isMatch || dfs(board, r, c, word, 0, visited);
            }
        }
        return isMatch;
    }

    public boolean dfs(char[][] board, int r, int c, String word, int i, boolean[][] visited) {
        // search dir, if == ptr, we traverse
        if (i == word.length()) {
            return true;
        }

        boolean isMatch = false;
        visited[r][c] = true;
        
        for (int[] dir : dirs) {
            int new_r = r + dir[0];
            int new_c = c + dir[1];

            if (new_r >= 0 && new_r < board.length && new_c >= 0 && new_c < board[0].length) {
                if (board[new_r][new_c] == word.charAt(i) && !visited[new_r][new_c]) {
                    isMatch = isMatch || dfs(board, new_r, new_c, word, i+1, visited);
                }
            }
        } 
        
        return isMatch;
    }
} 

