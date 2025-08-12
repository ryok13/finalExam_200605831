// Ryo Kato, 200605831

package com.georgiancollege.finalexam_200605831;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class TableViewController {
    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> totalPurchaseColumn;

    @FXML
    private ListView<Product> purchaseListView;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchasesFormatted"));

        tableView.getItems().addListener((ListChangeListener<Customer>) c -> updateRowCountLabel());

        loadAllCustomers();
    }

    @FXML
    private void loadAllCustomers()
    {
        System.out.println("called method loadAllCustomers");

        try {
            var customers = JsonReader.loadCustomers();
            tableView.getItems().setAll(customers);
            updateRowCountLabel();
        } catch (Exception e) {
            rowsInTableLabel.setText("Failed to load customers.json");
            e.printStackTrace();
        }
    }

    private void updateRowCountLabel() {
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
    }

}
