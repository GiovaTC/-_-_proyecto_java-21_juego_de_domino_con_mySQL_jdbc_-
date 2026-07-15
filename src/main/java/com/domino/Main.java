package com.domino;


public class Main {

    public static void main(String[] args) {
        try {
            Domino domino = new Domino();

            domino.barajar();

            DominoDAO dao = new DominoDAO();

            System.out.println();
            System.out.println("DOMINO");
            System.out.println("----------------------");

            for (Ficha ficha : domino.getFichas()) {

                System.out.println(ficha);
                dao.guardar(ficha);

            }

            dao.mostrar();

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}