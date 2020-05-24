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
     * Deze methode simuleert een aantal dagen in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        int i;

        // herhaal voor elke dag
        for (i = 1; i <= dagen; i++) {
            System.out.println("Dag: " + i);

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                // kantine.(...);
            }

            // verwerk rij voor de kassa

            // toon dagtotalen (artikelen en geld in kassa)

            // reset de kassa voor de volgende dag

        } // end for loop

    }
}
