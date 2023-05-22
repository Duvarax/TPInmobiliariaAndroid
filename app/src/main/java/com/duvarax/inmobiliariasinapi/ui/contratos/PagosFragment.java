package com.duvarax.inmobiliariasinapi.ui.contratos;

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
import com.duvarax.inmobiliariasinapi.databinding.FragmentPagosBinding;
import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.Pago;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel mv;
    private FragmentPagosBinding binding;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagosViewModel.class);
        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle contratoBundle = getArguments();
        mv.getListaPagos().observe(getActivity(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                RecyclerView rv = binding.rvListaPagos;
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grid);
                PagosFragmentAdapter pa = new PagosFragmentAdapter(getActivity(), pagos, inflater);
                rv.setAdapter(pa);
            }
        });
        mv.setListaPagos((Contrato) contratoBundle.getSerializable("contrato"));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}