package com.example.palpedia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PalsViewModel extends AndroidViewModel {

    PalsRepositorio palsRepositorio;
    MutableLiveData<List<Pal>> listPalsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Pal> palSeleccionado = new MutableLiveData<>();

    public PalsViewModel(@NonNull Application application) {
        super(application);

        palsRepositorio = new PalsRepositorio();

        listPalsMutableLiveData.setValue(palsRepositorio.obtener());
    }

    MutableLiveData<List<Pal>> obtener() {return listPalsMutableLiveData;}

    void insertar(Pal pal){
        palsRepositorio.insertar(pal, new PalsRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pal> pals) {
                listPalsMutableLiveData.setValue(pals);
            }
        });
    }

    void eliminar(Pal pal){
        palsRepositorio.eliminar(pal, new PalsRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pal> pals) {
                listPalsMutableLiveData.setValue(pals);
            }
        });
    }

    void actualizar(Pal pal, float valoracion){
        palsRepositorio.actualizar(pal, valoracion, new PalsRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pal> pals) {
                listPalsMutableLiveData.setValue(pals);
            }
        });
    }

    void seleccionar(Pal pal){ palSeleccionado.setValue(pal);}
    MutableLiveData<Pal> seleccionado(){
        return palSeleccionado;
    }

}
