package stan.presenter.core.ability.now.checkproperty;

import stan.presenter.core.ability.Ability;
import stan.presenter.core.ability.now.Now;
import stan.presenter.core.player.Player;

public class CheckProperty
        extends Now
{
    public CheckProperty(String n)
    {
        super(n);
    }

    @Override
    protected TypeAbility setTypeAbility()
    {
        return null;
    }

    @Override
    public int[] getMap()
    {
        return new int[0];
    }

    public String engage(Player p)
    {
        return null;
    }
}
