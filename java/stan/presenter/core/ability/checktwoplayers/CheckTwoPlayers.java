package stan.presenter.core.ability.checktwoplayers;

import stan.presenter.core.ability.Ability;
import stan.presenter.core.player.Player;

public class CheckTwoPlayers
        extends Ability
{
    public CheckTwoPlayers(String n)
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

    public boolean engage(Player p1, Player p2)
    {
        return true;
    }
}
