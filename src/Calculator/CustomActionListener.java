package Calculator;

import Calculator.Windows.Calculator;

import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {
    private static JLabel display = Calculator.getDisplay();

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
}
