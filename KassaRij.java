import java.util.ArrayList;
import java.util.LinkedList;

public class KassaRij {

    ArrayList<Dienblad> wachtRij = new ArrayList<Dienblad>();

    /**
     * Constructor
     */
    public KassaRij() {
        // method body omitted
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        wachtRij.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en retourneren. Als er
     * niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        Dienblad eerstePersoonInRij = new Dienblad();
        eerstePersoonInRij = (Dienblad) wachtRij.get(0);
        return eerstePersoonInRij;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        boolean erIsEenRij = false;
        if(wachtRij.size()>0) {
            erIsEenRij = true;
        }
        return erIsEenRij;
    }
}
