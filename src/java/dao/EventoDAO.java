/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Evento;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author comuni
 */
public class EventoDAO {

    public boolean salvar(Evento e) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            //Se getId for 0 será uma inserção
            if (e.getId() == 0) {

                sql
                        = "insert into evento values ("
                        + "default,"
                        + "'" + e.getNome_evento() + "',"
                        + "'" + e.getCategoria_id() + "',"
                        + "'" + e.getData_evento() + "',"
                        + "'" + e.getValor_custo_evento() + "',"
                        + "'" + e.getObservacoes() + "')";
                System.out.println("SQL: " + sql);

                //Caso contrário será um update
            } else {
                sql
                        = "update evento "
                        + "set nome_evento = '" + e.getNome_evento() + "', "
                        + " categoria_id = " + e.getCategoria_id() + ", "
                        + " data_evento = '" + e.getData_evento() + "', "
                        + " valor_custo_evento = '" + e.getValor_custo_evento() + "', "
                        + " observacoes = '" + e.getObservacoes() + "' "
                        + "where id = " + e.getId();
                System.out.println("SQL: " + sql);

            }
            int resultado = st.executeUpdate(sql);

            return true;

        } catch (Exception ex) {
            System.out.println("Erro ao salvar produto: " + ex);
            return false;
        }
    }

    public ArrayList<Evento> consultarEventos() {

        ArrayList<Evento> eventos = new ArrayList<Evento>();

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql
                    = "select * "
                    + "from evento "
                    + "order by id";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Evento e = new Evento();
                e.setId(resultado.getInt("id"));
                e.setNome_evento(resultado.getString("nome_evento"));
                e.setCategoria_id(resultado.getInt("categoria_id"));
                e.setData_evento(resultado.getString("data_evento"));
                e.setValor_custo_evento(resultado.getInt("valor_custo_evento"));
                e.setObservacoes(resultado.getString("observacoes"));
                eventos.add(e);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar eventos: " + e);
        }

        return eventos;
    }
    
    public ArrayList<Evento> consultarEventos(String name){
        ArrayList<Evento> eventosFiltrados = new ArrayList<Evento>();
        Evento e = null;
        
        try {            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql 
                    = "select * "
                    + "from evento "
                    + "where nome_evento like '%" + name +"%'" ;
                    
            System.out.println("SQL: " + sql);
            
            ResultSet resultado = st.executeQuery(sql);
            
            while(resultado.next()){
                e = new Evento();
                e.setId(resultado.getInt("id"));
                e.setNome_evento(resultado.getString("nome_evento"));
                e.setCategoria_id(resultado.getInt("categoria_id"));
                e.setData_evento(resultado.getString("data_evento"));
                e.setValor_custo_evento(resultado.getInt("valor_custo_evento"));
                e.setObservacoes(resultado.getString("observacoes"));     
                eventosFiltrados.add(e);
            }

            
        } catch(Exception ex) {
            System.out.println("Erro ao consultar evento: " + ex);
        }
      
        return eventosFiltrados;
    }    
    
    public boolean excluir(int id) {
                
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql 
                    = "delete from evento "
                    + "where id = " + id;
                    
            System.out.println("SQL: " + sql);
            
            int resultado = st.executeUpdate(sql);
            
            return true;
        } catch(Exception ex) {
            System.out.println("Erro ao excluir evento: " + ex);
            return false;
        }
        
    }

    public Evento consultarEvento(int id) {    
        Evento e = null;
        try {            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql 
                    = "select * "
                    + "from evento "
                    + "where id = " + id +"" ;
                    
            System.out.println("SQL: " + sql);
            
            ResultSet resultado = st.executeQuery(sql);
            
            while(resultado.next()){
                e = new Evento();
                e.setId(resultado.getInt("id"));
                e.setNome_evento(resultado.getString("nome_evento"));
                e.setCategoria_id(resultado.getInt("categoria_id"));
                e.setData_evento(resultado.getString("data_evento"));
                e.setValor_custo_evento(resultado.getInt("valor_custo_evento"));
                e.setObservacoes(resultado.getString("observacoes"));            
            }
        } catch(Exception ex) {
            System.out.println("Erro ao consultar evento: " + ex);
        }
      
        return e;
    }

}
