package stan.presenter.mafia.fragments.constructor;

import stan.presenter.mafia.fragments.MafiaFragment;

public abstract class ConstructorFragment
        extends MafiaFragment
{
    protected int minNameLenght = 0;
    protected int maxNameLenght = 15;
    protected int minDescrLenght = 0;
    protected int maxDescrLenght = 40;
    public ConstructorFragment(int lay, String tag)
    {
        super(lay, tag);
    }
}