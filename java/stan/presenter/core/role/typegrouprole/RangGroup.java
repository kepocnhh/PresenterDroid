package stan.presenter.core.role.typegrouprole;

public abstract class RangGroup
    extends TypeGroup
{
    static public final String CLAN_TAG = "clan";
    static public final String SECT_TAG = "sect";
    //
    int rang;
    boolean rangShot;
    boolean visibleRang;

    public RangGroup(String n, String d, String t, boolean vig, boolean rs, boolean vr)
    {
        super(n, d, t, vig);
        this.rangShot = rs;
        this.visibleRang = vr;
    }

    public static class Clan
            extends RangGroup
    {
        public Clan(String n, String d)
        {
            super(n, d, CLAN_TAG, true, false, true);
        }
    }
    public static class Sect
            extends RangGroup
    {
        public Sect(String n, String d)
        {
            super(n, d, SECT_TAG, false, false, true);
        }
    }

    public void setRang(int r)
    {
        rang = r;
    }
    public void upRang()
    {
        rang--;
    }
}