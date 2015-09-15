package stan.presenter.core.role;

import stan.presenter.core.Mafia;

public class Team
    extends Mafia
{
    String description;

    public Team(String n, String d)
    {
        super(n);
        this.description = d;
    }
}