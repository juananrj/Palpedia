package com.example.palpedia;

public class Pal {

    String nombre;
    String descripcion;
    String habilidad1;
    String habilidad2;
    String habilidad3;
    String pasiva;
    String vida;
    String ataque;
    String defensa;
    float valoracion;
    public Pal(String nombre, String descripcion, String habilidad1, String habilidad2, String habilidad3, String pasiva, String vida, String ataque, String defensa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.habilidad3 = habilidad3;
        this.pasiva = pasiva;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }
}
