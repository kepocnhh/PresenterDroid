package stan.presenter.people;

import java.util.List;

import stan.presenter.core.Mafia;

public class Clan
        extends Mafia
{
    List<Human> people;
    //
    ClanStatistics clanStatistics;

    public Clan(String n)
    {
        super(n);
    }
}