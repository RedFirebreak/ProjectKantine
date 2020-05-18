public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Maak een datum aan. Deze datum kan voor veel gebruikt worden.
	 * 
	 * Datum moet tussen 1-1-1900 en 31-12-2100 liggen.
	 * 
	 * De datum moet ook aal een paar eisen voldoen, zie bestaatDatum().
	 * 
	 * @param dag De dag voor de datum.
	 * @param maand De maand voor de datum.
	 * @param jaar Het jaar voor de datum.
	 */
	public Datum(int dag, int maand, int jaar) {
		this(); // zet de waarde op 0
		if(bestaatDatum(dag, maand, jaar)) {
			this.dag=dag;
			this.maand=maand;
			this.jaar=jaar;
		}
	}

	/**
	 * Een lege constructor. Als de klasse hierdoor opgevraagd wordt, worden dag, maand en jaar op 0 gezet.
	 */
	public Datum() {
		dag=0;
		maand=0;
		jaar=0;
	}

	/**
	 * Deze methode controleert of de datum die gegeven is mag bestaan.
	 * 
	 * De dag moet tussen 1-31 liggen en mag niet null zijn.
	 * De maand moet tussen 1-12 liggen en mag niet null zijn.
	 * Het jaar moet tussen 1900-2100 liggen en mag niet null zijn.
	 * 
	 * Maand 1, 3, 5, 7, 8, 10 en 12 hebben altijd maximaal 31 dagen.
	 * Maand 4, 6, 9 en 11 hebben altijd maximaal 30 dagen.
	 * 
	 * Maand 2 is een speciale maand. Normaal heeft deze een maximaal 28 dagen.
	 * In een schrikkeljaar (jaar kan door 4 gedeeld worden zonder een rest) is maand 2 maximaal 29 dagen.
	 * In een jaar dat deelbaar is door 100, is het geen schrikkeljaar.
	 * Als het jaar dat deelbaar is door 100, ook deelbaar is door 400, is het WEL een schrikkeljaar.
	 * 
	 * @param dag De dag van de datum.
	 * @param maand De maand van de datum.
	 * @param jaar Het jaar van de datum.
	 * @return True als de datum bestaat, False als de datum niet bestaat.
	 */
	public boolean bestaatDatum(int dag, int maand, int jaar) {
		if(dag<1 || dag>31) {
			return false;
		}
		if(maand<1 || maand>12) {
			return false;
		}
		if(jaar<1900 || jaar>2100) {
			return false;
		}
		if(maand==1 && dag>31 || maand==3 && dag>31 || maand==5 && dag>31 || maand==7 && dag>31 || maand==8 && dag>31 || maand==10 && dag>31 || maand==12 && dag>31) { 
			//deze is vgm overbodig, ivm eerste if
			return false;
		}
		if(maand==4 && dag>30 || maand==6 && dag>30 || maand==9 && dag>30 || maand==11 && dag>30) {
			return false;
		}
		if(maand==2 && dag>29) {
			return false;
		}
		if(maand==2 && dag==29 && jaar%4!=0) {
			return false;
		}
		if(maand==2 && dag==29 && jaar%4==0 && jaar%100==0) {
			return false;
		}
		if(maand==2 && dag==29 && jaar%4==0 && jaar%100==0 && jaar%400==0) {
			return true;
		}
		//[[FIX]] Moet hier een else?
		return true;
	}

	/**
	 * Krijg het jaar van de datum in een integer.
	 * 
	 * @return Het jaar van de datum.
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 * Zet het jaar van de datum.
	 * 
	 * Het jaar moet tussen 1900 en 2100 liggen, en mag niet null zijn.
	 * 
	 * @param jaar Het jaar voor de datum.
	 */
	public void setJaar(int setJaar) {
		if(bestaatDatum(dag, maand, setJaar)) {
			jaar = setJaar;
		}
	}

	/**
	 * Krijg de maand van de datum in een integer.
	 * 
	 * @return De maand van de datum.
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * Zet de maand van de datum.
	 * 
	 * De maand moet tussen 1 en 12 liggen, en mag niet null zijn.
	 * 
	 * @param maand De maand voor de datum.
	 */
	public void setMaand(int setMaand) {
		if(bestaatDatum(dag, setMaand, jaar)) {
			maand=setMaand;
		}
	}

	/**
	 * Krijg de dag van de datum in een integer.
	 * 
	 * @return De dag van de datum.
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * Zet de dag van de datum.
	 * 
	 * De dag moet tussen de 1-31 liggen, of tussen 1-30, of tussen 1-29, of tussen 1-28, maar mag nooit null zijn.
	 * Dit ligt allemaal aan welke maand en welk jaar het is, zie bestaatDatum() voor meer informatie.
	 * 
	 * @param dag De dag voor de datum.
	 */
	public void setDag(int setDag) {
		if(bestaatDatum(setDag, maand, jaar)) {
			dag=setDag;
		}
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Datum in vorm van: "1-1-1999"
	 */
	public String getDatumAsString() {
		return getDag()+ "-" +getMaand()+ "-" +getJaar();
	}
}
