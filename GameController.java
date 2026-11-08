import java.util.*;

class GameController {

    public void startRace(int[][] maze, String aiType) {
        Player player = new Player();
        int[] playerPos = {1, 1};
        int[] goalPos = {maze.length - 2, maze[0].length - 2};
        int[] start = {1, 1};

        // AI calculates path
        List<int[]> aiPath;
        if (aiType.equals("BFS")) {
            AIQueueBFS bfsAI = new AIQueueBFS();
            aiPath = bfsAI.solveMazeWithQueue(maze, start, goalPos);
            System.out.println("🤖 Computer using: BFS (Shortest Path Algorithm)");
        } else {
            AIStackDFS dfsAI = new AIStackDFS();
            aiPath = dfsAI.solveMazeWithStack(maze, start, goalPos);
            System.out.println("🤖 Computer using: DFS (Depth-First Search Algorithm)");
        }

        if (aiPath.isEmpty()) {
            System.out.println("❌ No solution exists for this maze!");
            return;
        }

        System.out.println("📊 AI path length: " + aiPath.size() + " steps");
        System.out.println("📍 Goal: Reach position (" + goalPos[0] + "," + goalPos[1] + ")");
        System.out.println("🎮 Race begins! Press Enter after each move...\n");

        int aiStep = 0;
        int[] computerPos = start.clone();

        while (true) {
            // Display current state
            player.updateMazeDisplay(maze, playerPos, computerPos, goalPos);

            // Check win conditions
            if (player.checkWin(playerPos, goalPos)) {
                System.out.println("🎉 Congratulations! You won the race!");
                System.out.println("📊 You completed the maze!");
                break;
            }

            if (player.checkWin(computerPos, goalPos)) {
                System.out.println("😢 Computer won! Better luck next time!");
                System.out.println("📊 Computer completed in " + aiStep + " steps");
                break;
            }

            // Check collision
            if (player.checkCollision(playerPos, computerPos)) {
                System.out.println("💥 You collided with the computer! Game over!");
                break;
            }

            // Player move
            String direction = player.handleInput();

            if (direction.isEmpty()) {
                System.out.println("⚠️  Invalid input! Use W/A/S/D");
                continue;
            }

            boolean moved = player.move(direction, playerPos, maze);
            if (!moved) {
                System.out.println("⚠️  Cannot move in that direction!");
                continue;
            }

            // AI move
            if (aiStep < aiPath.size() - 1) {
                aiStep++;
                computerPos = aiPath.get(aiStep).clone();
            }
        }
    }
}