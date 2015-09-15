package stan.presenter.core.ability.active;

import stan.presenter.core.ability.Ability;

public abstract class Active
        extends Ability
{
    public enum TypeActive
    {
        ChangeProperty,
        NotifyTown
    }

    private TypeActive typeActive;

    public Active(String n)
    {
        super(n);
        typeActive = setTypeActive();
    }

    @Override
    protected TypeAbility setTypeAbility()
    {
        return TypeAbility.Active;
    }

    protected abstract TypeActive setTypeActive();
}