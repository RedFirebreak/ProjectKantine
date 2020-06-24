import java.sql.Array;
import java.util.*;
import java.time.LocalDate;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class KantineSimulatie2 {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
        Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineAanbod;

    // random generator
    private Random random;

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
    private static final int MIN_PERSONEN_PER_DAG = 5;
    private static final int MAX_PERSONEN_PER_DAG = 20;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 2;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    // totaalomzet
    private int[] gemiddeldeArtikelen;
    private double[] dagOmzet;

    /**
     * Constructor
     */
    public KantineSimulatie2() {
        // Create the manager
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        // Maak een nieuwe kantine aan, de "hoofd" klasse.
        kantine = new Kantine(manager);

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
        
        // counters voor totaalstatistieken
            int totaalAantalStudenten = 0;
            int totaalAantalDocenten = 0;
            int totaalAantalKantineMedewerkers = 0;

        // een BSN die mee gegeven wordt met elk persoon. (9 cijfers)
            int bsn = 100000000;
        // Voor leerlingen: studentnummer
            int studentnummer = 10000;
        
        
        // For lus voor dagen.
        for (int i = 1; i < dagen + 1; i++) {
            System.out.println("-------------------------");
            System.out.println("Dag: " + i);

            // Nieuwe aantallen en dagaanbiedingen
                // Bepaal het aantal hoeveelheden van elk aspect.
                // Gebruikt de nieuwe getRandomArray();.
                int[] hoeveelheden = getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);

                // Maak een nieuw kantineaanbod aan.
                kantineAanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

            // Bedenk hoeveel personen vandaag binnen lopen.
            int aantalPersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // maak wat counters voor statistieken :
                int aantalStudenten = 0;
                int aantalDocenten = 0;
                int aantalKantineMedewerkers = 0;

            // Laat de personen maar komen...
            for (int j = 0; j < aantalPersonen; j++) {
                // Nieuw persoon, nieuw BSN!
                bsn++;

                // Maak een geboortedatum
                    // While loop to make sure we have a valid datum
                    Boolean datumAangemaakt = false;
                    // Maak een "fake" datum aan om een echte te kunnen checken.
                    Datum datum = new Datum(); 

                    // while loop todat er een echte datum gevonden is
                    while(datumAangemaakt == false) {
                        // Max 31 dagen 
                        int randomdag = random.nextInt(31);
                        // Max 12 maanden
                        int randommaand = random.nextInt(12);
                        // Min 10 jaar oud, max 60+10
                        int randomjaar = (2020-((random.nextInt(60))+10));

                        // Kijk of de 3 gegevens samen een echte datum maken
                        if (datum.bestaatDatum(randomdag, randommaand, randomjaar)) {
                            // datum bestaat, overwrite de fake datum!
                            datum = new Datum(randomdag, randommaand, randomjaar);
                            // exit loop!
                            datumAangemaakt = true;
                        } else {
                            // datum bestaat niet. Try again
                            datumAangemaakt = false;
                        }
                    }
                    

                // Get random geslacht (1 op 2)
                    int randomgetal = random.nextInt(2);
                    char geslacht = 'O';

                    if (randomgetal == 1) {
                        // 1 op 2: Man
                        geslacht = 'M';
                    } else {
                        // 1 op 2: Vrouw
                        geslacht = 'V';
                    }

                // Bepaal wat voor klant dit is
                    // maak een fake klant
                    Persoon klantInWinkel = new Persoon();

                    // Overwrite random getal en maak een 1 - 100
                    randomgetal = random.nextInt(100);
                    randomgetal++; // maak getal 1-100 i.p.v 0-99.

                    String[] voornamen = { "Stefan", "Teun", "Stijn", "Romano", "Maurice", "Jan-Wiepke", "Henkie", "Jessica"};
                    String[] achternamen = { "de Jong","Hoogezand","Braxhoofden","Pater","Wolthuis","Highsand-Juicylake","Timmermans","Willemse","Jansen","Kramer","Kuppen","Jilderda"};
                    int randomVoornaam = random.nextInt(voornamen.length);
                    int randomAchternaam = random.nextInt(achternamen.length);
                    String voornaam = voornamen[randomVoornaam];
                    String achternaam = achternamen[randomAchternaam];

                    if (randomgetal == 100) {
                        // 1 op 100: kantinemedewerker
                        klantInWinkel = new KantineMedewerker(bsn, voornaam, achternaam, datum, geslacht);
                        klantInWinkel.setKortingsKaartHouder(true);
                        aantalKantineMedewerkers++;
                    } else if(randomgetal <= 89) {
                        // 89 op 100: Student
                        studentnummer++;
                        String studierichting = "ICT"; // TODO random afdeling
                        klantInWinkel = new Student(bsn, voornaam, achternaam, datum, geslacht, studentnummer, studierichting);
                        aantalStudenten++;
                    } else if (randomgetal >= 90 && randomgetal < 100 ) {
                        // 10 op 100: Docent
                        String vierlettercode = "ABCD"; // TODO random vierlettercode
                        String afdeling = "ICT"; // TODO random afdeling
                        klantInWinkel = new Docent(bsn, voornaam, achternaam, datum, geslacht, vierlettercode, afdeling);
                        klantInWinkel.setKortingsKaartHouder(true);
                        aantalDocenten++;
                    }

                // Get random geslacht (1 op 2)
                randomgetal = random.nextInt(2);

                if (randomgetal == 1) {
                    // 1 op 2: Contant
                    Contant betaalwijze;
                    klantInWinkel.setBetaalwijze(betaalwijze = new Contant());
                    betaalwijze.setSaldo(7.50);
                } else {
                    // 1 op 2: Pinpas
                    Pinpas betaalwijze;
                    klantInWinkel.setBetaalwijze(betaalwijze = new Pinpas());
                    betaalwijze.setKredietLimiet(7.0);
                    betaalwijze.setSaldo(7.50);
                }

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

                // Spam for week 3 opgave 4b
                //System.out.println(klantInWinkel.toString());
            }

            // Verwerk rij voor de kassa.
            kantine.verwerkRijVoorKassa(i);

            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen.
            System.out.println("Aantal klanten: " + aantalPersonen);
            System.out.println("Studenten: " + aantalStudenten + " Docenten: " + aantalDocenten + " Kantinemedewerkers: " + aantalKantineMedewerkers);
            System.out.println("Aantal artikelen: " + kantine.getAantalArtikelen());
            System.out.printf("Totaalbedrag: " + "%.2f%n",kantine.getTotaalbedrag());
            System.out.println("");

            // sla de waarden van die dag op.
            gemiddeldeArtikelen[i] = kantine.getAantalArtikelen();
            dagOmzet[i] = kantine.getTotaalbedrag();

            // vul statistieken aan 
            totaalAantalStudenten += aantalStudenten;
            totaalAantalDocenten += aantalDocenten;
            totaalAantalKantineMedewerkers += aantalKantineMedewerkers;

            // reset de kassa voor de volgende dag.
            kantine.resetKassa();
        }
        System.out.println("--- Klanten --- ");
        System.out.println("Totaal aantal klanten: " + (totaalAantalStudenten + totaalAantalDocenten + totaalAantalKantineMedewerkers)); 
        System.out.println("Studenten: " + totaalAantalStudenten + " Docenten: " + totaalAantalDocenten + " Kantinemedewerkers: " + totaalAantalKantineMedewerkers);
        
        System.out.println("--- Gemiddelden --- ");
        System.out.printf("Gemiddelde omzet: " + "%.2f%n",Administratie.berekenGemiddeldeOmzet(dagOmzet));
        System.out.printf("Gemiddelde verkochte artikelen: " + "%.2f%n",Administratie.berekenGemiddeldAantal(gemiddeldeArtikelen));
        
        double[] dagTotalen = Administratie.berekenDagOmzet(dagOmzet);

        System.out.println("--- Totalen per dag --- ");
        System.out.printf("Maandag: " + "%.2f%n",dagTotalen[0]);
        System.out.printf("Dinsdag: " + "%.2f%n",dagTotalen[1]);
        System.out.printf("Woensdag: " + "%.2f%n",dagTotalen[2]);
        System.out.printf("Donderdag: " + "%.2f%n",dagTotalen[3]);
        System.out.printf("Vrijdag: " + "%.2f%n",dagTotalen[4]);
        System.out.printf("Zaterdag: " + "%.2f%n",dagTotalen[5]);
        System.out.printf("Zondag: " + "%.2f%n",dagTotalen[6]);

        // NOW STOP - > Querytime
        
        // single query
        System.out.println("~Now stop. Querytime~");

        // 3.a
        Query query = manager.createQuery("SELECT SUM(totaal) AS totaal, SUM(korting) AS korting FROM Factuur");
        List<Object[]> resultList = query.getResultList();
        /*
        System.out.println("3A: Totale omzetten");
        for (Object[] r : resultList) {
            System.out.println("Totaal Omzet: "   + r[0]);
            System.out.println("Totaal Korting: " + r[1]);
        }
        */

        resultList.forEach(r -> System.out.println(Arrays.toString(r)));

        // 3.b
        query = manager.createQuery("SELECT AVG(totaal) AS totaal, AVG(korting) AS korting FROM Factuur");
        resultList = query.getResultList();

        System.out.println("3B: Gemiddelde omzetten");
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));
        /*for (Object[] r : resultList) {
            System.out.println("Gemiddelde omzet per factuur: "   + r[0]);
            System.out.println("Gemiddelde korting per factuur: " + r[1]);
        }*/

        // 3.c
        query = manager.createQuery("SELECT totaal FROM Factuur ORDER BY totaal DESC");
        query.setMaxResults(3);
        resultList = query.getResultList();
        
        System.out.println("3C: Top 3 hoogste facturen");

        Object[] g = resultList.toArray();
            System.out.println("#1: "   + g[0]);
            System.out.println("#2: "   + g[1]);
            System.out.println("#3: "   + g[2]);

        System.out.println("~Now stop. Querytime V2~");

        // 5.a
        System.out.println("5a: Gemiddelde omzetten");
        OmzetKortingArtikel("Broodje kaas");
        OmzetKortingArtikel("Koffie");
        OmzetKortingArtikel("Broodje pindakaas");
        OmzetKortingArtikel("Appelsap");

        // 5.b 
        queryTotaalPerDag(dagen);

        // 5.c
        System.out.println("5c: top 3 meest populaire artikelen");
        query = manager
                .createQuery("SELECT artikel.naam, COUNT(artikel.naam) FROM FactuurRegel GROUP BY artikel.naam ORDER BY COUNT(artikel.naam) DESC");
        query.setMaxResults(3);
        resultList = query.getResultList();
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));


        // 5.d
        System.out.println("5d: top 3 hoogste omzet artikelen");
        query = manager
                .createQuery("SELECT artikel.naam, SUM(artikel.prijs - artikel.korting) FROM FactuurRegel GROUP BY artikel.naam ORDER BY SUM(artikel.prijs - artikel.korting) DESC");
        query.setMaxResults(3);
        resultList = query.getResultList();
        resultList.forEach(r -> System.out.println(Arrays.toString(r)));

        System.out.println("~Close DB~");
        // Close manager and database :)
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    public void OmzetKortingArtikel(String artikel) {
        Query query = manager.createQuery("SELECT id FROM FactuurRegel where naam = '"+ artikel + "' and korting > 0");
        List<FactuurRegel> resultList = query.getResultList();
        double korting = resultList.size() * (kantineAanbod.getArtikel(artikel).getPrijs()  * 0.2);
        System.out.println("Korting op " + artikel + ": €" + korting);

        query = manager.createQuery("SELECT id FROM FactuurRegel where naam = '"+ artikel + "'");
        resultList = query.getResultList();
        System.out.println("Totaal verdiend met " + artikel + ": €" + resultList.size() * kantineAanbod.getArtikel(artikel).getPrijs());

    }

    public void queryTotaalPerDag(int dagen) {
        for (int i = 0; i < dagen; i++) {
            System.out.println("Dag: " + LocalDate.now().plusDays(i));
            Query query = manager
                    .createQuery("SELECT fr.artikel.naam, sum(fr.artikel.prijs), sum(fr.artikel.korting) FROM FactuurRegel fr JOIN fr.factuur f WHERE f.datum = '"+ LocalDate.now().plusDays(i)+ "'GROUP BY fr.artikel.naam ");
            query.setMaxResults(3);
            List<Object[]> resultList = query.getResultList();
            resultList.forEach(r -> System.out.println(Arrays.toString(r)));
        }
    };
}