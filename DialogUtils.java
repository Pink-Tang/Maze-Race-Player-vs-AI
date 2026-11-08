import javax.swing.*;
import java.awt.*;

class DialogUtils {

    // Show welcome dialog
    public static void showWelcomeDialog() {
        JOptionPane.showMessageDialog(null,
                "🎮 Welcome to Maze Race Game! 🎮\n\n" +
                        "Race against AI to reach the goal!\n" +
                        "Use W/A/S/D to move\n\n" +
                        "Good luck!",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Show maze generated dialog
    public static void showMazeGeneratedDialog(int size, String aiType) {
        JOptionPane.showMessageDialog(null,
                "✅ Maze Generated Successfully!\n\n" +
                        "📏 Maze Size: " + size + " x " + size + "\n" +
                        "🤖 AI Algorithm: " + aiType + "\n" +
                        "📍 Goal: Bottom-Right Corner\n\n" +
                        "Click OK to start racing!",
                "Maze Ready",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Show player win dialog
    public static void showPlayerWinDialog(int steps) {
        String[] options = {"Play Again", "Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "🎉 Congratulations! You Won! 🎉\n\n" +
                        "You completed the maze!\n" +
                        "Great job!\n\n" +
                        "Would you like to play again?",
                "Victory!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

    // Show computer win dialog
    public static void showComputerWinDialog(int steps) {
        String[] options = {"Try Again", "Exit"};
        int choice = JOptionPane.showOptionDialog(null,
                "😢 Computer Won!\n\n" +
                        "Computer completed in " + steps + " steps\n" +
                        "Better luck next time!\n\n" +
                        "Would you like to try again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
    }

    // Show collision dialog
    public static void showCollisionDialog() {
        String[] options = {"Play Again", "Exit"};
        JOptionPane.showOptionDialog(null,
                "💥 Collision Detected!\n\n" +
                        "You ran into the computer!\n" +
                        "Game Over!\n\n" +
                        "Would you like to play again?",
                "Collision!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);
    }

    // Show invalid move dialog
    public static void showInvalidMoveDialog() {
        JOptionPane.showMessageDialog(null,
                "⚠️ Invalid Move!\n\n" +
                        "Cannot move in that direction.\n" +
                        "Try another direction!",
                "Invalid Move",
                JOptionPane.WARNING_MESSAGE);
    }

    // Show error dialog
    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null,
                "❌ Error!\n\n" + message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Get level selection dialog
    public static int getLevelDialog() {
        String[] levels = {"1 - Easy (11x11)", "2 - Medium (15x15)", "3 - Hard (19x19)",
                "4 - Expert (23x23)", "5 - Master (27x27)"};
        String choice = (String) JOptionPane.showInputDialog(null,
                "Select Maze Difficulty:",
                "Difficulty Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                levels,
                levels[0]);

        if (choice == null) return -1; // User cancelled
        return Integer.parseInt(choice.substring(0, 1));
    }

    // Show exit confirmation
    public static boolean confirmExit() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return choice == JOptionPane.YES_OPTION;
    }
}
