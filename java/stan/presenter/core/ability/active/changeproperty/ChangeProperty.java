package stan.presenter.core.ability.active.changeproperty;

import stan.presenter.core.ability.active.Active;
import stan.presenter.core.player.Player;

public abstract class ChangeProperty
        extends Active
{
    public enum TypeChangeProperty
    {
        Kill,
        HealDay,
        Block
    }

    private TypeChangeProperty typeChangeProperty;

    public ChangeProperty(String n)
    {
        super(n);
        typeChangeProperty = setTypeChangeProperty();
    }

    protected abstract TypeChangeProperty setTypeChangeProperty();

    @Override
    protected TypeActive setTypeActive()
    {
        return TypeActive.ChangeProperty;
    }

    public abstract Player engage(Player p);
}
