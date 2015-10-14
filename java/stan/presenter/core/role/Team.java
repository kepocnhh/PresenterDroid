package stan.presenter.core.role;

import stan.presenter.core.Mafia;

public class Team
    extends Mafia
{
    String description;
    public static String GOOD_ID = "0";
    public static String BAD_ID = "1";
    public static String NEUTRAL_ID = "2";

    public Team(String n, String d)
    {
        super(n);
        this.description = d;
    }

    public static class Good
            extends Team
    {
        public Good(String n, String d)
        {
            super(n, d);
            UID = GOOD_ID;
        }
    }
    public static class Bad
            extends Team
    {
        public Bad(String n, String d)
        {
            super(n, d);
            UID = BAD_ID;
        }
    }
    public static class Neutral
            extends Team
    {
        public Neutral(String n, String d)
        {
            super(n, d);
            UID = NEUTRAL_ID;
        }
    }
}