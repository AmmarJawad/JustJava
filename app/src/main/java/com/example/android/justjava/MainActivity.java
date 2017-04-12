/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.justjava.R.id.chocolate_checkbox;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameInput = (EditText) findViewById(R.id.nameField);
        String name = nameInput.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, name));
    }

    /**
     * Create a summary of the order
     * @param price refers to calculatePrice method which is quantity * 5
     * @param addWhippedCream is whether or no the whipped cream checkbox has been checked
     * @param addChocolate is whether chocolate has been selected in the checkbox
     * @return order summary
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String username){
        String priceMessage = "Name: " + username;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return  priceMessage;
    }

    /**
     * Calculates the price of the order.
     * @param addChocolate whether or not the user prompted for chocolate.
     * @param addWhippedCream whether or not the user prompted for whipped cream.
     *
     * @return total price
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // price of a cup of coffee is initialised to $5
        int basePrice = 5;

        // if a user checks whipped cream checkbox then add + $1 to coffee price.
        if (addWhippedCream) {
            basePrice += 1;
        }

        // if a user checks chocolate checkbox then add + $2 to coffee price.
        if (addChocolate) {
            basePrice += 2;
        }

        // return the quantity multiplied by the unit price.
        return quantity * basePrice;
    }

    public void increment(View view) {
        if (quantity == 100) {
            return;
        }

        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }

        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int tal) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + tal);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}