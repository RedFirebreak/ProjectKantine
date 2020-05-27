public class Kantine {

    private Kassa kassa;
    private KassaRij kassaRij;
    private KantineAanbod kantineAanbod;

    // artikelen
    private String[] artikelnamen = new String[] { "Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap" };

    // prijzen
    private double[] artikelprijzen = new double[] { 1.50, 2.10, 1.65, 1.65 };

    // maxaantallen
    private int[] hoeveelheden = new int[] { 10000, 15000, 2000, 12000 };

    /**
     * Constructor, zet de waarden voor de kassa op 0 en maak een kassa en een rij
     * aan.
     */
    public Kantine() {
        // Maak een aanbod artikelen
        kantineAanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        // Maak een kassarij aan
        kassaRij = new KassaRij();

        // Maak een kassa aan
        kassa = new Kassa(kassaRij);

        // Zet de kassa op 0
        kassa.resetKassa();
    }

    /**
     * In deze methode wordt een dienblad met artikelen in de kassarij geplaatst.
     * 
     * @param dienblad     Het dienblad waar producten aan toegevoegd worden.
     * @param artikelnamen Een lijst van artikelen die aan het dienblad toegevoegd
     *                     moeten worden.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for (int i = 0; i < artikelnamen.length; i++) {
            dienblad.voegToe(kantineAanbod.getArtikel(artikelnamen[i]));
        }
        kassaRij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af. Het is handiger om een
     * while-loop te doen dan een for-loop omdat we een methode isErEenRij is.
     */
    public void verwerkRijVoorKassa() {
        while (kassaRij.erIsEenRij()) {
            Dienblad eerstePersoonDienblad = kassaRij.eerstePersoonInRij(); // rij vgm...

            // totaalBedrag += kassa.getTotaalPrijs(eerstePersoonDienblad);
            // aantalArtikelen += kassa.getAantalArtikelen();

            kassa.rekenAf(eerstePersoonDienblad);
        }
    }

    /**
     * Deze methode telt hoeveel geld er in de kassa zit.
     * 
     * @return De hoeveelheid geld in de kassa.
     */
    public double getTotaalbedrag() {
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     * 
     * @return Het aantal gepasseerde artikelen.
     */
    public int getAantalArtikelen() {
        return kassa.aantalArtikelen();
    }

    /**
     * Zet de kassa op 0.
     */
    public void resetKassa() {
        kassa.resetKassa();
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
