package stan.presenter.mafia.fragments.constructor.menu;

import android.view.View;

import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorMenuFragment;

public class ConstructorMenu
    extends ConstructorMenuFragment
{
    public interface IConstructorMenuClick
            extends IMafiaFragmentClick
    {
        void toRole();
        void toAction();
    }

    public ConstructorMenu()
    {
        super(R.layout.constructor_menu, R.string.ConstructorMenu);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        v.findViewById(R.id.torole).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IConstructorMenuClick) clickListener).toRole();
            }
        });
        v.findViewById(R.id.toaction).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IConstructorMenuClick) clickListener).toAction();
            }
        });
    }
}
