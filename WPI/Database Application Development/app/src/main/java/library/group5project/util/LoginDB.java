package library.group5project.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class to manipulate tables & data
 * Uses singleton pattern to create single instance for login
 */
public class LoginDB
{
    private static LoginDB instance = null;
    private SQLiteDatabase db;

    private LoginDB()
    {
        //path of database file
        String pathDB = "/data/data/library.group5project/databases";
        String fileDB = "WPI_User_Data.db";
        String path = pathDB + "/" + fileDB;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /*
     * Singleton Pattern
     * Why should we avoid multiple instances here?
     */
    public static LoginDB getInstance()
    {
        if (instance==null) instance = new LoginDB();
        return instance;
    }

    /**
     * Copy database file
     * From assets folder (in the project) to android folder (on device)
     */
    public static void copyDB(Context context) throws IOException,FileNotFoundException
    {
        String pathDB = "/data/data/library.group5project/databases";
        String fileDB = "WPI_User_Data.db";
        String path = pathDB + "/" + fileDB;
        File file = new File(path);
        if (!file.exists())
        {
            OpenDB dbhelper = new OpenDB(context, path ,1);
            dbhelper.getWritableDatabase();
            InputStream is = context.getAssets().open(fileDB);
            OutputStream os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0)
            {
                os.write(buffer, 0, length);
            }
            is.close();
            os.flush();
            os.close();
        }
    }

    /**
     * execute sql without returning data, such as alter
     * @param sql
     */
    public void execSQL(String sql) throws SQLException
    {
        db.execSQL(sql);
    }
    /**
     * execute sql such as update/delete/insert
     * @param sql
     * @param args
     * @throws SQLException
     */
    public void execSQL(String sql, Object[] args) throws SQLException
    {
        db.execSQL(sql, args);
    }

    /**
     * execute sql query
     * @param sql
     * @param selectionArgs
     * @return cursor
     * @throws SQLException
     */
    public Cursor execQuery(String sql,String[] selectionArgs) throws SQLException
    {
        return db.rawQuery(sql, selectionArgs);
    }

    /**
     * execute query without arguments
     * @param sql
     * @return
     * @throws SQLException
     */
    public Cursor execQuery(String sql) throws SQLException
    {
        return this.execQuery(sql, null);
    }

    /**
     * close database
     */
    public void closeDB()
    {
        if (db!=null) db.close();
    }
}
