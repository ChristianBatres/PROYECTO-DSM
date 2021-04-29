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

import sv.edu.udb.proyectodsm.datos.Medicos;

public class AdaptadorMedicos extends ArrayAdapter<Medicos>{
    List<Medicos> listaMedicos;
    private Activity context;

    public AdaptadorMedicos(@NonNull Activity context, @NonNull List<Medicos> listaMedicos) {
        super(context, R.layout.adaptador_medicos, listaMedicos);

        this.context = context;
        this.listaMedicos = listaMedicos;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;

        if (view == null) {
            rowview = layoutInflater.inflate(R.layout.adaptador_medicos, null);
        } else {
            rowview = view;
        }

        TextView textNombre = rowview.findViewById(R.id.textNombre);
        TextView textApellido = rowview.findViewById(R.id.textApellido);
        TextView textEdad = rowview.findViewById(R.id.textEdad);
        TextView textDui = rowview.findViewById(R.id.textDUI);
        TextView textFechaNacimiento = rowview.findViewById(R.id.textFecha);


        textNombre.setText("Nombre: " + listaMedicos.get(position).getNombre());
        textApellido.setText("Apellido: " + listaMedicos.get(position).getApellido());
        textEdad.setText("Edad: " + listaMedicos.get(position).getEdad());
        textDui.setText("DUI: " + listaMedicos.get(position).getDui());
        textFechaNacimiento.setText("Fecha de nacimiento: " + listaMedicos.get(position).getFechaNac());

        return rowview;
    }

}
