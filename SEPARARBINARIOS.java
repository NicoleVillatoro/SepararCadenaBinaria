/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package separarbinarios;

/**
 *
 * @author Isel Mx
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class SEPARARBINARIOS extends JFrame {
    private JTextField binaryInputField;
    private JTextField separationInputField;
    private JButton separateButton;
    private JButton copyButton;
    private JTextArea resultTextArea;

    public SEPARARBINARIOS() {
        setTitle("Separador de Dígitos Binarios");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel binaryLabel = new JLabel("Introduce la cadena de dígitos binarios:");
        binaryInputField = new JTextField(20);
        JLabel separationLabel = new JLabel("Separar cada:");
        separationInputField = new JTextField(5);
        separateButton = new JButton("Separar");
        copyButton = new JButton("Copiar al Portapapeles");

        inputPanel.add(binaryLabel);
        inputPanel.add(binaryInputField);
        inputPanel.add(separationLabel);
        inputPanel.add(separationInputField);
        inputPanel.add(separateButton);
        inputPanel.add(copyButton);

        resultTextArea = new JTextArea(5, 20);
        resultTextArea.setEditable(false);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        separateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                separateBinaryString();
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyToClipboard();
            }
        });

        add(mainPanel);
    }

    private void separateBinaryString() {
        String binaryInput = binaryInputField.getText();
        String separationInput = separationInputField.getText();

        try {
            int separation = Integer.parseInt(separationInput);
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < binaryInput.length(); i++) {
                result.append(binaryInput.charAt(i));
                if ((i + 1) % separation == 0 && i != binaryInput.length() - 1) {
                    result.append(" ");
                }
            }

            resultTextArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido para la separación.");
        }
    }

    private void copyToClipboard() {
        String resultText = resultTextArea.getText();
        StringSelection selection = new StringSelection(resultText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Resultado copiado al portapapeles.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SEPARARBINARIOS app = new SEPARARBINARIOS();
                app.setVisible(true);
            }
        });
    }
}
