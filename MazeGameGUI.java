import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.List;

class MazeGameGUI extends JFrame {
    private MazePanel mazePanel;
    private JLabel statusLabel;
    private JLabel stepsLabel;
    private int[] playerPos;
    private int[] computerPos;
    private int[] goalPos;
    private int[][] maze;
    private List<int[]> aiPath;
    private int aiStep;
    private int playerSteps;
    private String aiType;
    private Timer aiTimer;

    public MazeGameGUI(int[][] maze, String aiType) {
        this.maze = maze;
        this.aiType = aiType;
        this.playerPos = new int[]{1, 1};
        this.computerPos = new int[]{1, 1};
        this.goalPos = new int[]{maze.length - 2, maze[0].length - 2};
        this.aiStep = 0;
        this.playerSteps = 0;

        // Calculate AI path
        if (aiType.equals("BFS")) {
            AIQueueBFS bfs = new AIQueueBFS();
            aiPath = bfs.solveMazeWithQueue(maze, playerPos, goalPos);
        } else {
            AIStackDFS dfs = new AIStackDFS();
            aiPath = dfs.solveMazeWithStack(maze, playerPos, goalPos);
        }

        setupUI();
        setupKeyListener();
    }

    private void setupUI() {
        setTitle("🎮 Maze Race Game - " + aiType + " AI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Calculate cell size based on maze size
        int cellSize = Math.min(600 / maze.length, 40);

        // Maze panel
        mazePanel = new MazePanel(maze, cellSize);
        JScrollPane scrollPane = new JScrollPane(mazePanel);
        add(scrollPane, BorderLayout.CENTER);

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel = new JLabel("🎮 Use Arrow Keys or WASD to move!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        stepsLabel = new JLabel("Steps: 0 | AI Steps: 0");
        stepsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stepsLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel legendLabel = new JLabel("🔵 You  🔴 Computer  🟡 Goal  ⬛ Wall");
        legendLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        legendLabel.setHorizontalAlignment(JLabel.CENTER);

        infoPanel.add(statusLabel);
        infoPanel.add(stepsLabel);
        infoPanel.add(legendLabel);

        add(infoPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (aiTimer != null && aiTimer.isRunning()) return;

                String direction = "";
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        direction = "UP";
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        direction = "DOWN";
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        direction = "LEFT";
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        direction = "RIGHT";
                        break;
                }

                if (!direction.isEmpty()) {
                    handlePlayerMove(direction);
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void handlePlayerMove(String direction) {
        int[] newPos = playerPos.clone();

        switch (direction) {
            case "UP" -> newPos[0]--;
            case "DOWN" -> newPos[0]++;
            case "LEFT" -> newPos[1]--;
            case "RIGHT" -> newPos[1]++;
        }

        if (isValidMove(newPos)) {
            mazePanel.markPath(playerPos, 2);
            playerPos = newPos;
            playerSteps++;

            checkGameState();

            // AI moves
            moveAI();

            mazePanel.updatePositions(playerPos, computerPos);
            updateLabels();
        } else {
            statusLabel.setText("⚠️ Cannot move into wall!");
        }
    }

    private boolean isValidMove(int[] pos) {
        return pos[0] >= 0 && pos[0] < maze.length &&
                pos[1] >= 0 && pos[1] < maze[0].length &&
                maze[pos[0]][pos[1]] != 1;
    }

    private void moveAI() {
        if (aiStep < aiPath.size() - 1) {
            aiStep++;
            mazePanel.markPath(computerPos, 3);
            computerPos = aiPath.get(aiStep).clone();
            checkGameState();
        }
    }

    private void checkGameState() {
        if (playerPos[0] == goalPos[0] && playerPos[1] == goalPos[1]) {
            endGame("🎉 Congratulations! You Won!",
                    "You completed the maze in " + playerSteps + " steps!");
        } else if (computerPos[0] == goalPos[0] && computerPos[1] == goalPos[1]) {
            endGame("😢 Computer Won!",
                    "Computer completed in " + aiStep + " steps. Try again!");
        }
    }

    private void updateLabels() {
        stepsLabel.setText("Your Steps: " + playerSteps + " | AI Steps: " + aiStep);
        statusLabel.setText("🎮 Keep moving! Use Arrow Keys or WASD");
    }

    private void endGame(String title, String message) {
        String[] options = {"Play Again", "Exit"};
        int choice = JOptionPane.showOptionDialog(this,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        dispose();

        if (choice == 0) {
            SwingUtilities.invokeLater(() -> new MainMenuGUI());
        }
    }
}


