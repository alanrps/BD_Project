/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Bairro;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class BairroDAO extends DBConnection {
    private Connection con;
    private final String sqlInsert = "INSERT INTO BAIRRO(nome) VALUES (?)";
    private final String sqlUpdate = "UPDATE BAIRRO SET nome= ?  WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM BAIRRO WHERE id = ?";
    private final String sqlList   = "SELECT id, nome FROM BAIRRO ORDER BY nome";
    private final String sqlFind   = "SELECT id, nome FROM BAIRRO WHERE id = ?";
    private final String sqlFind2   = "SELECT id, nome FROM BAIRRO WHERE nome = ?";
    
    public void insert(Bairro bairro) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlInsert);
        
            ps.setString(1, bairro.getNome());
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }        
    }

    public void update(Bairro bairro) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, bairro.getNome());
            ps.setInt(2, bairro.getId());
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }
    }
    
    public void remove(int id) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlRemove);
            ps.setInt(1, id);
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }

    }
    
    public ArrayList<Bairro> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Bairro> list = new ArrayList<>();
            Bairro bairro;
            while (rs.next()){
                bairro = new Bairro();
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
                list.add(bairro);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
    }
    
    public Bairro find(int id)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlFind);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            Bairro bairro = null ;
            if (rs.next()){
                bairro = new Bairro();
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
            }
            return bairro;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
        
    }
    
    public Bairro find2(String nome)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlFind2);
            ps.setString(1, nome);

            rs = ps.executeQuery();
            Bairro bairro = null ;
            if (rs.next()){
                bairro = new Bairro();
                bairro.setId(rs.getInt("id"));
                bairro.setNome(rs.getString("nome"));
            }
            return bairro;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
        
    }
}
