public class KantineMedewerker extends Persoon {
    String type = "KantineMedewerker";
    Boolean magAchterKassa = true;

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
     * Getter voor magAchterKassa
     * @return magAchterKassa
     */
    public Boolean getMagAchterKassa() {
        return this.magAchterKassa;
    }

    /**
     * Setter voor magAchterKassa
     * @param magAchterKassa
     */
    public void setMagAchterKassa(Boolean magAchterKassa) {
        this.magAchterKassa = magAchterKassa;
    }

    @Override
    public String toString() {
        return "{" +
            " Klant='" + type + "'" +
            " BSN='" + getBSN() + "'" +
            " Voornaam='" + getVoornaam() + "'" +
            " Achternaam='" + getAchternaam() + "'" +
            " Geboortedatum='" + getGeboorteDatum() + "'" +
            " Geslacht='" + getGeslacht() + "'" +
            " magAchterKassa='" + getMagAchterKassa() + "'" +
            "}";
    }

}