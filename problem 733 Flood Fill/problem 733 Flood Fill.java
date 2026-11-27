class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        if (oldColor == color) return image; // avoid infinite loop
        dfs(image, sr, sc, oldColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // bounds check
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) return;
        // only fill pixels with the original color
        if (image[r][c] != oldColor) return;

        image[r][c] = newColor;

        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
    }
}
