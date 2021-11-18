package com.study.store.servlets;

import com.study.store.ProductModel;
import com.study.store.ServletConstants;
import com.study.store.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/productEdit")
public class ProductEdit extends HttpServlet {

    // public final static Logger logger = LoggerFactory.getLogger(HttpServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String query = null;
        if (id != null || id == "" || id == "0"){
            query = ServletConstants.SQL_SELECT_ALL;
        } else {
            query = ServletConstants.SQL_SELECT_ALL + " were id=" + id;
        }

        try (Connection connect = DriverManager.getConnection(ServletConstants.SQL_CONNECTION);
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(query))

        {
            List<ProductModel> productModelList = new ArrayList<>();
            if (resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setDate(resultSet.getDate("data"));
                productModelList.add(productModel);
            } else
            {
                ProductModel productModel = new ProductModel();
                productModelList.add(productModel);
            }
            //--------------
            Map<String, Object> pageVariables = createPageVariablesMap(req, productModelList);
            pageVariables.put("message", "");

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("editPage.html", pageVariables);

            resp.getWriter().println(page);


        }  catch (Exception e) {
            //  logger.error(CONNECTION_ERROR + e.getMessage(), e);
            throw new RuntimeException(ServletConstants.CONNECTION_ERROR + e.getMessage(), e);
        }



        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String query = null;
        if (id != null || id == "" || id == "0"){
            query = ServletConstants.SQL_SELECT_ALL;
        } else {
            query = ServletConstants.SQL_SELECT_ALL + " were id=" + id;
        }

        try (Connection connect = DriverManager.getConnection(ServletConstants.SQL_CONNECTION);
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(query))

        {
            List<ProductModel> productModelList = new ArrayList<>();
            if (resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setDate(resultSet.getDate("data"));
                productModelList.add(productModel);
            } else
            {
                ProductModel productModel = new ProductModel();
                productModelList.add(productModel);
            }
            //--------------
            Map<String, Object> pageVariables = createPageVariablesMap(req, productModelList);
            pageVariables.put("message", "");

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("editPage.html", pageVariables);

            resp.getWriter().println(page);


        }  catch (Exception e) {
            //  logger.error(CONNECTION_ERROR + e.getMessage(), e);
            throw new RuntimeException(ServletConstants.CONNECTION_ERROR + e.getMessage(), e);
        }



        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }


    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, List<ProductModel> productModelList) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", productModelList);
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

    private void dbConnect() {

    }


}
