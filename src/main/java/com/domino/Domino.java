package com.domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Domino {

    private List<Ficha> fichas = new ArrayList<>();

    public Domino() {
        crearFichas();
    }

    private void crearFichas() {

        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fichas.add(new Ficha(i, j));
            }
        }
    }

    public void barajar() {

        Collections.shuffle(fichas);
    }

    public List<Ficha> getFichas() {
        //  lista con objeto ficha .
        return fichas;
    }
}
