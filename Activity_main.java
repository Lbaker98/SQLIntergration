{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package w8a39DB.project;\
\
import android.app.Activity;\
import android.content.Intent;\
import android.database.Cursor;\
import android.database.sqlite.SQLiteDatabase;\
import android.database.sqlite.SQLiteOpenHelper;\
import android.os.Bundle;\
import android.util.Log;\
import android.view.View;\
import android.widget.AdapterView;\
import android.widget.ListView;\
import android.widget.SimpleCursorAdapter;\
\
public class Activity_main extends Activity \{\
\
    private SQLiteDatabase db;\
    private Cursor cursor;\
    private SimpleCursorAdapter listAdapter;\
\
    @Override\
    protected void onCreate(Bundle savedInstanceState) \{\
\
        super.onCreate(savedInstanceState);\
        setContentView(R.layout.layout_main);\
\
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getApplicationContext());\
        SQLiteDatabase db = databaseHelper.getReadableDatabase();\
\
        ListView listView = (ListView) findViewById(R.id.listView_characters);\
\
        cursor = db.query("RECORD", new String[]\{"_id", "NAME", "IMAGE", "DESCRIPTION"\}, null, null, null, null, null);\
\
        listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] \{"NAME"\}, new int[] \{android.R.id.text1\}, 0);\
\
        listView.setAdapter(listAdapter);\
\
        Log.i("Activity_main", "Cursor size=" + Integer.toString(cursor.getCount()));\
        Log.i("Activity_main", "Database records:" + DatabaseHelper.getDatabaseContentsAsString(db));\
\
        //Create the listener\
        AdapterView.OnItemClickListener itemClickListener =\
                new AdapterView.OnItemClickListener()\{\
                    @Override\
                    public void onItemClick(AdapterView<?> listView,\
                                            View itemView,\
                                            int position,\
                                            long id) \{\
                        //Pass the option the user clicks on to character activity.\
                        Intent intent = new Intent(Activity_main.this, Activity_character.class);\
                        intent.putExtra(Activity_character.EXTRA_CHARACTER_ID, (int) id);\
                        startActivity(intent);\
                    \}\
                \};\
\
        //Assign the listener to the list view\
        listView.setOnItemClickListener(itemClickListener);\
\
    \}\
\
    @Override\
    protected void onDestroy() \{\
        super.onDestroy();\
\
        if(cursor != null) cursor.close();\
        if(db != null) db.close();\
    \}\
\}}