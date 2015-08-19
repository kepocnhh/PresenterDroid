package stan.presenter.core.ability.passive.selfie;

import stan.presenter.core.ability.passive.Passive;
import stan.presenter.core.player.Player;

public class Selfie
        extends Passive
{
    public Selfie(String n)
    {
        super(n);
    }

    public Player engage(Player p)
    {
        return p;
    }
}
