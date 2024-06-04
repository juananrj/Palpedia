package com.example.palpedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.palpedia.databinding.FragmentMostrarPalBinding;

public class MostrarPalFragment extends Fragment {
    private FragmentMostrarPalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPalBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        PalsViewModel palsViewModel = new ViewModelProvider(requireActivity()).get(PalsViewModel.class);

        palsViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Pal>() {
            @Override
            public void onChanged(Pal pal) {
                binding.nombre.setText(pal.nombre);
                binding.descripcion.setText(pal.descripcion);
                binding.habilidad1.setText(pal.habilidad1);
                binding.habilidad2.setText(pal.habilidad2);
                binding.habilidad3.setText(pal.habilidad3);
                binding.pasiva.setText(pal.pasiva);
                binding.vida.setText(pal.vida);
                binding.ataque.setText(pal.ataque);
                binding.defensa.setText(pal.defensa);
                binding.valoracion.setRating(pal.valoracion);

                binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            palsViewModel.actualizar(pal, rating);
                        }
                    }
                });
            }
        });
    }
}