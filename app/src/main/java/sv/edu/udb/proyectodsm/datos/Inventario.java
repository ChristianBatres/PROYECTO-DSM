package sv.edu.udb.proyectodsm.datos;

public class Inventario {
    private String nombreEquipo;
    private String cantidad;
    private String estado;
    private  String fechaRegistro;
    private long timestamp;
    private  String variable_prueba;


    String key;

    public Inventario(){

    }
    public Inventario(String nombreEquipo, String cantidad, String estado, String fechaRegistro){
        this.nombreEquipo = nombreEquipo;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key=key;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

//Comentario prueba
