import java.util.ArrayList;

public class TestFjernvarme {
    public static void main(String[] args) {
        Distrikt tagensvej = new Distrikt("Tagensvej", 12.5f);

        // Generate 10 new consumers
        tagensvej.generateForbrugere(1000);

        // Print the generated forbrugere
        tagensvej.printForbrugere();

        // You can still add specific consumers if needed
        Forbruger peter = new Forbruger("Peter Hansen", 1000, 10000);
        tagensvej.addForbruger(peter);

        System.out.println("\nEfter tilføjelse af Peter:");
        tagensvej.printForbrugere();

        // Test af aflæsning og afregning for en specifik forbruger
        peter.aflaesMaaler(990);
        peter.aflaesMaaler(1025);
        System.out.println("\nPeters forbrug: " + peter.beregnForbrug() + " m3");
        System.out.println("Peters afregning: " + tagensvej.afregnForbruger(1000) + " kr");

        tagensvej.countNames();


        // Write Peter to file
        writeForbrugereToFile(tagensvej.getForbrugere());

        // Read and print all forbrugere from file
        //System.out.println("\nReading forbrugere from file:");
        //readForbrugereFromFile();

        tagensvej.printForbrugereMedMaalerNr700til799();
    }

    public static void writeForbrugereToFile(ArrayList<Forbruger> forbrugere) {
        ForbrugerPersistens.writeForbrugere(forbrugere);
    }

    public static void readForbrugereFromFile() {
        ForbrugerPersistens.readForbrugere();
    }
}