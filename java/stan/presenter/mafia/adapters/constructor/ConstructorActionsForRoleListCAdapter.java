package stan.presenter.mafia.adapters.constructor;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stan.db.contract.Contract;
import stan.presenter.mafia.R;
import stan.presenter.mafia.adapters.MafiaCursorAdapter;

public class ConstructorActionsForRoleListCAdapter
        extends MafiaCursorAdapter
{
    public interface IConstrActionsFRoleClick
        extends IMafiaCursorClick
    {
    }
    static class ActionForRole
    {
        public String ID;
        public boolean selfieR = false;
        public boolean visibleRoleR = false;
        public ActionForRole(String i, boolean s, boolean v)
        {
            ID = i;
            selfieR = s;
            visibleRoleR = v;
        }
    }
    static class ConstructorActionsForeRoleHolder
            extends MafiaCursorHolder
    {
        private ActionForRole afr;
        public void setAFR()
        {
            afr = new ActionForRole(ID, selfieR, visibleRoleR);
        }
        public ActionForRole getAFR()
        {
            return afr;
        }
        private boolean check = false;
        private boolean selfieR = false;
        private boolean visibleRoleR = false;
        private String ID;

        void setID(String id)
        {
            ID = id;
        }
        String getID()
        {
            return ID;
        }

        TextView nameAction;
        TextView descrAction;
        Button selfieRestriction;
        Button visibleRoleRestriction;
        View detailAction;

        public ConstructorActionsForeRoleHolder(View v)
        {
            super(v);
            nameAction = (TextView) v.findViewById(R.id.nameAction);
            descrAction = (TextView) v.findViewById(R.id.descrAction);
            selfieRestriction = (Button) v.findViewById(R.id.selfieRestriction);
            visibleRoleRestriction = (Button) v.findViewById(R.id.visibleRoleRestriction);
            detailAction = v.findViewById(R.id.detailAction);
        }

        public boolean isChecked()
        {
            return check;
        }
        public boolean isVisibleRole()
        {
            return visibleRoleR;
        }
        public boolean isSelfie()
        {
            return selfieR;
        }
        public boolean changeChecked()
        {
            check = !check;
            return check;
        }
        public boolean changeSelfie()
        {
            selfieR = !selfieR;
            return selfieR;
        }
        public boolean changeVisibleRole()
        {
            visibleRoleR = !visibleRoleR;
            if(visibleRoleR)
            {
                selfieR = true;
            }
            return visibleRoleR;
        }
    }

    List<String> actions;

    int whiteColor;
    int greenColor;
    int redColor;

    public ConstructorActionsForRoleListCAdapter(Context context, Cursor c, IConstrActionsFRoleClick click)
    {
        super(context, c, R.layout.constr_actions_f_role_list_item, click);
        actions = new ArrayList<>();
        whiteColor = context.getResources().getColor(R.color.cwhite);
        greenColor = context.getResources().getColor(R.color.cnewgreen);
        redColor = context.getResources().getColor(R.color.cred);
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
        final ConstructorActionsForeRoleHolder holder = getHolder(h);
        holder.setID(cursor.getString(cursor.getColumnIndex(Contract.ID)));
        holder.nameAction.setText(cursor.getString(cursor.getColumnIndex(Contract.NAME)));
        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(holder.changeChecked())
                {
                    actions.add(holder.getID());
                }
                else
                {
                    actions.remove(holder.getID());
                }
                notifyDataSetChanged();
            }
        });
        //
        if(!holder.isChecked())
        {
            holder.parent.setBackgroundColor(whiteColor);
            holder.detailAction.setVisibility(View.GONE);
            return;
        }
        holder.parent.setBackgroundColor(redColor);
        holder.detailAction.setVisibility(View.VISIBLE);
        holder.descrAction.setText(cursor.getString(cursor.getColumnIndex(Contract.DESCRIPTION)));
        //
        int selfietext = R.string.canselfie;
        if(holder.isSelfie())
        {
            selfietext = R.string.cantselfie;
        }
        holder.selfieRestriction.setText(selfietext);
        holder.selfieRestriction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                holder.changeSelfie();
                notifyDataSetChanged();
            }
        });
        int visiblestext = R.string.canvisiblerole;
        if(holder.isVisibleRole())
        {
            visiblestext = R.string.cantvisiblerole;
        }
        holder.visibleRoleRestriction.setText(visiblestext);
        holder.visibleRoleRestriction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                holder.changeVisibleRole();
                notifyDataSetChanged();
            }
        });
        holder.setAFR();
        //
    }

    private ConstructorActionsForeRoleHolder getHolder(MafiaCursorHolder h)
    {
        return (ConstructorActionsForeRoleHolder)h;
    }

    public String[] getActionIDs()
    {
        String[] actionIDs = new String[actions.size()];
        actions.toArray(actionIDs);
        return  actionIDs;
    }
}