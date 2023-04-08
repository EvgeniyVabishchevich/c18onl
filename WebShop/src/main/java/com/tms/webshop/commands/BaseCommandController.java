package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BaseCommandController {
    Page execute(HttpServletRequest request, HttpServletResponse response);
}
