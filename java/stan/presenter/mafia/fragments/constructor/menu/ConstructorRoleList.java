package stan.presenter.mafia.fragments.constructor.menu;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import stan.db.DBHelper;
import stan.db.contract.Contract;
import stan.presenter.core.role.Team;
import stan.presenter.core.role.Role;
import stan.presenter.core.role.typegrouprole.IndividualsGroup;
import stan.presenter.core.role.typegrouprole.RangGroup;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.constructor.ConstructorRoleListAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorMenuFragment;

public class ConstructorRoleList
        extends ConstructorMenuFragment
        implements ConstructorRoleListAdapter.IConstructorRoleListListener
{
    public interface IConstructorRoleClick
            extends IMafiaFragmentClick
    {
        void addRole();
        void editRole(String id);
        void deleteRole(String id);
    }

    //______________Views
    private ListView listRoles;
    private Button addRole;

    private ConstructorRoleListAdapter adapter;

    public ConstructorRoleList()
    {
        super(R.layout.constructor_role_list, R.string.ConstructorRoleList);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        initListRoles(v);
        addRole = (Button) v.findViewById(R.id.addRole);
        addRole.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IConstructorRoleClick) clickListener).addRole();
            }
        });
    }

    private void initListRoles(View v)
    {
        listRoles = (ListView) v.findViewById(R.id.listRoles);
        updateData();
    }
    public List<Role> getRoles(Cursor cursor)
    {
        cursor.moveToFirst();
        List<Role> roles = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            String name = cursor.getString(cursor.getColumnIndex(Contract.NAME));
            String descr = cursor.getString(cursor.getColumnIndex(Contract.DESCRIPTION));
            String id = cursor.getString(cursor.getColumnIndex(Contract.ID));
            Role r = new Role(name, descr);
            r.UID = id;
            roles.add(r);
            cursor.moveToNext();
        }
        cursor.close();
        return roles;
    }
    public void updateData()
    {
        Cursor cursor = DBHelper.getInstance(getActivity()).query(Contract.getContract(Contract.TABLE_NAME_ROLE));
        adapter = new ConstructorRoleListAdapter(getActivity(), getRoles(cursor), this);
        listRoles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        cursor.close();
    }

    @Override
    public void pressItem(int pos)
    {

    }

    @Override
    public void editRole(String id)
    {
        ((IConstructorRoleClick) clickListener).editRole(id);
    }

    @Override
    public void deleteRole(String id)
    {
        ((IConstructorRoleClick) clickListener).deleteRole(id);
    }
}