import java.time.LocalDate;
import java.util.Iterator;
import java.util.Stack;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "factuurregels")
public class FactuurRegel implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "factuur", nullable = true)
    private Factuur factuur;

    @Column(name = "artikel", nullable = true)
    private Artikel artikel;

    public FactuurRegel() {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
    this.factuur = factuur;
    this.artikel = artikel;
    }

    public String toString() {
        // method body omitted
        return factuur + " " + artikel;
    }

}