public class run {

    public static final int DAGEN = 7; // 'Default' aantal dagen
    public static KantineSimulatie1 simulatie; // De opgeslagen KantineSimulatie

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

        simulatie = new KantineSimulatie1(tempdagen);

        // test print to confirm run
        System.out.println("yeet ~ Kantine has run and finished without crashing. Exiting.");
    }
}