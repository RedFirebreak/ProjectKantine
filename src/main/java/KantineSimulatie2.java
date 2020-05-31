import java.sql.Array;
import java.util.*;

public class KantineSimulatie2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineAanbod;

    // random generator
    private Random random;

    // administrator
    private Administratie administratie;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[] { "Koffie", "Broodje pindakaas", "Broodje kaas",
            "Appelsap" };

    // prijzen
    private static double[] artikelprijzen = new double[] { 1.50, 2.10, 1.65, 1.65 };

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 1;
    private static final int MAX_ARTIKELEN_PER_SOORT = 1;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    // totaalomzet
    private int[] gemiddeldeArtikelen;
    private double[] dagOmzet;

    /**
     * Constructor
     */
    public KantineSimulatie2() {
        // Maak een nieuwe kantine aan, de "hoofd" klasse.
        kantine = new Kantine();

        // Zet administratie klaar.
        administratie = new Administratie();

        // Zet een randomizer klaar.
        random = new Random();
        // Bepaal het aantal hoeveelheden van elk aspect.
        // Gebruikt de nieuwe getRandomArray();.
        int[] hoeveelheden = getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);

        // Maak een nieuw kantineaanbod aan.
        kantineAanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        // Verwerk het kantineaanbod in de kantine.
        kantine.setKantineAanbod(kantineAanbod);
    }

    /**
     * Methode om een array van random getallen te genereren. De getallen liggen
     * tussen min en max (inclusief).
     * 
     * @param lengte De gegeven lengte van de array.
     * @param min    Minimale waarde die in de array mag.
     * @param max    Maximale waarde die in de array mag.
     * @return De array met random getallen.
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
     * @param min De minimumwaarde.
     * @param max De maximumwaarde.
     * @return Het random gegenereerde getal.
     */
    private int getRandomValue(int min, int max) {
        // Omdat er misschien "0" klanten kunnen zijn, doen we +1.
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een gegeven array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken.
     * 
     * @param indexen De array met gegeven indexen.
     * @return De array met artikelnamen.
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];
        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen in het verloop van de kantine.
     * 
     * @param dagen De hoeveelheid dagen die je wil simuleren.
     */
    public void simuleer(int dagen) {
        // Zet de variabele voor administratie.
        gemiddeldeArtikelen = new int[dagen+1];
        dagOmzet = new double[dagen+1];
        

        // For lus voor dagen.
        for (int i = 1; i < dagen + 1; i++) {
            System.out.println("-------------------------");
            System.out.println("Dag: " + i);

            // Bedenk hoeveel personen vandaag binnen lopen.
            int aantalPersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // Laat de personen maar komen...
            for (int j = 0; j < aantalPersonen; j++) {
                Datum datum = new Datum(23, 3, 1997); // TODO Randomizer datum?
                char geslacht = 'M'; // TODO Randomizer M of V?
                Persoon klantInWinkel = new Persoon(j, "Stefan", "Jilderda", datum, geslacht); // TODO Randomizer
                                                                                               // persoon?

                // Maak persoon en dienblad aan, koppel ze aan elkaar.
                Dienblad dienbladVanKlant = new Dienblad(klantInWinkel);
                dienbladVanKlant.setKlant(klantInWinkel);

                // Bedenk hoeveel artikelen worden gepakt.
                int aantalArtikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // Genereer de "artikelnummers", dit zijn indexen van de artikelnamen.
                int[] tePakken = getRandomArray(aantalArtikelen, 0, AANTAL_ARTIKELEN - 1);

                // Vind de artikelnamen op basis van de indexen hierboven.
                String[] artikelen = geefArtikelNamen(tePakken);

                // Loop de kantine binnen, pak de gewenste artikelen en sluit aan.
                kantine.loopPakSluitAan(dienbladVanKlant, artikelen);

                // Vul artikelen aan als ze onder het minimum komen. 3.1
                for(int p = 0; p < aantalArtikelen; p++) {
                    kantineAanbod.vulVoorraadAan(artikelen[p]);
                }
            }

            // Verwerk rij voor de kassa.
            kantine.verwerkRijVoorKassa();

            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen.
            System.out.println("Aantal klanten: " + aantalPersonen);
            System.out.println("Aantal artikelen: " + kantine.getAantalArtikelen());
            System.out.printf("Totaalbedrag: " + "%.2f%n",kantine.getTotaalbedrag());
            System.out.println("");

            // sla de waarden van die dag op.
            gemiddeldeArtikelen[i] = kantine.getAantalArtikelen();
            dagOmzet[i] = kantine.getTotaalbedrag();

            // reset de kassa voor de volgende dag.
            kantine.resetKassa();
        }
        
        System.out.printf("Gemiddelde omzet: " + "%.2f%n",administratie.berekenGemiddeldeOmzet(dagOmzet));
        System.out.printf("Gemiddelde verkochte artikelen: " + "%.2f%n",administratie.berekenGemiddeldAantal(gemiddeldeArtikelen));
        System.out.println("Totalen per dag: ");
        System.out.printf("Maandag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[0]);
        System.out.printf("Dinsdag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[1]);
        System.out.printf("Woensdag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[2]);
        System.out.printf("Donderdag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[3]);
        System.out.printf("Vrijdag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[4]);
        System.out.printf("Zaterdag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[5]);
        System.out.printf("Zondag: " + "%.2f%n",administratie.berekenDagOmzet(dagOmzet)[6]);
        
    }
}
