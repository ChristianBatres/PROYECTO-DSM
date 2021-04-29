package sv.edu.udb.proyectodsm.datos;

public class Citas {
    private String dui;
    private String nombre;
    private String fecha_cita;
    private String hora;
    private String descripcion;
    String key;

    public Citas() {
    }

    public Citas(String dui, String nombre, String fecha_cita, String hora, String descripcion) {
        this.dui = dui;
        this.nombre = nombre;
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
