package sv.edu.udb.proyectodsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import sv.edu.udb.proyectodsm.modulos.Clientes;

public class AddClientesActivity extends AppCompatActivity {
    EditText editNombre, editApellido, editEdad, editDui, editFechaNacimiento;
    String key="", nombre="", apellido="", edad="", dui="", fechaNacimiento="", accion="";
    Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clientes);
        initialize();
    }

    private void initialize() {
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editEdad = findViewById(R.id.editEdad);
        editDui = findViewById(R.id.editDUI);
        editFechaNacimiento = findViewById(R.id.editFechaNacimiento);

        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            save();
        });


        Bundle data = getIntent().getExtras();
        key = data.getString("key");
        nombre = data.getString("nombre");
        apellido = data.getString("apellido");
        edad = data.getString("edad");
        dui = data.getString("dui");
        fechaNacimiento = data.getString("fechaNacimiento");
        accion = data.getString("accion");

        editNombre.setText(nombre);
        editApellido.setText(apellido);
        editEdad.setText(edad);
        editDui.setText(dui);
        editFechaNacimiento.setText(fechaNacimiento);
    }

    public void save() {
        String nombre = editNombre.getText().toString();
        String apellido = editApellido.getText().toString();
        String edad = editEdad.getText().toString();
        String dui = editDui.getText().toString();
        String fechaNacimiento = editFechaNacimiento.getText().toString();


        Clientes clientes = new Clientes(nombre, apellido, edad, dui, fechaNacimiento);

        if(accion.equals("a")) {
            ClientesActivity.refClientes.push().setValue(clientes);
        }
        else {
            ClientesActivity.refClientes.child(key).setValue(clientes);
        }

        finish();
    }

    public void cancel() {
        finish();
    }
}
