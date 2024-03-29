package com.study.store.main;

import com.study.store.servlets.AllRequestsServlet;
import com.study.store.servlets.StoreServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new StoreServlet()), "/store");
        context.addServlet(new ServletHolder(new StoreServlet()), "/productEdit");
        context.addServlet(new ServletHolder(new StoreServlet()), "/productDelete");
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
