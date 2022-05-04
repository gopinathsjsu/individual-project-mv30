package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.interfaces.FileIterator;
import com.cmpe_202.mv30.interfaces.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFileIterator extends FileIterator<Order> {

    private final BufferedReader bufferedReader;

    private String currString;

    public OrderFileIterator( String filePath) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        // read the first line to skip the csv headers
        bufferedReader.readLine();
        currString = bufferedReader.readLine();
    }

    @Override
    public Boolean hasNext() {
        return (currString!=null);
    }

    @Override
    public Order next() throws Exception {
        List<List<String>> orderDetailsArray = new ArrayList<>();
        do {
            orderDetailsArray.add(Arrays.asList(currString.split(",")));
            currString = bufferedReader.readLine();
        } while((currString!=null) && (Arrays.asList(currString.split(",")).size()<3));
        if(orderDetailsArray.isEmpty()) {
            return null;
        }
        String cardNumber = orderDetailsArray.get(0).get(2);
        System.out.println(String.format("processing for card number %s", cardNumber));
        List<OrderItemEntry> orderItemEntries = new ArrayList<>();
        for(List<String> orderItem: orderDetailsArray) {
            String itemName = orderItem.get(0).toLowerCase();
            Integer itemCount = Integer.valueOf(orderItem.get(1));
            orderItemEntries.add(new OrderItemEntry(itemName, itemCount));
        }
        OrderBuilder orderBuilder = new OrderBuilder();
        for(OrderItemEntry orderItemEntry: orderItemEntries) {
            orderBuilder.withOrderItem(orderItemEntry);
        }
        return orderBuilder.build();
    }
}
