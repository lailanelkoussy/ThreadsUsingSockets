import java.net.*;
import java.io.*;


public class PrimeFinder implements Runnable {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    long range = 1000000;
    String address;
    int port;


    public PrimeFinder(String address, int port) {
        this.address = address;
        this.port = port;

    }

    @Override
    public void run() {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("PrimeFinder is Connected\n");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }


        for (long i = 2; i < range; i++) {
            try {
                if (isPrime(i))
                    out.writeLong(i);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // close the connection
        try {
            out.writeLong(-1);
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }

    }


    public static boolean isPrime(long a) {

        if ((a == 0) && (a == 1))
            return false;

        for (long j = 2; j <= Math.sqrt(a); j++)
            if (a % j == 0)
                return false;

        return true;

    }
}
