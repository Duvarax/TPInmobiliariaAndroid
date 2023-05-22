package com.duvarax.inmobiliariasinapi.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.databinding.FragmentDetalleContratosBinding;
import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;

public class DetalleContratosFragment extends Fragment {

    private DetalleContratosViewModel mv;
    private FragmentDetalleContratosBinding binding;

    public static DetalleContratosFragment newInstance() {
        return new DetalleContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleContratosViewModel.class);
        binding = FragmentDetalleContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();

        mv.getContrato().observe(getActivity(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.tvCodigoContrato.setText(contrato.getIdContrato()+"");
                binding.tvFechaInContrato.setText(contrato.getFechaInicio());
                binding.tvFechaOutContrato.setText(contrato.getFechaFin());
                binding.tvInmuebleContrato.setText(contrato.getInmueble().getDireccion());
                binding.tvInquilinoContrato.setText(contrato.getInquilino().getNombre());
                binding.tvMontoContrato.setText(contrato.getMontoAlquiler()+"");
                binding.btPagosContrato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle contratoBundle = new Bundle();
                        contratoBundle.putSerializable("contrato", contrato);
                        Navigation.findNavController(getActivity() ,R.id.nav_host_fragment_content_menu).navigate(R.id.nav_pagos, contratoBundle);
                    }
                });
            }
        });

        mv.setContrato((Inmueble) bundle.getSerializable("inmueble"));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}