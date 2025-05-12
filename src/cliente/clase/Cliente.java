package cliente.clase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public String consultarEstudiante(String ipServidor, int puerto, String cedula) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        byte[] bufferEnvio = cedula.getBytes();
        InetAddress direccionServidor = InetAddress.getByName(ipServidor);

        DatagramPacket paqueteSalida = new DatagramPacket(
                bufferEnvio, bufferEnvio.length, direccionServidor, puerto);
        socket.send(paqueteSalida);

        byte[] bufferEntrada = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(paqueteEntrada);

        String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
        socket.close();
        return respuesta;
    }
}
