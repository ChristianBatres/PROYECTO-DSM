package sv.edu.udb.proyectodsm;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.udb.proyectodsm.datos.Citas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CitasActivity extends  AppCompatActivity{
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refCitas = database.getReference("cita");

            Query consuñtarOrdenada = refCitas.orderByChild("nombre");

    List<Citas> cita;
    ListView listaCitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_citas);

        inicializar();

    }

    private void inicializar() {
        FloatingActionButton fab_agregar= findViewById(R.id.fab_agregar);
        listaCitas = findViewById(R.id.ListaCitas);

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddCitasActivity.class);

                intent.putExtra("accion","e"); // Editar
                intent.putExtra("key", cita.get(i).getKey());
                intent.putExtra("nombre",cita.get(i).getNombre());
                intent.putExtra("dui",cita.get(i).getDui());
                intent.putExtra("fecha",cita.get(i).getFecha_cita());
                intent.putExtra("hora",cita.get(i).getHora());
                intent.putExtra("descripcion",cita.get(i).getDescripcion());
                startActivity(intent);
            }
        });

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaCitas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(CitasActivity.this);
                ad.setMessage("Está seguro de eliminar registro?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CitasActivity.refCitas
                                .child(cita.get(position).getKey()).removeValue();

                        Toast.makeText(CitasActivity.this,
                                "Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(CitasActivity.this,
                                "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        fab_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), AddCitasActivity.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombre","");
                i.putExtra("dui","");
                i.putExtra("fecha","");
                i.putExtra("hora","");
                i.putExtra("descripcion","");
                startActivity(i);
            }
        });

        cita = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consuñtarOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                cita.removeAll(cita);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Citas citas = dato.getValue(Citas.class);
                    citas.setKey(dato.getKey());
                    cita.add(citas);
                }

                AdaptadorCitas adapter = new AdaptadorCitas(CitasActivity.this,
                        cita );
                listaCitas.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
