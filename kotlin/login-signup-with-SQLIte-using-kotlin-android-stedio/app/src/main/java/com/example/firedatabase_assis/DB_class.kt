import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import android.util.Log

class DB_class(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "appDatabase"
        private const val TABLE_CONTACTS = "user"
        private const val KEY_NAME = "name"
        private const val KEY_UNAME = "username"
        private const val KEY_PSWD = "pswd"
        private const val KEY_AGE = "age"
        private const val KEY_IMAGE_DATA = "image_data"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val newtb = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_NAME + " TEXT," + KEY_UNAME + " TEXT,"
                + KEY_PSWD + " TEXT," + KEY_AGE + " TEXT,"
                + KEY_IMAGE_DATA + " BLOB" + ")")
        db?.execSQL(newtb)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    fun insertuser(data: ContentValues): Long {
        val db = writableDatabase
        val rs: Long = db.insert(TABLE_CONTACTS,null,data)
        Log.d("DB_class","Inserting data: $data")
        Log.d("DB_class","Insertion result: $rs")
        return rs
    }
}
