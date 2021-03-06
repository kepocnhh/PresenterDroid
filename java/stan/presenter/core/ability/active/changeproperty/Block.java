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
    public int[] getMap()
    {
        int[] map = new int[3];
        map[0] = TypeAbility.Active.ordinal();
        map[1] = TypeActive.ChangeProperty.ordinal();
        map[2] = TypeChangeProperty.Block.ordinal();
        return map;
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
