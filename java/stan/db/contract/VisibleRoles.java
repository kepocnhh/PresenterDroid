package stan.db.contract;

public class VisibleRoles
        extends ConnectTable
{
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_WHOM = "role_whom";

    public VisibleRoles()
    {
        super(ROLE_ID, ROLE_WHOM, Contract.TABLE_NAME_ROLE, Contract.TABLE_NAME_ROLE);
    }

    @Override
    protected String setTableName()
    {
        return Contract.TABLE_NAME_VISIBLE_ROLES;
    }

    @Override
    protected String createTable(String... args)
    {
        String s = "create table " +
                TABLE_NAME + "(" +
                ID + " " + TEXT_TYPE;
        for(int i = 0; i < args.length; i += 2)
        {
            s += "," + args[i] + " " + args[i + 1];
        }
        s += ");";
        return s;
    }

    @Override
    public String createTable()
    {
        return createTable(CONN_FROM, TEXT_TYPE, CONN_TO, TEXT_TYPE);
    }
}