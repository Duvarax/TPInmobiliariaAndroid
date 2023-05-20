package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;

import java.util.List;

public class InmueblesFragmentAdapter extends RecyclerView.Adapter<InmueblesFragmentAdapter.ViewHolder> {

    private Context context;
    private List<Inmueble> listaInmuebles;
    private LayoutInflater inflater;

    public InmueblesFragmentAdapter(Context context, List<Inmueble> listaInmuebles, LayoutInflater inflater) {
        this.context = context;
        this.listaInmuebles = listaInmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context =parent.getContext();
        View root = inflater.inflate(R.layout.inmueble_item, parent, false);
        return new ViewHolder(root);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.direccion.setText(listaInmuebles.get(position).getDireccion());
        holder.precio.setText(listaInmuebles.get(position).getPrecio()+"");
        Glide.with(context)
                .load(listaInmuebles.get(position).getImagen())
                .into(holder.foto);
        holder.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", listaInmuebles.get(position));
                Navigation.findNavController((Activity)context ,R.id.nav_host_fragment_content_menu).navigate(R.id.nav_detalle_inmueble, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView foto;
        TextView direccion;
        TextView precio;
        ConstraintLayout contenedor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivInmuebleFoto);
            direccion = itemView.findViewById(R.id.tvDireccionInmueble);
            precio = itemView.findViewById(R.id.tvPrecioInmueble);
            contenedor = itemView.findViewById(R.id.clContenedorInmueble);

        }
    }
}
