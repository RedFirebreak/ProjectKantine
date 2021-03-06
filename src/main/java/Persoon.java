public class Persoon {
    private int BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboorteDatum;
    private char geslacht;
    private Betaalwijze betaalwijze;
    private boolean kortingsKaartHouder;

    /**
     * Maak een persoon aan.
     * 
     * @param BSN           BurgerServiceNummer van de persoon.
     * @param voornaam      De voornaam van de persoon.
     * @param achternaam    De achternaam van de persoon.
     * @param geboorteDatum de geboorteDatum van de persoon.
     * @param geslacht      Het geslacht van de persoon (M/V)
     */
    public Persoon(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        checkGeslacht(geslacht);
        kortingsKaartHouder = false;
    }

    public boolean isKortingsKaartHouder() {
		return kortingsKaartHouder;
	}

	public void setKortingsKaartHouder(boolean kortingsKaartHouder) {
		this.kortingsKaartHouder = kortingsKaartHouder;
	}

	/**
     * Krijg de betaalwijze van deze persoon.
     * 
     * @return De betaalwijze van de persoon.
     */
    public Betaalwijze getBetaalwijze() {
		return betaalwijze;
	}

    /**
     * Zet de betaalwijze van de persoon.
     * 
     * @param betaalwijze De betaalwijze van de persoon.
     */
	public void setBetaalwijze(Betaalwijze betaalwijze) {
		this.betaalwijze = betaalwijze;
	}

	/**
     * Maak een leeg persoon aan.
     */
    public Persoon() {
        geslacht = 'O';
        geboorteDatum = new Datum();
    }

    /**
     * Krijg het BSN nummer van de Persoon.
     * 
     * @return Het BSN nummer.
     */
    public int getBSN() {
        return BSN;
    }

    /**
     * Krijg de voornaam van de Persoon.
     * 
     * @return De voornaam.
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Krijg de achternaam van de Persoon.
     * 
     * @return De achternaam.
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Krijg de geboortedatum van de Persoon.
     * 
     * @return De geboortedatum.
     */
    public String getGeboorteDatum() {
        return geboorteDatum.getDatumAsString();
    }

    /**
     * Krijg het geslacht van de Persoon.
     * 
     * Dit geeft het geslacht terug in "Man" of "Vrouw", niet in M of V vorm. Als
     * dit niet M of V is, geef onbekend.
     * 
     * @return Het geslacht.
     */
    public String getGeslacht() {
        if (geslacht == 'M') {
            return "Man";
        } else if (geslacht == 'V') {
            return "Vrouw";
        } else {
            return "Onbekend";
        }
    }

    /**
     * Zet het BSN nummer van de Persoon.
     * 
     * @param BSN Het nieuwe BSN nummer.
     */
    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    /**
     * Zet de voornaam van de Persoon.
     * 
     * @param voornaam De nieuwe voornaam.
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * Zet de acternaam van de Persoon.
     * 
     * @param achernaam De nieuwe achternaam.
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Zet het geslacht van de Persoon.
     * 
     * @param geslacht Het nieuwe geslacht.
     */
    public void setGeslacht(char geslacht) {
        checkGeslacht(geslacht);
    }

    /**
     * Zet de geboortedatum van de Persoon.
     * 
     * @param geboortedatum De nieuwe geboortedatum.
     */
    public void setGeboorteDatum(Datum geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    /**
     * Deze methode controleert of het geslacht bestaat.
     * 
     * Het geslacht mag alleen M of V zijn.
     * 
     * @param geslacht Het geslacht om te controleren.
     */
    public void checkGeslacht(char geslacht) {
        if (geslacht == 'M' || geslacht == 'V') {
            this.geslacht = geslacht;
        } else {
            this.geslacht = 'O';
            System.out.println(
                    "Dit is niet een geldig geslacht, we doen hier alleen maar aan 2 geslachten, M (man) en V (vrouw).");
        }
    }

    @Override
    /**
     * Geef alle gegevens van de persoon weer in één String. getGeboorteDatum en
     * getGeslacht moeten een Getter blijven i.v.m. extra checks.
     */
    public String toString() {
        return "{" + 
            "BSN: '" + BSN + "'" + 
            ", naam: '" + voornaam + " " + achternaam + "'" + 
            ", geboortedatum: '" + getGeboorteDatum() + "'" + 
            ", geslacht: '" + getGeslacht() + "'" + 
            "}";
    }
}
// Week 3 -> opgave 3b: Een super aanroep moet altijd bovenin de constructor staan
//                      omdat anders er al wijzigingen gemaakt kunnen zijn zonder de
//                      Superklasse te benaderen