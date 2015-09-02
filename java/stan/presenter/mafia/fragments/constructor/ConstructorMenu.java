package stan.presenter.mafia.fragments.constructor;

import android.view.View;

import stan.presenter.mafia.R;

public class ConstructorMenu
    extends ConstructorFragment
{
    public interface IConstructorMenuClick
            extends IMafiaFragmentClick
    {
        void toRole();
        void toAction();
    }

    public ConstructorMenu()
    {
        super(R.layout.constructor_menu);
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
