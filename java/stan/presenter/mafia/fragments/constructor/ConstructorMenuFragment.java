package stan.presenter.mafia.fragments.constructor;

import android.view.View;

import stan.presenter.mafia.fragments.MafiaFragment;

public abstract class ConstructorMenuFragment
        extends MafiaFragment
{
    public interface IConstructorMenuFragmentClick
            extends IMafiaFragmentClick
    {
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
    }

    public ConstructorMenuFragment(int lay, String tag)
    {
        super(lay, tag);
    }
}