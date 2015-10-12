package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
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
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ConstructorRoleResult
        extends ConstructorFragment
{
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
        public void saveRole(Role r);
    }

    //______________Views
    TextView nameRole;
    TextView descriptionRole;
    TextView sideRole;
    TextView typeGroupRole;
    TextView teamRole;
    ListView actionsList;

    ConstrActionsFRoleResultAdapter actionsAdapter;
    List<ConstructorRole.ActionForRole> actions;

    public ConstructorRoleResult()
    {
        super(R.layout.constructor_role_result, R.string.ConstructorRoleResult);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        //
        nameRole = (TextView) v.findViewById(R.id.nameRole);
        descriptionRole = (TextView) v.findViewById(R.id.descriptionRole);
        sideRole = (TextView) v.findViewById(R.id.sideRole);
        typeGroupRole = (TextView) v.findViewById(R.id.typeGroupRole);
        teamRole = (TextView) v.findViewById(R.id.teamRole);
        //
        actionsList = (ListView) v.findViewById(R.id.actionsList);
        actionsAdapter = new ConstrActionsFRoleResultAdapter(getActivity(), actions, new ConstrActionsFRoleResultAdapter.IonstrActionsFRoleResultListener()
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

    public void setRoleBits(String name,
                            String d,
                            Role.TypeVisibility tv,
                            TypeGroup tg,
                            Team cmd,
                            Role[] rls,
                            List<ConstructorRole.ActionForRole> act)
    {
        role = new Role(name, d, tv, tg, cmd, rls, null);
        actions = act;
        //
    }

    private void updateViews()
    {
        nameRole.setText(role.name);
        descriptionRole.setText(role.description);
        if(role.getTypeVisibility() == Role.TypeVisibility.peace)
        {
            sideRole.setText(R.string.peace);
        }
        else
        {
            sideRole.setText(R.string.mafia);
        }
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
                ((IRoleResultClick) clickListener).saveRole(role);
            }
        };
    }
}
