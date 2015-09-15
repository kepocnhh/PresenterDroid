package stan.presenter.core.ability.active.changeproperty;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class Block
        extends ChangeProperty
{

    public Block(String n)
    {
        super(n);
    }

    @Override
    protected TypeChangeProperty setTypeChangeProperty()
    {
        return TypeChangeProperty.Block;
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.stop = true;
        return p;
    }
}
