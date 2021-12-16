package com.p4pijk.carrental.command;

import com.p4pijk.carrental.command.admin.AdminPage;
import com.p4pijk.carrental.command.admin.ChangeUserStatus;
import com.p4pijk.carrental.command.admin.DeleteCar;
import com.p4pijk.carrental.command.getcommands.AddManagerGetCommand;
import com.p4pijk.carrental.command.getcommands.AddNewCarGetCommand;
import com.p4pijk.carrental.command.getcommands.CheckCommentCommand;
import com.p4pijk.carrental.command.getcommands.EditCarGetCommand;
import com.p4pijk.carrental.command.getcommands.GetCarByClass;
import com.p4pijk.carrental.command.getcommands.GetCarByCostCE;
import com.p4pijk.carrental.command.getcommands.GetCarByCostEC;
import com.p4pijk.carrental.command.getcommands.GetCarByMark;
import com.p4pijk.carrental.command.getcommands.GetCarByNameAZ;
import com.p4pijk.carrental.command.getcommands.GetCarByNameZA;
import com.p4pijk.carrental.command.getcommands.GoToUserCabinet;
import com.p4pijk.carrental.command.getcommands.LoginGetCommand;
import com.p4pijk.carrental.command.getcommands.LogoutGetCommand;
import com.p4pijk.carrental.command.getcommands.RejectReceiptCommand;
import com.p4pijk.carrental.command.getcommands.SignUpGetCommand;
import com.p4pijk.carrental.command.getcommands.TakeOrderGetCommand;
import com.p4pijk.carrental.command.getcommands.TopUpGetCommand;
import com.p4pijk.carrental.command.getcommands.VerifyReceiptCommand;
import com.p4pijk.carrental.command.manager.CheckReceipt;
import com.p4pijk.carrental.command.manager.ManagerPage;
import com.p4pijk.carrental.command.postcommands.AddManagerCommand;
import com.p4pijk.carrental.command.postcommands.AddNewCarCommand;
import com.p4pijk.carrental.command.postcommands.EditCarCommand;
import com.p4pijk.carrental.command.postcommands.LoginCommand;
import com.p4pijk.carrental.command.postcommands.ReceiptAction;
import com.p4pijk.carrental.command.postcommands.RejectReceipt;
import com.p4pijk.carrental.command.postcommands.SignUpCommand;
import com.p4pijk.carrental.command.postcommands.TakeOrderCommand;
import com.p4pijk.carrental.command.postcommands.TopUpCommand;
import com.p4pijk.carrental.command.postcommands.VerifyReceipt;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    private final static Logger log = Logger.getLogger(CommandManager.class);
    private final Map<String, ServletCommand> getCommands;
    private final Map<String, ServletCommand> postCommands;

    public CommandManager() {
        getCommands = new ConcurrentHashMap<>();
        postCommands = new ConcurrentHashMap<>();

        log.info("GET commands are loaded");
        getCommands.put("/login", new LoginGetCommand());
        getCommands.put("/signup", new SignUpGetCommand());
        getCommands.put("/admin", new AdminPage());
        getCommands.put("/manager", new ManagerPage());
        getCommands.put("/logout", new LogoutGetCommand());
        getCommands.put("/getCarByMark", new GetCarByMark());
        getCommands.put("/getCarByClass", new GetCarByClass());
        getCommands.put("/getCarByNameAZ", new GetCarByNameAZ());
        getCommands.put("/getCarByNameZA", new GetCarByNameZA());
        getCommands.put("/getCarByCostEC", new GetCarByCostEC());
        getCommands.put("/getCarByCostCE", new GetCarByCostCE());
        getCommands.put("/addNewCar", new AddNewCarGetCommand());
        getCommands.put("/editCar", new EditCarGetCommand());
        getCommands.put("/addManager", new AddManagerGetCommand());
        getCommands.put("/takeOrder", new TakeOrderGetCommand());
        getCommands.put("/cabinet", new GoToUserCabinet());
        getCommands.put("/topUp", new TopUpGetCommand());
        getCommands.put("/checkComment", new CheckCommentCommand());
        getCommands.put("/rejectReceipt", new RejectReceiptCommand());
        getCommands.put("/verifyReceipt", new VerifyReceiptCommand());

        log.info("POST commands are loaded");
        postCommands.put("/login", new LoginCommand());
        postCommands.put("/signup", new SignUpCommand());
        postCommands.put("/addNewCar", new AddNewCarCommand());
        postCommands.put("/editCar", new EditCarCommand());
        postCommands.put("/deleteCar", new DeleteCar());
        postCommands.put("/addManager", new AddManagerCommand());
        postCommands.put("/takeOrder", new TakeOrderCommand());
        postCommands.put("/topUp", new TopUpCommand());
        postCommands.put("/changeStatus", new ChangeUserStatus());
        postCommands.put("/receiptAction", new ReceiptAction());
        postCommands.put("/checkReceipt", new CheckReceipt());
        postCommands.put("/rejectReceipt", new RejectReceipt());
        postCommands.put("/verifyReceipt", new VerifyReceipt());
    }

    public ServletCommand getGetCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (getCommands.get(command) == null) {
            return getCommands.get("/");
        }
        return getCommands.get(command);
    }

    public ServletCommand getPostCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (postCommands.get(command) == null) {
            return postCommands.get("/");
        }
        return postCommands.get(command);
    }

    private String getMapping(HttpServletRequest req) {
        String mapping = req.getRequestURI().substring(req.getContextPath().length());
        if (mapping.endsWith("/")) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }
        return mapping;
    }
}
