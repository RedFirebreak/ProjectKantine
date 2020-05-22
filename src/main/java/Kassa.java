public class Kassa {

    KassaRij kassaRij = new KassaRij();
    Double totaalPrijs = 0.0;
    int aantalArtikelen = 0;

    /**
     * Constructor.
     * 
     * @param kassaRij De kassaRij die bij de kassa hoort.
     */
    public Kassa(KassaRij kassaRij) {
        this.kassaRij = kassaRij;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de
     * controletotalen die voor de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        aantalArtikelen += klant.getAantalArtikelen();
        totaalPrijs += klant.getTotaalPrijs();
        System.out.println("Aantal artikelen: " + klant.getAantalArtikelen());
        System.out.println("Totaalprijs: " + klant.getTotaalPrijs());
        //TODO klant uit kassaRij verwijderen?
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment
     * dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
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
}
