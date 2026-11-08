import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MazePanel extends JPanel {
    private int[][] maze;
    private int[] playerPos;
    private int[] computerPos;
    private int[] goalPos;
    private int cellSize;

    public MazePanel(int[][] maze, int cellSize) {
        this.maze = maze;
        this.cellSize = cellSize;
        this.playerPos = new int[]{1, 1};
        this.computerPos = new int[]{1, 1};
        this.goalPos = new int[]{maze.length - 2, maze[0].length - 2};

        setPreferredSize(new Dimension(maze[0].length * cellSize, maze.length * cellSize));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw maze
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                if (maze[y][x] == 1) {
                    // Wall
                    g2d.setColor(new Color(50, 50, 50));
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else if (maze[y][x] == 2) {
                    // Player trail
                    g2d.setColor(new Color(200, 230, 255));
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else if (maze[y][x] == 3) {
                    // Computer trail
                    g2d.setColor(new Color(255, 230, 200));
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else {
                    // Path
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }

                // Grid lines
                g2d.setColor(new Color(200, 200, 200));
                g2d.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        // Draw goal
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillOval(goalPos[1] * cellSize + 5, goalPos[0] * cellSize + 5, cellSize - 10, cellSize - 10);
        g2d.setColor(new Color(200, 170, 0));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(goalPos[1] * cellSize + 5, goalPos[0] * cellSize + 5, cellSize - 10, cellSize - 10);

        // Draw computer
        g2d.setColor(new Color(255, 100, 100));
        g2d.fillOval(computerPos[1] * cellSize + 3, computerPos[0] * cellSize + 3, cellSize - 6, cellSize - 6);
        g2d.setColor(new Color(200, 50, 50));
        g2d.drawOval(computerPos[1] * cellSize + 3, computerPos[0] * cellSize + 3, cellSize - 6, cellSize - 6);

        // Draw player
        g2d.setColor(new Color(100, 150, 255));
        g2d.fillOval(playerPos[1] * cellSize + 3, playerPos[0] * cellSize + 3, cellSize - 6, cellSize - 6);
        g2d.setColor(new Color(50, 100, 200));
        g2d.drawOval(playerPos[1] * cellSize + 3, playerPos[0] * cellSize + 3, cellSize - 6, cellSize - 6);
    }

    public void updatePositions(int[] player, int[] computer) {
        this.playerPos = player.clone();
        this.computerPos = computer.clone();
        repaint();
    }

    public void markPath(int[] pos, int type) {
        if (maze[pos[0]][pos[1]] == 0) {
            maze[pos[0]][pos[1]] = type;
        }
    }
}