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

import sv.edu.udb.proyectodsm.datos.Inventario;

public class AgregarInventario extends AppCompatActivity {
    EditText edtNombreEquipo, edtCantidad, edtEstado, edtFecha;
    String key = "", nombreEquipo = "", cantidad = "", estado = "", accion = "", fechaRegistro;
    Button buttonSave;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario);
        initialize();
    }

    private void initialize() {
        edtNombreEquipo = findViewById(R.id.nombreEquipo);
        edtCantidad = findViewById(R.id.cantidad);
        edtEstado = findViewById(R.id.estado);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            save();
        });


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        nombreEquipo = datos.getString("nombreEquipo");
        cantidad = datos.getString("cantidad");
        estado = datos.getString("estado");
        accion = datos.getString("accion");
        fechaRegistro = datos.getString("fechaRegistro");
        edtNombreEquipo.setText(nombreEquipo);
        edtCantidad.setText(cantidad);
        edtEstado.setText(estado);


    }

    public void save() {
        String nombreEquipo = edtNombreEquipo.getText().toString();
        String cantidad = edtCantidad.getText().toString();
        String estado = edtEstado.getText().toString();
        String fechaReg = fechaRegistro;


        // Se forma objeto persona

        Inventario inv = new Inventario(nombreEquipo, cantidad, estado, fechaReg);
        inv.setFechaRegistro(getFechaNormal(getFechaMili()));
        inv.setTimestamp(getFechaMili() * -1);


        if (accion.equals("a")) { //Agregar usando push()
            ActivityInventario.refInventario.push().setValue(inv);
        } else // Editar usando setValue
        {
            ActivityInventario.refInventario.child(key).setValue(inv);
        }
        finish();
    }

    public void cancelar(View v) {
        finish();
    }

    public long getFechaMili() {
        Calendar calendar = Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();
        return tiempounix;
    }

    public String getFechaNormal(long fechamili) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        String fecha = sdf.format(fechamili);
        return fecha;
    }
}