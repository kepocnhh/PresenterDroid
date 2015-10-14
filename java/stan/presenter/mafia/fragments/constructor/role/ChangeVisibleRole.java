package stan.presenter.mafia.fragments.constructor.role;

import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import stan.db.DBHelper;
import stan.db.contract.Contract;
import stan.presenter.core.role.Role;
import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.constructor.ConstrActionsFRoleListAdapter;
import stan.presenter.mafia.adapters.constructor.ConstrRolesFRoleListAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeVisibleRole
        extends ConstructorFragment
{
    public interface IChangeVisibleRoleClick
            extends IConstructorClick
    {
        void visibleRoleNext(List<ConstructorRole.RoleForRole> r);
    }

    //______________Views
    ListView roleList;

    ConstrRolesFRoleListAdapter adapter;

    public ChangeVisibleRole()
    {
        super(R.layout.constructor_role_visiblerole, R.string.ChangeVisibleRole);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        roleList = (ListView) v.findViewById(R.id.roleList);
        Cursor cursor = DBHelper.getInstance(getActivity()).query(Contract.getContract(Contract.TABLE_NAME_ROLE), null, null, null, null);
        adapter = new ConstrRolesFRoleListAdapter(getActivity(), getRoles(cursor), new ConstrRolesFRoleListAdapter.IConstrRolesFRoleListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        cursor.close();
        roleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public List<ConstructorRole.RoleForRole> getRoles(Cursor cursor)
    {
        cursor.moveToFirst();
        List<ConstructorRole.RoleForRole> roles = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            String name = cursor.getString(cursor.getColumnIndex(Contract.NAME));
            String descr = cursor.getString(cursor.getColumnIndex(Contract.DESCRIPTION));
            String id = cursor.getString(cursor.getColumnIndex(Contract.ID));
            ConstructorRole.RoleForRole rfr = new ConstructorRole.RoleForRole();
            rfr.name = name;
            rfr.description = descr;
            rfr.id = id;
            roles.add(rfr);
            cursor.moveToNext();
        }
        cursor.close();
        return roles;
    }

    @Override
    protected int setWhyString()
    {
        return 0;
    }

    @Override
    protected int setLabelString()
    {
        return R.string.constructor_role_visiblerole;
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeVisibleRoleClick) clickListener).visibleRoleNext(adapter.getRoles());
            }
        };
    }
}