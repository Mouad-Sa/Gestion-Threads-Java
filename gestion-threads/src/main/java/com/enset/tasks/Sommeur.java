package com.enset.tasks;

// Exercice 2: Classe Sommeur implémentant Runnable
public class Sommeur implements Runnable {
    private int[] tableau;
    private int debut, fin;
    private int somme;

    public Sommeur(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        somme = 0;
        for (int i = debut; i < fin; i++) {
            somme += tableau[i];
        }
    }

    public int getSomme() {
        return somme;
    }
}
