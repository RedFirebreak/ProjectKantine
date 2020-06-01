public class Student extends Persoon{
    String type = "Student";
    int studentnummer;
    String studierichting;

    /**
     * Constructor.
     * 
     * @param BSN               BurgerServiceNummer van de persoon.
     * @param voornaam          De voornaam van de persoon.
     * @param achternaam        De achternaam van de persoon.
     * @param geboorteDatum     de geboorteDatum van de persoon.
     * @param geslacht          Het geslacht van de persoon (M/V)
     * @param studentnummer     Het studentnummer van de student
     * @param studierichting    De studierichting van de student
     */
    public Student(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int studentnummer, String studierichting) {
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    /**
     * Getter voor studentnummer
     * @return het studentnummer van de student
     */
    public int getStudentnummer() {
        return this.studentnummer;
    }

    /**
     * Setter voor studentnummer
     * @param studentnummer
     */
    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    /**
     * Getter van studierichting
     * @return de studierichting van de student
     */
    public String getStudierichting() {
        return this.studierichting;
    }

    /**
     * Setter van studierichting
     * @param studierichting
     */
    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
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
            " studentnummer='" + getStudentnummer() + "'" +
            ", studierichting='" + getStudierichting() + "'" +
            "}";
    }
}