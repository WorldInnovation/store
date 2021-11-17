package com.study.store.servlets;

import com.study.store.ProductModel;
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

@WebServlet("/store")
public class StoreServlet extends HttpServlet {
   // public final static Logger logger = LoggerFactory.getLogger(HttpServlet.class);
    public static final String SQL_SELECT_ALL = "select * from store.products";
    public static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/store?user=root&password=root";
    public static final String CONNECTION_ERROR = "Connection error";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();



        try (Connection connect = DriverManager.getConnection(SQL_CONNECTION);
              Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL))

        {
            out.println("<table border=\"1\">");
            List<ProductModel> productModelList = new ArrayList<>();
            while(resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setDate(resultSet.getDate("data"));
                productModelList.add(productModel);
            }
            //--------------
            Map<String, Object> pageVariables = createPageVariablesMap(req, productModelList);
            pageVariables.put("message", "");

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("storePage.html", pageVariables);

            resp.getWriter().println(page);


        }  catch (Exception e) {
          //  logger.error(CONNECTION_ERROR + e.getMessage(), e);
            throw new RuntimeException(CONNECTION_ERROR + e.getMessage(), e);
        }



        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }


    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, List<ProductModel> productModelList) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productModelList);
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
