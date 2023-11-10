package ActividadSockets.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteSocketGrafico extends JFrame {
    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClienteSocketGrafico window = new ClienteSocketGrafico();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClienteSocketGrafico() {
        // No necesitas getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(35, 56, 96, 20);
        add(textField);

        textField_1 = new JTextField();
        textField_1.setBounds(35, 104, 96, 20);
        add(textField_1);

        JButton btnSumar = new JButton("Sumar");
        btnSumar.setBounds(22, 198, 89, 23);
        add(btnSumar);

        JButton btnRestar = new JButton("Restar");
        btnRestar.setBounds(121, 198, 89, 23);
        add(btnRestar);

        JButton btnMulti = new JButton("Multiplicar");
        btnMulti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica de multiplicación aquí
            }
        });
        btnMulti.setBounds(220, 198, 89, 23);
        add(btnMulti);

        JButton btnDiv = new JButton("Dividir");
        btnDiv.setBounds(319, 198, 89, 23);
        add(btnDiv);

        JButton btnSalir = new JButton("X");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir aquí
            }
        });
        btnSalir.setBounds(373, 0, 53, 45);
        add(btnSalir);

        JLabel lblnum1 = new JLabel("Numero 1:");
        lblnum1.setBounds(35, 31, 75, 14);
        add(lblnum1);

        JLabel lblnum2 = new JLabel("Numero 2:");
        lblnum2.setBounds(35, 87, 64, 14);
        add(lblnum2);

        JLabel lblNewLabel = new JLabel("Resultado: ");
        lblNewLabel.setBounds(35, 135, 64, 14);
        add(lblNewLabel);

        JTextPane ResHolder = new JTextPane();
        ResHolder.setBounds(35, 160, 96, 20);
        ResHolder.setEditable(false);
        add(ResHolder);

        // initialize() no es necesario

        // Asigna las propiedades del marco principal
        setTitle("Cliente Socket Grafico");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Opcional, dependiendo de tus necesidades de diseño
    }
}
