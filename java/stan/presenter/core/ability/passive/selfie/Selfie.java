package stan.presenter.core.ability.passive.selfie;

import stan.presenter.core.ability.Ability;
import stan.presenter.core.ability.passive.Passive;
import stan.presenter.core.player.Player;

public class Selfie
        extends Passive
{
    public Selfie(String n)
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

    public Player engage(Player p)
    {
        return p;
    }
}
