package ActividadSockets.Cliente;


import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
public class ClienteSocketGrafico extends JFrame {
    JTextField num1;
    JTextField num2;
    JTextPane ResHolder;

    public static final int PUERTO = 2017;
    public static final String IP_SERVER = "localhost";
    Socket socketAlServidor = new Socket();
    String numero1;
    String numero2;
    String operandos;
    PrintStream salida;
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                try {
                    ClienteSocketGrafico window = new ClienteSocketGrafico();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public ClienteSocketGrafico() throws IOException {
        try {
            socketAlServidor = new Socket(IP_SERVER, PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
        num1 = new JTextField();
        num1.setBounds(35, 56, 96, 20);
        add(num1);

        num2 = new JTextField();
        num2.setBounds(35, 104, 96, 20);
        add(num2);


        JButton btnSumar = new JButton("Sumar");
        btnSumar.setBounds(22, 198, 89, 23);
        btnSumar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numero1 = num1.getText();
                numero2 = num2.getText();
                operandos = numero1 + "_" + numero2;
                try {
                    salida = new PrintStream(socketAlServidor.getOutputStream());
                    salida.println(operandos);

                    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
                    BufferedReader bf = new BufferedReader(entrada);

                    System.out.println("CLIENTE: Esperando al resultado del servidor...");
                    String resultado = bf.readLine(); // Espera al resultado del servidor

                    ResHolder.setText(resultado);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnSumar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(22, 230, 89, 23);
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1.setText("");
                num2.setText("");
                ResHolder.setText("");
            }
        });
        add(btnLimpiar);

        JButton btnRestar = new JButton("Restar");
        btnRestar.setBounds(121, 198, 89, 23);
        btnRestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numero1 = num1.getText();
                numero2 = num2.getText();
                operandos = numero1 + "-" + numero2;
                try {
                    salida = new PrintStream(socketAlServidor.getOutputStream());
                    salida.println(operandos);

                    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
                    BufferedReader bf = new BufferedReader(entrada);

                    System.out.println("CLIENTE: Esperando al resultado del servidor...");
                    String resultado = bf.readLine(); // Espera al resultado del servidor

                    ResHolder.setText(resultado);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnRestar);

        JButton btnMulti = new JButton("Multiplicar");
        btnMulti.setBounds(220, 198, 99, 23);
        btnMulti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numero1 = num1.getText();
                numero2 = num2.getText();
                operandos = numero1 + "m" + numero2;
                try {
                    salida = new PrintStream(socketAlServidor.getOutputStream());
                    salida.println(operandos);

                    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
                    BufferedReader bf = new BufferedReader(entrada);

                    System.out.println("CLIENTE: Esperando al resultado del servidor...");
                    String resultado = bf.readLine(); // Espera al resultado del servidor

                    ResHolder.setText(resultado);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnMulti);

        JButton btnDiv = new JButton("Dividir");
        btnDiv.setBounds(328, 198, 89, 23);
        btnDiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numero1 = num1.getText();
                numero2 = num2.getText();
                operandos = numero1 + "/" + numero2;
                try {
                    salida = new PrintStream(socketAlServidor.getOutputStream());
                    salida.println(operandos);

                    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
                    BufferedReader bf = new BufferedReader(entrada);

                    System.out.println("CLIENTE: Esperando al resultado del servidor...");
                    String resultado = bf.readLine(); // Espera al resultado del servidor

                    ResHolder.setText(resultado);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });
        add(btnDiv);

        JButton btnSalir = new JButton("X");
        btnSalir.setBounds(373, 0, 53, 45);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ResHolder.setText("Server Cerrado, hasta pronto");
                    operandos ="salir";
                //socketAlServidor.connect(direccionServidor);
                salida = new PrintStream(socketAlServidor.getOutputStream());
                salida.println(operandos);
                System.exit(0);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnSalir);

        JLabel lblnum1 = new JLabel("Numero 1:");
        lblnum1.setBounds(35, 31, 75, 14);
        add(lblnum1);

        JLabel lblnum2 = new JLabel("Numero 2:");
        lblnum2.setBounds(35, 87, 64, 14);
        add(lblnum2);

        JLabel lblNewLabel = new JLabel("Resultado: ");
        lblNewLabel.setBounds(35, 135, 305, 14);
        add(lblNewLabel);



        ResHolder = new JTextPane();
        ResHolder.setBounds(35, 160, 315, 20);
        ResHolder.setEditable(false);
        add(ResHolder);


        setTitle("Cliente Socket Grafico");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }
}
