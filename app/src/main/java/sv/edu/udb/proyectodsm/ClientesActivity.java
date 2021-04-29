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

import sv.edu.udb.proyectodsm.modulos.Clientes;

public class ClientesActivity extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refClientes = database.getReference("clientes");

    Query consultaOrdenada = refClientes.orderByChild("nombre");

    List<Clientes> clientes;
    ListView listClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        initialize();
    }

    private void initialize() {
        FloatingActionButton fabAdd= findViewById(R.id.fabAdd);
        listClientes = findViewById(R.id.listClientes);

        listClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddClientesActivity.class);

                intent.putExtra("accion","e");
                intent.putExtra("key", clientes.get(i).getKey());
                intent.putExtra("nombre",clientes.get(i).getNombre());
                intent.putExtra("apellido",clientes.get(i).getApellido());
                intent.putExtra("edad",clientes.get(i).getEdad());
                intent.putExtra("dui",clientes.get(i).getDui());
                intent.putExtra("fechaNacimiento",clientes.get(i).getFechaNacimiento());
                startActivity(intent);
            }
        });

        listClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ClientesActivity.this);
                alert.setMessage("¿Está seguro que desea eliminar el registro?")
                        .setTitle("Confirmación");

                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ClientesActivity.refClientes
                                .child(clientes.get(position).getKey()).removeValue();

                        Toast.makeText(ClientesActivity.this,
                                "¡Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(ClientesActivity.this,
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
                Intent i = new Intent(getBaseContext(), AddClientesActivity.class);
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

        clientes = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                clientes.removeAll(clientes);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Clientes persona = dato.getValue(Clientes.class);
                    persona.setKey(dato.getKey());
                    clientes.add(persona);
                }

                AdaptadorClientes adapter = new AdaptadorClientes(ClientesActivity.this,
                        clientes );
                listClientes.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}