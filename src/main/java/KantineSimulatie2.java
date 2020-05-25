import java.sql.Array;
import java.util.*;

public class KantineSimulatie2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[] { "Koffie", "Broodje pindakaas", "Broodje kaas",
            "Appelsap" };

    // prijzen
    private static double[] artikelprijzen = new double[] { 1.50, 2.10, 1.65, 1.65 };
    // TODO DOCENT wat.
    // private static double[] artikelprijzen = new double[] { 1.0, 2.0, 1.0, 1.0 };

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie2(int dagen) {
        // Maak een nieuwe kantine aan, de "hoofd" klase
        kantine = new Kantine();

        // Zet een ranomizer klaar
        random = new Random();
        // Bepaal het aantal hoeveelheden van elk aspect. Gebruikend de nieuwe
        // getRandomArray();
        int[] hoeveelheden = getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);

        // Maak een nieuw kantineaanbod aan
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        // Verwerk het kantineaanbod in de kantine.
        kantine.setKantineAanbod(kantineaanbod);

        simuleer(dagen);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de
     * gegeven lengte te genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        // Omdat er misschien "0" klanten kunnen zijn, doen we +1.
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de
     * bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // for lus voor dagen
        for (int i = 1; i < dagen + 1; i++) {
            System.out.println("-------------------------");
            System.out.println("Dag: " + i);

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {
                Datum datum = new Datum(23, 3, 1997); // Randomizer datum?
                char geslacht = 'M'; // randomizer M of V
                Persoon klantinwinkel = new Persoon(j, "Stefan", "Jilderda", datum, geslacht);

                // maak persoon en dienblad aan, koppel ze
                Dienblad dienbladvanklant = new Dienblad(klantinwinkel);
                dienbladvanklant.setKlant(klantinwinkel);

                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON); // FIX

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienbladvanklant, artikelen);
            }

            // Verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
            System.out.println("Aantal klanten:" + aantalpersonen);
            System.out.println("Aantal artikelen:" + kantine.getAantalArtikelen());
            System.out.println("Totaalbedrag:" + kantine.getTotaalbedrag());

            // reset de kassa voor de volgende dag
            kantine.resetKassa();
        }
    }
}
