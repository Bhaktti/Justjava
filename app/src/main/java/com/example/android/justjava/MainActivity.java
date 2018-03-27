/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        createOrderSummary();

        }

    /**
     * Calculates the price of the order.
     *
     * @param
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;

    }

    public void createOrderSummary() {
        //int price = calculatePrice();
        CheckBox whippedcream = findViewById(R.id.Whipped_Cream);
        boolean hasWhippedCream = whippedcream.isChecked();

        CheckBox choclateToppings = findViewById(R.id.choclate_Toppings);
        boolean hasChoclateToppings = choclateToppings.isChecked();
         int price= 0;
        if (hasWhippedCream){
             price = quantity*(6);
        }

         if (hasChoclateToppings){

             price = quantity*(7);
        }

        if(hasChoclateToppings && hasWhippedCream ){
            price=quantity* (8) ;
        }

        else {
             price = calculatePrice();
        }

        TextView Edittextview = (TextView)findViewById(R.id.edit_text_view);
        String userName = Edittextview.getText().toString();
        String priceMessage = " Name :  " + userName + "\n Add whipped cream " + hasWhippedCream + "\n Want to add choclate "+ hasChoclateToppings+"\n quantity " + quantity + " \n Total: $   " + price +  getString(R.string.Thank_You) ;
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL, "vineetzunjarwad@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your coffee order sent with love from Bhaktu");
        intent.putExtra(Intent.EXTRA_TEXT,"I am Email Body "+ priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given increment button is clicked on the screen.
     */
    public void increment(View view) {
   if (quantity==100){
       Toast.makeText(this , "you cannot have 100 cups of coffee!!!" ,Toast.LENGTH_LONG).show();
          return;
       }
        quantity = quantity +1;
        display(quantity);
    }

    /**
     * This method displays the given decrement button is clicked on the screen.
     */
    public void decrement(View view) {

        if(quantity==1){
            Toast.makeText(this,"you cannot have less tha one cup of coffee" , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity-1;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}
