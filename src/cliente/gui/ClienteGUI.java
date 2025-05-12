package cliente.gui;

import cliente.clase.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClienteGUI extends JFrame {
    private JTextField cedulaField;
    private JTextArea resultadoArea;
    private JButton consultarButton;

    public ClienteGUI() {
        setTitle("Cliente UDP - Consulta Estudiantes");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(new JLabel("Ingrese Cédula del Estudiante:"));
        cedulaField = new JTextField();
        panel.add(cedulaField);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        consultarButton = new JButton("Consultar");
        panel.add(consultarButton);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        resultadoArea = new JTextArea(4, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(resultadoArea));

        add(panel);

        // Acción del botón
        consultarButton.addActionListener(e -> {
            String cedula = cedulaField.getText().trim();
            if (cedula.isEmpty()) {
                resultadoArea.setText("⚠ Por favor, ingrese una cédula.");
                return;
            }

            try {
                Cliente cliente = new Cliente();
                String respuesta = cliente.consultarEstudiante("127.0.0.1", 5000, cedula); // Cambiar IP si es remoto
                resultadoArea.setText(respuesta);
            } catch (Exception ex) {
                resultadoArea.setText("❌ Error al consultar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
