### Create SqlLiteHelper Class 
 
The SQLiteOpenHelper methods  `onCreate` and `onUpgrade` are implemented.  
and `CRUD functions` implemented to access SQLite table data to `read,` `insert,` `update,` and `delete`
 
Setup
-----

```kotlin
data class User(
    val ID:Int,
    val Name:String,
    val Password:String
)
class DbHelper(context: Context):SQLiteOpenHelper(context,DBNAME,null,VERSION) {
   companion object {
       private const val VERSION=1
       private const val DBNAME="mydb"
       private const val TNAME = "mytable"
       private const val ID = "id"
       private const val NAME = "username"
       private const val PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sql="CREATE TABLE $TNAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME TEXT,$PASSWORD TEXT)"
        db?.execSQL(sql)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TNAME")
        onCreate(db)
    }
    fun save(user: User) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(NAME, user.Name)
        cv.put(PASSWORD, user.Password)
        db.insert(TNAME, null, cv)
        db.close()
    }
    fun update(user: User) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(NAME, user.Name)
        cv.put(PASSWORD, user.Password)
        val where = "$ID=${user.ID}"
        db.update(TNAME, cv, where, null)
        db.close()
    }
    fun delete(id: Int) {
        val db = writableDatabase
        val where = "$ID=$id"
        db.delete(TNAME, where, null)
        db.close()
    }
    fun getUsers(): List<User> {
        val users = ArrayList<User>()
        val db = writableDatabase
        val columns = arrayOf(ID, NAME, PASSWORD)
        val cursor:Cursor = db.query(TNAME, columns, null, null, null, null, null)
        while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val username = cursor.getString(1)
                val password = cursor.getString(2)
                users.add(User(id, username, password))
             }
        cursor.close()
        return users
    }
}
```
