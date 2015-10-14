package stan.db.contract;

import android.net.Uri;

public abstract class Contract
{
    private static final String AUTHORITY = "delaemcode.mym1y.provider";
    private static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    private final String CONTENT_TYPE_PATH = "vnd.android.cursor.dir/" + AUTHORITY + ".";
    private final String CONTENT_ITEM_TYPE_PATH = "vnd.android.cursor.item/" + AUTHORITY + ".";

    public static final String TABLE_NAME_ACTION = "actionstable";
    public static final String TABLE_NAME_ROLE = "rolestable";
    public static final String TABLE_NAME_ROLES_ACTIONS = "rolesandactionstable";
    public static final String TABLE_NAME_VISIBLE_ROLES = "visiblerolestable";

    public static final Contract[] contracts = {
            new Action(),
            new Role(),
            new RolesAndActions(),
            new VisibleRoles()
    };

    public static Contract getContract(String tn)
    {
        for(int i = 0; i < Contract.contracts.length; i++)
        {
            if(Contract.contracts[i].TABLE_NAME.equals(tn))
            {
                return Contract.contracts[i];
            }
        }
        return null;
    }

    protected final String INTEGER_TYPE = "integer";
    protected final String TEXT_TYPE = "text";

    public final Uri CONTENT_URI;
    public final String CONTENT_TYPE;
    public final String CONTENT_ITEM_TYPE;

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";

    public final String TABLE_NAME;

    protected abstract String setTableName();

    public String getTableName()
    {
        return TABLE_NAME;
    }

    public Contract()
    {
        TABLE_NAME = setTableName();
        CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, TABLE_NAME);
        CONTENT_TYPE = CONTENT_TYPE_PATH + TABLE_NAME;
        CONTENT_ITEM_TYPE = CONTENT_ITEM_TYPE_PATH + TABLE_NAME;
    }

    protected String createTable(String... args)
    {
        String s = "create table " +
                TABLE_NAME + "(" +
                ID + " " + TEXT_TYPE + " PRIMARY KEY," +
                NAME + " " + TEXT_TYPE + "," +
                DESCRIPTION + " " + TEXT_TYPE;
        for(int i = 0; i < args.length; i += 2)
        {
            s += "," + args[i] + " " + args[i + 1];
        }
        s += ");";
        return s;
    }

    public abstract String createTable();

    public String dropTable()
    {
        return "drop table if exists " + TABLE_NAME;
    }

    public static String getKeyForSearch(String s)
    {
        return s+" = ?";
    }
}
