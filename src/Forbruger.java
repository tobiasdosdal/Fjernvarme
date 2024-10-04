import java.util.ArrayList;

public class Forbruger {
    private String navn;
    private int maalerNr;
    private int nyAflaesning;
    private int forrigeAflaesning;
    private int maalerMax;

    public Forbruger() {}

    public Forbruger(String navn, int maalerNr, int maalerMax) {
        this.navn = navn;
        this.maalerNr = maalerNr;
        this.maalerMax = maalerMax;
    }

    public int getMaalerNr() {
        return maalerNr;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }


    public void aflaesMaaler(int aflaesning) {
        forrigeAflaesning = nyAflaesning;
        nyAflaesning = aflaesning;
    }

    public int beregnForbrug() {
        if (nyAflaesning >= forrigeAflaesning) {
            return nyAflaesning - forrigeAflaesning;
        } else {
            return (maalerMax - forrigeAflaesning) + nyAflaesning;
        }
    }
}