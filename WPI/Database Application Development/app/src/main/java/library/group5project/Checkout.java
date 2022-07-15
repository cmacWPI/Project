package library.group5project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import library.group5project.util.DBOperator;


public class Checkout extends Activity implements OnClickListener, OnItemSelectedListener
{
    // Attributes
    Button mainBtn;
    Spinner orderNum;
    EditText orderStatus, titleStatus, priceStatus;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_page);
        mainBtn = (Button)this.findViewById(R.id.goBack_btn);
        mainBtn.setOnClickListener(this);
        orderNum = (Spinner) this.findViewById(R.id.orderNumData);
        orderStatus = (EditText) this.findViewById(R.id.orderStatus);
        titleStatus = (EditText) this.findViewById(R.id.titleStatus);
        priceStatus = (EditText) this.findViewById(R.id.priceStatus);
        orderNum.setOnItemSelectedListener(this);

        // Get user's email to be used in SQL and get their checkout data on startup of this activity
        Intent checkoutData = getIntent();
        String checkout = "SELECT * FROM Customer INNER JOIN Invoice ON Customer.Cust_ID = Invoice.Cust_ID INNER JOIN Sale ON Invoice.Invoice_ID = Sale.Invoice_ID WHERE Cust_Email = '" + checkoutData.getStringExtra("customer") + "'";

        // Populates list of user's order number fields for Spinner widget
        ArrayList<String> orderNumList = new ArrayList<>();
        Cursor cursor = DBOperator.getInstance().execQuery(checkout);
        while (cursor.moveToNext())
        {
            orderNumList.add(cursor.getString(cursor.getColumnIndex("Order_Num")));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_data, orderNumList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_data);
        orderNum.setAdapter(adapter);
        String checkoutStatus = "SELECT * FROM Customer INNER JOIN Invoice ON Customer.Cust_ID = Invoice.Cust_ID INNER JOIN Sale ON Invoice.Invoice_ID = Sale.Invoice_ID INNER JOIN Book ON Sale.Book_ID = Book.Book_ID WHERE Cust_Email = '" + checkoutData.getStringExtra("customer") + "' AND Order_Num = '" + orderNum.getSelectedItem().toString()+ "'";
        cursor = DBOperator.getInstance().execQuery(checkoutStatus);
        if(cursor.moveToFirst())
        {
            orderStatus.setText(cursor.getString(cursor.getColumnIndex("Order_Status")));
            titleStatus.setText(cursor.getString(cursor.getColumnIndex("Book_Title")));
            priceStatus.setText("$" + cursor.getString(cursor.getColumnIndex("Book_Price")));
        }
    }

    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.goBack_btn)
        {
            Intent userData = getIntent();
            Intent back = new Intent(this, MainPage.class);
            back.putExtra("user", userData.getStringExtra("customer"));
            this.startActivity(back);
        }
    }

    // When data is selected on Spinner widget, change the data to display based on user's selection for order number
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {
        Intent checkoutData = getIntent();
        String checkoutStatus = "SELECT * FROM Customer INNER JOIN Invoice ON Customer.Cust_ID = Invoice.Cust_ID INNER JOIN Sale ON Invoice.Invoice_ID = Sale.Invoice_ID INNER JOIN Book ON Sale.Book_ID = Book.Book_ID WHERE Cust_Email = '" + checkoutData.getStringExtra("customer") + "' AND Order_Num = '" + orderNum.getSelectedItem().toString()+ "'";
        Cursor cursor = DBOperator.getInstance().execQuery(checkoutStatus);
        if(cursor.moveToFirst())
        {
            orderStatus.setText(cursor.getString(cursor.getColumnIndex("Order_Status")));
            titleStatus.setText(cursor.getString(cursor.getColumnIndex("Book_Title")));
            priceStatus.setText("$" + cursor.getString(cursor.getColumnIndex("Book_Price")));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}