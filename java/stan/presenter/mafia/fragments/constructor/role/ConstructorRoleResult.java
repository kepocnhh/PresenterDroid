package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import stan.presenter.core.action.Action;
import stan.presenter.core.role.Role;
import stan.presenter.core.role.Team;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.constructor.ConstrActionsFRoleResultAdapter;
import stan.presenter.mafia.adapters.constructor.ConstrRolesFRoleResultAdapter;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ConstructorRoleResult
        extends ConstructorFragment
{
    //______________Views
//    LinearLayout sideLL;

    Role role;

    @Override
    protected int setWhyString()
    {
        return 0;
    }

    @Override
    protected int setLabelString()
    {
        return R.string.constructor_role_result;
    }


    public interface IRoleResultClick
            extends IConstructorClick
    {
        void saveRole(Role r);
        void editRole(Role r);
        void editSide();
    }

    //______________Views
    TextView nameRole;
    TextView descriptionRole;
    TextView sideRole;
    TextView typeGroupRole;
    TextView teamRole;
    ListView actionsList;
    ListView visibleRolesList;

    List<ConstructorRole.RoleForRole> roles;
    ConstrRolesFRoleResultAdapter rolesAdapter;
    List<ConstructorRole.ActionForRole> actions;
    ConstrActionsFRoleResultAdapter actionsAdapter;

    public ConstructorRoleResult()
    {
        super(R.layout.constructor_role_result, R.string.ConstructorRoleResult);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        //
        v.findViewById(R.id.sideLL).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IRoleResultClick) clickListener).editSide();
            }
        });
        nameRole = (TextView) v.findViewById(R.id.nameRole);
        descriptionRole = (TextView) v.findViewById(R.id.descriptionRole);
        sideRole = (TextView) v.findViewById(R.id.sideRole);
        typeGroupRole = (TextView) v.findViewById(R.id.typeGroupRole);
        teamRole = (TextView) v.findViewById(R.id.teamRole);
        //
        visibleRolesList = (ListView) v.findViewById(R.id.visibleRolesList);
        rolesAdapter = new ConstrRolesFRoleResultAdapter(getActivity(), roles, new ConstrRolesFRoleResultAdapter.IConstrRolesFRoleResultListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        visibleRolesList.setAdapter(rolesAdapter);
        rolesAdapter.notifyDataSetChanged();
        //
        actionsList = (ListView) v.findViewById(R.id.actionsList);
        actionsAdapter = new ConstrActionsFRoleResultAdapter(getActivity(), actions, new ConstrActionsFRoleResultAdapter.IConstrActionsFRoleResultListener()
        {
            @Override
            public void pressItem(int pos)
            {

            }
        });
        actionsList.setAdapter(actionsAdapter);
        actionsAdapter.notifyDataSetChanged();
        //
        updateViews();
    }
    public void setRolePeaceSide(boolean peace_side)
    {
        Role.TypeVisibility tv;
        if(peace_side)
        {
            tv = Role.TypeVisibility.peace;
        }
        else
        {
            tv = Role.TypeVisibility.mafia;
        }
        role.setTypeVisibility(tv);
        sideRoleUpdate();
    }
    private void sideRoleUpdate()
    {
        if(role.getTypeVisibility() == Role.TypeVisibility.peace)
        {
            sideRole.setText(R.string.peace);
        }
        else
        {
            sideRole.setText(R.string.mafia);
        }
    }
    public void setRoleBits(Role r,
                            List<ConstructorRole.RoleForRole> rls,
                            List<ConstructorRole.ActionForRole> act)
    {
        customizeState();
        role = r;
        roles = rls;
        actions = act;
    }
    public void setRoleBits(String name,
                            String d,
                            Role.TypeVisibility tv,
                            TypeGroup tg,
                            Team cmd,
                            List<ConstructorRole.RoleForRole> rls,
                            List<ConstructorRole.ActionForRole> act)
    {
        createState();
        Role[] rm = new Role[rls.size()];
        Action[] am = new Action[act.size()];
        for(int i=0; i<rls.size(); i++)
        {
            rm[i] = new Role(rls.get(i).name, rls.get(i).description);
            rm[i].UID = rls.get(i).id;
        }
        for(int i=0; i<act.size(); i++)
        {
            am[i] = new Action(act.get(i).name, act.get(i).description);
            am[i].setRestrictions(act.get(i).restrictions);
            am[i].UID = act.get(i).id;
        }
        role = new Role(name, d, tv, tg, cmd, rm, am);
        roles = rls;
        actions = act;
    }

    private void updateViews()
    {
        nameRole.setText(role.name);
        descriptionRole.setText(role.description);
        sideRoleUpdate();
        teamRole.setText(role.getTeam().name);
        typeGroupRole.setText(role.getTypeGroupRole().name);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(getCreateState())
                {
                    ((IRoleResultClick) clickListener).saveRole(role);
                }
                else
                {
                    ((IRoleResultClick) clickListener).editRole(role);
                }
            }
        };
    }
}
