package stan.presenter.mafia.adapters.constructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.MafiaAdapter;

public class ConstrRolesFRoleListAdapter
        extends MafiaAdapter
{
    @Override
    protected void pressItem(int p)
    {

    }

    @Override
    protected MafiaHolder initItems(View v)
    {
        ConstrRolesFRoleListHolder holder = new ConstrRolesFRoleListHolder();
        holder.nameRole = (TextView) v.findViewById(R.id.nameRole);
        holder.descrRole = (TextView) v.findViewById(R.id.descrRole);
        return holder;
    }

    @Override
    protected void realizeItem(MafiaHolder h, final int p)
    {
        ConstrRolesFRoleListHolder holder = getHolder(h);
        holder.nameRole.setText(getRole(p).name);
        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(getRole(p).changeChecked())
                {
                } else
                {
                }
                notifyDataSetChanged();
            }
        });
        if(!getRole(p).isChecked())
        {
            holder.parent.setBackgroundColor(whiteColor);
            holder.descrRole.setVisibility(View.GONE);
            return;
        }
        holder.parent.setBackgroundColor(redColor);
        holder.descrRole.setVisibility(View.VISIBLE);
        holder.descrRole.setText(getRole(p).description);
    }

    public interface IConstrRolesFRoleListener
            extends MafiaAdapterListener
    {
    }

    static class ConstrRolesFRoleListHolder
            extends MafiaHolder
    {
        TextView nameRole;
        TextView descrRole;
    }

    int whiteColor;
    int redColor;

    public ConstrRolesFRoleListAdapter(Activity context, List<ConstructorRole.RoleForRole> d, IConstrRolesFRoleListener l)
    {
        super(context, d, l, R.layout.constr_roles_f_role_list_item);
        whiteColor = context.getResources().getColor(R.color.cwhite);
        redColor = context.getResources().getColor(R.color.cred);
    }

    private ConstrRolesFRoleListHolder getHolder(MafiaHolder h)
    {
        return (ConstrRolesFRoleListHolder)h;
    }
    private ConstructorRole.RoleForRole getRole(int p)
    {
        return ((ConstructorRole.RoleForRole) data.get(p));
    }

    public List<ConstructorRole.RoleForRole> getRoles()
    {
        List<ConstructorRole.RoleForRole> roleForRoles = new ArrayList<>();
        for(int i=0; i<data.size(); i++)
        {
            if(getRole(i).isChecked())
            {
                roleForRoles.add(getRole(i));
            }
        }
        return roleForRoles;
    }
}