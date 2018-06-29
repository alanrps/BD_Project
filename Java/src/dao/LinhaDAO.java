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
import java.util.ArrayList;
import model.Linha;

/**
 *
 * @author alan
 */
public class LinhaDAO extends DBConnection{
    private Connection con;
    private final String sqlInsert = "INSERT INTO LINHA(nome) VALUES (?)";
    private final String sqlUpdate = "UPDATE LINHA SET nome= ?  WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM LINHA WHERE id = ?";
    private final String sqlList   = "SELECT id, nome FROM LINHA ORDER BY nome";
    private final String sqlFind   = "SELECT id, nome FROM LINHA WHERE id = ?";
    
     public void insert(Linha linha) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlInsert);
        
            ps.setString(1, linha.getNome());
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }        
    }

    public void update(Linha linha) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, linha.getNome());
            ps.setInt(2, linha.getId());
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
    
    public ArrayList<Linha> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Linha> list = new ArrayList<>();
            Linha linha;
            while (rs.next()){
                linha = new Linha();
                linha.setId(rs.getInt("id"));
                linha.setNome(rs.getString("nome"));
                list.add(linha);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
    }
    
    public Linha find(int id)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlFind);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            Linha linha = null ;
            if (rs.next()){
                linha = new Linha();
                linha.setId(rs.getInt("id"));
                linha.setNome(rs.getString("nome"));
            }
            return linha;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
        
    }
    
}
