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
import sv.edu.udb.proyectodsm.datos.Inventario;

public class AdaptadorInventario extends ArrayAdapter<Inventario> {
    List<Inventario> inv;
    private Activity context;

    public  AdaptadorInventario(@NonNull Activity context, @NonNull List<Inventario> inv) {
        super(context, R.layout.inventario_adapter, inv);
        this.context = context;
        this.inv = inv;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;
        if(view == null)
            rowview = layoutInflater.inflate(R.layout.inventario_adapter,null);
        else rowview = view;

        TextView tvNombreEquipo = rowview.findViewById(R.id.textNombreEquipo);
        TextView tvCantidad = rowview.findViewById(R.id.textCantidad);
        TextView tvEstado = rowview.findViewById(R.id.textEstado);
        TextView tvFecha = rowview.findViewById(R.id.textFecha);


        tvNombreEquipo.setText("Nombre del Equipo : "+inv.get(position).getNombreEquipo());
        tvCantidad.setText("Cantidad : "+inv.get(position).getCantidad());
        tvEstado.setText("Estado del Equipo(1-10): "+inv.get(position).getEstado());
        tvFecha.setText("fecha de Registro : "+inv.get(position).getFechaRegistro());


        return rowview;
    }
}

