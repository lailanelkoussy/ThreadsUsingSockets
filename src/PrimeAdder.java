import java.net.*;
import java.io.*;

public class PrimeAdder implements Runnable {

    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    int port;

    // constructor with port
    public PrimeAdder(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        long inputNumber = 0;
        long sum = 0;

        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("PrimeAdder started");

            System.out.println("Waiting for PrimeFinder ...");

            socket = server.accept();
            System.out.println("PrimeFinder accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));


        } catch (IOException i) {
            System.out.println(i);
        }
        try {
            // reads message from client until "Over" is sent
            do {
                try {
                    inputNumber = in.readLong();
                    sum+=inputNumber;
                    if(isPrime(sum)) System.out.println("Found summation of primes: " + sum + "\n");

                } catch (IOException i) {
                    System.out.println(i);
                }
            } while (!(inputNumber == -1));

            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
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


