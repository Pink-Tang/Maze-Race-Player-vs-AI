import javax.swing.*;
import java.util.*;

class Player {
    private final Scanner scanner = new Scanner(System.in);

    public String handleInput() {
        System.out.print("Your move (W/A/S/D): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.isEmpty()) {
            return "";
        }
        char c = input.charAt(0);

        return switch (c) {
            case 'W' -> "UP";
            case 'S' -> "DOWN";
            case 'A' -> "LEFT";
            case 'D' -> "RIGHT";
            default -> "";
        };
    }

    public boolean move(String direction, int[] playerPos, int[][] maze) {
        if (direction == null || direction.isEmpty()){
            return false;
        }

        int y = playerPos[0];
        int x = playerPos[1];

        int ny = y, nx = x;

        switch (direction) {
            case "UP" -> ny = y - 1;
            case "DOWN" -> ny = y + 1;
            case "LEFT" -> nx = x - 1;
            case "RIGHT" -> nx = x + 1;
        }

        if (ny < 0 || ny >= maze.length || nx < 0 || nx >= maze[0].length)
            return false;

        if (maze[ny][nx] == 1)
            return false;

        markPath(maze, playerPos);

        playerPos[0] = ny;
        playerPos[1] = nx;

        return true;
    }

    public void markPath(int[][] maze, int[] playerPos) {
        maze[playerPos[0]][playerPos[1]] = 2; // 2 = player trail
    }

    public boolean checkCollision(int[] playerPos, int[] computerPos) {
        return playerPos[0] == computerPos[0] &&
                playerPos[1] == computerPos[1];
    }

    public boolean checkWin(int[] playerPos, int[] goalPos) {
        return playerPos[0] == goalPos[0] &&
                playerPos[1] == goalPos[1];
    }

    public void updateMazeDisplay(int[][] maze,
                                  int[] playerPos,
                                  int[] computerPos,
                                  int[] goalPos) {

        System.out.println("\n" + "=".repeat(maze[0].length * 2));
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                if (y == playerPos[0] && x == playerPos[1]) {
                    System.out.print("P ");
                }
                else if (y == computerPos[0] && x == computerPos[1]) {
                    System.out.print("C ");
                }
                else if (y == goalPos[0] && x == goalPos[1]) {
                    System.out.print("G ");
                }
                else if (maze[y][x] == 1) {
                    System.out.print("██");
                }
                else if (maze[y][x] == 2) {
                    System.out.print("· ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("=".repeat(maze[0].length * 2));
        System.out.println("P=Player C=Computer G=Goal ██=Wall ·=Trail\n");
    }
}