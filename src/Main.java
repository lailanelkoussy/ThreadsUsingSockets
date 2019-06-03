
public class Main {


    public static void main(String[] args) {

        PrimeAdder primeAdder = new PrimeAdder(5000);
        PrimeFinder primeFinder = new PrimeFinder("127.0.0.1", 5000);



        new Thread(primeAdder).start();
        new Thread(primeFinder).start();


    }
}
