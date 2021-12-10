package com.p4pijk.carrental.servlet;

import com.p4pijk.carrental.command.CommandManager;
import com.p4pijk.carrental.command.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class MainServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(MainServlet.class);
    private CommandManager commandManager;

    public void init() throws ServletException {
        log.info("Initializing servlet");
        commandManager = new CommandManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Processing GET request");
        ServletCommand command = commandManager.getGetCommand(req);
        String loadedPage = command.execute(req, resp);
        req.getRequestDispatcher(loadedPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Processing POST request");
        ServletCommand command = commandManager.getPostCommand(req);
        String loadedMapping = command.execute(req, resp);
        resp.sendRedirect(req.getContextPath() + loadedMapping);
    }
}
