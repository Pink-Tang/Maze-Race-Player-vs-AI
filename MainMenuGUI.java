import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainMenuGUI extends JFrame {

    public MainMenuGUI() {
        setupUI();
    }

    private void setupUI() {
        setTitle("🎮 Maze Race Game - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel titleLabel = new JLabel("🎮 MAZE RACE GAME 🎮");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton dfsButton = new JButton("🤖 Compete Against DFS AI");
        dfsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        dfsButton.addActionListener(e -> startGame("DFS"));

        JButton bfsButton = new JButton("🚀 Compete Against BFS AI");
        bfsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        bfsButton.addActionListener(e -> startGame("BFS"));

        JButton exitButton = new JButton("❌ Exit Game");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(dfsButton);
        buttonPanel.add(bfsButton);
        buttonPanel.add(exitButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame(String aiType) {
        String[] levels = {"1 - Easy (11x11)", "2 - Medium (15x15)", "3 - Hard (19x19)",
                "4 - Expert (23x23)", "5 - Master (27x27)"};
        String choice = (String) JOptionPane.showInputDialog(this,
                "Select Maze Difficulty:",
                "Difficulty Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                levels,
                levels[0]);

        if (choice != null) {
            int level = Integer.parseInt(choice.substring(0, 1));

            // Generate maze
            MazeGenerator mg = new MazeGenerator(level);
            mg.generate();

            // Start game
            dispose();
            new MazeGameGUI(mg.getMaze(), aiType);
        }
    }
}