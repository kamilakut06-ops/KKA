package Lab3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Lab3_3 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== ВЫБЕРИТЕ ЗАДАНИЕ (GUI) ===");
                System.out.println("1 - Задание 1: Калькулятор (кнопки)");
                System.out.println("2 - Задание 2: Выбор страны (JComboBox)");
                System.out.println("3 - Задание 3: Меню и две кнопки");
                System.out.println("4 - Задание 4: Кнопочный калькулятор (Сетка)");
                System.out.println("0 - Выход");
                System.out.print("Ваш выбор: ");

                int choice = sc.nextInt();
                sc.nextLine(); // очистка буфера

                switch (choice) {
                    case 1:
                        SwingUtilities.invokeLater(() -> new Task1_Calculator());
                        break;
                    case 2:
                        SwingUtilities.invokeLater(() -> new Task2_Countries());
                        break;
                    case 3:
                        SwingUtilities.invokeLater(() -> new Task3_MenuAndButtons());
                        break;
                    case 4:
                        SwingUtilities.invokeLater(() -> new Task4_GridCalculator());
                        break;
                    case 0:
                        System.out.println("Выход...");
                        System.exit(0);
                    default:
                        System.out.println("Нет такого пункта.");
                }
            }
        }

        // =========================================================
        // ЗАДАНИЕ 1. Калькулятор
        // =========================================================
        static class Task1_Calculator extends JFrame {
            JTextField num1 = new JTextField(10);
            JTextField num2 = new JTextField(10);
            JTextField result = new JTextField(15);
            JButton btnAdd = new JButton("+");
            JButton btnSub = new JButton("-");
            JButton btnMul = new JButton("*");
            JButton btnDiv = new JButton("/");

            public Task1_Calculator() {
                super("Калькулятор");
                setSize(400, 200);
                setLayout(new FlowLayout());
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрывает только это окно

                add(new JLabel("Число 1:"));
                add(num1);
                add(new JLabel("Число 2:"));
                add(num2);
                add(new JLabel("Результат:"));
                add(result);

                result.setEditable(false);

                add(btnAdd); add(btnSub); add(btnMul); add(btnDiv);

                ActionListener calcAction = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double a = Double.parseDouble(num1.getText().trim());
                            double b = Double.parseDouble(num2.getText().trim());
                            double res = 0;

                            if (e.getSource() == btnAdd) res = a + b;
                            else if (e.getSource() == btnSub) res = a - b;
                            else if (e.getSource() == btnMul) res = a * b;
                            else if (e.getSource() == btnDiv) {
                                if (b == 0) { result.setText("Деление на 0!"); return; }
                                res = a / b;
                            }
                            result.setText(String.valueOf(res));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Ошибка ввода!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                };

                btnAdd.addActionListener(calcAction);
                btnSub.addActionListener(calcAction);
                btnMul.addActionListener(calcAction);
                btnDiv.addActionListener(calcAction);

                setVisible(true);
            }
        }

        // =========================================================
        // ЗАДАНИЕ 2. Выбор страны
        // =========================================================
        static class Task2_Countries extends JFrame {
            JComboBox<String> countries;
            JLabel infoLabel;

            public Task2_Countries() {
                super("Hello Swing");
                setSize(300, 200);
                setLayout(new FlowLayout());
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String[] countryList = {"Australia", "China", "England", "Russia"};
                countries = new JComboBox<>(countryList);
                infoLabel = new JLabel("Выберите страну");

                add(countries); add(infoLabel);

                countries.addActionListener(e -> {
                    String selected = (String) countries.getSelectedItem();
                    switch (selected) {
                        case "Australia": infoLabel.setText("Столица: Канберра"); break;
                        case "China": infoLabel.setText("Столица: Пекин"); break;
                        case "England": infoLabel.setText("Столица: Лондон"); break;
                        case "Russia": infoLabel.setText("Столица: Москва"); break;
                    }
                });

                setVisible(true);
            }
        }

        // =========================================================
        // ЗАДАНИЕ 3. Меню и кнопки
        // =========================================================
        static class Task3_MenuAndButtons extends JFrame {
            public Task3_MenuAndButtons() {
                super("Hello Swing");
                setSize(400, 300);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JMenuBar menuBar = new JMenuBar();

                JMenu fileMenu = new JMenu("File");
                fileMenu.add(new JMenuItem("Save"));
                fileMenu.add(new JMenuItem("Exit"));

                JMenu editMenu = new JMenu("Edit");
                editMenu.add(new JMenuItem("Copy"));
                editMenu.add(new JMenuItem("Cut"));
                editMenu.add(new JMenuItem("Paste"));

                JMenu helpMenu = new JMenu("Help");

                menuBar.add(fileMenu); menuBar.add(editMenu); menuBar.add(helpMenu);
                setJMenuBar(menuBar);

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new FlowLayout());

                JTextField textField = new JTextField(20);
                JButton btn1 = new JButton("Button 1");
                JButton btn2 = new JButton("Button 2");

                mainPanel.add(btn1); mainPanel.add(btn2); mainPanel.add(textField);
                add(mainPanel);
                setVisible(true);
            }
        }

        // =========================================================
        // ЗАДАНИЕ 4. Кнопочный калькулятор
        // =========================================================
        static class Task4_GridCalculator extends JFrame {
            JTextField display;
            double num1 = 0;
            String operator = "";

            public Task4_GridCalculator() {
                super("Simple Calculator");
                setSize(300, 400);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLayout(new BorderLayout());

                display = new JTextField();
                display.setEditable(false);
                display.setHorizontalAlignment(JTextField.RIGHT);
                display.setFont(new Font("Arial", Font.BOLD, 24));
                add(display, BorderLayout.NORTH);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

                String[] buttons = {
                        "7", "8", "9", "/",
                        "4", "5", "6", "*",
                        "1", "2", "3", "-",
                        "0", ".", "=", "+"
                };

                ActionListener listener = e -> {
                    String cmd = e.getActionCommand();

                    if (cmd.matches("[0-9\\.]")) {
                        display.setText(display.getText() + cmd);
                    } else if (cmd.equals("=")) {
                        double num2 = Double.parseDouble(display.getText());
                        double res = 0;
                        switch (operator) {
                            case "+": res = num1 + num2; break;
                            case "-": res = num1 - num2; break;
                            case "*": res = num1 * num2; break;
                            case "/": res = num1 / num2; break;
                        }
                        display.setText(String.valueOf(res));
                        operator = "";
                    } else {
                        num1 = Double.parseDouble(display.getText());
                        operator = cmd;
                        display.setText("");
                    }
                };

                for (String text : buttons) {
                    JButton btn = new JButton(text);
                    btn.setFont(new Font("Arial", Font.BOLD, 18));
                    btn.addActionListener(listener);
                    buttonPanel.add(btn);
                }

                add(buttonPanel, BorderLayout.CENTER);
                setVisible(true);
            }
        }
    }

