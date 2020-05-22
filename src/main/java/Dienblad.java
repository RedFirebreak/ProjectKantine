import java.util.Stack;
import java.util.Iterator;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Maak een dienblad aan, hieraan wordt een klant gekoppeld van de klasse
     * Persoon.
     */
    public Dienblad(Persoon klant) {
        artikelen = new Stack<Artikel>();
        klant = new Persoon();
        this.klant = klant;
    }

    /**
     * Lege constructor zodat de klasse zonder iets opgeroepen kan worden.
     */
    public Dienblad() {
        Datum datum = new Datum(1, 1, 1900);
        klant = new Persoon(0, "Leeg", "Leegerd", datum, 'O');
    }

    /**
     * Krijg de klant terug in een Persoon.
     * 
     * @return de klant (Persoon klasse).
     */
    public Persoon getKlant() {
        return klant;
    }

    /**
     * Zet de klant, met een klasse Persoon.
     * 
     * @param klant De klant om te zetten.
     */
    public void setKlant(Persoon klant) {
        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel Het artikel om toe te voegen.
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen.
     *
     * @return Het aantal artikelen.
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen.
     *
     * @return De totaalprijs.
     */
    public Double getTotaalPrijs() {
        Double totaal = 0.0;
        Iterator<Artikel> iterator = artikelen.iterator();
        while (iterator.hasNext()) {
            totaal += ((Artikel) iterator.next()).getPrijs();
        }
        return totaal;
    }

    /**
     * Methode om de artikelen op het dienblad op te noemen.
     */
    public void noemArtikelen() {
        Iterator<Artikel> iterator = artikelen.iterator();
        System.out.println("Het dienblad bevat de volgende artikelen: ");
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(i + ": " + iterator.next() + " ");
            i++;
        }
    }

}
