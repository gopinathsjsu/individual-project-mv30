package com.cmpe_202.mv30.service;

import com.cmpe_202.mv30.dao.InventoryTable;
import com.cmpe_202.mv30.interfaces.FileIterator;
import com.cmpe_202.mv30.interfaces.Order;
import com.cmpe_202.mv30.pojo.OrderFileIterator;
import com.cmpe_202.mv30.pojo.OrderItemEntry;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.ITEM_KEY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.QUANTITY_EKY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.PRICE_KEY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.TOTAL_KEY;

public class FulfillmentService {

    private final String ERROR_FILE_PATH;

    private final String OUTPUT_FILE_PATH;

    private InventoryTable inventoryTable;

    public FulfillmentService() {
        this.ERROR_FILE_PATH = "./error.txt";
        this.OUTPUT_FILE_PATH = "./out.csv";
        this.inventoryTable = InventoryTable.getInstance();
    }

    public void handleOrder( Order order, LogService outputLogService, LogService errorLogService) throws IOException {
        String filePath = null;
        List<String> logs = null;
        if(order.isValid()) {
            filePath = OUTPUT_FILE_PATH;
            List<OrderItemEntry> orderItemEntryList = order.getOrderItemEntries();
            inventoryTable.fulfill(orderItemEntryList);
            logs = order.getFulfillmentLogs();
            Integer totalAmount = order.getTotalAmount();
            if(logs.size()>0) {
                logs.set( 0, logs.get(0) + totalAmount.toString());
            }
            outputLogService.log(String.format("%s,%s,%s,%s",ITEM_KEY, QUANTITY_EKY, PRICE_KEY, TOTAL_KEY), logs);
        } else {
            filePath = ERROR_FILE_PATH;
            logs = order.getExcessAmountsLogs();
            Collections.reverse(logs);
            logs.add(String.format(" Error for card number : %s ", order.getCardNumber()));
            Collections.reverse(logs);
            errorLogService.log("", logs);
        }
    }

    public void handleOrders( String path) throws Exception {
        LogService outputLogService = new LogService(this.OUTPUT_FILE_PATH);
        LogService errorLogService = new LogService(this.ERROR_FILE_PATH);
        FileIterator<Order> fileIterator = new OrderFileIterator(path);
        while(fileIterator.hasNext()) {
            Order currentOrder = fileIterator.next();
            handleOrder(currentOrder, outputLogService, errorLogService);
        }
    }
}
