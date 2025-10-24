import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private String operator;
    private double num1, num2, result;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 22));
        add(display, BorderLayout.NORTH);

        // Buttons layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            display.setText(display.getText() + cmd);
        } else if (cmd.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = result = 0;
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
