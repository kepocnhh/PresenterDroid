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