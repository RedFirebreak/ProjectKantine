public class KantineMedewerker extends Persoon implements KortingskaartHouder {
    private final String type = "KantineMedewerker";
    private boolean magAchterKassa = true;

    /**
     * Constructor.
     * 
     * @param BSN               BurgerServiceNummer van de persoon.
     * @param voornaam          De voornaam van de persoon.
     * @param achternaam        De achternaam van de persoon.
     * @param geboorteDatum     de geboorteDatum van de persoon.
     * @param geslacht          Het geslacht van de persoon (M/V)
     */
    public KantineMedewerker(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht) {
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);
    }

    /**
     * Getter voor magAchterKassa.
     * 
     * @return magAchterKassa
     */
    public boolean getMagAchterKassa() {
        return this.magAchterKassa;
    }

    /**
     * Setter voor magAchterKassa.
     * 
     * @param magAchterKassa
     */
    public void setMagAchterKassa(boolean magAchterKassa) {
        this.magAchterKassa = magAchterKassa;
    }

    @Override
    public double geefKortingsPercentage() {
        return 35.0;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }

    @Override
    public double geefMaximum() {
        return 9001.0;
    }

    @Override
    public String toString() {
        return "{" +
            "Klant: '" + type + "'" +
            ", " + super.toString() + 
            ", magAchterKassa: '" + getMagAchterKassa() + "'" +
            "}";
    }

}