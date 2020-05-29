public class Administratie {

    private static final int DAYS_IN_WEEK = 7;

    /**
     * Private constructor zodat deze klasse niet kan worden aangeroepen vanuit andere klassen, omdat deze niet "zichtbaar" is.
     */
    private Administratie() {

    }

    public static void main(String[] args) {
        int[] test1 = new int[6];
        test1[0]=45;
        test1[1]=56;
        test1[2]=34;
        test1[3]=39;
        test1[4]=40;
        test1[5]=31;
        System.out.println("Gemiddelde test1: " + berekenGemiddeldAantal(test1));

        double[] test2 = new double[3];
        test2[0]=567.70;
        test2[1]=498.25;
        test2[2]=458.90;
        System.out.println("Gemiddelde test2: " + berekenGemiddeldeOmzet(test2));

        double[] test3 = new double[11];
        test3[0]=321.35;
        test3[1]=450.50;
        test3[2]=210.45;
        test3[3]=190.85;
        test3[4]=193.25;
        test3[5]=159.90;
        test3[6]=214.25;
        test3[7]=220.90;
        test3[8]=201.90;
        test3[9]=242.70;
        test3[10]=260.35;
        System.out.println("Gemiddelden test3: ");
        for(int t = 0; t < test3.length; t++) {
            System.out.println(berekenDagOmzet(test3)[t]);
        }
    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde.
     *
     * @param aantal Het aantal dingen die er in gaan.
     * @return       Het gemiddelde per index.
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        double forReturn = 0.0;
        int totaal=0;
        for(int i = 0; i < aantal.length; i++){
            totaal += aantal[i];
        }
        forReturn = ((double)totaal/(double)aantal.length);
        return forReturn;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde.
     *
     * @param omzet De totale omzet.
     * @return      Het gemiddelde per index.
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double forReturn = 0.0;
        double totaal=0;
        for(int i = 0; i < omzet.length; i++){
            totaal += omzet[i];
        }
        forReturn = (totaal/(double)omzet.length);
        return forReturn;
    }

    /**
     * Methode om dagomzetten uit te rekenen.
     *
     * @param omzet De volledige array met omzetten van dagen.
     * @return      Array met 7 elementen met dagomzetten.
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        // nieuwe double array met 7 plekken.
        double[] temp = new double[DAYS_IN_WEEK]; 

        //forloop om door de 7 dagen heen te lopen.
        for (int i = 0; i < DAYS_IN_WEEK; i++) {

            //TODO I NEED HELP
            //int j = 0;
            //while(j==0) {
            //    temp[i] += omzet[i + DAYS_IN_WEEK * j];
            //    
            //}
        }
        return temp;
    }

    //Antwoorden voor pdf
    //2c: In Java wordt altijd een lege constructor aangemaakt als je deze niet zelf implementeerd.
    //2d: Deze kunnen static zijn omdat ze alleen in deze methode worden aangeroepen. Ook besparen static methodes memory, wat altijd een voordeel is. 
    //2e: Private constructor zodat deze klasse niet kan worden aangeroepen vanuit andere klassen, omdat deze niet "zichtbaar" is.
    //2g: Final zorgt ervoor dat dit een constante wordt en dus niet meer aangepast wordt (Zo heeft een constante ook een mooie naam).
    //2h: Alles in deze methode is static, en als je binnen een static methode iets niet-static wil aanroepen, mag dat gewoon niet.
    //2i: Door final weg te halen is days_in_week weereen variabele geworden, terwijl we het een constante wilden hebben.
}
