package com.domino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DominoDAO {

    public void  guardar(Ficha ficha) throws Exception {

        Connection cn = Conexion.conectar();

        String sql =
                "INSERT INTO fichas(lado1,lado2) VALUES(?,?)";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setInt(1, ficha.getLado1());
        ps.setInt(2, ficha.getLado2());

        ps.executeUpdate();
        ps.close();
        cn.close();
    }

    public void mostrar() throws Exception {

        Connection cn = Conexion.conectar();

        String sql = "SELECT * FROM fichas";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println();
        System.out.println("FICHAS EN MYSQL");
        System.out.println("--------------------");

        while (rs.next()) {

            System.out.println(

                    rs.getInt("id")
                        + " "
                        + "["
                    + rs.getInt("lado1")
                    + "|"
                    + rs.getInt("lado2")
                    + "]"
            );
        }

        rs.close();

        ps.close();

        cn.close();
    }
}
