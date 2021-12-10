package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewCarGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddNewCarGetCommand.class);
    private final String resultPage;

    public AddNewCarGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("addNewCar");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing additional new car by GET command");
        return resultPage;
    }
}
