package stan.presenter.mafia.fragments.constructor;

import android.view.View;

import stan.presenter.mafia.fragments.MafiaFragment;

public abstract class ConstructorFragment
        extends MafiaFragment
{
    public interface IConstructorClick
            extends IMafiaFragmentClick
    {
        View getViewNext();
    }

    protected View.OnClickListener nextClickListener;

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        if(!hidden)
        {
            setViewNext();
        }
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        setViewNext();
    }

    public ConstructorFragment(int lay, int tag)
    {
        super(lay, tag);
        nextClickListener = setNextClickListener();
    }

    protected void setViewNext()
    {
        ((IConstructorClick) clickListener).getViewNext().setOnClickListener(nextClickListener);
    }

    private void getNextClickListener()
    {
        nextClickListener = setNextClickListener();
    }

    protected abstract View.OnClickListener setNextClickListener();
}