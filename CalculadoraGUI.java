import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private JTextField pantalla;
    private double num1, num2, resultado;
    private String operador;

    public CalculadoraGUI() {
        // Configuración básica de la ventana
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Creación de la pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);

        // Creación de los botones y disposición
        JPanel panelBotones = new JPanel(new GridLayout(4, 4));
        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String boton : botones) {
            JButton btn = new JButton(boton);
            btn.addActionListener(new BotonClickListener());
            panelBotones.add(btn);
        }

        add(panelBotones);

        // Hacer visible la ventana
        setVisible(true);
    }

    private class BotonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton origen = (JButton) e.getSource();
            String textoBoton = origen.getText();

            // Determinar la acción a realizar
            if (textoBoton.matches("[0-9]")) {
                pantalla.setText(pantalla.getText() + textoBoton);
            } else if (textoBoton.matches("[+\\-*/]")) {
                num1 = Double.parseDouble(pantalla.getText());
                operador = textoBoton;
                pantalla.setText("");
            } else if (textoBoton.equals("=")) {
                num2 = Double.parseDouble(pantalla.getText());
                realizarOperacion();
                pantalla.setText(String.valueOf(resultado));
            } else if (textoBoton.equals(".")) {
                if (!pantalla.getText().contains(".")) {
                    pantalla.setText(pantalla.getText() + ".");
                }
            }
        }

        private void realizarOperacion() {
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(null, "No es posible dividir por cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}
