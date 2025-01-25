/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.LocalDate;
import model.Cart;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Item;

/**
 *
 * @author huyng
 */
public class OrderDAO extends DBContext {

    public void addOrder(Customer u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            //add to Order table
            String sql = "insert into [Order] values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, date);
            stmt.setInt(2, u.getId());
            stmt.setDouble(3, cart.totalMoney());
            stmt.executeUpdate();
            String sql1 = "select top 1 id from [Order] order by id desc";
            PreparedStatement stmt1 = connection.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();
            //add to orderline table
            while (rs.next()) {
                int oid = rs.getInt("id");
                for (Item item : cart.getItems()) {
                    String sql2 = "insert into [OrderLine] values(?,?,?,?)";
                    PreparedStatement stmt2 = connection.prepareStatement(sql2);
                    stmt2.setInt(1, oid);
                    stmt2.setInt(2, item.getProduct().getId());
                    stmt2.setInt(3, item.getQuantity());
                    stmt2.setDouble(4, item.getPrice());
                    stmt2.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
