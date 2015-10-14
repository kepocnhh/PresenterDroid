package stan.db.contract;

public class VisibleRoles
        extends Contract
{
    public static final String ROLE_WHOM = "role_whom";
    public static final String ROLE_ID = "role_id";

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
        return createTable(ROLE_ID, TEXT_TYPE, ROLE_WHOM, TEXT_TYPE);
    }
}