package com.example.palpedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.palpedia.databinding.FragmentRecyclerPalsBinding;
import com.example.palpedia.databinding.ViewholderPalBinding;

import java.util.List;

public class RecyclerPalsFragment extends Fragment {
    private FragmentRecyclerPalsBinding binding;
    private PalsViewModel palsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPalsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        palsViewModel = new ViewModelProvider(requireActivity()).get(PalsViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerPalsFragment_to_nuevoPalFragment);
            }
        });

        PalsAdapter palsAdapter = new PalsAdapter();

        binding.recyclerView.setAdapter(palsAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pal pal = palsAdapter.obtenerPal(posicion);
                palsViewModel.eliminar(pal);

            }
        }).attachToRecyclerView(binding.recyclerView);

        palsViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Pal>>() {
            @Override
            public void onChanged(List<Pal> pals) {
                palsAdapter.establecerLista(pals);
            }
        });
    }

    class PalsAdapter extends RecyclerView.Adapter<PalViewHolder> {
        List<Pal> pals;

        @NonNull
        @Override
        public PalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            return new PalViewHolder(ViewholderPalBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PalViewHolder holder, int position) {

            Pal pal = pals.get(position);

            holder.binding.nombre.setText(pal.nombre);
            holder.binding.valoracion.setRating(pal.valoracion);

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        palsViewModel.actualizar(pal, rating);
                    }
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    palsViewModel.seleccionar(pal);
                    navController.navigate(R.id.action_recyclerPalsFragment_to_mostrarPalFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pals != null ? pals.size() : 0;
        }

        public void establecerLista(List<Pal> pals){
            this.pals = pals;
            notifyDataSetChanged();
        }

        public Pal obtenerPal(int posicion){
            return pals.get(posicion);
        }
    }

    static class PalViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPalBinding binding;

        public PalViewHolder(ViewholderPalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}