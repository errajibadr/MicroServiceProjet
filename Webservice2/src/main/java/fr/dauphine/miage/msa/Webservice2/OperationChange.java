package fr.dauphine.miage.msa.Webservice2;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OperationChange {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String deviseSrc;
    private String deviseDest;
    private double taux;
    private String date;
    private double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviseSrc() {
        return deviseSrc;
    }

    public void setDeviseSrc(String deviseSrc) {
        this.deviseSrc = deviseSrc;
    }

    public String getDeviseDest() {
        return deviseDest;
    }

    public void setDeviseDest(String deviseDest) {
        this.deviseDest = deviseDest;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
