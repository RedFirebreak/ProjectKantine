public class Docent extends Persoon implements KortingskaartHouder {
    String type = "Docent";
    String vierLetterCode;
    String afdeling;

    /**
     * Constructor.
     * 
     * @param BSN               BurgerServiceNummer van de persoon.
     * @param voornaam          De voornaam van de persoon.
     * @param achternaam        De achternaam van de persoon.
     * @param geboorteDatum     de geboorteDatum van de persoon.
     * @param geslacht          Het geslacht van de persoon (M/V)
     * @param vierLetterCode    De vierlettercode van de Docent
     * @param afdeling          De afdeling van de Docent
     */
    public Docent(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, String vierLetterCode, String afdeling) {
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);
        this.vierLetterCode = vierLetterCode;
        this.afdeling = afdeling;
    }

    /**
     * Getter voor vierlettercode
     * @return
     */
    public String getVierLetterCode() {
        return this.vierLetterCode;
    }

    /**
     * Setter voor vierlettercode
     * @param vierLetterCode
     */
    public void setVierLetterCode(String vierLetterCode) {
        this.vierLetterCode = vierLetterCode;
    }

    /**
     * Getter voor afdeling
     * @return
     */
    public String getAfdeling() {
        return this.afdeling;
    }

    /**
     * Setter voor afdeling
     * @param afdeling
     */
    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    @Override
    public double geefKortingsPercentage() {
        return 25.0;
    }

    @Override
    public boolean heeftMaximum() {
        return true;
    }

    @Override
    public double geefMaximum() {
        return 1.00;
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
            " vierLetterCode='" + getVierLetterCode() + "'" +
            ", afdeling='" + getAfdeling() + "'" +
            "}";
    }

}