package sv.edu.udb.proyectodsm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sv.edu.udb.proyectodsm.modulos.Clientes;

public class AdaptadorClientes extends ArrayAdapter<Clientes> {
    List<Clientes> listaClientes;
    private Activity context;

    public AdaptadorClientes(@NonNull Activity context, @NonNull List<Clientes> listaClientes) {
        super(context, R.layout.activity_adaptador_clientes, listaClientes);

        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;

        if(view == null) {
            rowview = layoutInflater.inflate(R.layout.activity_adaptador_clientes, null);
        }
        else {
            rowview = view;
        }

        TextView textNombre = rowview.findViewById(R.id.textNombre);
        TextView textApellido = rowview.findViewById(R.id.textApellido);
        TextView textEdad = rowview.findViewById(R.id.textEdad);
        TextView textDui = rowview.findViewById(R.id.textDUI);
        TextView textFechaNacimiento = rowview.findViewById(R.id.textFecha);



        textNombre.setText("Nombre: " + listaClientes.get(position).getNombre());
        textApellido.setText("Apellido: " + listaClientes.get(position).getApellido());
        textEdad.setText("Edad: " + listaClientes.get(position).getEdad());
        textDui.setText("DUI: " + listaClientes.get(position).getDui());
        textFechaNacimiento.setText("Fecha de nacimiento: " + listaClientes.get(position).getFechaNacimiento());

        return rowview;
    }
}