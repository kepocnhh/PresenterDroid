package stan.presenter.mafia.fragments.transaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ConstructorTransaction
    extends FragmentTransactionPattern
{
    private Fragment currentFragment;

    public ConstructorTransaction(AppCompatActivity act, int id)
    {
        super(act, id);
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
        {
            @Override
            public void onBackStackChanged()
            {
                currentFragment = getCurrentFragment();
            }
        });
    }

    private Fragment getCurrentFragment()
    {
        return fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount());
    }

    public void add(Fragment f)
    {
        super.add(f);
        currentFragment = f;
    }

    public void addHideTag(Fragment newF, String tag)
    {
        fragmentManager.beginTransaction().
                add( this.ID, newF).
                hide(currentFragment).
                addToBackStack(tag).
                commit();
    }
}