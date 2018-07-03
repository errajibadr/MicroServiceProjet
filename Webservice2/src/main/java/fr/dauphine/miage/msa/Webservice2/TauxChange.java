package fr.dauphine.miage.msa.Webservice2;

public class TauxChange {


        private Long id;
        private String deviseSrc;
        private String deviseDest;
        private double taux;
        private String date;

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

    @Override
    public String toString() {
        return "TauxChange{" +
                "id=" + id +
                ", deviseSrc='" + deviseSrc + '\'' +
                ", deviseDest='" + deviseDest + '\'' +
                ", taux=" + taux +
                ", date='" + date + '\'' +
                '}';
    }
}
