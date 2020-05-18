public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar
     * gekoppeld. Maak twee Artikelen aan en plaats deze op het dienblad. Tenslotte
     * sluit de Persoon zich aan bij de rij voor de kassa.
     */
    public void loopPakSluitAan() {
        // method body omitted
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        // while () {
        // omitted
        // }
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    public void hoeveelheidGeldInKassa() {
        // method body omitted

        // RETURN DOUBLE. MADE VOID TO COMPILE
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public void aantalArtikelen() {
        // method body omitted

        // RETURN INT. MADE VOID TO COMPILE
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt"
     * de inhoud van de kassa.
     */
    public void resetKassa() {
        // method body omitted
    }

    /**
     * TODO WAS UNDEFINED
     */
    public void setKantineAanbod(KantineAanbod kantineaanbod) {
        // method body omitted
    }
}
