package com.pluralsight.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class TableHeaderPrep {
    public static TableView<Transaction> tableBuilder() {
        TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, LocalTime> timeCol = new TableColumn<>("Time");
        timeCol.setMinWidth(200);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Transaction, String> description = new TableColumn<>("Description");
        description.setMinWidth(200);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, String> vendor = new TableColumn<>("Vendor");
        vendor.setMinWidth(200);
        vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));

        TableColumn<Transaction, Double> amount = new TableColumn<>("Amount");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableView<Transaction> table = new TableView<>();

        table.getColumns().addAll(dateCol, timeCol, description, vendor, amount);

        return table;
    }
}
