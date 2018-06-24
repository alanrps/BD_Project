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
import model.Rua;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class RuaDAO extends DBConnection{
    private Connection con;
    private final String sqlInsert = "INSERT INTO RUA(nome) VALUES (?)";
    private final String sqlUpdate = "UPDATE RUA SET nome= ?  WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM RUA WHERE id = ?";
    private final String sqlList   = "SELECT id, nome FROM RUA ORDER BY nome";
    private final String sqlFind   = "SELECT id, nome FROM RUA WHERE id = ?";
    public BairroDao() {
        initComponents();
    }
    public void insert(Rua rua) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlInsert);
        
            ps.setString(1, rua.getNome());
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }        
    }

    public void update(Rua rua) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, rua.getNome());
            ps.setInt(2, rua.getId());
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
    
    public ArrayList<Rua> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Rua> list = new ArrayList<>();
            Rua rua;
            while (rs.next()){
                rua = new Rua();
                rua.setId(rs.getInt("id"));
                rua.setNome(rs.getString("nome"));
                list.add(rua);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
    }
    
    public Rua find(int id)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlFind);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            Rua rua = null ;
            if (rs.next()){
                rua = new Rua();
                rua.setId(rs.getInt("id"));
                rua.setNome(rs.getString("nome"));
            }
            return rua;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
        
    }
}
