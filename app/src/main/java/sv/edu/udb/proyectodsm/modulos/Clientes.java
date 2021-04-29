package sv.edu.udb.proyectodsm.modulos;

public class Clientes {
    private String nombre;
    private String apellido;
    private String edad;
    private String dui;
    private String fechaNacimiento;
    String key;

    public Clientes() {
    }

    public Clientes(String nombre, String apellido, String edad, String dui, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dui =dui;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) { this.edad = edad;}


    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }


    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setPeso(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
