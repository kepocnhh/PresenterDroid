package stan.presenter.core.ability.active.changeproperty;

import stan.presenter.core.ability.Ability;
import stan.presenter.core.player.Player;

public class HealNight
        extends ChangeProperty
{
    public HealNight(String n)
    {
        super(n);
    }

    @Override
    public int[] getMap()
    {
        int[] map = new int[3];
        map[0] = TypeAbility.Active.ordinal();
        map[1] = TypeActive.ChangeProperty.ordinal();
        map[2] = TypeChangeProperty.HealNight.ordinal();
        return map;
    }

    @Override
    protected TypeChangeProperty setTypeChangeProperty()
    {
        return TypeChangeProperty.HealNight;
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.heal_night = true;
        return p;
    }
}