public class KantineSimulatie1 {

    private Kantine kantine;

    /**
     * Constructor
     */
    public KantineSimulatie1(int dagen) {

        kantine = new Kantine();
        simuleer(dagen);
    }

    /**
     * Deze methode simuleert een aantal dagen in het verloop van de kantine.
     *
     * @param dagen Hoeveel dagen te simuleren.
     */
    public void simuleer(int dagen) {
        int i;

        // herhaal voor elke dag
        for (i = 1; i <= dagen; i++) {
            System.out.println("-----------------");
            System.out.println("Dag: " + i);

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                // make new persoon
                // TODO arraylist met persoon namen, datum, geslacht (randomizer)?
                Datum datum = new Datum(23, 3, 1997); // Randomizer datum?
                char geslacht = 'M'; // randomizer M of V
                Persoon klantinwinkel = new Persoon(j, "Stefan", "Jilderda", datum, geslacht);

                // laat persoon een dienblad pakken
                Dienblad dienbladvanklant = new Dienblad(klantinwinkel);

                // Pak een aanbod artikelen HARDCODED OM TE LATEN WERKEN
                String[] artikelnamen = new String[] { "Koffie" };

                // sluit aan aan rij
                kantine.loopPakSluitAan(dienbladvanklant, artikelnamen);
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            // toon dagtotalen (artikelen en geld in kassa)
            System.out.println("Artikelen:" + kantine.getAantalArtikelen());
            System.out.println("Totaalbedrag:" + kantine.getTotaalbedrag());

            // reset de kassa voor de volgende dag
            kantine.resetKassa();

        } // end for loop

    }
}
