package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// runs my program
public class Main {
    // EFFECTS: creates a new ScoreSheetApp
    //          catches FileNotFoundException
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ScoreSheetAppGUI();
                //new ScoreSheetApp();
            } catch (FileNotFoundException e) {
                System.out.println("Could not read file");
            }
        });
    }
}
