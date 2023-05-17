package com.duvarax.inmobiliariasinapi.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.duvarax.inmobiliariasinapi.R;

import com.duvarax.inmobiliariasinapi.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InicioViewModel.class);
        View root = binding.getRoot();

        mv.getMapa().observe(getActivity(), new Observer<InicioViewModel.miMapa>() {
            @Override
            public void onChanged(InicioViewModel.miMapa miMapa) {
                SupportMapFragment smf = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
                smf.getMapAsync(miMapa);
            }
        });

        mv.iniciarMapa();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}