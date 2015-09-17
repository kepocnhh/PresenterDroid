package stan.presenter.core.ability.active.notifytown;

import stan.presenter.core.ability.active.Active;
import stan.presenter.core.player.Player;

public class NotifyTown
        extends Active
{
    public NotifyTown(String n)
    {
        super(n);
    }

    @Override
    public int[] getMap()
    {
        return new int[0];
    }

    @Override
    protected TypeActive setTypeActive()
    {
        return null;
    }

    public boolean engage(Player p)
    {
        return true;
    }
}
