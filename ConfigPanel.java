package org.example.tema_lab8;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private final JTextField rowsField;
    private final JTextField colsField;
    private final JSlider speedSlider;

    public ConfigPanel(MainFrame frame) {
        setLayout(new FlowLayout());

        rowsField = new JTextField("10", 5);
        colsField = new JTextField("10", 5);

        JButton drawButton = new JButton("Draw Maze");

        speedSlider = new JSlider(10, 500, 100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);

        drawButton.addActionListener(e -> {
            int rows = getRowsValue();
            int cols = getColsValue();

            if (rows <= 0 || cols <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid dimensions.");
                return;
            }

            frame.createEmptyMaze(rows, cols);
        });

        add(new JLabel("Rows:"));
        add(rowsField);
        add(new JLabel("Cols:"));
        add(colsField);
        add(drawButton);
        add(new JLabel("Speed:"));
        add(speedSlider);
    }

    public int getRowsValue() {
        try {
            return Integer.parseInt(rowsField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getColsValue() {
        try {
            return Integer.parseInt(colsField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getSpeed() {
        return speedSlider.getValue();
    }
}