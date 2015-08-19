package stan.presenter.core.ability.active.changeproperty;

import stan.presenter.core.ability.active.Active;
import stan.presenter.core.player.Player;

public class ChangeProperty
        extends Active
{
    public enum TypeActive
    {
        Kill,
        Block
    }

    private TypeActive typeActive;
    public boolean try_stop = false;

    public ChangeProperty(String n)
    {
        super(n);
    }

    public void setTypeActive(TypeActive ta)
    {
        typeActive = ta;
    }
    public Player engage(Player p)
    {
        if(typeActive == TypeActive.Kill)
        {
            p.prop.life = false;
        }
        else if(typeActive == TypeActive.Block)
        {
            p.prop.stop = true;
        }
        return p;
    }
}
