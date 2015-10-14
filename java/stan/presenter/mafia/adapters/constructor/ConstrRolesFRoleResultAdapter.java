package stan.presenter.mafia.adapters.constructor;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import stan.presenter.mafia.R;
import stan.presenter.mafia.activities.constructor.ConstructorRole;
import stan.presenter.mafia.adapters.MafiaAdapter;

public class ConstrRolesFRoleResultAdapter
        extends MafiaAdapter
{
    public interface IConstrRolesFRoleResultListener
            extends MafiaAdapterListener
    {
    }

    static class ConstrRolesFRoleResultHolder
            extends MafiaHolder
    {
        TextView nameRole;
    }

    public ConstrRolesFRoleResultAdapter(Activity context, List<ConstructorRole.RoleForRole> d, IConstrRolesFRoleResultListener l)
    {
        super(context, d, l, R.layout.constr_roles_f_role_result_item);
    }

    @Override
    protected void pressItem(int p)
    {

    }

    @Override
    protected MafiaHolder initItems(View v)
    {
        ConstrRolesFRoleResultHolder holder = new ConstrRolesFRoleResultHolder();
        holder.nameRole = (TextView) v.findViewById(R.id.nameRole);
        return holder;
    }

    @Override
    protected void realizeItem(MafiaHolder h, int p)
    {
        ConstrRolesFRoleResultHolder holder = getHolder(h);
        holder.nameRole.setText(getRole(p).name);
    }

    private ConstrRolesFRoleResultHolder getHolder(MafiaHolder h)
    {
        return (ConstrRolesFRoleResultHolder)h;
    }

    private ConstructorRole.RoleForRole getRole(int p)
    {
        return ((ConstructorRole.RoleForRole) data.get(p));
    }
}