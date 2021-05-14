package sv.edu.udb.proyectodsm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import sv.edu.udb.proyectodsm.R;
import sv.edu.udb.proyectodsm.datos.Sala;

public class AdaptadorSala extends ArrayAdapter<Sala> {
    List<Sala> salas;
    private Activity context;

    public  AdaptadorSala(@NonNull Activity context, @NonNull List<Sala> salas) {
        super(context, R.layout.sala_adapter, salas);
        this.context = context;
        this.salas = salas;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;
        if(view == null)
            rowview = layoutInflater.inflate(R.layout.sala_adapter,null);
        else rowview = view;

        TextView tvNombreSala = rowview.findViewById(R.id.textNombreSala);



        tvNombreSala.setText("Nombre de la Sala : "+salas.get(position).getNombreSala());



        return rowview;
    }
}

