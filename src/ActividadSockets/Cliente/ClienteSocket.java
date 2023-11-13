package ActividadSockets.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
    public static final int PUERTO = 2017;
    public static final String IP_SERVER = "localhost";

    public static void main(String[] args) throws IOException {
        String numero1;
        String numero2;
        String operandos = null;
        PrintStream salida;
        System.out.println("        APLICACI�N CLIENTE         ");
        System.out.println("-----------------------------------");

        InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
        boolean flag=true;



        Socket socketAlServidor = new Socket();
        try (Scanner sc = new Scanner(System.in))
        {
            socketAlServidor.connect(direccionServidor);
            while (flag) {
                System.out.println("Que operacion desea realizar?");
                    System.out.println("1-Sumar\n2-Restar\n3-Multiplicar\n4-Dividir\n5-Salir");
                    int eleccion = sc.nextInt();
                    sc.nextLine(); // Consumir el carácter de nueva línea

                    switch (eleccion) {
                        case 1:
                            System.out.println("CLIENTE: Introduzca los numeros a sumar");
                            numero1 = sc.nextLine();
                            numero2 = sc.nextLine();
                            operandos = numero1 + "_" + numero2;
                            //socketAlServidor.connect(direccionServidor);
                            salida = new PrintStream(socketAlServidor.getOutputStream());
                            salida.println(operandos);
                            break;
                        case 2:
                            System.out.println("CLIENTE: Introduzca los numeros a restar");
                            numero1 = sc.nextLine();
                            numero2 = sc.nextLine();
                            operandos = numero1 + "-" + numero2;
                            //socketAlServidor.connect(direccionServidor);
                            salida = new PrintStream(socketAlServidor.getOutputStream());
                            salida.println(operandos);
                            break;
                        case 3:
                            System.out.println("CLIENTE: Introduzca los numeros a multiplicar");
                            numero1 = sc.nextLine();
                            numero2 = sc.nextLine();
                            operandos = numero1 + "m" + numero2;
                            //socketAlServidor.connect(direccionServidor);
                            salida = new PrintStream(socketAlServidor.getOutputStream());
                            salida.println(operandos);
                            break;
                        case 4:
                            System.out.println("CLIENTE: Introduzca los numeros a dividir");
                            numero1 = sc.nextLine();
                            numero2 = sc.nextLine();
                            operandos = numero1 + "/" + numero2;
                            //socketAlServidor.connect(direccionServidor);
                            salida = new PrintStream(socketAlServidor.getOutputStream());
                            salida.println(operandos);
                            break;
                        case 5:
                            System.out.println("Hasta pronto!");
                            operandos ="salir";
                            //socketAlServidor.connect(direccionServidor);
                            salida = new PrintStream(socketAlServidor.getOutputStream());
                            salida.println(operandos);
                            flag=false;
                            break;
                        default:
                            System.out.println("Error, operacion no valida");
                    }


                    System.out.println("CLIENTE: Esperando a que el servidor acepte la conexi�n");
                    //socketAlServidor.connect(direccionServidor);
                    System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER
                            + " por el puerto " + PUERTO);

                    //Creamos el objeto que nos permite mandar informacion al servidor
                    //PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
                    //Mandamos la informaci�n por el Stream

                    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());

                    BufferedReader bf = new BufferedReader(entrada);

                    System.out.println("CLIENTE: Esperando al resultado del servidor...");
                    String resultado = bf.readLine();//7

                    System.out.println("CLIENTE: El resultado de la operacion es: " + resultado);//7
                }
            } catch (UnknownHostException e) {
                System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("CLIENTE: Error de entrada/salida");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("CLIENTE: Error -> " + e);
                e.printStackTrace();
            }



            System.out.println("CLIENTE: Fin del programa");
        }

}
