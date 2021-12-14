{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package w8a39DB.project;\
\
import android.content.ContentValues;\
import android.content.Context;\
import android.database.Cursor;\
import android.database.sqlite.SQLiteDatabase;\
import android.database.sqlite.SQLiteOpenHelper;\
import android.util.Log;\
\
public class DatabaseHelper extends SQLiteOpenHelper \{\
\
    private static final String DB_NAME = "peanuts";\
\
    //private static final int DB_VERSION = 1;\
    private static final int DB_VERSION = 2;\
\
    public DatabaseHelper(Context context) \{\
        super(context, DB_NAME, null, DB_VERSION);\
    \}\
\
    @Override\
    public void onCreate(SQLiteDatabase db) \{\
        db.execSQL("CREATE TABLE RECORD ("\
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "\
                + "NAME TEXT, "\
                + "IMAGE INTEGER, "\
                + "DESCRIPTION TEXT);"\
        );\
\
\
        insertRecord(db, "Charlie Brown",\
                R.drawable.charliebrown,\
                "Good ol\'92 Charlie Brown is the lovable loser in the zig-zag t-shirt\'97the kid who never gives up (even though he almost never wins). He manages the world\'92s worst baseball team\'85yet shows up for every game. He can\'92t muster the courage to talk to the Little Red-Haired girl\'85yet keeps hoping. Even though he gets grief from his friends, his kite-eating tree, even his own dog, Charlie Brown remains the stalwart hero.");\
\
        insertRecord(db, "Snoopy",\
                R.drawable.snoopy,\
                "The wildly imaginative, supremely confident, world-famous beagle is a canine master of disguise. As Joe Cool, he\'92s aloof, unflappable, above the fray, the hip dog we\'92d all like to be. As the World War I Flying Ace, he engages in aerial combat with the notorious Red Baron. While pondering life from the top of his doghouse, he writes the great American novel, travels to the moon, and plots revenge on the cat next door.");\
\
        insertRecord(db, "Woodstock",\
                R.drawable.woodstock,\
                "The fluttering, sometimes sputtering, little yellow bird is Snoopy\'92s sidekick, whether it\'92s as faithful mechanic to the World War I Flying Ace, stalwart secretary to the Head Beagle, or root beer drinking buddy. Chirping in a language only Snoopy understands, Woodstock and his feathered friends are never far from Snoopy\'92s doghouse.");\
\
        insertRecord(db, "Linus",\
                R.drawable.linus,\
                "The benevolent, blanket-clutching philosopher always has a kind word for everybody\'85even his bossy older sister, Lucy. While he\'92s often the voice of reason in the neighborhood, Linus also believes firmly in the Great Pumpkin, and he suffers more than most when people (or pumpkins) let him down.");\
\
        insertRecord(db, "Sally",\
                R.drawable.sally,\
                "Charlie Brown\'92s little sister believes the world owes her an explanation. Why does she have to go to school? Why doesn\'92t Linus, her Sweet Babboo, love her? And what\'92s the capital of Venezuela? Sally is always on the hunt for answers\'97and when she doesn\'92t get them, she comes up with a whole new philosophy: \\"Who cares?\\"");\
\
        insertRecord(db, "Franklin",\
                R.drawable.franklin,\
                "Charlie Brown\'92s quiet friend and confidant, Franklin might be the only one who never has an unkind word about our hapless hero. At school, Franklin sits one seat ahead of Peppermint Patty, which makes his school days that much more unbearable.");\
\
        insertRecord(db, "Marcie",\
                R.drawable.marcie,\
                "Peppermint Patty\'92s best friend, loyal follower, and complete opposite, Marcie is the smart one of the duo\'97even if she doesn\'92t know the difference between baseball and hockey. She\'92s horrible at sports, but terrific at friendship, especially with Charlie Brown (whom she calls \\"Charles\\") and Peppermint Patty (whom she calls \\"sir\\").");\
\
        insertRecord(db, "Peppermint Patty",\
                R.drawable.peppermintpatty,\
                "A fearless born leader and a natural athlete, Peppermint Patty is up to any challenge\'85except studying. She never met a school day she didn\'92t hate. But this tough girl has a soft side, too: She\'92s hopelessly in love with her pal Charlie \\"Chuck\\" Brown\'97who has no idea. For Peppermint Patty, sports are easy; it\'92s life that\'92s hard.");\
\
        insertRecord(db, "Pigpen",\
                R.drawable.pigpen,\
                "Happily traveling in his own private dust storm, Pigpen is completely comfortable in his own (dust-streaked) skin. Despite his outward appearance, he always carries himself with dignity, knowing full well that he has affixed to him the \\"dust of countless ages.\\"");\
\
        insertRecord(db, "Schroeder",\
                R.drawable.schroeder,\
                "This mini musical genius is rarely separated from his toy piano or his idol, Beethoven\'97except when he\'92s calling a game as the reliable catcher on Charlie Brown\'92s baseball team. The rest of his time is spent fending off unwanted advances from the bane of his existence: Lucy.");\
\
    \}\
\
    @Override\
    public void onUpgrade(SQLiteDatabase db, int i, int i1) \{\
        if((i == 1) && (i1 == 2)) \{\
\
            insertRecord(db, "Lucy",\
                    R.drawable.lucy,\
                    "Known around the neighborhood (and by her little brother, Linus) for being crabby and bossy, Lucy can often be found dispensing advice from her 5-cent psychiatrist\'92s booth, yanking away Linus\'92 security blanket, or humiliating Charlie Brown. Lucy\'92s only weakness? Her unrequited love for the piano-playing Schroeder.");\
\
        \}\
\
        Log.i(">>> DatabaseHelper", "Database upgraded");\
    \}\
\
    @Override\
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) \{\
        if((i == 2) && (i1 == 1)) \{\
\
            sqLiteDatabase.delete("RECORD", "NAME=?", new String[] \{"Lucy"\});\
\
        \}\
\
        Log.i(">>> DatabaseHelper", "Database downgraded");\
    \}\
\
    public static long insertRecord(SQLiteDatabase db, String name, int image, String description) \{\
\
        ContentValues recordValues = new ContentValues();\
\
        recordValues.put("NAME", name);\
        recordValues.put("IMAGE", image);\
        recordValues.put("DESCRIPTION", description);\
\
        long newRecordID = db.insert("RECORD", null, recordValues);\
\
        return newRecordID;\
    \}\
\
    public static String getDatabaseContentsAsString(SQLiteDatabase db) \{\
\
        Cursor cursor = db.query("RECORD",\
                new String[]\{"_id", "NAME", "IMAGE", "DESCRIPTION"\},\
                null, null, null, null, "_id ASC");\
\
        String databaseAsString = System.getProperty("line.separator");\
\
        if(cursor.getCount() != 0) \{\
            cursor.moveToFirst();\
            while (!cursor.isAfterLast()) \{\
                for (int i=0; i < cursor.getColumnCount() - 1; i++) \{\
                    databaseAsString += cursor.getString(i) + "     ";\
                \}\
                databaseAsString += System.getProperty("line.separator");\
                cursor.moveToNext();\
            \}\
\
            if(cursor != null) cursor.close();\
        \}\
\
        return databaseAsString;\
    \}\
\
    public static void updateRecord(SQLiteDatabase db, Long id, String name, int image, String description) \{\
\
        ContentValues recordValues = new ContentValues();\
\
        recordValues.put("NAME", name);\
        recordValues.put("IMAGE", image);\
        recordValues.put("DESCRIPTION", description);\
\
        db.update("RECORD", recordValues, "_id = ?", new String[] \{Long.toString(id)\});\
    \}\
\
    public static void deleteRecord(SQLiteDatabase db, Long id) \{\
        db.delete("RECORD", "_id=?", new String[] \{Long.toString(id)\});\
    \}\
\
    public static void deleteAllRecords(SQLiteDatabase db) \{\
        db.delete("RECORD", null, null);\
        db.delete("SQLITE_SEQUENCE","NAME = ?",new String[]\{"RECORD"\});  // To also reset the primary key. Comment out if not required.\
    \}\
\
\}\
}