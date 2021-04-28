package sv.edu.udb.proyectodsm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import sv.edu.udb.proyectodsm.datos.Medicos;

public class add_medicos extends AppCompatActivity {
    EditText editNombre, editApellido, editEdad, editDui, editFechaNacimiento;
    String key="", nombre="", apellido="", edad="", dui="", fechaNacimiento="", accion="";
    Button buttonSave;
    int cyear, cday, cmonth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicos);
        initialize();
    }
    private void initialize() {
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editEdad = findViewById(R.id.editEdad);
        editDui = findViewById(R.id.editDUI);
        editFechaNacimiento = findViewById(R.id.editFechaNacimiento);

        editFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(add_medicos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFechaNacimiento.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

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


        Medicos medicos = new Medicos(nombre, apellido, edad, dui, fechaNacimiento);

        if(accion.equals("a")) {
            MedicosActivity.refClientes.push().setValue(medicos);
        }
        else {
            MedicosActivity.refClientes.child(key).setValue(medicos);
        }

        finish();
    }

}