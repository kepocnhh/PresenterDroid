package stan.presenter.mafia.adapters.constructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.MafiaAdapter;

public class ConstrActionsFRoleResultAdapter
        extends MafiaAdapter
{
    public interface IConstrActionsFRoleResultListener
            extends MafiaAdapterListener
    {
    }

    static class ConstrActionsFRoleResultHolder
            extends MafiaHolder
    {
        TextView nameAction;
        TextView selfieRestriction;
        TextView visibleRoleRestriction;
    }

    public ConstrActionsFRoleResultAdapter(Activity context, List<ConstructorRole.ActionForRole> d, IConstrActionsFRoleResultListener l)
    {
        super(context, d, l, R.layout.constr_actions_f_role_result_item);
    }

    @Override
    protected void pressItem(int p)
    {

    }

    @Override
    protected MafiaHolder initItems(View v)
    {
        ConstrActionsFRoleResultHolder holder = new ConstrActionsFRoleResultHolder();
        holder.nameAction = (TextView) v.findViewById(R.id.nameAction);
        holder.selfieRestriction = (TextView) v.findViewById(R.id.selfieRestriction);
        holder.visibleRoleRestriction = (TextView) v.findViewById(R.id.visibleRoleRestriction);
        return holder;
    }

    @Override
    protected void realizeItem(MafiaHolder h, int p)
    {
        ConstrActionsFRoleResultHolder holder = getHolder(h);
        holder.nameAction.setText(getAction(p).name);
        if(!getAction(p).restrictions.canSelfie())
        {
            holder.selfieRestriction.setText(R.string.cantselfie);
        }
        if(!getAction(p).restrictions.canVisibles())
        {
            holder.visibleRoleRestriction.setText(R.string.cantvisiblerole);
        }
    }

    private ConstrActionsFRoleResultHolder getHolder(MafiaHolder h)
    {
        return (ConstrActionsFRoleResultHolder)h;
    }

    private ConstructorRole.ActionForRole getAction(int p)
    {
        return ((ConstructorRole.ActionForRole) data.get(p));
    }
}