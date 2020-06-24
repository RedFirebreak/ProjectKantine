import java.time.LocalDate;
import java.util.Iterator;
import java.util.Stack;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "factuurregel")
public class FactuurRegel implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    
    @Embedded
    private Artikel artikel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur")
    private Factuur factuur;

    public FactuurRegel() {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    public String toString() {
        // method body omitted
        return artikel.getNaam() + " Korting: €" + artikel.getKorting() + " Totaal €" + artikel.getPrijs();
    }

}