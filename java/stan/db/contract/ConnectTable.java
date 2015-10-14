package stan.db.contract;

public abstract class ConnectTable
        extends Contract
{
    public ConnectTable(String cf, String ct, String tf, String tt)
    {
        super();
        CONN_FROM = cf;
        CONN_TO = ct;
        TABLE_FROM = tf;
        TABLE_TO = tt;
    }

    public String CONN_FROM = "conn_from";
    public String CONN_TO = "conn_to";

    public String TABLE_FROM = "table_from";
    public String TABLE_TO = "table_to";
}