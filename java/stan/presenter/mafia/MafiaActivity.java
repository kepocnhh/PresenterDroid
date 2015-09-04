package stan.presenter.mafia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import stan.presenter.mafia.fragments.FragmentTransactionPattern;
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
        fTP = new FragmentTransactionPattern(this, frameView);
        //
        initFragments();
        initViews();
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