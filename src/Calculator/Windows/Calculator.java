package Calculator.Windows;

import Calculator.CustomActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private static JFrame mainWindow = null;
    private static JLabel display = null;
    private static JPanel buttonPanel = null;
    private static JPanel equalityPanel = null;

    private static String[] buttonLabels = {"7", "8", "9", "+",
                                            "4", "5", "6", "-",
                                            "1", "2", "3", "*",
                                            "CE", "0", ".", "/"};

    public Calculator() {
        mainWindow = createMainWindow();
        display = creteDisplay();
        buttonPanel = createButtonPanel();
        equalityPanel = createEqualityPanel();

        mainWindow.add(display, BorderLayout.NORTH);
        mainWindow.add(buttonPanel, BorderLayout.CENTER);
        mainWindow.add(equalityPanel, BorderLayout.SOUTH);
        mainWindow.setVisible(true);
    }

    private JFrame createMainWindow(){
        JFrame mainWindow = new JFrame();
        mainWindow.setTitle("Calculator");
        mainWindow.setMinimumSize(new Dimension(300, 465));
        mainWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainWindow.setResizable(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(new BorderLayout());
        return mainWindow;
    }

    private JLabel creteDisplay(){
        JLabel display = new JLabel("0", JLabel.RIGHT);
        display.setPreferredSize(new Dimension(300, 50));
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        return display;
    }

    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4, 10,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButton(buttonPanel, buttonLabels);
        return buttonPanel;
    }

    private JPanel createEqualityPanel(){
        JPanel equalityPanel = new JPanel();
        equalityPanel.setLayout(new BorderLayout());
        equalityPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        JButton equalityButton = new JButton("=");
        equalityButton.setPreferredSize(new Dimension(277, 70));
        equalityButton.addActionListener(new CustomActionListener());
        equalityPanel.add(equalityButton);

        return equalityPanel;
    }

    //TODO add actionListener
    private void addButton(JPanel panel, String ... label){
        for (String str :
                label) {
            JButton button = new JButton(str);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new CustomActionListener());
            panel.add(button);
        }
    }

    public static JLabel getDisplay() {
        return display;
    }
}
