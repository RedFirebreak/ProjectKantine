import java.util.HashMap;
import java.util.ArrayList;

public class KantineAanbod {
    // interne opslag voorraad
    private HashMap<String, ArrayList<Artikel>> aanbod;
    private HashMap<String, Integer> startVoorraad;
    private HashMap<String, Double> prijzen;

    /**
     * Constructor, de dimenties van deze arrays moeten gelijk zijn.
     * 
     * @param artikelnaam Een array met artikelnamen.
     * @param prijs       Een array met prijzen.
     * @param hoeveelheid Een array met hoeveelheden.
     */
    public KantineAanbod(String[] artikelnaam, double[] prijs, int[] hoeveelheid) {
        aanbod = new HashMap<String, ArrayList<Artikel>>();
        startVoorraad = new HashMap<String, Integer>();
        prijzen = new HashMap<String, Double>();
        for (int i = 0; i < artikelnaam.length; i++) {
            ArrayList<Artikel> artikelen = new ArrayList<Artikel>();
            for (int j = 0; j < hoeveelheid[i]; j++) {
                artikelen.add(new Artikel(artikelnaam[i], prijs[i]));
            }
            startVoorraad.put(artikelnaam[i], hoeveelheid[i]);
            prijzen.put(artikelnaam[i], prijs[i]);
            aanbod.put(artikelnaam[i], artikelen);
        }
    }

    /**
     * Methode om de de voorraad voor een product aan te vullen.
     * 
     * @param productnaam Het product dat aangevult moet worden.
     */
    private void vulVoorraadAan(String productnaam) {
        ArrayList<Artikel> huidigeVoorraad = aanbod.get(productnaam);
        int startHoeveelheid = startVoorraad.get(productnaam);
        int huidigeHoeveelheid = huidigeVoorraad.size();
        double prijs = prijzen.get(productnaam);
        for (int j = huidigeHoeveelheid; j < startHoeveelheid; j++) {
            huidigeVoorraad.add(new Artikel(productnaam, prijs));
        }
        aanbod.put(productnaam, huidigeVoorraad);
    }

    /**
     * Private methode om de lijst van artikelen te krijgen op basis van de naam van
     * het artikel.
     * 
     * @param productnaam Het artikel om een lijst van te krijgen.
     * @return Een ArrayList met artikelen erin die overeenkomen met de productnaam,
     *         of null als het artikel niet bestaat.
     */
    private ArrayList<Artikel> getArrayList(String productnaam) {
        return aanbod.get(productnaam);
    }

    /**
     * Private methode om een Artikel van de stapel artikelen af te pakken.
     * 
     * @param stapel De stapel om een artikel af te pakken.
     * @return Het eerste artikel uit de ArrayList stapel, of null als de stapel
     *         leeg is.
     */
    private Artikel getArtikel(ArrayList<Artikel> stapel) {
        if (stapel == null) {
            return null;
        }
        if (stapel.size() == 0) {
            return null;
        } else {
            Artikel a = stapel.get(0);
            stapel.remove(0);
            if (stapel.size() <= 10)
                vulVoorraadAan(a.getNaam());
            return a;
        }
    }

    /**
     * Publieke methode om een artikel via naam van de stapel te pakken. Retouneert
     * null als artikel niet bestaat of niet op voorraad is.
     *
     * @param naam De naam van het artikel.
     * @return Het Artikel, of null.
     */
    public Artikel getArtikel(String productnaam) {
        return getArtikel(getArrayList(productnaam));
    }

    // Antwoorden voor pdf:
    // 5a: Deze 2 methodes worden gebruik voor Artikel getArtikel(String
    // productnaam). Verder zijn deze methodes niet nuttig buiten deze klasse, dus
    // het is nutteloos omdeze public te maken.
    // 5b: Een HashMap gebruik je als je meerdere values van hetzelfde soort wil.
    // Deze kunnen dan op verschillende keys staan en er komen geen errors.
    // Een HashSet is handig als je alleen unieke values wil. Hierin kan je geen
    // meerdere values van hetzelfde soort, dan komen er errors.
}
