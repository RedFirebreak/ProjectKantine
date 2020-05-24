public class run {

    public static final int DAGEN = 7; // 'Default' aantal dagen
    public static KantineSimulatie2 simulatie; // De opgeslagen KantineSimulatie

    /**
     * Start de simulatie https://shorturl.at/hCDKR
     */
    public static void main(String[] args) {
        int tempdagen = 0;

        if (args.length == 0) {
            tempdagen = DAGEN;
        } else {
            tempdagen = Integer.parseInt(args[0]);
        }

        simulatie = new KantineSimulatie2(tempdagen);

        // test print to confirm run
        System.out.println("");
        System.out.println("[EXIT] Kantine has run and finished without crashing. Exiting program.");
    }
}