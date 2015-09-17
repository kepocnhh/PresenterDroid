package stan.presenter.core.ability.active.changeproperty;

import stan.presenter.core.player.Player;

public class HealDay
        extends ChangeProperty
{
    public HealDay(String n)
    {
        super(n);
    }

    @Override
    public int[] getMap()
    {
        int[] map = new int[3];
        map[0] = TypeAbility.Active.ordinal();
        map[1] = TypeActive.ChangeProperty.ordinal();
        map[2] = TypeChangeProperty.HealDay.ordinal();
        return map;
    }

    @Override
    protected TypeChangeProperty setTypeChangeProperty()
    {
        return TypeChangeProperty.HealDay;
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.heal_day = true;
        return p;
    }
}