package cashier;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierUI extends Frame {
    private TextField productCodeField, quantityField, cashierNameField;
    private TextArea invoiceArea;
    private InvoiceManager invoiceManager;
    private Label shiftStatusLabel;

    private String cashierName = "";
    private String shiftStartTime = "";
    private String shiftEndTime = "";

    public CashierUI(InvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;

        setTitle("Cashier Application");
        setSize(800, 600);
        setLayout(new GridLayout(4, 1));

        // Panel 1: Cashier Login Panel
        Panel loginPanel = new Panel(new GridLayout(2, 2));
        loginPanel.add(new Label("Cashier Name:"));
        cashierNameField = new TextField();
        loginPanel.add(cashierNameField);

        Button startShiftButton = new Button("Start Shift");
        Button endShiftButton = new Button("End Shift");
        loginPanel.add(startShiftButton);
        loginPanel.add(endShiftButton);
        add(loginPanel);

        // Panel 2: Product Input Panel
        Panel inputPanel = new Panel(new GridLayout(2, 2));
        inputPanel.add(new Label("Product Code:"));
        productCodeField = new TextField();
        inputPanel.add(productCodeField);

        inputPanel.add(new Label("Quantity:"));
        quantityField = new TextField();
        inputPanel.add(quantityField);

        Button addItemButton = new Button("Add Item");
        Button removeItemButton = new Button("Remove Item");
        inputPanel.add(addItemButton);
        inputPanel.add(removeItemButton);
        add(inputPanel);

        // Panel 3: Invoice Display Panel
        Panel invoicePanel = new Panel(new BorderLayout());
        invoiceArea = new TextArea();
        invoiceArea.setEditable(false);
        invoicePanel.add(invoiceArea, BorderLayout.CENTER);
        add(invoicePanel);

        // Panel 4: Receipt and Shift Status Panel
        Panel actionPanel = new Panel(new GridLayout(2, 1));
        Button printReceiptButton = new Button("Print Receipt");
        actionPanel.add(printReceiptButton);

        shiftStatusLabel = new Label("Shift Status: Not Started");
        actionPanel.add(shiftStatusLabel);
        add(actionPanel);

        // Button Actions
        startShiftButton.addActionListener(e -> startShift());
        endShiftButton.addActionListener(e -> endShift());
        addItemButton.addActionListener(e -> addItem());
        removeItemButton.addActionListener(e -> removeItem());
        printReceiptButton.addActionListener(e -> printReceipt());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void startShift() {
        cashierName = cashierNameField.getText().trim();
        if (cashierName.isEmpty()) {
            shiftStatusLabel.setText("Error: Cashier name is required to start shift!");
            return;
        }

        // Record start time
        shiftStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        shiftStatusLabel.setText("Shift Started by: " + cashierName + " at " + shiftStartTime);
        invoiceArea.setText("Welcome, " + cashierName + "!\nShift started at: " + shiftStartTime);
    }

    private void endShift() {
        if (cashierName.isEmpty()) {
            shiftStatusLabel.setText("Error: No active shift to end!");
            return;
        }

        // Record end time
        shiftEndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        shiftStatusLabel.setText("Shift Ended by: " + cashierName + " at " + shiftEndTime);
        invoiceArea.setText("Thank you, " + cashierName + "!\nShift ended at: " + shiftEndTime);

        // Reset cashier name and times
        cashierName = "";
        shiftStartTime = "";
        shiftEndTime = "";
    }

    private void addItem() {
        if (cashierName.isEmpty()) {
            invoiceArea.setText("Error: Start your shift before adding items.");
            return;
        }

        String productCode = productCodeField.getText().trim();
        String quantityText = quantityField.getText().trim();

        if (productCode.isEmpty() || quantityText.isEmpty()) {
            invoiceArea.setText("Error: Product code and quantity must not be empty.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            String result = invoiceManager.addItem(productCode, quantity);
            invoiceArea.setText(result);
        } catch (NumberFormatException e) {
            invoiceArea.setText("Error: Quantity must be a valid number.");
        }
    }

    private void removeItem() {
        if (cashierName.isEmpty()) {
            invoiceArea.setText("Error: Start your shift before removing items.");
            return;
        }

        String productCode = productCodeField.getText().trim();

        if (productCode.isEmpty()) {
            invoiceArea.setText("Error: Product code must not be empty.");
            return;
        }

        String result = invoiceManager.removeItem(productCode);
        invoiceArea.setText(result);
    }

    private void printReceipt() {
        if (cashierName.isEmpty()) {
            invoiceArea.setText("Error: Start your shift before printing a receipt.");
            return;
        }

        String receipt = invoiceManager.generateReceipt();
        invoiceArea.setText(receipt);
    }
}
