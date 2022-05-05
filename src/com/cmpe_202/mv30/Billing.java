package com.cmpe_202.mv30;

import com.cmpe_202.mv30.constants.CategoryType;
import com.cmpe_202.mv30.dao.InventoryTable;
import com.cmpe_202.mv30.service.FulfillmentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Billing {

    public void loadData() {
        InventoryTable inventoryTable = InventoryTable.getInstance();
        inventoryTable.addItem(CategoryType.ESSENTIALS, "Clothes", 100, 20);
        inventoryTable.addItem(CategoryType.ESSENTIALS, "Soap", 200, 5);
        inventoryTable.addItem(CategoryType.ESSENTIALS, "Shampoo", 200, 10);
        inventoryTable.addItem(CategoryType.ESSENTIALS, "Milk", 100, 5);

        inventoryTable.addItem(CategoryType.LUXURY, "Perfume", 50, 50);
        inventoryTable.addItem(CategoryType.LUXURY, "Chocolates", 300, 3);
        inventoryTable.addItem(CategoryType.LUXURY, "Handbag", 75, 150);
        inventoryTable.addItem(CategoryType.LUXURY, "Wallet", 100, 100);

        inventoryTable.addItem(CategoryType.MISCELLANEOUS, "Bedsheet", 150, 75);
        inventoryTable.addItem(CategoryType.MISCELLANEOUS, "Footware", 200, 25);
        inventoryTable.addItem(CategoryType.MISCELLANEOUS, "HomeDecorPiece", 100, 40);
        inventoryTable.addItem(CategoryType.MISCELLANEOUS, "Pen", 400, 3);
        inventoryTable.addItem(CategoryType.MISCELLANEOUS, "Pencil", 400, 3);
    }

    public String getInputFilePath() throws IOException {
        System.out.println(" enter the path to input file path ");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        return br.readLine();
    }

    public void drive() throws Exception{
        loadData();
        final String ORDERS_FILE_PATH = getInputFilePath();
        FulfillmentService fulfillmentService = new FulfillmentService();
        fulfillmentService.handleOrders(ORDERS_FILE_PATH);
    }

    public static void main(String[] args) throws Exception {

        new Billing().drive();
    }
}