package stan.presenter.core.ability;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class Active
        extends Ability
{
    public Active(String n)
    {
        super(n);
    }


    //________________________________________________ChangeProperty
        private static class ChangeProperty
                extends Active
        {
            public boolean try_stop = false;
            public ChangeProperty(String n)
            {
                super(n);
            }
            public Player engage(Player p)
            {
                return p;
            }
        }
            public static class Kill
                    extends ChangeProperty
            {
                public Kill()
                {
                    super(Resources.getSystem().getString(R.string.kill));
                }
                @Override
                public Player engage(Player p)
                {
                    p.life = false;
                    return p;
                }
            }
            public static class Block
                    extends ChangeProperty
            {
                public Block()
                {
                    super(Resources.getSystem().getString(R.string.block));
                    try_stop = true;
                }
                @Override
                public Player engage(Player p)
                {
                    p.stop = true;
                    return p;
                }
            }


    //________________________________________________NotifyTown
        private static class NotifyTown
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
        }
            public static class TryKill
                    extends NotifyTown
            {
                public TryKill()
                {
                    super(Resources.getSystem().getString(R.string.kill));
                }
                @Override
                public boolean engage(Player p)
                {
                    return p.tries.life;
                }
            }
}
