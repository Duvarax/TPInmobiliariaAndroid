package com.duvarax.inmobiliariasinapi.ui.contratos;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.databinding.FragmentContratosBinding;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.ui.InmueblesFragmentAdapter;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mv;
    private FragmentContratosBinding binding;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosViewModel.class);
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.setInmuebles().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                RecyclerView rv = binding.rvListaContratos;
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grid);
                InmueblesFragmentAdapter ca = new InmueblesFragmentAdapter(getActivity(), inmuebles, inflater, R.id.nav_detalle_contratos);
                rv.setAdapter(ca);

            }
        });

        mv.setListaInmueblesMutable();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}