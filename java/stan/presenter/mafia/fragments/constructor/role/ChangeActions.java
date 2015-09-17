package stan.presenter.mafia.fragments.constructor.role;

import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import stan.db.DBHelper;
import stan.db.contract.Contract;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.constructor.ConstructorActionsForeRoleListCAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeActions
        extends ConstructorFragment
{
    public interface IChangeActionsClick
            extends ConstructorFragment.IConstructorClick
    {
        void getActionIDs(int[] actions);
    }

    //______________Views
    ListView listActionsForRole;

    ConstructorActionsForeRoleListCAdapter adapter;

    public ChangeActions()
    {
        super(R.layout.constructor_role_actions, R.string.ChangeActions);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        initListActions(v);
    }

    private void initListActions(View v)
    {
        listActionsForRole = (ListView) v.findViewById(R.id.listActionsForRole);
        Cursor cursor = DBHelper.getInstance(getActivity()).query(Contract.getContract(Contract.TABLE_NAME_ACTION), null, null, null, null);
        adapter = new ConstructorActionsForeRoleListCAdapter(getActivity(), cursor,
                new ConstructorActionsForeRoleListCAdapter.IConstrActionsFRoleClick()
                {
                    @Override
                    public void click()
                    {

                    }
                });
        listActionsForRole.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return null;
    }
}