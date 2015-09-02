package stan.presenter.mafia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stan.presenter.mafia.fragments.FragmentTransactionPattern;
import stan.presenter.mafia.fragments.constructor.ConstructorMenu;

public class Constructor
        extends AppCompatActivity
        implements ConstructorMenu.IConstructorMenuClick
{
    //__________FRAGMENTS
    private FragmentTransactionPattern fTP;
    private ConstructorMenu constructorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constructor);
        //
        initFragments();
    }

    public void initFragments()
    {
        fTP = new FragmentTransactionPattern(this, R.id.constructorframe);
        constructorMenu = new ConstructorMenu();
        fTP.add(constructorMenu);
    }

    @Override
    public void toRole()
    {

    }

    @Override
    public void toAction()
    {

    }
}