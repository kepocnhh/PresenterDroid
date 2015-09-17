package stan.presenter.mafia.adapters.constructor;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stan.db.contract.Contract;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.MafiaCursorAdapter;

public class ConstructorActionsForeRoleListCAdapter
        extends MafiaCursorAdapter
{
    public interface IConstrActionsFRoleClick
        extends IMafiaCursorClick
    {
    }
    static class ConstructorActionsForeRoleHolder
            extends MafiaCursorHolder
    {
        TextView nameAction;
        public ConstructorActionsForeRoleHolder(View v)
        {
            super(v);
            nameAction = (TextView) v.findViewById(R.id.nameAction);
        }
    }

    List<Integer> actions;

    public ConstructorActionsForeRoleListCAdapter(Context context, Cursor c, IConstrActionsFRoleClick click)
    {
        super(context, c, R.layout.constr_actions_f_role_list_item, click);
        actions = new ArrayList<>();
    }

    @Override
    protected View newView(View view, Cursor cursor)
    {
        return view;
    }

    @Override
    protected MafiaCursorHolder initHolder(View view)
    {
        ConstructorActionsForeRoleHolder holder = new ConstructorActionsForeRoleHolder(view);
        return holder;
    }

    @Override
    public void bindView(MafiaCursorHolder h, Cursor cursor)
    {
        ConstructorActionsForeRoleHolder holder = getHolder(h);
        holder.nameAction.setText(cursor.getString(cursor.getColumnIndex(Contract.NAME)));
    }

    private ConstructorActionsForeRoleHolder getHolder(MafiaCursorHolder h)
    {
        return (ConstructorActionsForeRoleHolder)h;
    }
}