package ActividadSockets.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
    public static final int PUERTO = 2017;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("      APLICACI�N DE SERVIDOR      ");
        System.out.println("----------------------------------");

        //ServidorSocket -> Este objeto es el que estar� escuchando peticiones por un puerto
        //y creara un objeto socket por cada peticion

        //Entrada de datos. Es el canal de entrada del servidor, es decir, el canal por
        //el cual el cliente nos va a mandar la informaci�n.
        InputStreamReader entrada = null;
        //Salida de datos. Es el canal de salida del servidor, es decir, el canal por
        //el cual vamos a enviar informaci�n al cliente.
        PrintStream salida = null;

        //Notese como ahora:
        //1. El PrintStream del cliente es el InputStreamReader del servidor
        //2. El PrintStream del servidor es el InputStreamReader del cliente

        //Socket -> es la clase que nos va a permitir comunicarnos con el cliente,
        //en este caso no lo crearemos nosotros, sino que sera el SocketServer quien
        //lo cree cuando acepte una peticion de un cliente.
        Socket socketAlCliente = null;

        InetSocketAddress direccion = new InetSocketAddress(PUERTO);

        //En este caso no podemos hacer la declaracion try-with-resources como antes
        //ya que el servidor es un hilo que no para nunca y por cada peticion
        //crea un nuevo objeto Socket a partir del objeto ServerSocket, es decir,
        //sera el objeto ServerSocket el que nos crerara el objeto Socket por nosostros
        try (ServerSocket serverSocket = new ServerSocket()) {

            //Decimos al server socket que escuche peticiones desde el puerto
            //que hayamos establecido
            serverSocket.bind(direccion);

            //Vamos a llevar la cuenta del numero de peticiones que nos llegan
            int peticion = 0;

            //Estamos continuamente escuchando, es lo normal dentro del comportamiento
            //de un servidor, un programa que no para nunca
            boolean flag = true;
            socketAlCliente = serverSocket.accept();
            while (flag) {

                System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);

                //En este punto, se parara el programa, hasta que entre la peticion de
                //un cliente, y sera en ese momento cuando se cree un objeto Socket
                System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");

                entrada = new InputStreamReader(socketAlCliente.getInputStream());
                BufferedReader bf = new BufferedReader(entrada);

                //El servidor se quedará aquí parado hasta que el cliente nos mande
                //informacion, es decir, cuando haga un salida.println(INFORMACION);
                String stringRecibido = bf.readLine();//3-4

                //Hay que tener en cuenta que toda comunicacion entre cliente y servidor
                //esta en formato de cadena de texto
                System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);
                //Como sabemos que el cliente nos envia un 3-7, hacemos un split por "-"
                //para obtener la información.
                if (stringRecibido.contains("_")) {//suma
                    String[] operadores = stringRecibido.split("_");
                    int iNumero1 = Integer.parseInt(operadores[0]);//3
                    int iNumero2 = Integer.parseInt(operadores[1]);//4
                    int resultado = iNumero1 + iNumero2;//7
                    System.out.println("SERVIDOR: La suma de los numeros es: " + resultado);
                    salida = new PrintStream(socketAlCliente.getOutputStream());
                    salida.println(resultado);
                } else if (stringRecibido.contains("-")) {//resta
                    String[] operadores = stringRecibido.split("-");
                    int iNumero1 = Integer.parseInt(operadores[0]);//3
                    int iNumero2 = Integer.parseInt(operadores[1]);//4
                    int resultado = iNumero1 - iNumero2;//7
                    System.out.println("SERVIDOR: La resta de los numeros es: " + resultado);
                    salida = new PrintStream(socketAlCliente.getOutputStream());
                    salida.println(resultado);

                } else if (stringRecibido.contains("m")) {//multiplicacion
                    String[] operadores = stringRecibido.split("m");
                    int iNumero1 = Integer.parseInt(operadores[0]);//3
                    int iNumero2 = Integer.parseInt(operadores[1]);//4
                    int resultado = iNumero1 * iNumero2;//7
                    System.out.println("SERVIDOR: La multiplicacion de los numeros es: " + resultado);
                    salida = new PrintStream(socketAlCliente.getOutputStream());
                    salida.println(resultado);
                } else if (stringRecibido.contains("/")) {//division
                    String[] operadores = stringRecibido.split("/");
                    int iNumero1 = Integer.parseInt(operadores[0]);//3
                    int iNumero2 = Integer.parseInt(operadores[1]);//4
                    int resultado = iNumero1 / iNumero2;//7
                    System.out.println("SERVIDOR: La division de los numeros es: " + resultado);
                    salida = new PrintStream(socketAlCliente.getOutputStream());
                    salida.println(resultado);
                }
                //cerrar servidor?

                    else if(stringRecibido.contains("salir")){
                        System.out.println("Apagando servidor...");
                        //flag=false;
                        String resultado="Server apagado";
                        salida = new PrintStream(socketAlCliente.getOutputStream());
                        salida.println(resultado);
                        socketAlCliente.close();


                }

                //Si hemos llegado hasta aqui, cerramos las conexiones

            }
        } catch (IOException e) {
            System.err.println("SERVIDOR: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("SERVIDOR: Error -> " + e);
            e.printStackTrace();
        }
    }//FIN DEL PROGRAMA


}
