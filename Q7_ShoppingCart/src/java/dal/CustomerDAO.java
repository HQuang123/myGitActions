/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huyng
 */
public class CustomerDAO extends DBContext {

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from customer";
        try {
            Statement st = connection.createStatement(); //create an object prepareStatement 
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Customer t = new Customer();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setAmount(rs.getDouble("amount"));
                t.setUserName(rs.getString("username"));
                t.setPassword(rs.getString("password"));
                list.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Customer getAccount(String user, String pass) {
        String sql = "select * from customer where "
                + "username =? and password = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("amount"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//in case of sql injection
    public Customer getAccountSQLInject(String user, String pass) {
        String sql = "select * from customer where ";
        try {
            Statement stmt = connection.createStatement();
            sql += "username = " + user + "and password=" + pass;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("amount"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
