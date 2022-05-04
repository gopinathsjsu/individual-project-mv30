package com.cmpe_202.mv30.service;

import com.cmpe_202.mv30.dao.InventoryTable;
import com.cmpe_202.mv30.interfaces.FileIterator;
import com.cmpe_202.mv30.interfaces.Order;
import com.cmpe_202.mv30.pojo.OrderFileIterator;
import com.cmpe_202.mv30.pojo.OrderItemEntry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.ITEM_HEADING_KEY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.QUANTITY_HEADING_EKY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.PRICE_KEY;
import static com.cmpe_202.mv30.constants.OutCsvHeadersKeys.TOTAL_KEY;

public class FulfillmentService {

    private final String ERROR_FILE_PATH;

    private final String OUTPUT_FILE_PATH;

    private InventoryTable inventoryTable;

    public FulfillmentService() {
        this.ERROR_FILE_PATH = "./out.csv";
        this.OUTPUT_FILE_PATH = "./error.txt";
        this.inventoryTable = InventoryTable.getInstance();
    }

    public void handleOrder( Order order) throws IOException {
        String filePath = null;
        List<String> logs = null;
        if(order.isValid()) {
            filePath = OUTPUT_FILE_PATH;
            List<OrderItemEntry> orderItemEntryList = order.getOrderItemEntries();
            inventoryTable.fulfill(orderItemEntryList);
            logs = order.getFulfillmentLogs();
            Integer totalAmount = order.getTotal();
            if(logs.size()>0) {
                logs.set( 0, logs.get(0) + totalAmount.toString());
            }
        } else {
            filePath = ERROR_FILE_PATH;
            logs = order.getExcessAmountsLogs();
        }
        writeToFile( filePath, logs);
    }

    public void handleOrders( String path) throws Exception {
        FileIterator<Order> fileIterator = new OrderFileIterator(path);
        while(fileIterator.hasNext()) {
            Order currentOrder = fileIterator.next();
            handleOrder(currentOrder);
        }
    }

    public void writeToFile( String path, List<String> logs) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(path));
        String headers = String.format("%s,%s,%s,%s\n",ITEM_HEADING_KEY, QUANTITY_HEADING_EKY, PRICE_KEY, TOTAL_KEY);
        fileWriter.write(headers);
        for(String log: logs) {
            fileWriter.write(log+"\n");
        }
        fileWriter.close();
    }
}
