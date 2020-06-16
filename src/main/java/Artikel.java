public class Artikel {
    private String naam;
    private double prijs;
    private double korting;

    // Initialisatie = de instantievariabelen initialiseren, dat wil zeggen dat je
    // ze laat bestaan (hierboven gedaan)
    // Declaratie = de instantievariabelen een eerste waarde geven (hier in de
    // constructor)

    /**
     * Een artikel is een product wat op een dienblad gezet kan worden, zodat de
     * klant dit artikel kan afrekenen bij de kassa.
     * 
     * @param naam  De naam van het artikel, in een String.
     * @param prijs De prijs van het artikel, in een double waarde (12.34)
     */
    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = 0;
    }

    /**
     * Een artikel is een product wat op een dienblad gezet kan worden, zodat de
     * klant dit artikel kan afrekenen bij de kassa. Ook met korting! :D
     * 
     * @param naam  De naam van het artikel, in een String.
     * @param prijs De prijs van het artikel, in een double waarde (12.34)
     */
    public Artikel(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    /**
     * Een lege constructor, zodat de klasse kan worden aangeroepen zonder
     * parameters. Naam en prijs worden hierdoor gezet.
     */
    public Artikel() {
        this.naam = "Geen artikel gekozen";
        this.prijs = 9999.99;
        this.korting = 0;
    }

    @Override
    /**
     * Alle informatie teruggeven in een String, dit is in de vorm: "Artikelnaam:
     * Boter, prijs: 1.21"
     * 
     * @return Een String die de informatie van het artikel weergeeft.
     */
    public String toString() {
        return "Artikelnaam: " + naam + ", prijs: " + prijs;
    }

    /**
     * De prijs van het artikel. Het is een Double dus de prijs bestaat uit hele
     * getallen en getallen achter de komma. (bijvoorbeeld 12.50)
     * 
     * @return De prijs van het artikel.
     */
    public double getPrijs() {
        return prijs;
    }

    /**
     * De naam van het artikel, opgeslagen in een String.
     * 
     * @return De naam van het artikel.
     */
    public String getNaam() {
        return naam;
    }

    /**
     * De prijs van het artikel. Het is een Double dus de prijs bestaat uit hele
     * getallen en getallen achter de komma. (bijvoorveeld 12.50)
     * 
     * @param setPrijs De prijs van het artikel.
     */
    public void setPrijs(double setPrijs) {
        prijs = setPrijs;
    }

    /**
     * De naam van het artikel, opgeslagen in een String.
     * 
     * @param setNaam De naam van het artikel.
     */
    public void setNaam(String setNaam) {
        naam = setNaam;
    }

    /**
     * De totale korting van dit artikel
     */
    public double getKorting() {
        return this.korting;
    }

    /**
     * Zet de korting van het product.
     * 
     * @param korting de totale korting van het product
     */
    public void setKorting(double korting) {
        this.korting = korting;
    }

}