package stan.presenter.core.ability.active;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class NotifyTown
        extends Active
{
    public boolean try_stop = false;
    public NotifyTown(String n)
    {
        super(n);
    }
    public boolean engage(Player p)
    {
        return true;
    }
        public static class TryKill
                extends NotifyTown
        {
            public TryKill()
            {
                super(Resources.getSystem().getString(R.string.try_kill));
            }
            @Override
            public boolean engage(Player p)
            {
                return p.tries.life;
            }
        }
        public static class TryBlock
                extends NotifyTown
        {
            public TryBlock()
            {
                super(Resources.getSystem().getString(R.string.try_kill));
            }
            @Override
            public boolean engage(Player p)
            {
                return p.tries.stop;
            }
        }
}
