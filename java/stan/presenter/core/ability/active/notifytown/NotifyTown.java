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

    public boolean engage(Player p)
    {
        return true;
    }
}
