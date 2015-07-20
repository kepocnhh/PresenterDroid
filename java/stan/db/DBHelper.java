package stan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

public class DBHelper
        extends SQLiteOpenHelper
{
    static public String NAME = "name";
    static public String UI = "ui";
    static public String DESCRIPTION = "description";
    static public String TABLEROLES = "roles";
    static public String ACTIONS = "actions";
    static public String TYPEVISIBILITY = "typevisibility";
    static public String TYPEROLE = "typerole";
    static public String RANGSHOT = "rangshot";
    static public String RANG = "rang";
    static public String TABLEACTIONS = "actions";
    static public String TRYSTOP = "trystop";
    static public String TO = "whom";
    static public String FROM = "who";
    static public String SELFIE = "selfie";

    static public String[] namesTYPES = {
            DBHelper.UI,
            DBHelper.NAME};
    static public String[] namesRoles = {
            DBHelper.UI,
            DBHelper.NAME,
            DBHelper.DESCRIPTION,
            DBHelper.ACTIONS,
            DBHelper.TYPEVISIBILITY,
            DBHelper.TYPEROLE,
            DBHelper.RANGSHOT,
            DBHelper.RANG};
    static public String[] namesActions = {
            DBHelper.UI,
            DBHelper.NAME,
            DBHelper.DESCRIPTION,
            DBHelper.TRYSTOP,
            DBHelper.TO,
            DBHelper.FROM,
            DBHelper.SELFIE};

    public DBHelper(Context context, String name)
    {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        db.execSQL("create table mytable ("
//                + "id integer primary key autoincrement,"
//                + "name text,"
//                + "email text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public void createTable(String name, String[] names, String[] values)
    {
        if(names.length!=values.length)
        {
            return;
        }
        String query = "create table if not exists " + name + " ("
                + "id integer primary key autoincrement,";
        for(int i = 0; i<names.length; i++)
        {
            query+= names[i] + " " + values[i];
            if(i != names.length-1)
            {
                query+=",";
            }
        }
        query+=");";
        getWritableDatabase().execSQL(query);
    }
    public void initTableTypes()
    {
        String[] valuesTypes = {
                "TEXT",
                "TEXT"};
        this.createTable(DBHelper.TYPEROLE, DBHelper.namesTYPES, valuesTypes);
    }
    public void initTableRoles()
    {
        String[] valuesRoles = {
                "TEXT",//UI
                "TEXT",//NAME
                "TEXT",//DESCRIPTION
                "TEXT",//ACTIONS
                "INTEGER",//TYPEVISIBILITY
                "INTEGER",//TYPEROLE
                "INTEGER",//RANGSHOT
                "INTEGER"};
        this.createTable(DBHelper.TABLEROLES, DBHelper.namesRoles, valuesRoles);
    }
    static public String[] getValuesRole(String ui,String name,String descr,String tv,String tr)
    {
        return getValuesRole(ui, name, descr, "", tv,tr);
    }
    static public String[] getValuesRole(String ui,String name,String descr,String actions,String tv,String tr)
    {
        return getValuesRole(ui, name, descr, actions, tv,tr, "0", "-1");
    }
    static public String[] getValuesRole(String ui,String name,String descr,String actions,String tv,String tr,String rs,String r)
    {
        String[] valuesForRole = {
                ui,//UI
                name,//NAME
                descr,//DESCRIPTION
                actions,//ACTIONS
                tv,//TYPEVISIBILITY
                tr,//TYPEROLE
                rs,//RANGSHOT
                r};
        return valuesForRole;
    }
    public void initTableActions()
    {
        String[] valuesActions = {
                "TEXT",//UI
                "TEXT",//NAME
                "TEXT",//DESCRIPTION
                "INTEGER",//TRY_STOP
                "INTEGER",//TO
                "INTEGER",//FROM
                "INTEGER",//SELFIE
        };
        this.createTable(DBHelper.TABLEACTIONS, DBHelper.namesActions, valuesActions);
    }
    static public String[] getValuesAction(String ui,String name, String descr)
    {
        return getValuesAction(ui, name, descr, "0", "-1", "-1", "0");
    }
    static public String[] getValuesAction(String ui,String name,String descr,String trystop,String to,String from,String selfie)
    {
        return new String[]{
                ui,//UI
                name,//NAME
                descr,//DESCRIPTION
                trystop,//TRY_STOP
                to,//TO
                from,//FROM
                selfie,//SELFIE
        };
    }
    public void insert(String nameTable, ContentValues cv)
    {
        getWritableDatabase().insert(nameTable, null, cv);
    }
    static public ContentValues setContentValues(String[] names, String[] values)
    {
        ContentValues cv = new ContentValues();
        for(int i=0; i<names.length; i++)
        {
            cv.put(names[i], values[i]);
        }
        return cv;
    }
}