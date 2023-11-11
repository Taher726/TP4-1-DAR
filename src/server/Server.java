package server;
import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 1234;
    private static byte[] data = new byte[1024];

    public static void main(String[] args) throws Exception {
        // Crée un socket UDP pour écouter sur le port 1234
        DatagramSocket socket = new DatagramSocket(PORT);
        System.out.println("Lancement du serveur");

        while (true) {
            // Prépare un DatagramPacket pour recevoir les données
            DatagramPacket paquet = new DatagramPacket(data, data.length);

            // Attends la réception d'un paquet
            socket.receive(paquet);

            // Convertit les données du paquet en une chaîne de caractères
            String msg = new String(paquet.getData(), 0, paquet.getLength());

            // Affiche l'adresse IP de l'expéditeur et le message reçu
            System.out.println(paquet.getAddress() + " : " + msg);

            // Prépare une réponse
            String reponse = "Bienvenue " + msg;

            // Crée un DatagramPacket pour envoyer la réponse à l'expéditeur
            DatagramPacket envoi = new DatagramPacket(reponse.getBytes(), reponse.length(), paquet.getAddress(), paquet.getPort());

            // Envoie la réponse
            socket.send(envoi);
        }
    }
}
