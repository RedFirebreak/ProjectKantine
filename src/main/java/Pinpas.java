public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten.
     *
     * @param kredietlimiet Het kredietlimiet.
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet=kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen.
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(kredietlimiet + saldo >= tebetalen) {
                saldo -= tebetalen;
            } else {
                throw new TeWeinigGeldException("Saldo te laag");
            }
    }
}
