import java.lang.annotation.ElementType;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Distrikt {
    private String navn;
    private float prisPrM3;
    private ArrayList<Forbruger> forbrugere;
    private int antalForbrugere;
    private Random random;
    private HashSet<Integer> usedMaalerNumbers;

    private String[] pigenavne = {"Anna", "Emma", "Ida", "Clara", "Laura", "Sofie", "Freja", "Ella", "Alma", "Karla"};
    private String[] drengenavne = {"William", "Noah", "Oscar", "Oliver", "Carl", "Victor", "Malthe", "Alfred", "Elias", "Emil"};
    private String[] efternavne = {"Nielsen", "Jensen", "Hansen", "Pedersen", "Andersen", "Christensen", "Larsen", "Sørensen", "Rasmussen", "Jørgensen"};

    public Distrikt() {
        forbrugere = new ArrayList<>();
        antalForbrugere = 0;
        random = new Random();
        usedMaalerNumbers = new HashSet<>();
    }

    public Distrikt(String navn, float prisPrM3) {
        this();
        this.navn = navn;
        this.prisPrM3 = prisPrM3;
    }

    public void printForbrugere() {
        System.out.println("Forbrugere i " + navn + " distrikt:");
        System.out.println("--------------------------------");

        // Create a copy of the forbrugere list
        ArrayList<Forbruger> sortedForbrugere = new ArrayList<>(forbrugere);

        // Bubble sort
        for (int i = 0; i < sortedForbrugere.size() - 1; i++) {
            for (int j = 0; j < sortedForbrugere.size() - i - 1; j++) {
                if (sortedForbrugere.get(j).getNavn().compareTo(sortedForbrugere.get(j + 1).getNavn()) > 0) {
                    // Swap elements
                    Forbruger temp = sortedForbrugere.get(j);
                    sortedForbrugere.set(j, sortedForbrugere.get(j + 1));
                    sortedForbrugere.set(j + 1, temp);
                }
            }
        }

        // Print the sorted list
        for (Forbruger f : sortedForbrugere) {
            System.out.printf("Navn: %-20s Målernr: %d%n", f.getNavn(), f.getMaalerNr());
        }

        System.out.println("--------------------------------");
        System.out.println("Antal forbrugere: " + forbrugere.size());
    }

    public void generateForbrugere(int antal) {
        for (int i = 0; i < antal && forbrugere.size() < 200; i++) {
            String fornavn = random.nextBoolean() ?
                    pigenavne[random.nextInt(pigenavne.length)] :
                    drengenavne[random.nextInt(drengenavne.length)];
            String efternavn = efternavne[random.nextInt(efternavne.length)];
            String fuldeNavn = fornavn + " " + efternavn;

            int maalerNr;
            do {
                maalerNr = random.nextInt(999) + 1; // 1-999
            } while (usedMaalerNumbers.contains(maalerNr));

            usedMaalerNumbers.add(maalerNr);

            int maalerMax = 10000; // Assuming a max reading of 10000 for all meters

            Forbruger nyForbruger = new Forbruger(fuldeNavn, maalerNr, maalerMax);
            addForbruger(nyForbruger);
        }
    }

    public void addForbruger(Forbruger forbruger) {
        if (forbrugere.size() < 200) {
            forbrugere.add(forbruger);
            antalForbrugere++;
        }
    }

    public void printForbrugereMedMaalerNr700til799() {
        System.out.println("Forbrugere med målernummer mellem 700 og 799:");
        boolean found = false;

        for (Forbruger forbruger : forbrugere) {
            int maalerNr = forbruger.getMaalerNr();
            if (maalerNr >= 600 && maalerNr <= 799) {
                System.out.println(forbruger.getNavn() + " - Målernr: " + maalerNr);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Ingen forbrugere fundet i dette interval.");
        }
    }

    public boolean isGirlName(String firstName) {
        return Arrays.asList(pigenavne).contains(firstName);
    }

    public void countNames() {
        int boyCount = 0;
        int girlCount = 0;

        for (Forbruger forbruger : forbrugere) {
            String fullName = forbruger.getNavn();
            String firstName = fullName.split(" ")[0];

            if (isGirlName(firstName)) {
                girlCount++;
            } else {
                boyCount++;
            }
        }

        System.out.println("Drenge: " + boyCount);
        System.out.println("Piger: " + girlCount);
    }

    public float afregnForbruger(int maalerNr) {
        for (Forbruger f : forbrugere) {
            if (f.getMaalerNr() == maalerNr) {
                return f.beregnForbrug() * prisPrM3;
            }
        }
        return -1;
    }

    public ArrayList<Forbruger> getForbrugere() {
        return new ArrayList<>(forbrugere);  // Return a copy to maintain encapsulation
    }
}
