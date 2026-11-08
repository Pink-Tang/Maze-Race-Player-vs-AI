import java.util.*;

class MazeGenerator {
    private int[][] maze;
    private int size;

    public MazeGenerator(int level) {
        this.size = 11 + (level * 4);
        this.maze = new int[size][size];
    }

    public void generate() {
        boolean[][] visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(maze[i], 1);
        }

        dfs(visited, 1, 1);

        maze[1][1] = 0;
        maze[size-2][size-2] = 0;
    }

    private void dfs(boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        maze[row][col] = 0;

        int[][] directions = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};

        List<int[]> dirList = new ArrayList<>(Arrays.asList(directions));
        Collections.shuffle(dirList);

        for (int[] dir : dirList) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                maze[row + dir[0]/2][col + dir[1]/2] = 0;
                dfs(visited, newRow, newCol);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row < size - 1 && col > 0 && col < size - 1;
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getSize() {
        return size;
    }
}
