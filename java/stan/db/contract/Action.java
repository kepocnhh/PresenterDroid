package stan.db.contract;

public class Action
        extends Contract
{
    public static final String ABILITIES="abilities";

    @Override
    protected String setTableName()
    {
        return Contract.TABLE_NAME_ACTION;
    }

    @Override
    public String createTable()
    {
        return createTable(ABILITIES, TEXT_TYPE);
    }
}