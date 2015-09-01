package stan.presenter.core.ability.active.notifytown;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class TryKill
        extends NotifyTown
{
    public TryKill()
    {
        super(Resources.getSystem().getString(R.string.try_kill));
    }

    @Override
    public boolean engage(Player p)
    {
        return p.tries.tryKill();
    }
}
