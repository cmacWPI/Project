package library.group5project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import library.group5project.util.DBOperator;

import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class BookPage extends Activity implements OnClickListener
{
    // Attributes
    EditText Title, ISBN, Publish, Author, Price;
    Button backSearchBtn;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_page);

        backSearchBtn = (Button)this.findViewById(R.id.backSearch_btn);
        backSearchBtn.setOnClickListener(this);

        Title = (EditText)this.findViewById(R.id.titleText);
        ISBN = (EditText)this.findViewById(R.id.ISBNText);
        Publish = (EditText)this.findViewById(R.id.yearText);
        Author = (EditText)this.findViewById(R.id.authorText);
        Price = (EditText)this.findViewById(R.id.priceText);

        // Pulls book data from database based on textview clicked from previous activity.
        Intent bookData = getIntent();
        String dataSQL = "SELECT * FROM Book WHERE Book_Title = '" + bookData.getStringExtra("book") + "'";
        Cursor cursor = DBOperator.getInstance().execQuery(dataSQL);
        if (cursor.moveToFirst())
        {
            Title.setText(cursor.getString(cursor.getColumnIndex("Book_Title")));
            ISBN.setText(cursor.getString(cursor.getColumnIndex("Book_ISBN")));
            Publish.setText(cursor.getString(cursor.getColumnIndex("Book_Year")));
            Price.setText("$" + cursor.getString(cursor.getColumnIndex("Book_Price")));
        }
        String authorData = "SELECT * FROM AUTHOR INNER JOIN Book ON Book.Author_ID = Author.Author_ID WHERE Book_Title =  '" + bookData.getStringExtra("book") + "'";
        cursor = DBOperator.getInstance().execQuery(authorData);
        if (cursor.moveToFirst())
        {
            Author.setText(cursor.getString(cursor.getColumnIndex("Author_FName")) + " " + cursor.getString(cursor.getColumnIndex("Author_LName")));
        }
    }
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.backSearch_btn)
        {
            Intent userData = getIntent();
            Intent back = new Intent(this, BookSearch.class);
            back.putExtra("customer", userData.getStringExtra("user"));
            this.startActivity(back);
        }
    }
}
