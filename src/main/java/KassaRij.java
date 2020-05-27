import java.util.LinkedList;
// Een linkedlist gebruikt minder memory dan een arraylist.
// Omdat deze het eerste en het laatste object in de lijst goed onthoudt.
// Terwijl een arraylist voor het laatste object door de hele list moet lopen.

public class KassaRij {

    LinkedList<Dienblad> wachtRij = new LinkedList<Dienblad>();

    /**
     * Constructor
     */
    public KassaRij() {

    }

    /**
     * Persoon sluit achter in de rij aan.
     *
     * @param klant Het dienblad van de klant, die toegevoegd moet worden.
     */
    public void sluitAchteraan(Dienblad klant) {
        wachtRij.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en
     * retourneren. Als er niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        if (!erIsEenRij()) {
            return null;
        } else {
            return wachtRij.remove(0);
        }
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        return !wachtRij.isEmpty();
    }
}