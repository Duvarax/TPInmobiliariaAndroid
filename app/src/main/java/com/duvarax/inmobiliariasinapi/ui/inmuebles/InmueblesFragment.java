package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.databinding.FragmentInmueblesBinding;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.ui.InmueblesFragmentAdapter;

import java.util.List;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private InmueblesViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesViewModel.class);
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.setInmuebles().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                RecyclerView rv = binding.rvInmuebleLista;
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grid);
                InmueblesFragmentAdapter adapter = new InmueblesFragmentAdapter(getActivity(), inmuebles, getLayoutInflater(), R.id.nav_detalle_inmueble);

                rv.setAdapter(adapter);
            }
        });
        mv.setListaInmueblesMutable();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}