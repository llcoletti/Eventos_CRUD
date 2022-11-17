/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Categoria;
import entidade.Evento;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author comuni
 */
public class CategoriaDAO {
    
    public ArrayList<Categoria> consultarCategorias() {

        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "select * "
                    + "from categoria "
                    + "order by id";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                
                System.out.println("categoria: "+ resultado.getString("categoria"));
                Categoria c = new Categoria();
                c.setId( resultado.getInt("id") );
                c.setDescricao( resultado.getString("descricao") );
                c.setCategoria( resultado.getString("categoria") );
                categorias.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar eventos: " + e);
        }

        return categorias;
    }
    public  Categoria consultarCategoria(int id) {

        Categoria c = new Categoria();

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "select * "
                    + "from categoria "
                    + "where id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                System.out.println("categoria: "+ resultado.getString("categoria"));
                c.setId( resultado.getInt("id") );
                c.setDescricao( resultado.getString("descricao") );
                c.setCategoria( resultado.getString("categoria") );                
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar eventos: " + e);
        }

        return c;
    }
    
}
