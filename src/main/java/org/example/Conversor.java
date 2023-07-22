package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Conversor extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JButton convertButton;
    private JTextArea resultTextArea;

    private static final double[] valores = {1.0, 0.85, 4200.0, 21.0, 1.25}; // Conversion rates from USD

    private static final String[] monedas = {"USD", "EUR", "COP", "MXN", "CAD"};

    public Conversor() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10)); // Adding gaps between components

        // Input Text Field
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.RIGHT); // Aligning text to the right

        // Currency Options
        fromCurrencyComboBox = new JComboBox<>(monedas);
        toCurrencyComboBox = new JComboBox<>(monedas);

        // Convert Button
        convertButton = new JButton("Convertir");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Result Text Area
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        // Add Components to the Frame
        add(inputField);
        add(fromCurrencyComboBox);
        add(toCurrencyComboBox);
        add(convertButton);
        add(new JScrollPane(resultTextArea)); // Adding a scroll pane to the text area

        setSize(600, 400); // Set the size of the window to be twice as large (width: 600, height: 400)
        setLocationRelativeTo(null); // Centering the frame on the screen
    }

    private void convertCurrency() {
        String amountStr = inputField.getText();
        if (amountStr.isEmpty()) {
            resultTextArea.setText("Por favor ingrese la cantidad a convertir.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            int fromCurrencyIndex = fromCurrencyComboBox.getSelectedIndex();
            int toCurrencyIndex = toCurrencyComboBox.getSelectedIndex();

            double convertedAmount = amount * (valores[toCurrencyIndex] / valores[fromCurrencyIndex]);

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String resultado = decimalFormat.format(convertedAmount);

            resultTextArea.setText("Resultado: " + resultado + " " + monedas[toCurrencyIndex]);
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Ingrese una cantidad numerica válida.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Conversor().setVisible(true);
            }
        });
    }
}
