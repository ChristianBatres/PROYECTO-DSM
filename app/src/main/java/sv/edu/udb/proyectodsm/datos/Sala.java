package sv.edu.udb.proyectodsm.datos;

public class Sala {
    private String nombreSala;
    String key;

    public Sala(){

    }

    public Sala(String nombreSala){
        this.nombreSala = nombreSala;

    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
