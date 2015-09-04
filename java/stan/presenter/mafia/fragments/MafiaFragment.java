package stan.presenter.mafia.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MafiaFragment
        extends Fragment
{
    protected View container;
    private String fragmentTag;

    public String getFragmentTag()
    {
        return this.fragmentTag;
    }

    protected interface IMafiaFragmentClick
    {
    }
    protected IMafiaFragmentClick clickListener;

    public MafiaFragment(int lay, String tag)
    {
        Bundle args = new Bundle();
        args.putInt("layout", lay);
        setArguments(args);
        fragmentTag = tag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(getArguments().getInt("layout", 0), container, false);
        findViews(v);
        return v;
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        clickListener = (IMafiaFragmentClick) activity;
    }

    public void setVisibility(boolean visibility)
    {
        if(visibility)
        {
            container.setVisibility(View.VISIBLE);
        }
        else
        {
            container.setVisibility(View.INVISIBLE);
        }
    }

    protected void findViews(View v)
    {
        container = v;
    }
}