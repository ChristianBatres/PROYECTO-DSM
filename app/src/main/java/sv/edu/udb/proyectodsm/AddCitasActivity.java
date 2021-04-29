package sv.edu.udb.proyectodsm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.udb.proyectodsm.datos.Citas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

public class AddCitasActivity extends AppCompatActivity{
    EditText edtDUI,edtNombre,edtFecha,edtHora,edtDescripcion;
    String key="",nombre="",dui="",fecha="",hora="",descripcion="",accion="";
    Button buttonSave;
    int cyear, cday, cmonth;
    int chour,cminute;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_citas);
        inicializar();
    }
    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDUI = findViewById(R.id.edtDUI);
        edtFecha = findViewById(R.id.edtFech);
        edtHora = findViewById(R.id.edtHora);
        edtDescripcion = findViewById(R.id.edtDescripcion);

        edtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddCitasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

        edtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Calendar calendar = Calendar.getInstance();
                chour=calendar.get(Calendar.HOUR_OF_DAY);
                cminute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddCitasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtHora.setText(hourOfDay+":"+minute);
                    }
                },chour,cminute,false);
                timePickerDialog.show();
            }
        });

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        nombre=datos.getString("nombre");
        fecha=datos.getString("fecha");
        hora=datos.getString("hora");
        descripcion=datos.getString("descripcion");


        accion=datos.getString("accion");
        edtDUI.setText(dui);
        edtNombre.setText(nombre);
        edtFecha.setText(fecha);
        edtHora.setText(hora);
        edtDescripcion.setText(descripcion);
    }

    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String dui = edtDUI.getText().toString();
        String fecha = edtFecha.getText().toString();
        String hora = edtHora.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        // Se forma objeto persona
        Citas cita = new Citas(dui,nombre,fecha,hora,descripcion);

        if (accion.equals("a")) { //Agregar usando push()
            CitasActivity.refCitas.push().setValue(cita);
        }
        else // Editar usando setValue
        {
            CitasActivity.refCitas.child(key).setValue(cita);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }

}
