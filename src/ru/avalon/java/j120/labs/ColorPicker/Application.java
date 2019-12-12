package ru.avalon.java.j120.labs.ColorPicker;

import ru.avalon.java.j120.labs.ColorPicker.Windows.ColorPickerMainWindow;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> ColorPickerMainWindow.getInstance());

    }
}
