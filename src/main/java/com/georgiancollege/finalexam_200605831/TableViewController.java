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
    private void loadSavedFiveOrMore() {
        try {
            var customers = JsonReader.loadCustomers();

            var filtered = customers.stream()
                    .filter(Customer::savedFiveOrMore)
                    .toList();

            tableView.getItems().setAll(filtered);
            updateRowCountLabel();

            if (!filtered.isEmpty()) {
                tableView.getSelectionModel().selectFirst();
            } else {
                purchaseListView.getItems().clear();
                updatePurchaseLabels(null);
            }

            System.out.println("Filtered customers (saved >= $5): " + filtered.size());
        } catch (Exception e) {
            rowsInTableLabel.setText("Failed to filter customers");
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchasesFormatted"));

        imageView.setVisible(false);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            purchaseListView.getItems().clear();
            purchaseListView.getSelectionModel().clearSelection();
            clearImage();

            if (newSel != null) {
                System.out.println("selected purchases size = " + newSel.getPurchases().size());
                purchaseListView.getItems().setAll(newSel.getPurchases());
            }
            updatePurchaseLabels(newSel);
        });

        purchaseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldP, newP) -> {
            showProductImage(newP);
        });

        tableView.getItems().addListener((ListChangeListener<Customer>) c -> updateRowCountLabel());

        loadAllCustomers();
    }

    @FXML
    private void loadAllCustomers()
    {
        System.out.println("called method loadAllCustomers");

        try {
            tableView.getSelectionModel().clearSelection();

            var customers = JsonReader.loadCustomers();
            tableView.getItems().setAll(customers);

            updateRowCountLabel();

            if (!customers.isEmpty()) {
                tableView.getSelectionModel().selectFirst();
            } else {
                purchaseListView.getItems().clear();
                updatePurchaseLabels(null);
            }
        } catch (Exception e) {
            rowsInTableLabel.setText("Failed to load customers.json");
            e.printStackTrace();
        }
    }

    private void updateRowCountLabel() {
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
    }

    private void updatePurchaseLabels(Customer c) {
        if (c == null) {
            msrpLabel.setText("Total regular price: $0.00");
            saleLabel.setText("Total sale price: $0.00");
            savingsLabel.setText("Total savings: $0.00");
            return;
        }

        double totalRegular = c.getPurchases().stream()
                .mapToDouble(Product::getRegularPrice)
                .sum();

        double totalSale = c.getPurchases().stream()
                .mapToDouble(Product::getSalePrice)
                .sum();

        double totalSavings = Math.max(0.0, totalRegular - totalSale);

        msrpLabel.setText(String.format("Total regular price: $%.2f", totalRegular));
        saleLabel.setText(String.format("Total sale price: $%.2f", totalSale));
        savingsLabel.setText(String.format("Total savings: $%.2f", totalSavings));
    }

    private void showProductImage(Product p) {
        if (p == null) { clearImage(); return; }

        String url = p.getImage();

        if (url == null || url.isBlank()) { clearImage(); return; }

        try {
            javafx.scene.image.Image img = new javafx.scene.image.Image(url, true);
            imageView.setImage(img);
            imageView.setVisible(true);
        } catch (Exception e) {
            clearImage();
        }
    }

    private void clearImage() {
        imageView.setImage(null);
        imageView.setVisible(false);
    }

}
