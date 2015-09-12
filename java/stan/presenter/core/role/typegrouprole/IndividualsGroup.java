package stan.presenter.core.role.typegrouprole;

public abstract class IndividualsGroup
    extends TypeGroup
{
    static public final String INDIVIDUALS_TAG = "individuals";
    static public final String SECT_TAG = "sect";

    public IndividualsGroup(String n, String d, String t, boolean vig)
    {
        super(n, d, t, vig);
    }

    public static class Individuals
            extends IndividualsGroup
    {
        public Individuals(String n, String d)
        {
            super(n,d,INDIVIDUALS_TAG, false);
        }
    }
}