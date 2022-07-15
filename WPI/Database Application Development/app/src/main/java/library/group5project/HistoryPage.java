package library.group5project;

import library.group5project.util.DBOperator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryPage extends Activity implements OnClickListener
{
    // Attributes
    Button mainBtn;
    LinearLayout historyView;
    String bookData = "";
    String priceData = "";
    String saleData = "";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);
        mainBtn = (Button)this.findViewById(R.id.goBack_btn);
        mainBtn.setOnClickListener(this);

        historyView = (LinearLayout)this.findViewById(R.id.orderView);

        // Get user's email to be used in SQL and get their order history data on startup of this activity
        Intent historyData = getIntent();
        String orderHistory = "SELECT * FROM Customer INNER JOIN Invoice ON Customer.Cust_ID = Invoice.Cust_ID INNER JOIN Sale ON Invoice.Invoice_ID = Sale.Invoice_ID INNER JOIN Book ON Sale.Book_ID = Book.Book_ID WHERE Cust_Email = '" + historyData.getStringExtra("customer") + "'";
        Cursor cursor = DBOperator.getInstance().execQuery(orderHistory);
        while (cursor.moveToNext())
        {
            bookData = cursor.getString(cursor.getColumnIndex("Book_Title"));
            priceData = cursor.getString(cursor.getColumnIndex("Book_Price"));
            saleData = cursor.getString(cursor.getColumnIndex("Invoice_Date"));
            TextView data = new TextView(this);
            data.setTextSize(12);
            data.setPadding(15, 2, 15, 0);
            data.setText("Book Title: " + bookData + "\t\t\tPrice: $" + priceData + "\t\t\tDate Bought: " + saleData);
            data.setTextColor(Color.WHITE);
            historyView.addView(data);
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
}