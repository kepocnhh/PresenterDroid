package stan.presenter.mafia.fragments.constructor;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import stan.presenter.core.role.Command;
import stan.presenter.core.role.Role;
import stan.presenter.core.role.typegrouprole.IndividualsGroup;
import stan.presenter.core.role.typegrouprole.RangGroup;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.constructor.ConstructorRoleListAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ConstructorRoleList
        extends ConstructorMenuFragment
{
    public interface IConstructorRoleClick
            extends IMafiaFragmentClick
    {
        void addRole();
    }

    //______________Views
    private ListView listRoles;
    private Button addRole;

    private ConstructorRoleListAdapter adapter;

    public ConstructorRoleList()
    {
        super(R.layout.constructor_role_list, "constructor_role_list");
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
                ((IConstructorRoleClick)clickListener).addRole();
            }
        });
    }

    private void initListRoles(View v)
    {
        listRoles = (ListView) v.findViewById(R.id.listRoles);
        ArrayList<Role> d = new ArrayList<>();
        d.add(new Role("Коммисар", "Сажает в тюрьму или убивает подозреваемого", Role.TypeVisibility.peace, new IndividualsGroup.Individuals(), new Command("Город"), null));
        d.add(new Role("Мирный житель", "Ничего не делает", Role.TypeVisibility.peace, new IndividualsGroup.Individuals(), new Command("Город"), null));
        d.add(new Role("Мафия", "Босс мафии убивает одного человека", Role.TypeVisibility.mafia, new RangGroup.Clan(true), new Command("Мафия"), null));
        adapter = new ConstructorRoleListAdapter(getActivity(), d, new ConstructorRoleListAdapter.IConstructorRoleListListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        listRoles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}