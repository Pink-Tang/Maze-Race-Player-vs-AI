import java.util.*;
class AIQueueBFS {

    public List<int[]> solveMazeWithQueue(int[][] maze, int[] start, int[] goal) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Map<String, int[]> parent = new HashMap<>();

        queue.offer(start);
        visited[start[0]][start[1]] = true;
        parent.put(Arrays.toString(start), null);

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (Arrays.equals(current, goal)) {
                return reconstructPath(parent, start, goal);
            }

            for (int[] dir : directions) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];
                int[] next = {newRow, newCol};

                if (isValid(maze, next) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.offer(next);
                    parent.put(Arrays.toString(next), current);
                }
            }
        }

        return new ArrayList<>();
    }

    private boolean isValid(int[][] maze, int[] pos) {
        return pos[0] >= 0 && pos[0] < maze.length &&
                pos[1] >= 0 && pos[1] < maze[0].length &&
                maze[pos[0]][pos[1]] != 1;
    }

    private List<int[]> reconstructPath(Map<String, int[]> parent, int[] start, int[] goal) {
        List<int[]> path = new ArrayList<>();
        int[] current = goal;

        while (current != null) {
            path.add(0, current);
            current = parent.get(Arrays.toString(current));
        }

        return path;
    }
}
