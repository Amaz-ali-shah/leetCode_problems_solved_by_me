import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pacQueue = new LinkedList<>();
        Queue<int[]> atlQueue = new LinkedList<>();

        // Pacific border: top row + left column
        // Atlantic border: bottom row + right column
        for (int i = 0; i < m; i++) {
            pacific[i][0] = true;
            pacQueue.offer(new int[]{i, 0});

            atlantic[i][n - 1] = true;
            atlQueue.offer(new int[]{i, n - 1});
        }

        for (int j = 0; j < n; j++) {
            pacific[0][j] = true;
            pacQueue.offer(new int[]{0, j});

            atlantic[m - 1][j] = true;
            atlQueue.offer(new int[]{m - 1, j});
        }

        bfs(heights, pacific, pacQueue);
        bfs(heights, atlantic, atlQueue);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void bfs(int[][] heights, boolean[][] reachable, Queue<int[]> queue) {
        int m = heights.length;
        int n = heights[0].length;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (reachable[nr][nc]) continue;

                // Water can flow from (nr, nc) to (r, c) only if (nr, nc) is higher/equal
                if (heights[nr][nc] >= heights[r][c]) {
                    reachable[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
