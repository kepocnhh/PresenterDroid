package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;

import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeVisibleRole
        extends ConstructorFragment
{
    public interface IChangeVisibleRoleClick
            extends IConstructorClick
    {
        void visibleRoleNext();
    }

    //______________Views

    public ChangeVisibleRole()
    {
        super(R.layout.constructor_role_visiblerole, R.string.ChangeVisibleRole);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeVisibleRoleClick) clickListener).visibleRoleNext();
            }
        };
    }
}