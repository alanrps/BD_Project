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
import model.Ponto;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class PontoDAO extends DBConnection{
    private Connection con;
    private final String sqlInsert = "INSERT INTO PONTO(idRua, idBairro) VALUES (?, ?)";
    private final String sqlUpdate = "UPDATE PONTO SET idRua= ?, idBairro= ? WHERE id = ? ";
    private final String sqlRemove = "DELETE FROM PONTO WHERE id = ?";
    private final String sqlList   = "SELECT id, idRua, idBairro FROM PONTO ORDER BY id";
    private final String sqlFind   = "SELECT id, idRua, idBairro FROM PONTO WHERE id = ?";
    
    public void insert(Ponto ponto) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlInsert);
        
            ps.setInt(1, ponto.getRua().getId());
            ps.setInt(2, ponto.getBairro().getId());
            ps.execute();
        }
        finally{
            ps.close();
            close(con);
        }        
    }

    public void update(Ponto ponto) throws SQLException{
        PreparedStatement ps = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlUpdate);
            ps.setInt(1, ponto.getRua().getId());
            ps.setInt(2, ponto.getBairro().getId());
            ps.setInt(3, ponto.getId());
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
    
    public ArrayList<Ponto> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Ponto> list = new ArrayList<>();
            Ponto ponto;
            RuaDAO ruaDao = new RuaDAO();
            BairroDAO bairroDao = new BairroDAO();
            while (rs.next()){
                ponto = new Ponto();
                ponto.setId(rs.getInt("id"));
                ponto.setRua(ruaDao.find(rs.getInt("idRua")));
                ponto.setBairro(bairroDao.find(rs.getInt("idBairro")));
                list.add(ponto);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
    }
    
    public Ponto find(int id)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = connect();
            ps = con.prepareStatement(sqlFind);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            Ponto ponto = null ;
            RuaDAO ruaDao = new RuaDAO();
            BairroDAO bairroDao = new BairroDAO();
            if (rs.next()){
                ponto = new Ponto();
                ponto.setId(rs.getInt("id"));
                ponto.setRua(ruaDao.find(rs.getInt("idRua")));
                ponto.setBairro(bairroDao.find(rs.getInt("idBairro")));
            }
            return ponto;
        }
        finally{
            rs.close();
            ps.close();
            close(con);
        }
        
    }
}
