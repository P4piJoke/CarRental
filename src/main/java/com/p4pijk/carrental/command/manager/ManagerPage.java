package com.p4pijk.carrental.command.manager;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.util.MappingProperties;
import com.p4pijk.carrental.util.ReceiptUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerPage implements ServletCommand {

    private static final Logger log = Logger.getLogger(ManagerPage.class);
    private final ReceiptService receiptService;
    private final String managerPage;

    public ManagerPage() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPage = properties.getProperty("managerPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing manager page get command");
        ReceiptUtil.setRecipesAttributes(req, receiptService);
        return managerPage;
    }
}
