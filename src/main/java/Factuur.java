import java.time.LocalDate;
import java.util.Iterator;
import java.util.Stack;
import java.io.Serializable;

public class Factuur implements Serializable {

    private Long id;
    private LocalDate datum;
    private double korting;
    private int aantalArtikelen;
    private double totaal;

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad dienblad) {
        Persoon klant = dienblad.getKlant();
            if ((klant instanceof KortingskaartHouder)) {
                KortingskaartHouder kortingskaartHouder = ((KortingskaartHouder) klant);
                // Zet totaalkorting op 0
                double totaalKorting = 0;
                double aanbiedingkorting = 0;
                double pashouderkorting = 0;

                // Krijg alle artikelen van het dienblad
                Stack<Artikel> artikelen = dienblad.getArtikelen();
                // Iterate om de korting te berekenen
                Iterator<Artikel> iterator = artikelen.iterator();
                while (iterator.hasNext()) {
                    Artikel artikel = iterator.next();
                    double artikelkorting = artikel.getKorting();

                    if (artikelkorting > 0) {
                        // Artikel is een dagaanbieding!
                        aanbiedingkorting += artikelkorting;
                    } else {
                        // Artikel is geen dagaanbieding, maar user heeft een kortingskaart
                        pashouderkorting += (artikel.getPrijs() * (kortingskaartHouder.geefKortingsPercentage() / 100));
                    }
                }

                // Alleen voor docent op het moment:
                if (kortingskaartHouder.heeftMaximum()) {
                    if (pashouderkorting > kortingskaartHouder.geefMaximum()) {
                        pashouderkorting = kortingskaartHouder.geefMaximum();
                    }
                }

                totaalKorting = aanbiedingkorting + pashouderkorting;
            } else {
                // Zet totaalkorting op 0
                double totaalKorting = 0;

                // Krijg alle artikelen van het dienblad
                Stack<Artikel> artikelen = dienblad.getArtikelen();
                // Iterate om de korting te berekenen
                Iterator<Artikel> iterator = artikelen.iterator();
                while (iterator.hasNext()) {
                    Artikel artikel = iterator.next();
                    totaalKorting += artikel.getKorting();
                }

                totaal += (getTotaalPrijs(dienblad) - totaalKorting);
                korting = totaalKorting;
                aantalArtikelen = dienblad.getArtikelen().size();
            }
        }

    /**
     * @return het totaalbedrag.
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting.
     */
    public double getKorting() {
        return korting;
    }

    public int getAantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen.
     *
     * @return De totaalprijs.
     */
    public double getTotaalPrijs(Dienblad artikelen) {
        double totaal = 0.0;
        Iterator<Artikel> iterator = artikelen.getArtikelen().iterator();
        while (iterator.hasNext()) {
            totaal += ((Artikel) iterator.next()).getPrijs();
        }
        return totaal;
    }

    /**
     * @return een printbaar bonnetje.
     */
    public String toString() {
        return "Datum: " + datum + 
            "Aantal artikelen: " + getAantalArtikelen() + 
            "Totaal: " + getTotaal() + 
            "Korting: " + getKorting();
    }
}