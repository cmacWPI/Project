package library.group5project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainPage extends Activity implements OnClickListener
{
    Button logoutBtn;

    // Menu for user
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        logoutBtn = (Button)this.findViewById(R.id.logout_Btn);
        logoutBtn.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        int id = v.getId();

        // Gets username from login page to be used for checkout and history pages.
        Intent userData = getIntent();

        // Menu for user to click on
        if (id == R.id.search_btn)
        {
            Intent search = new Intent(this, BookSearch.class);
            search.putExtra("customer", userData.getStringExtra("user"));
            this.startActivity(search);
        }
        else if(id == R.id.history_btn)
        {
            Intent history = new Intent(this, HistoryPage.class);
            history.putExtra("customer", userData.getStringExtra("user"));
            this.startActivity(history);
        }
        else if(id == R.id.checkout_btn)
        {
            Intent checkout = new Intent(this, Checkout.class);
            checkout.putExtra("customer", userData.getStringExtra("user"));
            this.startActivity(checkout);
        }
        else if(id == R.id.logout_Btn)
        {
            Intent logout = new Intent(this, MainActivity.class);
            this.startActivity(logout);
        }
    }
}
