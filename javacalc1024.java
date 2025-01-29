import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {

    private JTextField textField;
    private double number1, number2, result;
    private char operation;

    public Calculator() {
        createGUI();
    }

    private void createGUI() {
        // Set up the frame
        setTitle("Calculator");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the text field
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        add(buttonPanel, BorderLayout.CENTER);

        // Create the buttons
        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "0", ".", "=", "+"};

        for (String button : buttons) {
            JButton jButton = new JButton(button);
            jButton.addActionListener(new ButtonListener());
            buttonPanel.add(jButton);
        }

        // Create the clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                number1 = 0;
                number2 = 0;
                result = 0;
                operation = ' ';
            }
        });
        add(clearButton, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                number1 = Double.parseDouble(textField.getText());
                operation = command.charAt(0);
                textField.setText("");
            } else if (command.equals("=")) {
                number2 = Double.parseDouble(textField.getText());
                switch (operation) {
                    case '+':
                        result = number1 + number2;
                        break;
                    case '-':
                        result = number1 - number2;
                        break;
                    case '*':
                        result = number1 * number2;
                        break;
                    case '/':
                        if (number2 != 0) {
                            result = number1 / number2;
                        } else {
                            result = Double.NaN;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
            } else {
                textField.setText(textField.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}