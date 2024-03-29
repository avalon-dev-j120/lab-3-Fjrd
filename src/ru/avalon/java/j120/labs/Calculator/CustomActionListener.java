package ru.avalon.java.j120.labs.Calculator;

import ru.avalon.java.j120.labs.Calculator.Windows.CalcMainWindow;

import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {
    private static JLabel display = CalcMainWindow.getDisplay();
    private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        switch (command){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
            case "+":
            case "-":
            case "*":
            case "/": {
                if (display.getText().equals("0"))
                    display.setText("");
                display.setText(display.getText() + actionEvent.getActionCommand());
                break;
            }
            case "CE": {
                display.setText("0");
                break;
            }
            case "=": {
                display.setText(calc(display.getText()));
                copyToClipboard(display.getText());
            }
        }
    }

    private static String calc(String expression) {
        String result;
        try {
            result = new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString();
        } catch (Exception ex) {
            result = "NaN";
        }
        return result;
    }
    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
}
