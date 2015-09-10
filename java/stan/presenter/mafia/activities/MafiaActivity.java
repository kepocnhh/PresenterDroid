package stan.presenter.mafia.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import stan.presenter.mafia.fragments.transaction.FragmentTransactionPattern;
import stan.presenter.mafia.fragments.MafiaFragment;

public abstract class MafiaActivity
        extends AppCompatActivity
{
    //__________FRAGMENTS
    private FragmentTransactionPattern fTP;

    private int contentView;
    private int frameView;

    public MafiaActivity(int content, int frame)
    {
        initActivity(content, frame);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(contentView);
        fTP = setFragmentTransactionPattern();
        initViews();
        initFragments();
    }

    protected int getFrameView()
    {
        return frameView;
    }
    protected FragmentTransactionPattern getFragmentTransaction()
    {
        return fTP;
    }

    protected FragmentTransactionPattern setFragmentTransactionPattern()
    {
        return new FragmentTransactionPattern(this, getFrameView());
    }

    private void initActivity(int content, int frame)
    {
        this.contentView = content;
        this.frameView = frame;
    }
    public void addFragment(Fragment f)
    {
        fTP.add(f);
    }
    public void addFragmentWithTag(Fragment f, String tag)
    {
        fTP.add(f, tag);
    }
    public void addFragmentWithTag(MafiaFragment f)
    {
        addFragmentWithTag(f, f.getFragmentTag());
    }

    public abstract void initFragments();
    public abstract void initViews();

}