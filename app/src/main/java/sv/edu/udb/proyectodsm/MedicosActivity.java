package sv.edu.udb.proyectodsm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.proyectodsm.datos.Medicos;

public class MedicosActivity extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refClientes = database.getReference("medicos");

    Query consultaOrdenada = refClientes.orderByChild("nombre");

    List<Medicos> medicos;
    ListView listMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);
        initialize();
    }
    private void initialize() {
        FloatingActionButton fabAdd= findViewById(R.id.fabAdd);
        listMedicos = findViewById(R.id.listMedicos);

        listMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), add_medicos.class);

                intent.putExtra("accion","e");
                intent.putExtra("key", medicos.get(i).getKey());
                intent.putExtra("nombre",medicos.get(i).getNombre());
                intent.putExtra("apellido",medicos.get(i).getApellido());
                intent.putExtra("edad",medicos.get(i).getEdad());
                intent.putExtra("dui",medicos.get(i).getDui());
                intent.putExtra("fechaNacimiento",medicos.get(i).getFechaNac());
                startActivity(intent);
            }
        });

        listMedicos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MedicosActivity.this);
                alert.setMessage("¿Está seguro que desea eliminar el registro?")
                        .setTitle("Confirmación");

                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MedicosActivity.refClientes
                                .child(medicos.get(position).getKey()).removeValue();

                        Toast.makeText(MedicosActivity.this,
                                "¡Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MedicosActivity.this,
                                "¡Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

                return true;
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), add_medicos.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombre","");
                i.putExtra("apellido","");
                i.putExtra("edad","");
                i.putExtra("dui","");
                i.putExtra("fechadenacimiento","");
                startActivity(i);
            }
        });

        medicos = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                medicos.removeAll(medicos);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Medicos medico = dato.getValue(Medicos.class);
                    medico.setKey(dato.getKey());
                    medicos.add(medico);
                }

                AdaptadorMedicos adapter = new AdaptadorMedicos(MedicosActivity.this,
                        medicos );
                listMedicos.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}