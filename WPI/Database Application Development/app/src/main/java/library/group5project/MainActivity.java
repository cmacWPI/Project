package library.group5project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

import library.group5project.util.DBOperator;
import library.group5project.util.LoginDB;

public class MainActivity extends Activity implements OnClickListener
{
    // Attributes
    Button loginBtn;
    EditText ID, PW;

    // First Activity starts upon startup
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        loginBtn = (Button)this.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        ID = (EditText)this.findViewById(R.id.idText);
        PW = (EditText)this.findViewById(R.id.pwText);
    }

    public void onClick(View v)
    {
        int id = v.getId();
        String loginCheck = "";
        String passwordCheck = "";
        if (id == R.id.login_btn)
        {
            // Login Check
            try
            {
                LoginDB.copyDB(getBaseContext());
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            String usernameData = "SELECT username FROM Login WHERE username = '" + ID.getText() + "'";
            Cursor cursor = LoginDB.getInstance().execQuery(usernameData);
            if (cursor.moveToFirst())
            {
                loginCheck = cursor.getString(cursor.getColumnIndex("username"));
            }

            // If username is not found in record, display error message, else proceed to another checkpoint
            if(loginCheck != "")
            {
                String loginQuery = "SELECT password FROM Login WHERE username = '" + ID.getText() + "'";
                cursor = LoginDB.getInstance().execQuery(loginQuery);
                if (cursor.moveToFirst())
                {
                    passwordCheck = cursor.getString(cursor.getColumnIndex("password"));
                }

                if(passwordCheck.equals(PW.getText().toString()))
                {
                    // Close the database before moving to main page, but copy main book database
                    cursor.close();
                    try
                    {
                        DBOperator.copyDB(getBaseContext());
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    Intent main = new Intent(this, MainPage.class);
                    main.putExtra("user", loginCheck);
                    this.startActivity(main);
                }
                else
                {
                    cursor.close();
                    dlgAlert.setTitle("Error Logging in");
                    dlgAlert.setMessage("Wrong password");
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
            else
            {
                cursor.close();
                dlgAlert.setTitle("Error Logging in");
                dlgAlert.setMessage("Wrong Username or Password.");
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
    }
}
