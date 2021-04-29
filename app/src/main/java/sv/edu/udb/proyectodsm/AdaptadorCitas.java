package sv.edu.udb.proyectodsm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sv.edu.udb.proyectodsm.datos.Citas;

import java.util.List;
public class AdaptadorCitas extends ArrayAdapter<Citas>{
    List<Citas> citas;
    private Activity context;

    public  AdaptadorCitas(@NonNull Activity context, @NonNull List<Citas> citas) {
        super(context, R.layout.citas_layout, citas);
        this.context = context;
        this.citas = citas;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;
        if(view == null)
            rowview = layoutInflater.inflate(R.layout.citas_layout,null);
        else rowview = view;

        TextView tvNombre = rowview.findViewById(R.id.tvNombre);
        TextView tvDUI = rowview.findViewById(R.id.tvDUI);
        TextView tvFecha = rowview.findViewById(R.id.tvFecha);
        TextView tvHora = rowview.findViewById(R.id.tvHora);
        TextView tvDescriupcion = rowview.findViewById(R.id.tvDescripcion);

        tvNombre.setText("Nombre : "+citas.get(position).getNombre());
        tvDUI.setText("DUI : "+citas.get(position).getDui());
        tvFecha.setText("Fecha : "+citas.get(position).getFecha_cita());
        tvHora.setText("De que se trata : "+citas.get(position).getDescripcion());

        return rowview;
    }
}
