package stan.presenter.mafia.activities.constructor;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import stan.db.ContentDriver;
import stan.db.DBHelper;
import stan.db.contract.Contract;
import stan.presenter.core.action.Action;
import stan.presenter.core.action.Restrictions;
import stan.presenter.core.role.Role;
import stan.presenter.core.role.Team;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.activities.MafiaActivity;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.MafiaFragment;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;
import stan.presenter.mafia.fragments.constructor.role.ChangeActions;
import stan.presenter.mafia.fragments.constructor.role.ChangeNameAndDescription;
import stan.presenter.mafia.fragments.constructor.role.ChangeTeam;
import stan.presenter.mafia.fragments.constructor.role.ChangeSide;
import stan.presenter.mafia.fragments.constructor.role.ChangeTypeGroup;
import stan.presenter.mafia.fragments.constructor.role.ChangeVisibleRole;
import stan.presenter.mafia.fragments.constructor.role.ConstructorRoleResult;
import stan.presenter.mafia.fragments.transaction.ConstructorTransaction;
import stan.presenter.mafia.fragments.transaction.FragmentTransactionPattern;

public class ConstructorRole
        extends MafiaActivity
        implements ChangeSide.IChangeSideClick,
        ChangeTypeGroup.IChangeTypeGroupClick,
        ChangeTeam.IChangeCommandClick,
        ChangeVisibleRole.IChangeVisibleRoleClick,
        ChangeActions.IChangeActionsClick,
        ChangeNameAndDescription.IChangeNameNDescrClick,
        ConstructorRoleResult.IRoleResultClick
{
    public static final int request = 105;
    public static final int result = 0;
    public static final int result_new_role = 1;
    public static final int result_cancel = 2;
    public static final int result_error = 3;

    //__________FRAGMENTS
    private ChangeSide constructorChangeSide;
    private ChangeTypeGroup constructorChangeTypeGroup;
    private ChangeTeam constructorChangeTeam;
    private ChangeVisibleRole constructorChangeVisibleRole;
    private ChangeActions constructorChangeActions;
    private ChangeNameAndDescription constructorChangeNameNDescr;
    private ConstructorRoleResult constructorRoleResult;

    //______________Views
    private TextView lableConstructor;
    private TextView constructorWhyText;
    private Button constructorNext;
    private Button constructorBack;
    private Button constructorCancel;
    private LinearLayout constructorFooter;

    //
    String name;
    String descr;
    Role.TypeVisibility tv;
    TypeGroup tg;
    Team cmd;
    List<RoleForRole> rls;
    List<ActionForRole> act;

    public static class RoleForRole
    {
        private boolean check = false;
        public boolean isChecked()
        {
            return check;
        }
        public boolean changeChecked()
        {
            check = !check;
            return check;
        }
        public String id;
        public String name;
        public String description;
    }
    public static class ActionForRole
    {
        private boolean check = false;
        public boolean isChecked()
        {
            return check;
        }
        public boolean changeChecked()
        {
            check = !check;
            return check;
        }
        public String id;
        public String name;
        public String description;
        public Restrictions restrictions;
    }

    public ConstructorRole()
    {
        super(R.layout.constructor, R.id.constructorframe);
    }

    @Override
    protected FragmentTransactionPattern setFragmentTransactionPattern()
    {
        return new ConstructorTransaction(this, getFrameView());
    }

    @Override
    public void initFragments()
    {
        constructorChangeSide = new ChangeSide();
        constructorChangeTypeGroup = new ChangeTypeGroup();
        ChangeTypeGroup.setTypeGroups(this);
        constructorChangeTeam = new ChangeTeam();
        constructorChangeVisibleRole = new ChangeVisibleRole();
        constructorChangeActions = new ChangeActions();
        constructorChangeNameNDescr = new ChangeNameAndDescription();
        constructorRoleResult = new ConstructorRoleResult();
        addFragment(constructorChangeSide);
        stateChange(constructorChangeSide);
    }

    @Override
    public void initViews()
    {
        lableConstructor = (TextView) findViewById(R.id.lableConstructor);
        lableConstructor.setText(R.string.constructor);
        constructorWhyText = (TextView) findViewById(R.id.constructorWhyText);
        //        constructorWhyText.setVisibility(View.GONE);
        constructorNext = (Button) findViewById(R.id.constructorNext);
        //        constructorFooter = (LinearLayout) findViewById(R.id.constructorFooter);
        //        constructorFooter.setVisibility(View.GONE);
        constructorBack = (Button) findViewById(R.id.constructorBack);
        constructorCancel = (Button) findViewById(R.id.constructorCancel);
    }

    @Override
    public View getViewNext()
    {
        return constructorNext;
    }

    @Override
    public void back(ConstructorFragment from)
    {
        stateChange(from);
    }

    @Override
    public void sideNext(boolean peace_side)
    {
        if(peace_side)
        {
            tv = Role.TypeVisibility.peace;
        }
        else
        {
            tv = Role.TypeVisibility.mafia;
        }
        addFragmentWithHideTag(constructorChangeTypeGroup);
    }

    @Override
    public void typeGroupNext(TypeGroup tg)
    {
        this.tg = tg;
        addFragmentWithHideTag(constructorChangeTeam);
    }

    @Override
    public void teamNext(Team team)
    {
        cmd = team;
        addFragmentWithHideTag(constructorChangeVisibleRole);
    }

    @Override
    public void visibleRoleNext(List<RoleForRole> r)
    {
        rls = r;
        addFragmentWithHideTag(constructorChangeActions);
    }

    @Override
    public void getActionIDs(List<ActionForRole> actions)
    {
        act = actions;
        addFragmentWithHideTag(constructorChangeNameNDescr);
    }

    @Override
    public void getNameAndDescription(String n, String d)
    {
        name = n;
        descr = d;
        constructorRoleResult.setRoleBits(name, descr, tv, tg, cmd, rls, act);
        addFragmentWithHideTag(constructorRoleResult);
    }

    @Override
    public void saveRole(Role r)
    {
        insertNewRole(r);
        setResult(result_new_role);
        finish();
    }
    private void insertNewRole(Role r)
    {
        DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_ROLE), ContentDriver.getContentValues(r));
        for(int i=0; i< act.size(); i++)
        {
            int s = 0;
            if(act.get(i).restrictions.canSelfie())
            {
                s = 1;
            }
            int v = 0;
            if(act.get(i).restrictions.canVisibles())
            {
                v = 1;
            }
            DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_ROLES_ACTIONS),
                    ContentDriver.getContentValuesFRolesActions(r.UID, act.get(i).id, s, v));
        }
        for(int i=0; i< rls.size(); i++)
        {
            DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_VISIBLE_ROLES),
                    ContentDriver.getContentValuesFRolesVisiblesRoles(r.UID, rls.get(i).id));
        }
    }

    private void addFragmentWithHideTag(Fragment f, String tag)
    {
        getConstructorTransaction().addHideTag(f, tag);
    }

    private void addFragmentWithHideTag(ConstructorFragment f)
    {
        addFragmentWithHideTag(f, f.getFragmentTag());
        stateChange(f);
    }

    private ConstructorTransaction getConstructorTransaction()
    {
        return ((ConstructorTransaction) getFragmentTransaction());
    }

    private void stateChange(ConstructorFragment cf)
    {
        lableConstructor.setText(cf.getLabelString());
    }
    private void stateChangeOld(MafiaFragment mf)
    {
        if(mf instanceof ChangeSide)
        {
            lableConstructor.setText(R.string.constructor_role_side);
        } else if(mf instanceof ChangeTypeGroup)
        {
            lableConstructor.setText(R.string.constructor_role_typegroup);
        } else if(mf instanceof ChangeTeam)
        {
            lableConstructor.setText(R.string.constructor_role_command);
        } else if(mf instanceof ChangeVisibleRole)
        {
            lableConstructor.setText(R.string.constructor_role_visiblerole);
        } else if(mf instanceof ChangeActions)
        {
            lableConstructor.setText(R.string.constructor_role_actions);
        } else if(mf instanceof ChangeNameAndDescription)
        {
            lableConstructor.setText(R.string.constructor_role_name_n_descr);
        } else
        {
        }

        //        if(mf.equals(constructorChangeSide))
        //        {
        //            lableConstructor.setText(R.string.constructor_role_side);
        //        }
        //        else if(mf.equals(constructorChangeTypeGroup))
        //        {
        //            lableConstructor.setText(R.string.constructor_role_typegroup);
        //        }
        //        else if(mf.equals(constructorChangeTeam))
        //        {
        //            lableConstructor.setText(R.string.constructor_role_command);
        //        }
    }

    public void constructorBack(View view)
    {
        onBackPressed();
    }

    public void constructorCancel(View view)
    {
        finish();
    }
}