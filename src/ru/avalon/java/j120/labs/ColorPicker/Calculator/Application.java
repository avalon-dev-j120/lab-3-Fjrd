package ru.avalon.java.j120.labs.ColorPicker.Calculator;

import ru.avalon.java.j120.labs.ColorPicker.Calculator.Windows.CalcMainWindow;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> CalcMainWindow.getInstance());
    }
}