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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.proyectodsm.datos.Inventario;

public class ActivityInventario extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refInventario = database.getReference("inventario");

    Query consuñtarOrdenada = refInventario.orderByChild("timestamp");

    List<Inventario> inv;
    ListView linv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        inicializar();

    }

    private void inicializar() {
        FloatingActionButton fab_agregar= findViewById(R.id.fabAdd);
        linv = findViewById(R.id.listInventario);

        // Cuando el usuario haga clic en la lista (para editar registro)
        linv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AgregarInventario.class);

                intent.putExtra("accion","e"); // Editar
                intent.putExtra("key", inv.get(i).getKey());
                intent.putExtra("nombreEquipo",inv.get(i).getNombreEquipo());
                intent.putExtra("cantidad",inv.get(i).getCantidad());
                intent.putExtra("estado",inv.get(i).getEstado());
                intent.putExtra("fechaRegistro",inv.get(i).getFechaRegistro());

                startActivity(intent);
            }
        });

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        linv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(ActivityInventario.this);
                ad.setMessage("Está seguro de eliminar registro?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityInventario.refInventario
                                .child(inv.get(position).getKey()).removeValue();

                        Toast.makeText(ActivityInventario.this,
                                "Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(ActivityInventario.this,
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
                Intent i = new Intent(getBaseContext(), AgregarInventario.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombreEquipo","");
                i.putExtra("cantidad","");
                i.putExtra("estado","");
                i.putExtra("fechaRegistro","");
                startActivity(i);
            }
        });

        inv = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consuñtarOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                inv.removeAll(inv);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Inventario inven = dato.getValue(Inventario.class);
                    inven.setKey(dato.getKey());
                    inv.add(inven);
                }

                AdaptadorInventario adapter = new AdaptadorInventario(ActivityInventario.this,
                        inv );
                linv.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
