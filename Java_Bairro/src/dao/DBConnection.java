/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class DBConnection {
  
    public Connection getMyConnection() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(Config.URL, Config.LOGIN, Config.PASSWORD);
    }
   
    public Connection connect(){
        try {
            return this.getMyConnection();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
             System.out.println(ex.getMessage());
             return null;
        }
    }
    public void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
