package stan.presenter.core.ability;

import stan.presenter.core.Mafia;

public abstract class Ability
        extends Mafia
{
    public enum TypeAbility
    {
        Active,
        CheckTwoPlayers,
        Now,
        Passive
    }

    private TypeAbility typeAbility;

    public Ability(String n)
    {
        super(n);
        typeAbility = setTypeAbility();
    }

    protected abstract TypeAbility setTypeAbility();

    public abstract int[] getMap();
}
