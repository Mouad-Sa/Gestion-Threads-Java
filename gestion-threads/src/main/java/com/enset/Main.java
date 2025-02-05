package com.enset;

import com.enset.tasks.Sommeur;
import com.enset.tasks.Talkative;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        // Exercice 1: Création et exécution de threads Talkative
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Talkative(i));
            t.start();
        }

        // Exercice 2: Calcul de la somme d'un tableau en parallèle
        int[] tableau = new int[1000];
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = i + 1;
        }

        int nbThreads = 5;
        ExecutorService executor = Executors.newFixedThreadPool(nbThreads);
        List<Future<?>> futures = new ArrayList<>();
        int taille = tableau.length / nbThreads;
        int sommeTotale = 0;

        List<Sommeur> sommeurs = new ArrayList<>();
        for (int i = 0; i < nbThreads; i++) {
            int debut = i * taille;
            int fin = (i == nbThreads - 1) ? tableau.length : (i + 1) * taille;
            Sommeur s = new Sommeur(tableau, debut, fin);
            sommeurs.add(s);
            futures.add(executor.submit(s));
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}

        for (Sommeur s : sommeurs) {
            sommeTotale += s.getSomme();
        }

        System.out.println("Somme totale du tableau: " + sommeTotale);
    }
}
