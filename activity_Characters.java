{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package w8a39DB.project;\
\
import android.app.Activity;\
import android.database.Cursor;\
import android.database.sqlite.SQLiteDatabase;\
import android.database.sqlite.SQLiteOpenHelper;\
import android.os.Bundle;\
import android.util.Log;\
import android.widget.ImageView;\
import android.widget.TextView;\
\
public class Activity_character extends Activity \{\
\
    public static final String EXTRA_CHARACTER_ID = "character_id";\
\
    @Override\
    protected void onCreate(Bundle savedInstanceState) \{\
        super.onCreate(savedInstanceState);\
        setContentView(R.layout.layout_character);\
\
        //Get the character id from the intent\
        int id = (Integer)getIntent().getExtras().get(EXTRA_CHARACTER_ID);\
\
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getApplicationContext());\
        SQLiteDatabase db = databaseHelper.getReadableDatabase();\
\
        Cursor cursor = db.query("RECORD",\
                new String[]\{"_id", "NAME", "IMAGE", "DESCRIPTION"\},\
                "_id = ?", new String[] \{Integer.toString(id)\}, null, null, null);\
\
        String name = null;\
        int image = 0;\
        String description = null;\
\
        if(cursor.getCount() != 0) \{\
            cursor.moveToFirst();\
            name = cursor.getString(1);\
            image = cursor.getInt(2);\
            description = cursor.getString(3);\
        \} else \{\
            Log.e("getRecord", "Error retrieving record " + id + " from database.");\
        \}\
\
        if(cursor != null) cursor.close();\
\
        // Initialise the layout components\
\
        TextView textView_name = (TextView)findViewById(R.id.textView_name);\
        textView_name.setText(name);\
\
        TextView textView_description = (TextView)findViewById(R.id.textView_description);\
        textView_description.setText(description);\
\
        ImageView imageView = (ImageView)findViewById(R.id.imageView);\
        imageView.setImageResource(image);\
        imageView.setContentDescription(name);\
    \}\
\}\
}