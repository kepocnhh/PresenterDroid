package stan.presenter.mafia.fragments.constructor.role;

import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import stan.db.DBHelper;
import stan.db.contract.Contract;
import stan.presenter.core.action.Action;
import stan.presenter.core.action.Restrictions;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.constructor.ConstrActionsFRoleListAdapter;
import stan.presenter.mafia.adapters.constructor.ConstructorActionsForRoleListCAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeActions
        extends ConstructorFragment
{
    public interface IChangeActionsClick
            extends ConstructorFragment.IConstructorClick
    {
        void getActionIDs(String[] actions);
    }

    //______________Views
    ListView listActionsForRole;

    ConstructorActionsForRoleListCAdapter adapterCursor;
    ConstrActionsFRoleListAdapter adapter;

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
        adapter = new ConstrActionsFRoleListAdapter(getActivity(), getActions(cursor), new ConstrActionsFRoleListAdapter.IConstrActionsFRoleListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        listActionsForRole.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public List<ConstrActionsFRoleListAdapter.ActionForRole> getActions(Cursor cursor)
    {
        cursor.moveToFirst();
        List<ConstrActionsFRoleListAdapter.ActionForRole> actions = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            String name = cursor.getString(cursor.getColumnIndex(Contract.NAME));
            String descr = cursor.getString(cursor.getColumnIndex(Contract.DESCRIPTION));
            String id = cursor.getString(cursor.getColumnIndex(Contract.ID));
            Restrictions restrictions = new Restrictions();
            ConstrActionsFRoleListAdapter.ActionForRole afr = new ConstrActionsFRoleListAdapter.ActionForRole();
            afr.name = name;
            afr.description = descr;
            afr.id = id;
            afr.restrictions = restrictions;
//            Action action = new Action(name, descr, restrictions, null);
//            action.UID = id;
//            actions.add(action);
            actions.add(afr);
            cursor.moveToNext();
        }
        cursor.close();
        return actions;
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                ((IChangeActionsClick) clickListener).getActionIDs(adapter.getActionIDs());
            }
        };
    }
}