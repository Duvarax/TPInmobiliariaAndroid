package com.duvarax.inmobiliariasinapi.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.duvarax.inmobiliariasinapi.databinding.FragmentPerfilBinding;
import com.duvarax.inmobiliariasinapi.modelo.EditPropietario;
import com.duvarax.inmobiliariasinapi.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

        View root = binding.getRoot();

        mv.getPropietario().observe(getActivity(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etPerfilid.setText(propietario.getId()+"");
                binding.etPerfilDni.setText(propietario.getDni().toString());
                binding.etPerfilNombre.setText(propietario.getNombre());
                binding.etPerfilApellido.setText(propietario.getApellido());
                binding.etPerfilEmail.setText(propietario.getEmail());
                binding.etPerfilClave.setText(propietario.getContraseña());
                binding.etPerfilTelefono.setText(propietario.getTelefono());
                editable(false);
            }
        });
        mv.getGuardar().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                int id = Integer.parseInt(binding.etPerfilid.getText().toString());
                String dni = binding.etPerfilDni.getText().toString();
                String nombre = binding.etPerfilNombre.getText().toString();
                String apellido = binding.etPerfilApellido.getText().toString();
                String contraseña = binding.etPerfilClave.getText().toString();
                String telefono = binding.etPerfilTelefono.getText().toString();
                String email = binding.etPerfilEmail.getText().toString();
                EditPropietario propietario = new EditPropietario(id,dni,nombre,apellido,email,contraseña,telefono);

                mv.editarPropietario(propietario);
                binding.btPerfilEditar.setText("Editar");
                editable(false);
                binding.btPerfilEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mv.setEditarMutable();
                    }
                });
            }
        });
        mv.getEditar().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.btPerfilEditar.setText("Guardar");
                editable(true);
                binding.btPerfilEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mv.setGuardarMutable();
                    }
                });
            }
        });
        binding.btPerfilEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.setEditarMutable();
            }
        });

        mv.setPropietarioMutable();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void editable(Boolean bool){
        binding.etPerfilid.setEnabled(bool);
        binding.etPerfilApellido.setEnabled(bool);
        binding.etPerfilDni.setEnabled(bool);
        binding.etPerfilNombre.setEnabled(bool);
        binding.etPerfilClave.setEnabled(bool);
        binding.etPerfilTelefono.setEnabled(bool);
        binding.etPerfilEmail.setEnabled(bool);
    }
}