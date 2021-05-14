package sv.edu.udb.proyectodsm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import sv.edu.udb.proyectodsm.datos.Sala;

public class AgregarSala extends AppCompatActivity {
    EditText edtNombreSala;
    String key = "", nombreSala = "",accion="";
    Button buttonSave;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sala);
        initialize();
    }

    private void initialize() {
        edtNombreSala = findViewById(R.id.nombreSala);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            save();
        });


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        nombreSala = datos.getString("nombreSala");
        accion = datos.getString("accion");
        edtNombreSala.setText(nombreSala);



    }

    public void save() {
        String nombreSala = edtNombreSala.getText().toString();



        // Se forma objeto persona

        Sala sla = new Sala(nombreSala);


        if (accion.equals("a")) { //Agregar usando push()
            ActivitySala.refSala.push().setValue(sla);
        } else // Editar usando setValue
        {
            ActivitySala.refSala.child(key).setValue(sla);
        }
        finish();
    }

    public void cancelar(View v) {
        finish();
    }
}