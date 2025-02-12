/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author huyng
 */
@WebServlet(name = "ProcessServlet", urlPatterns = {"/process"})
public class ProcessServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pdb = new ProductDAO();
        List<Product> prods = pdb.getAll();
        String id_raw = request.getParameter("id");
        String num_raw = request.getParameter("num");
        Cookie arr[] = request.getCookies();
        int id, num;
        try {
            String txt = "";
            for (Cookie cookie : arr) {
                if (cookie.getName().equalsIgnoreCase("cart")) {
                    txt += URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            Cart cart = new Cart(txt, prods);
            System.out.println(txt);
            List<Item> list = cart.getItems();
            id = Integer.parseInt(id_raw);
            num = Integer.parseInt(num_raw);
            Item item = cart.getItemById(id);
            if (num == -1 && cart.getQuantityById(id) <= 1) {
                cart.removeItem(id);
            } else if (num == -1 && cart.getQuantityById(id) > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else if (num == 1) {
                item.setQuantity(item.getQuantity() + 1);
            }
            //phai edit lai encodedText thanh current cart -> 
            String newText = "";
            for (Item item1 : list) {
                newText += item1.getProduct().getId() + ":" + item1.getQuantity() + ",";
            }
            newText = newText.substring(0, newText.length() - 1);
            String encodedText = URLEncoder.encode(newText, StandardCharsets.UTF_8);
            //create new cookie for cart
            Cookie c = new Cookie("cart", encodedText);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("MyEcart.jsp").forward(request, response);
        } catch (NumberFormatException e) {

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pdb = new ProductDAO();
        List<Product> prods = pdb.getAll();
        String id_raw = request.getParameter("id");
        Cookie arr[] = request.getCookies();
        int id;
        try {
            String txt = "";
            for (Cookie cookie : arr) {
                if (cookie.getName().equalsIgnoreCase("cart")) {
                    txt += URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            Cart cart = new Cart(txt, prods);
            System.out.println(txt);
            List<Item> list = cart.getItems();
            id = Integer.parseInt(id_raw);
            Iterator<Item> iterator = list.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getProduct().getId() == id) {
                    iterator.remove(); // Safe removal
                }
            }
            //phai edit lai encodedText thanh current cart -> 
            String newText = "";
            for (Item item1 : list) {
                newText += item1.getProduct().getId() + ":" + item1.getQuantity() + ",";
            }
            newText = newText.substring(0, newText.length() - 1);
            String encodedText = URLEncoder.encode(newText, StandardCharsets.UTF_8);
            //create new cookie for cart
            Cookie c = new Cookie("cart", encodedText);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("MyEcart.jsp").forward(request, response);
        } catch (NumberFormatException e) {

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
