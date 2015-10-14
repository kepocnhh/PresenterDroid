package stan.db.contract;

public class Role
        extends Contract
{
    public static final String SIDE = "side";
    public static final String TYPEGROUP = "typegroup";
    public static final String TEAM = "team";

    @Override
    protected String setTableName()
    {
        return Contract.TABLE_NAME_ROLE;
    }

    @Override
    public String createTable()
    {
        return createTable(SIDE, INTEGER_TYPE, TYPEGROUP, TEXT_TYPE, TEAM, TEXT_TYPE);
    }
}