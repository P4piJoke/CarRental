package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiptAction implements ServletCommand {

    private static final Logger log = Logger.getLogger(ReceiptAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
