package ColorPicker.Windows;

import ColorPicker.utils.GBHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class ColorPickerMainWindow {

    private static JFrame mainWindow = null;
    private static JPanel colorBox = null;
    private static JPanel toolsPanel = null;

    private static int redColor = 125;
    private static int greenColor = 125;
    private static int blueColor = 125;

    private static String hexColor = "#7D7D7D";

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
        mainWindow.setSize(400,200);
        //mainWindow.setPreferredSize(new Dimension(400, 200));
        mainWindow.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // отступы от края окна
        mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(new GridLayout(1,2));
        //mainWindow.pack();


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
        toolsPanel.setLayout(new GridLayout());
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
        //slider.addChangeListener(mainWindow);
        return slider;
    }

    public static String getHexColor(Color color) {
        String hexColor = "#" + Integer.toHexString(redColor)
                              + Integer.toHexString(greenColor)
                              + Integer.toHexString(blueColor);
        return hexColor.toUpperCase();
    }

    private void stateChanged(ChangeEvent changeEvent) {
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
        //copyToClipboard(hexColor);


    }
}
