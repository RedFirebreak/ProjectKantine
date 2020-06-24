import java.time.LocalDate;
import java.util.*;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "datum", nullable = true)
    private LocalDate datum;

    @Column(name = "korting", nullable = true)
    private double korting;

    @Column(name = "aantalArtikelen", nullable = true)
    private int aantalArtikelen;

    @Column(name = "totaal", nullable = true)
    private double totaal;

    @OneToMany(targetEntity = FactuurRegel.class, mappedBy = "factuur",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FactuurRegel> regels = new ArrayList<FactuurRegel>();

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad dienblad) {
        Persoon klant = dienblad.getKlant();
            if ((klant instanceof KortingskaartHouder)) {
                KortingskaartHouder kortingskaartHouder = ((KortingskaartHouder) klant);
                // Zet totaalkorting op 0
                double totaalKorting = 0;
                double aanbiedingkorting = 0;
                double pashouderkorting = 0;

                // Krijg alle artikelen van het dienblad
                Stack<Artikel> artikelen = dienblad.getArtikelen();
                // Iterate om de korting te berekenen
                Iterator<Artikel> iterator = artikelen.iterator();
                while (iterator.hasNext()) {
                    Artikel artikel = iterator.next();
                    double artikelkorting = artikel.getKorting();

                    if (artikelkorting > 0) {
                        // Artikel is een dagaanbieding!
                        aanbiedingkorting += artikelkorting;
                    } else {
                        // Artikel is geen dagaanbieding, maar user heeft een kortingskaart
                        pashouderkorting += (artikel.getPrijs() * (kortingskaartHouder.geefKortingsPercentage() / 100));
                    }

                    // Maak een FactuurRegel aan
                    regels.add(new FactuurRegel(this, artikel));

                }

                // Alleen voor docent op het moment:
                if (kortingskaartHouder.heeftMaximum()) {
                    if (pashouderkorting > kortingskaartHouder.geefMaximum()) {
                        pashouderkorting = kortingskaartHouder.geefMaximum();
                    }
                }

                totaalKorting = aanbiedingkorting + pashouderkorting;
            } else {
                // Zet totaalkorting op 0
                double totaalKorting = 0;

                // Krijg alle artikelen van het dienblad
                Stack<Artikel> artikelen = dienblad.getArtikelen();
                // Iterate om de korting te berekenen
                Iterator<Artikel> iterator = artikelen.iterator();
                while (iterator.hasNext()) {
                    Artikel artikel = iterator.next();
                    totaalKorting += artikel.getKorting();

                    // Maak een FactuurRegel aan
                    regels.add(new FactuurRegel(this, artikel));
                }

                totaal += (getTotaalPrijs(dienblad) - totaalKorting);
                korting = totaalKorting;
                aantalArtikelen = dienblad.getArtikelen().size();
            }

        }

    /**
     * @return het totaalbedrag.
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting.
     */
    public double getKorting() {
        return korting;
    }

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

    /**
     * @return een printbaar bonnetje.
     */
    public String toString() {
        String returnstring =   "Datum: " + datum + 
                                "Aantal artikelen: " + getAantalArtikelen() + 
                                "Totaal: " + getTotaal() + 
                                "Korting: " + getKorting();
        
        int artikelcount    = 0;
        Iterator<FactuurRegel> iterator = regels.iterator();
        while (iterator.hasNext()) {
            artikelcount++;
            FactuurRegel artikel = iterator.next();
            returnstring += "Artikel #" + artikelcount + " " +artikel.toString();
        }   
        return returnstring;
    }
}