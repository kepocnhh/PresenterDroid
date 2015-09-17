package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.ListView;

import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeActions
        extends ConstructorFragment
{
    public interface IChangeActionsClick
            extends ConstructorFragment.IConstructorClick
    {
    }

    //______________Views
    ListView listActionsForRole;

    public ChangeActions()
    {
        super(R.layout.constructor_role_actions, R.string.ChangeActions);
    }
    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        listActionsForRole = (ListView) v.findViewById(R.id.listActionsForRole);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return null;
    }
}