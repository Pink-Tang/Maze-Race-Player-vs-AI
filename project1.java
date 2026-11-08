import javax.swing.*;

class project1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Show welcome message
            JOptionPane.showMessageDialog(null,
                    "🎮 Welcome to Maze Race Game! 🎮\n\n" +
                            "Race against AI to reach the goal!\n" +
                            "Use Arrow Keys or WASD to move\n\n" +
                            "Good luck!",
                    "Welcome",
                    JOptionPane.INFORMATION_MESSAGE);

            new MainMenuGUI();
        });
    }
}