import java.sql.Date;

public class Tasca {
    private int id;
    private String nom;
    private String descripcio;
    private int prioritat;
    private Date dataLim;
    private int equipId;

    // Constructor
    public Tasca(int id, String nom, String descripcio, int prioritat, Date dataLim, int equipId) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.prioritat = prioritat;
        this.dataLim = dataLim;
        this.equipId = equipId;
    }

    // Getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getPrioritat() {
        return prioritat;
    }

    public void setPrioritat(int prioritat) {
        this.prioritat = prioritat;
    }

    public Date getDataLim() {
        return dataLim;
    }

    public void setDataLim(Date dataLim) {
        this.dataLim = dataLim;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    @Override
    public String toString() {
        return "Tasca{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", prioritat=" + prioritat +
                ", dataLim=" + dataLim +
                ", equipId=" + equipId +
                '}';
    }
}
