package stan.presenter.mafia.fragments.constructor;

import android.view.View;

import stan.presenter.core.Role;
import stan.presenter.mafia.R;

public class ConstructorRole
        extends ConstructorFragment
{
    public interface IConstructorRoleClick
            extends IMafiaFragmentClick
    {
        void saveNewRole(Role r);
    }

    public ConstructorRole()
    {
        super(R.layout.constructor_role, "constructorrole");
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        v.findViewById(R.id.constructorrolesave).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Role r = checkRole();
                if(r != null)
                {
                    ((IConstructorRoleClick) clickListener).saveNewRole(r);
                }
            }
        });
    }

    private Role checkRole()
    {
        return null;
    }
}
