/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.cs313.teamreadiness11;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dblankenship
 */
@WebServlet(name = "PersonDetail", urlPatterns = {"/PersonDetail"})
public class PersonDetail extends HttpServlet {

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
        
        String id = request.getParameter("id");
        
        String personDetailSql = "SELECT * FROM ancestor WHERE id = " + id;
        
        String getChildrenSql = "SELECT * FROM ancestor CROSS JOIN ("
                                    + "SELECT childOf.child_id FROM ancestor LEFT JOIN childOf "
                                    + "ON ancestor.id = childOf.parent_id WHERE ancestor.id = " + id
                                + ") AS result "
                                + "WHERE result.child_id = ancestor.id";
        
        String getParentsSql = "SELECT * FROM ancestor CROSS JOIN ("
                                    + "SELECT childOf.parent_id FROM ancestor LEFT JOIN childOf "
                                    + "ON ancestor.id = childOf.child_id WHERE ancestor.id = " + id
                                + ") AS result "
                                + "WHERE result.parent_id = ancestor.id";
        
        ResultSet personDetailResult = null;
        ResultSet childrenResult = null;
        ResultSet parentsResult = null;

        String personDetail = null;
        List<String> parents = new ArrayList<>();
        List<String> children = new ArrayList<>();
        
        try {
            Connection conn = Database.getConnection();
            
            PreparedStatement personDetailStmt = conn.prepareStatement(personDetailSql);
            personDetailResult = personDetailStmt.executeQuery();
            
            PreparedStatement childrenStmt = conn.prepareStatement(getChildrenSql);
            childrenResult = childrenStmt.executeQuery();
            
            PreparedStatement parentStmt = conn.prepareStatement(getParentsSql);
            parentsResult = parentStmt.executeQuery();
            
            personDetailResult.next();
            personDetail = personDetailResult.getString("first_name") + " " + personDetailResult.getString("last_name") + " DOB: " + personDetailResult.getString("birthday");
            
            while (childrenResult.next()) {
                children.add(childrenResult.getString("first_name") + " " + childrenResult.getString("last_name"));
            }
            
            while (parentsResult.next()) {
                parents.add(parentsResult.getString("first_name") + " " + parentsResult.getString("last_name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        request.setAttribute("personDetail", personDetail);
        request.setAttribute("parents", parents);
        request.setAttribute("children", children);
        request.getRequestDispatcher("personDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
