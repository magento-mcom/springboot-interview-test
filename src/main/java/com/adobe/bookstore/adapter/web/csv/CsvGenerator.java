package com.adobe.bookstore.adapter.web.csv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.adobe.bookstore.domain.Order;

@Component
public class CsvGenerator {
	
	public void generateCsv(HttpServletResponse response, List<Order> orders) throws IOException {
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=orders_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);
        CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT);
        for (Order order : orders) {
        	csvPrinter.printRecord(order.getId(), order.getBooks());
        	csvPrinter.println();
        }
	}
}
