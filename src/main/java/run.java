public class run {

    public static final int DAGEN = 7; // 'Default' aantal dagen
    public static KantineSimulatie2 simulatie; // De opgeslagen KantineSimulatie

    /**
     * Start de simulatie
     */
    public static void main(String[] args) {
        int tempDagen = 0;

        if (args.length == 0) {
            tempDagen = DAGEN;
        } else {
            tempDagen = Integer.parseInt(args[0]);
        }

        simulatie = new KantineSimulatie2();

        // Start de simulatie.
        simulatie.simuleer(tempDagen);

        // test print to confirm run
        System.out.println("");
        System.out.println("[EXIT] Kantine has run and finished without crashing. Exiting program.");
    }
}