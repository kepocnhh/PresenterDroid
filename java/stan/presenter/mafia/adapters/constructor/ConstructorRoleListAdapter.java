package stan.presenter.mafia.adapters.constructor;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import stan.presenter.core.role.Role;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.MafiaAdapter;

public class ConstructorRoleListAdapter
        extends MafiaAdapter
{
    //__________KEYS
    public static final String LABLE_ROLE = "lablerole";

    private int checkPosition = -1;

    static class ConstructorRoleListHolder
            extends MafiaHolder
    {
        Button customizeRole;
        Button deleteRole;
        TextView lableRole;
        TextView descriptionRole;
        LinearLayout infoRole;
    }

    public interface IConstructorRoleListListener
            extends MafiaAdapterListener
    {
    }

    public ConstructorRoleListAdapter(Activity context, List<Role> d, IConstructorRoleListListener l)
    {
        super(context, d, l, R.layout.constr_role_list_item);
    }

    @Override
    protected void pressItem(int p)
    {
        if(checkPosition == p)
        {
            checkPosition = -1;
        }
        else
        {
            checkPosition = p;
        }
        notifyDataSetChanged();
    }

    @Override
    protected MafiaHolder initItems(View vi)
    {
        ConstructorRoleListHolder holder = new ConstructorRoleListHolder();
        holder.customizeRole = (Button) vi.findViewById(R.id.customizeRole);
        holder.deleteRole = (Button) vi.findViewById(R.id.deleteRole);
        holder.lableRole = (TextView) vi.findViewById(R.id.lableRole);
        holder.descriptionRole = (TextView) vi.findViewById(R.id.descriptionRole);
        holder.infoRole = (LinearLayout) vi.findViewById(R.id.infoRole);
        return holder;
    }

    @Override
    protected void realizeItem(MafiaHolder holder, int p)
    {
        ((ConstructorRoleListHolder) holder).lableRole.setText(((Role) data.get(p)).name);
        if(checkPosition == p)
        {
            ((ConstructorRoleListHolder) holder).infoRole.setVisibility(View.VISIBLE);
            ((ConstructorRoleListHolder) holder).customizeRole.setVisibility(View.VISIBLE);
            ((ConstructorRoleListHolder) holder).deleteRole.setVisibility(View.VISIBLE);
            ((ConstructorRoleListHolder) holder).descriptionRole.setText(((Role) data.get(p)).description);
        }
        else
        {
            ((ConstructorRoleListHolder) holder).infoRole.setVisibility(View.GONE);
            ((ConstructorRoleListHolder) holder).customizeRole.setVisibility(View.INVISIBLE);
            ((ConstructorRoleListHolder) holder).deleteRole.setVisibility(View.INVISIBLE);
        }
    }
}