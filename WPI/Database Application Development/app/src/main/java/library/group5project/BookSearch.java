package library.group5project;

import library.group5project.util.DBOperator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.TextUtils;

public class BookSearch extends Activity implements OnClickListener
{
    // Attributes
    Button resultBtn, mainBtn;
    SearchView bookSearch;
    String bookData = "";
    LinearLayout bookView;
    Spinner searchType;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        resultBtn = (Button)this.findViewById(R.id.result_btn);
        resultBtn.setOnClickListener(this);
        mainBtn = (Button)this.findViewById(R.id.goBack_btn);
        mainBtn.setOnClickListener(this);

        searchType = (Spinner) this.findViewById(R.id.searchList);
        bookSearch = (SearchView)this.findViewById(R.id.bookSearchView);
        bookView = (LinearLayout)this.findViewById(R.id.bookSearch);
    }

    public void onClick(View v)
    {
        int id = v.getId();

        // If Textview is clicked, go to next page
        if(v.getClass().getName().equals("android.widget.TextView"))
        {
            Intent userData = getIntent();
            Intent bookInfo = new Intent(this, BookPage.class);
            bookInfo.putExtra("book", v.getTag().toString());
            bookInfo.putExtra("user", userData.getStringExtra("customer"));
            this.startActivity(bookInfo);
        }

        // Populates search results
        if (id == R.id.result_btn)
        {
            if(!TextUtils.isEmpty(bookSearch.getQuery()))
            {
                if(searchType.getSelectedItem().toString().equals("Title"))
                {
                    String searchSQL = "SELECT Book_Title FROM Book WHERE Book_Title LIKE '%" + bookSearch.getQuery() + "%'";
                    Cursor cursor = DBOperator.getInstance().execQuery(searchSQL);

                    // Check if LinearLayout is empty, then add book data to it. But if it has data inside it, remove all of it before adding new ones.
                    if(bookView.getChildCount() == 0)
                    {
                        while (cursor.moveToNext())
                        {
                            bookData = cursor.getString(cursor.getColumnIndex("Book_Title"));
                            TextView data = new TextView(this);
                            data.setTextSize(24);
                            data.setPadding(15, 2, 15, 0);
                            data.setText(bookData);
                            data.setTextColor(Color.WHITE);
                            data.setClickable(true);
                            data.setTag(bookData);
                            data.setOnClickListener(this);
                            bookView.addView(data);
                        }
                    }
                    else
                    {
                        bookView.removeAllViewsInLayout();
                        while (cursor.moveToNext())
                        {
                            bookData = cursor.getString(cursor.getColumnIndex("Book_Title"));
                            TextView data = new TextView(this);
                            data.setTextSize(24);
                            data.setPadding(15, 2, 15, 0);
                            data.setText(bookData);
                            data.setTextColor(Color.WHITE);
                            data.setClickable(true);
                            data.setTag(bookData);
                            data.setOnClickListener(this);
                            bookView.addView(data);
                        }
                    }
                }
                else
                {
                    String searchSQL = "SELECT Book_Title FROM Book INNER JOIN Author ON Book.Author_ID = Author.Author_ID WHERE Author_FName LIKE '%" + bookSearch.getQuery() + "%' OR Author_LName LIKE '%" + bookSearch.getQuery() + "%'";
                    Cursor cursor = DBOperator.getInstance().execQuery(searchSQL);

                    // Check if LinearLayout is empty, then add book data to it. But if it has data inside it, remove all of it before adding new ones.
                    if(bookView.getChildCount() == 0)
                    {
                        while (cursor.moveToNext())
                        {
                            bookData = cursor.getString(cursor.getColumnIndex("Book_Title"));
                            TextView data = new TextView(this);
                            data.setTextSize(24);
                            data.setPadding(15, 2, 15, 0);
                            data.setText(bookData);
                            data.setTextColor(Color.WHITE);
                            data.setClickable(true);
                            data.setTag(bookData);
                            data.setOnClickListener(this);
                            bookView.addView(data);
                        }
                    }
                    else
                    {
                        bookView.removeAllViewsInLayout();
                        while (cursor.moveToNext())
                        {
                            bookData = cursor.getString(cursor.getColumnIndex("Book_Title"));
                            TextView data = new TextView(this);
                            data.setTextSize(24);
                            data.setPadding(15, 2, 15, 0);
                            data.setText(bookData);
                            data.setTextColor(Color.WHITE);
                            data.setClickable(true);
                            data.setTag(bookData);
                            data.setOnClickListener(this);
                            bookView.addView(data);
                        }
                    }
                }
            }
            else
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setTitle("Error");
                dlgAlert.setMessage("Please enter your search query.");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });
            }
        }
        else if (id == R.id.goBack_btn)
        {
            Intent userData = getIntent();
            Intent back = new Intent(this, MainPage.class);
            back.putExtra("user", userData.getStringExtra("customer"));
            this.startActivity(back);
        }
    }
}
