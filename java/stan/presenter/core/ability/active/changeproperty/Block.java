package stan.presenter.core.ability.active.changeproperty;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class Block
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
