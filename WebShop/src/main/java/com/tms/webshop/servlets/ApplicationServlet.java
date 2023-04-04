package com.tms.webshop.servlets;

import com.tms.webshop.commands.BaseCommandController;
import com.tms.webshop.commands.CommandControllerFactory;
import com.tms.webshop.model.enums.Command;
import com.tms.webshop.model.enums.RequestParams;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@MultipartConfig
@WebServlet(value = "/eshop")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String commandKey = request.getParameter(RequestParams.COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = Command.SIGN_IN.getCommand();
        }

        BaseCommandController baseCommandController = CommandControllerFactory.defineCommand(Command.fromString(commandKey));

        String path;
        try {
            path = baseCommandController.execute(request, response);
            if (path != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            log.error("Error, while trying to forward to the next page", e);
        }
    }
}
