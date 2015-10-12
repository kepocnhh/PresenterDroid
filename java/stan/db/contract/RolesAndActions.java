package stan.db.contract;

public class RolesAndActions
        extends Contract
{
    public static final String ROLE_ID = "role_id";
    public static final String ACTION_ID = "action_id";
    public static final String SELFIE = "selfie";
    public static final String VISIBLES = "visibles";

    @Override
    protected String setTableName()
    {
        return Contract.TABLE_NAME_ROLES_ACTIONS;
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
        return createTable(
                ROLE_ID, TEXT_TYPE,
                ACTION_ID, TEXT_TYPE,
                SELFIE, INTEGER_TYPE,
                VISIBLES, INTEGER_TYPE
        );
    }
}