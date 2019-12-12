package ru.avalon.java.j120.labs.ColorPicker.Windows;

import ru.avalon.java.j120.labs.ColorPicker.utils.GBHelper;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ColorPickerMainWindow {

    private static ColorPickerMainWindow instance;

    private static JFrame mainWindow = null;
    private static JPanel colorBox = null;
    private static JPanel toolsPanel = null;

    private static int redColor = 125;
    private static int greenColor = 125;
    private static int blueColor = 125;

    private static String hexColor = "#7D7D7D";
    private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public ColorPickerMainWindow() {
        mainWindow = createMainWindow();
        colorBox = createColorBox();
        toolsPanel = createToolsPanel();

        mainWindow.add(colorBox);
        mainWindow.add(toolsPanel);
        mainWindow.setVisible(true);

    }

    private static JFrame createMainWindow(){
        JFrame mainWindow = new JFrame("Color Picker");
        mainWindow.setPreferredSize(new Dimension(400, 200));
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainWindow.setLayout(new GridLayout(1,2));
        mainWindow.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainWindow.pack();


        return mainWindow;
    }

    private JPanel createColorBox() {
        JPanel colorBox = new JPanel();
        colorBox.setBackground(new Color(125,125,125));
        colorBox.setToolTipText(hexColor);
        return colorBox;
    }

    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel();

        JSlider redSlider = createJSlider("Red");
        JSlider greenSlider = createJSlider("Green");
        JSlider blueSlider = createJSlider("Blue");

        JLabel redLabel = new JLabel("Red");
        JLabel greenLabel = new JLabel("Green");
        JLabel blueLabel = new JLabel("Blue");

        toolsPanel.setBackground(new Color(235,235,235));
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        toolsPanel.setLayout(new GridBagLayout());
        GBHelper position = new GBHelper();
        JComponent gap = new JPanel();
        gap.setPreferredSize(new Dimension(10,10));

        toolsPanel.add(redLabel, position);
        toolsPanel.add(gap, position.nextCol());
        toolsPanel.add(redSlider, position.nextCol().expandW());
        toolsPanel.add(gap, position.nextRow());

        toolsPanel.add(greenLabel, position);
        toolsPanel.add(gap, position.nextCol());
        toolsPanel.add(greenSlider, position.nextCol().expandW());
        toolsPanel.add(gap, position.nextRow());

        toolsPanel.add(blueLabel, position);
        toolsPanel.add(gap, position.nextCol());
        toolsPanel.add(blueSlider, position.nextCol().expandW());
        toolsPanel.add(gap, position.nextRow());

        return toolsPanel;
    }


    private JSlider createJSlider(String label){
        JSlider slider = new JSlider(0, 255,125);
        slider.setName(label);
        slider.setMajorTickSpacing(255);
        slider.setMinorTickSpacing(15);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setName(label);
        slider.addChangeListener(createCustomChangeListener());
        return slider;
    }

    private ChangeListener createCustomChangeListener(){
        ChangeListener changeListener = changeEvent -> {
            JSlider slider = (JSlider) changeEvent.getSource();
            String sliderName = slider.getName();
            switch (sliderName) {
                case ("Red"):
                    redColor = slider.getValue();
                    break;
                case ("Green"):
                    greenColor = slider.getValue();
                    break;
                case ("Blue"):
                    blueColor = slider.getValue();
                    break;
            }
            Color color = new Color(redColor, greenColor, blueColor);
            colorBox.setBackground(color);
            hexColor = getHexColor(colorBox.getBackground());
            colorBox.setToolTipText(hexColor);
            copyToClipboard(hexColor);
        };
        return changeListener;
    }

    public static String getHexColor(Color color) {
        String hexColor = "#" + Integer.toHexString(redColor)
                              + Integer.toHexString(greenColor)
                              + Integer.toHexString(blueColor);
        return hexColor.toUpperCase();
    }

    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

    public static ColorPickerMainWindow getInstance() {
        if (instance == null) {
            instance = new ColorPickerMainWindow();
        }
        return instance;
    }

}
