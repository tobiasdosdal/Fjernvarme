import java.io.*;
import java.util.ArrayList;

public class ForbrugerPersistens {
    public static void writeForbrugere(ArrayList<Forbruger> forbrugere) {
        String ForbrugerFile = "forbrugere.txt";

        try (FileWriter writer = new FileWriter(ForbrugerFile, false)) {
            for (Forbruger p : forbrugere) {
                String navn = p.getNavn();
                int maalerNr = p.getMaalerNr();

                writer.append(navn).append(",").append(String.valueOf(maalerNr)).append("\n");
            }
            System.out.println("Forbrugere file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readForbrugere() {
        String komma = ",";
        String line = "";
        String forbrugerFile = "forbrugere.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(forbrugerFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(komma);
                System.out.println("Navn: " + data[0] + ", Måler Nr: " + data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}