package sv.edu.udb.proyectodsm.datos;

public class Medicos {
    private String nombre;
    private String apellido;
    private String edad;
    private String dui;
    private String fechaNac;

    String key;

    public Medicos(){

    }
    public Medicos(String nombre, String apellido, String edad, String dui, String fechaNac){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dui=dui;
        this.fechaNac=fechaNac;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    //---------------------------------------
    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido=apellido;
    }
    //---------------------------------------
    public String getEdad(){
        return edad;
    }
    public void setEdad(String edad){
        this.edad=edad;
    }
    //----------------------------------------
    public String getDui(){
        return dui;
    }
    public void setDui(String dui){
        this.dui=dui;
    }
    //----------------------------------------
    public String getFechaNac(){
        return fechaNac;
    }
    public void setFechaNac(String fechaNac){
        this.fechaNac=fechaNac;
    }
    //---------------------------------------------
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key=key;
    }
}
