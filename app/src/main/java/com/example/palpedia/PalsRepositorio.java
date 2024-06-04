package com.example.palpedia;

import java.util.ArrayList;
import java.util.List;

public class PalsRepositorio {

    List<Pal> pals = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Pal> pals);
    }

    PalsRepositorio(){
        pals.add(new Pal("Lamball", "Una caminata cuesta arriba tiende a terminar con este Pal cayendo de nuevo.", "Roly Poly", "Air Cannon", "Power Shot", "Fluffy Shield", "70", "70","70"));
    }

    List<Pal> obtener() { return pals; }

    void insertar(Pal pal, Callback callback){
        pals.add(pal);
        callback.cuandoFinalice(pals);
    }

    void eliminar(Pal pal, Callback callback){
        pals.remove(pal);
        callback.cuandoFinalice(pals);
    }

    void actualizar(Pal pal, float valoracion, Callback callback){
        pal.valoracion = valoracion;
        callback.cuandoFinalice(pals);
    }
}
