package com.example.palpedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.palpedia.databinding.FragmentNuevoPalBinding;

public class NuevoPalFragment extends Fragment {
    private FragmentNuevoPalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoPalBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PalsViewModel palsViewModel = new ViewModelProvider(requireActivity()).get(PalsViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String vida = binding.vida.getText().toString();
                String ataque = binding.ataque.getText().toString();
                String defensa = binding.defensa.getText().toString();
                String habilidad1 = binding.habilidad1.getText().toString();
                String habilidad2 = binding.habilidad2.getText().toString();
                String habilidad3 = binding.habilidad3.getText().toString();
                String pasiva = binding.pasiva.getText().toString();

                if (nombre.isEmpty() || descripcion.isEmpty() || vida.isEmpty() || ataque.isEmpty() || defensa.isEmpty() || habilidad1.isEmpty() || habilidad2.isEmpty() || habilidad3.isEmpty() || pasiva.isEmpty()) {
                    Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    palsViewModel.insertar(new Pal(nombre, descripcion, vida, ataque,defensa,habilidad1,habilidad2,habilidad3,pasiva));
                    navController.popBackStack();
                }
            }
        });
    }
}