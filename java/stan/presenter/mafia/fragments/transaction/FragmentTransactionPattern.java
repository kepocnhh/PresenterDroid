package stan.presenter.mafia.fragments.transaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentTransactionPattern
{
    FragmentManager fragmentManager;
    int ID;

    public FragmentTransactionPattern(AppCompatActivity act, int id)
    {
        fragmentManager = act.getSupportFragmentManager();
        this.ID = id;
    }

    public void add(Fragment f)
    {
        fragmentManager.beginTransaction().add(this.ID, f).commit();
    }

    public void add(Fragment f, int id)
    {
        if(fragmentManager.findFragmentById(id) == null)
        {
            fragmentManager.beginTransaction().add(id, f).commit();
        }
    }
    public void add(Fragment f,String tag)
    {
        fragmentManager.beginTransaction().
                add( this.ID, f).
                addToBackStack(tag).
                commit();
    }
    public void add(Fragment oldF, Fragment newF, String tag)
    {
        fragmentManager.beginTransaction().
                add(this.ID, newF).
                hide(oldF).
                addToBackStack(tag).
                commit();
    }
    public void replace(Fragment f)
    {
        fragmentManager.beginTransaction().replace(ID, f).commit();
    }
}