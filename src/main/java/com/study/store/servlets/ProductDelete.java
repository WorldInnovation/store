package com.study.store.servlets;

import com.study.store.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String query = null;
        if (id != null || id == "" || id == "0"){
            throw new IllegalArgumentException(ServletConstants.PRODUCT_NOT_FOUND );
        } else {
            query = ServletConstants.SQL_SELECT_ALL + " were id=" + id;
        }

        try (Connection connect = DriverManager.getConnection(ServletConstants.SQL_CONNECTION);
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(query))

        {
            if (resultSet.next()) {
                String deleteQuery = ServletConstants.SQL_DELET_PRODUCT_STATMENT + id;
                statement.execute(deleteQuery);

            } else
            {
                throw new IllegalArgumentException(ServletConstants.PRODUCT_NOT_FOUND );
            }

        }  catch (Exception e ) {
            //  logger.error(CONNECTION_ERROR + e.getMessage(), e);
            throw new RuntimeException(ServletConstants.CONNECTION_ERROR + e.getMessage(), e);
        }

        resp.sendRedirect("/store");
    }
}
