public class Kantine {

    private Kassa kassa;
    private KassaRij kassaRij;
    private double totaalBedrag;
    private int aantalArtikelen;
    private KantineAanbod kantineAanbod;

    /**
     * Constructor, zet de waarden voor de kassa op 0 en maak een kassa en een rij aan.
     */
    public Kantine() {
        resetKassa();
        kassaRij = new KassaRij();
        kassa = new Kassa(kassaRij);
    }

    /**
     * In deze methode wordt een dienblad met artikelen in de kassarij geplaatst.
     * 
     * @param dienblad Het dienblad waar producten aan toegevoegd worden.
     * @param artikelnamen Een lijst van artikelen die aan het dienblad toegevoegd moeten worden.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for(int i = 0; i < artikelnamen.length; i++) {
            dienblad.voegToe(kantineAanbod.getArtikel(artikelnamen[i]));
        }
        kassaRij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     * Het is handiger om een while-loop te doen dan een for-loop omdat we een methode isErEenRij is.
     */
    public void verwerkRijVoorKassa() {
        while (kassaRij.erIsEenRij()) {
            totaalBedrag += kassaRij.eerstePersoonInRij().getTotaalPrijs();
            aantalArtikelen += kassaRij.eerstePersoonInRij().getAantalArtikelen();
            kassa.rekenAf(kassaRij.eerstePersoonInRij());
        }
        //TODO lijkt me weinig.. maar maybe goed.
    }

    /**
     * Deze methode telt hoeveel geld er in de kassa zit.
     * TODO docent: totaal voor altijd of per dag?
     * @return De hoeveelheid geld in de kassa.
     */
    public double hoeveelheidGeldInKassa() {
        return totaalBedrag;
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     * TODO docent: aantal voor altijd of per dag?
     * @return Het aantal gepasseerde artikelen.
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
    public void resetKassa() {
        totaalBedrag = 0.0;
        aantalArtikelen = 0;
    }

    /**
     * Methode om het kantineAanbod te zetten.
     * 
     * @param kantineAanbod Het hele kantineaanbod.
     */
    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }

    /**
     * Methode om het kantineAanbod te verkrijgen.
     * 
     * @return Het gehele kantineAanbod.
     */
    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }
}
