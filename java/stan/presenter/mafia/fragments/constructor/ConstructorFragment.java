package stan.presenter.mafia.fragments.constructor;

import android.view.View;

import stan.presenter.mafia.fragments.MafiaFragment;

public abstract class ConstructorFragment
        extends MafiaFragment
{
    protected int label;
    protected int why;
    public int getWhyString()
    {
        return this.why;
    }
    public int getLabelString()
    {
        return this.label;
    }
    protected abstract int setWhyString();
    protected abstract int setLabelString();

    public interface IConstructorClick
            extends IMafiaFragmentClick
    {
        View getViewNext();
        void back(ConstructorFragment from);
    }

    protected View.OnClickListener nextClickListener;

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        if(!hidden)
        {
            setViewNext();
            back();
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
        why = setWhyString();
        label = setLabelString();
    }

    protected void setViewNext()
    {
        ((IConstructorClick) clickListener).getViewNext().setOnClickListener(nextClickListener);
    }

    private void getNextClickListener()
    {
        nextClickListener = setNextClickListener();
    }
    private void back()
    {
        ((IConstructorClick) clickListener).back(this);
    }

    protected abstract View.OnClickListener setNextClickListener();
}