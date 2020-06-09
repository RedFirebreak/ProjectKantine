import java.util.*;

public class Kassa {

    KassaRij kassaRij = new KassaRij();
    double totaalPrijs = 0.0;
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
     * @param dienblad Die moet afrekenen.
     */
    public void rekenAf(Dienblad dienblad) {
        // Als de klant kan betalen, rekenen we af, anders niet.
        Persoon klant = dienblad.getKlant();
        try {
            if((klant instanceof KantineMedewerker)) {
                KantineMedewerker kantineMedewerker = ((KantineMedewerker)klant);

                double korting = (getTotaalPrijs(dienblad) * (kantineMedewerker.geefKortingsPercentage() / 100));
                totaalPrijs += (getTotaalPrijs(dienblad) - korting);
                aantalArtikelen += dienblad.getArtikelen().size();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            } else if((klant instanceof Docent)) {
                Docent docent = ((Docent)klant);

                double korting = (getTotaalPrijs(dienblad) * (docent.geefKortingsPercentage() / 100));
                totaalPrijs += (getTotaalPrijs(dienblad) - korting);
                aantalArtikelen += dienblad.getArtikelen().size();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            } else {
                totaalPrijs += getTotaalPrijs(dienblad);
                aantalArtikelen += dienblad.getArtikelen().size();
                klant.getBetaalwijze().betaal(getTotaalPrijs(dienblad));
            }
        } catch(TeWeinigGeldException e) {
            System.out.println(klant.getVoornaam() + " " +  klant.getAchternaam() + ": Betaling gefaald, " + e + ".");
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
}
