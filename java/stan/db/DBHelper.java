package stan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import stan.db.contract.ConnectTable;
import stan.db.contract.Contract;
import stan.db.contract.Role;
import stan.db.contract.RolesAndActions;
import stan.db.contract.VisibleRoles;

public class DBHelper
        extends SQLiteOpenHelper
{
    private static final int VERSION = 1510141920;
    private static final String DB_NAME = "mafiadatabase";

    private static DBHelper instance;

    public static DBHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context)
    {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion > oldVersion)
        {
            dropTables(db);
            createTables(db);
        }
    }

    private void createTables(SQLiteDatabase db)
    {
        for(int i=0; i< Contract.contracts.length; i++)
        {
            db.execSQL(Contract.contracts[i].createTable());
        }
    }

    public void dropTables(SQLiteDatabase db)
    {
        for(int i=0; i< Contract.contracts.length; i++)
        {
            db.execSQL(Contract.contracts[i].dropTable());
        }
    }

    public Cursor query(Contract contract, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return getWritableDatabase().query(contract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }
    public Cursor query(Contract contract)
    {
        return getWritableDatabase().query(contract.TABLE_NAME, null, null, null, null, null, null);
    }
    public Cursor rawQuery(ConnectTable ct,String[] f_from,String[] f_mid,String[] f_to, String id)
    {
        String fields_to = "";
        String fields_mid = "";
        String fields_from = "";
        if(f_to!=null)
        {
            for(int i=0; i<f_to.length; i++)
            {
                fields_to+=" table_to." + f_to[i];
                if(i!=f_to.length-1)
                {
                    fields_to+=",";
                }
            }
        }
        if(f_mid!=null)
        {
            if(!fields_to.isEmpty())
            {
                fields_mid+=",";
            }
            for(int i=0; i<f_mid.length; i++)
            {
                fields_mid+=" table_mid." + f_mid[i];
                if(i!=f_mid.length-1)
                {
                    fields_mid+=",";
                }
            }
        }
        if(f_from!=null)
        {
            if(!fields_to.isEmpty() || !fields_mid.isEmpty())
            {
                fields_from+=",";
            }
            for(int i=0; i<f_from.length; i++)
            {
                fields_from+=" table_mid." + f_from[i];
                if(i!=f_from.length-1)
                {
                    fields_from+=",";
                }
            }
        }
        String query =  "SELECT" + fields_to + fields_mid + fields_from +
                " FROM " + ct.TABLE_FROM + " AS table_from" +
                " JOIN " + ct.TABLE_NAME + " AS table_mid" +
                " ON table_from." + Contract.ID + "=table_mid." + ct.CONN_FROM +
                " JOIN " + ct.TABLE_TO + " AS table_to" +
                " ON table_to." + Contract.ID + "=table_mid." + ct.CONN_TO +
                " WHERE table_from." + Contract.ID + "=?";
        String[] selectionArgs = new String[]{id};
        return getWritableDatabase().rawQuery(query, selectionArgs);
    }
    public Cursor rawQueryActionsForRole(String id_role)
    {
        String[] f_to = new String[]{Contract.NAME, Contract.DESCRIPTION, Contract.ID};
        String[] f_mid = new String[]{RolesAndActions.SELFIE, RolesAndActions.VISIBLES};
        return rawQuery(((RolesAndActions) Contract.getContract(Contract.TABLE_NAME_ROLES_ACTIONS)), null, f_mid, f_to, id_role);
    }
    public Cursor rawQueryVisibles(String id_role)
    {
        String[] f = new String[]{Contract.NAME, Contract.DESCRIPTION, Contract.ID};
        return rawQuery(((VisibleRoles) Contract.getContract(Contract.TABLE_NAME_VISIBLE_ROLES)), null, null, f, id_role);
    }
    public Cursor rawQueryVisiblesOld(String id_role)
    {
        String query =  "SELECT" +
                " table_to." + Contract.NAME + "," +
                " table_to." + Contract.DESCRIPTION + "," +
                " table_to." + Contract.ID +
                " FROM " + Contract.TABLE_NAME_ROLE + " AS table_from" +
                " JOIN " + Contract.TABLE_NAME_VISIBLE_ROLES + " AS table_mid" +
                " ON table_from." + Contract.ID + "=table_mid." + VisibleRoles.ROLE_ID +
                " JOIN " + Contract.TABLE_NAME_ROLE + " AS table_to" +
                " ON table_to." + Contract.ID + "=table_mid." + VisibleRoles.ROLE_WHOM +
                " WHERE table_from." + Contract.ID + "=?";
        String[] selectionArgs = new String[]{id_role};
        return getWritableDatabase().rawQuery(query, selectionArgs);
    }
    public Cursor rawQuery(Contract contract1, Contract contractMid, Contract contract2, String[] selectionArgs)
    {
        String query =  "SELECT *" +
                " FROM " + contractMid.TABLE_NAME + " as table_mid" +
                " JOIN " + contract1.TABLE_NAME + " as table_from" +
                " ON table_from." + Contract.ID + "=table_mid." + Contract.ID +
                " JOIN " + contract2.TABLE_NAME + " as table_to" +
                " ON table_to." + Contract.ID + "=table_mid." + Contract.ID;
        //
//        String query =  "SELECT DISTINCT " +
//                contract2.TABLE_NAME + "." + Contract.NAME + ", " +
//                contract2.TABLE_NAME + "." + Contract.ID +
//                " FROM " + contract1.TABLE_NAME + " INNER JOIN " +
//                contractMid.TABLE_NAME +
//                " ON " + contract1.TABLE_NAME + "." + Contract.ID + "=" + contractMid.TABLE_NAME + "." + Contract.ID +
//                " INNER JOIN " + contract2.TABLE_NAME + " ON " + contract2.TABLE_NAME + "." + Contract.ID + "=" + contractMid.TABLE_NAME + "." + Contract.ID +
//                " WHERE " + contract1.TABLE_NAME + "." + Contract.ID + "=?";
        return getWritableDatabase().rawQuery(query, selectionArgs);
    }

    public long insert(Contract contract, ContentValues values)
    {
        SQLiteDatabase db;
        db = getWritableDatabase();
        db.beginTransaction();
        long id = db.insert(contract.TABLE_NAME, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        return id;
    }
}