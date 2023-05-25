package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.databinding.FragmentDetalleInmuebleBinding;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel mv;
    private FragmentDetalleInmuebleBinding binding;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInmuebleViewModel.class);
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = getArguments();

        mv.getInmueble().observe(getActivity(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvAmbientesInmueble.setText(inmueble.getAmbientes()+"");
                binding.tvCodigoInmueble.setText(inmueble.getIdInmueble()+"");
                binding.tvInmuebleDireccion.setText(inmueble.getDireccion());
                binding.tvTipoInmueble.setText(inmueble.getTipo());
                binding.tvUsoInmueble.setText(inmueble.getUso());
                binding.tvInmuebleprecio.setText(inmueble.getprecio()+"");
                Glide.with(getActivity())
                        .load(inmueble.getImagen())
                        .into(binding.ivFotoInmueble);
                binding.cbInmuebleActivo.setChecked(inmueble.isEstado());
            }
        });

        binding.cbInmuebleActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.modificarEstadoInmueble(binding.cbInmuebleActivo.isChecked());
            }
        });

        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        Log.d("salida inmueble", inmueble.toString());
        mv.setInmueble(inmueble);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}