package stan.presenter.core.ability.active;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class ChangeProperty
        extends Active
{
    public boolean try_stop = false;
    public static enum TypeActive
    {
        Kill,
        Block
    }
    private TypeActive typeActive;
    public void setTypeActive(TypeActive ta)
    {
        typeActive = ta;
    }
    public ChangeProperty(String n)
    {
        super(n);
    }
    public Player engage(Player p)
    {
        if(typeActive == TypeActive.Kill)
        {
            p.prop.life = false;
        }
        else if(typeActive == TypeActive.Block)
        {
            p.prop.stop = true;
        }
        return p;
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
                p.prop.life = false;
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
                p.prop.stop = true;
                return p;
            }
        }
}
