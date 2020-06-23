import java.time.LocalDate;

public class Kassa {

    KassaRij kassaRij = new KassaRij();
    double totaalPrijs;
    int aantalArtikelen;
    double toegepasteKorting;

    /**
     * Constructor.
     * 
     * @param kassaRij De kassaRij die bij de kassa hoort.
     */
    public Kassa(KassaRij kassaRij) {
        this.kassaRij = kassaRij;
        totaalPrijs = 0.0;
        aantalArtikelen = 0;
        toegepasteKorting = 0.0;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de
     * controletotalen die voor de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param dienblad Die moet afrekenen.
     */
    public void rekenAf(Dienblad dienblad) {
        Persoon klant = dienblad.getKlant();

        Factuur factuur = new Factuur(dienblad, LocalDate.now());
        
        try {
            klant.getBetaalwijze().betaal(factuur.getTotaal());
            aantalArtikelen += factuur.getAantalArtikelen();
            toegepasteKorting += factuur.getKorting();
            totaalPrijs += factuur.getTotaal();

        } catch(TeWeinigGeldException e) {
            System.out.println(klant.getVoornaam() + " " +  klant.getAchternaam() + ".");
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment
     * dat de methode resetWaarden is aangeroepen.
     *
     * @return Het aantal artikelen dat door de kassa is gekomen.
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kassa zijn gepasseerd, vanaf
     * het moment dat de methode resetKassa is aangeroepen.
     *
     * @return Hoeveelheid geld dat betaald moet worden aan de kassa.
     */
    public double hoeveelheidGeldInKassa() {
        return totaalPrijs;
    }

    /**
     * Reset de waarden van het aantal gepasseerde artikelen en de totale
     * hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        totaalPrijs = 0.0;
        aantalArtikelen = 0;
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen.
     *
     * @return Het aantal artikelen.
     */
    public int getAantalArtikelen() {
        return aantalArtikelen;
    }

    
}
