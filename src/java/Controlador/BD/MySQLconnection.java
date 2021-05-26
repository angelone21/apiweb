/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Osnayder
 */
public class MySQLconnection {

    private  Connection con = null;
    private  boolean status = false;
    
    public MySQLconnection() {
        if(con==null){
            try {
                String user = "root";
                String password = "";
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                String sURL = "jdbc:mysql://localhost:3306/capstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(sURL,user,password);
                status = true;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
            }
            
        }
    }
    
    
    public Connection getConnection() {
        return this.con;
    }

    public boolean getStatus() {
        return this.status;
    }
    
    public void close(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
