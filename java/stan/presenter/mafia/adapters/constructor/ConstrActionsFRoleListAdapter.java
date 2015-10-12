package stan.presenter.mafia.adapters.constructor;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole.ActionForRole;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.MafiaAdapter;

public class ConstrActionsFRoleListAdapter
        extends MafiaAdapter
{
    public interface IConstrActionsFRoleListener
            extends MafiaAdapterListener
    {
    }

    static class ConstrActionsFRoleListHolder
            extends MafiaHolder
    {
        TextView nameAction;
        TextView descrAction;
        Button selfieRestriction;
        Button visibleRoleRestriction;
        View detailAction;
    }

    int whiteColor;
    int greenColor;
    int redColor;

    int canselfie = R.string.canselfie;
    int cantselfie = R.string.cantselfie;
    int canvisiblerole = R.string.canvisiblerole;
    int cantvisiblerole = R.string.cantvisiblerole;

    public ConstrActionsFRoleListAdapter(Activity context, List<ConstructorRole.ActionForRole> d, IConstrActionsFRoleListener l)
    {
        super(context, d, l, R.layout.constr_actions_f_role_list_item);
        whiteColor = context.getResources().getColor(R.color.cwhite);
        greenColor = context.getResources().getColor(R.color.cnewgreen);
        redColor = context.getResources().getColor(R.color.cred);
    }

    @Override
    protected void pressItem(int p)
    {
    }

    @Override
    protected MafiaHolder initItems(View v)
    {
        ConstrActionsFRoleListHolder holder = new ConstrActionsFRoleListHolder();
        holder.nameAction = (TextView) v.findViewById(R.id.nameAction);
        holder.descrAction = (TextView) v.findViewById(R.id.descrAction);
        holder.selfieRestriction = (Button) v.findViewById(R.id.selfieRestriction);
        holder.visibleRoleRestriction = (Button) v.findViewById(R.id.visibleRoleRestriction);
        holder.detailAction = v.findViewById(R.id.detailAction);
        return holder;
    }

    @Override
    protected void realizeItem(MafiaHolder h, final int p)
    {
        ConstrActionsFRoleListHolder holder = getHolder(h);
        holder.nameAction.setText(getAction(p).name);
        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(getAction(p).changeChecked())
                {
                } else
                {
                }
                notifyDataSetChanged();
            }
        });
        if(!getAction(p).isChecked())
        {
            holder.parent.setBackgroundColor(whiteColor);
            holder.detailAction.setVisibility(View.GONE);
            return;
        }
        //
        holder.parent.setBackgroundColor(redColor);
        holder.detailAction.setVisibility(View.VISIBLE);
        holder.descrAction.setText(getAction(p).description);
        //
        if(getAction(p).restrictions.canSelfie())
        {
            holder.selfieRestriction.setText(canselfie);
        }
        else
        {
            holder.selfieRestriction.setText(cantselfie);
        }
        holder.selfieRestriction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getAction(p).restrictions.changeSelfie();
                notifyDataSetChanged();
            }
        });
        if(getAction(p).restrictions.canVisibles())
        {
            holder.visibleRoleRestriction.setText(canvisiblerole);
        }
        else
        {
            holder.visibleRoleRestriction.setText(cantvisiblerole);
        }
        holder.visibleRoleRestriction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getAction(p).restrictions.changeVisibleRole();
                notifyDataSetChanged();
            }
        });
    }

    private ConstrActionsFRoleListHolder getHolder(MafiaHolder h)
    {
        return (ConstrActionsFRoleListHolder)h;
    }
    private ActionForRole getAction(int p)
    {
        return ((ActionForRole) data.get(p));
    }

    public List<ActionForRole> getActions()
    {
        List<ActionForRole> actionForRoles = new ArrayList<>();
        for(int i=0; i<data.size(); i++)
        {
            if(getAction(i).isChecked())
            {
                actionForRoles.add(getAction(i));
            }
        }
        return actionForRoles;
    }
}