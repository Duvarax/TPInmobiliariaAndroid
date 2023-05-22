package com.duvarax.inmobiliariasinapi.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.databinding.FragmentInquilinosBinding;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.request.ApiClient;
import com.duvarax.inmobiliariasinapi.ui.InmueblesFragmentAdapter;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel mv;
    private FragmentInquilinosBinding binding;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinosViewModel.class);
        binding = FragmentInquilinosBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        mv.setInmuebles().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                for(int i = 0; i< inmuebles.size(); i++){
                    Log.d("salida", inmuebles.get(i).toString());
                }
                RecyclerView rv = binding.rvListaInquilinos;
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grid);
                InmueblesFragmentAdapter ifa = new InmueblesFragmentAdapter(getActivity(), inmuebles, getLayoutInflater(), R.id.nav_detalle_inquilino);
                rv.setAdapter(ifa);
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