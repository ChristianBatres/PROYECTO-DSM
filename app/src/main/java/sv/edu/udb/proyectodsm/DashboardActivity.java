package sv.edu.udb.proyectodsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void citas (View v) {
        Intent i=new Intent(this, CitasActivity.class);
        startActivity(i);
    }
    public void pacientes (View v) {
        Intent i=new Intent(this, ClientesActivity.class);
        startActivity(i);
    }
    public void inventario (View v) {
        Intent i=new Intent(this, ActivityInventario.class);
        startActivity(i);
    }
    public void doctores (View v) {
        Intent i=new Intent(this, MedicosActivity.class);
        startActivity(i);
    }
}