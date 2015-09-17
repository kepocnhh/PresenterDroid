package stan.presenter.mafia.activities.constructor;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import stan.presenter.core.role.Team;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.activities.MafiaActivity;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.MafiaFragment;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;
import stan.presenter.mafia.fragments.constructor.role.ChangeActions;
import stan.presenter.mafia.fragments.constructor.role.ChangeTeam;
import stan.presenter.mafia.fragments.constructor.role.ChangeSide;
import stan.presenter.mafia.fragments.constructor.role.ChangeTypeGroup;
import stan.presenter.mafia.fragments.constructor.role.ChangeVisibleRole;
import stan.presenter.mafia.fragments.transaction.ConstructorTransaction;
import stan.presenter.mafia.fragments.transaction.FragmentTransactionPattern;

public class ConstructorRole
        extends MafiaActivity
        implements ChangeSide.IChangeSideClick, ChangeTypeGroup.IChangeTypeGroupClick, ChangeTeam.IChangeCommandClick, ChangeVisibleRole.IChangeVisibleRoleClick, ChangeActions.IChangeActionsClick
{
    //__________FRAGMENTS
    private ChangeSide constructorChangeSide;
    private ChangeTypeGroup constructorChangeTypeGroup;
    private ChangeTeam constructorChangeTeam;
    private ChangeVisibleRole constructorChangeVisibleRole;
    private ChangeActions constructorChangeActions;

    //______________Views
    private TextView lableConstructor;
    private TextView constructorWhyText;
    private Button constructorNext;
    private Button constructorBack;
    private Button constructorCancel;
    private LinearLayout constructorFooter;

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
    public void onBackPressed()
    {
        //        stateChange((MafiaFragment) getConstructorTransaction().getCurrentFragment());
        //
        //        String tag = ((MafiaFragment)getConstructorTransaction().getCurrentFragment()).getFragmentTag();
        //        if(tag == null)
        //        {
        //            super.onBackPressed();
        //            return;
        //        }
        //        if(tag.equals(ChangeSide.getFragmentTag()))
        //        {
        //
        //        }
        //        else if(tag.equals(ChangeTypeGroup.getFragmentTag()))
        //        {
        //
        //        }
        //        else if(tag.equals(ChangeTeam.getFragmentTag()))
        //        {
        //
        //        }
        super.onBackPressed();
    }

    @Override
    public void initFragments()
    {
        constructorChangeSide = new ChangeSide();
        constructorChangeTypeGroup = new ChangeTypeGroup();
        constructorChangeTeam = new ChangeTeam();
        constructorChangeVisibleRole = new ChangeVisibleRole();
        constructorChangeActions = new ChangeActions();
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
        addFragmentWithHideTag(constructorChangeTypeGroup);
    }

    @Override
    public void typeGroupNext(TypeGroup tg)
    {
        addFragmentWithHideTag(constructorChangeTeam);
    }

    @Override
    public void teamNext(Team team)
    {
        addFragmentWithHideTag(constructorChangeVisibleRole);
    }

    @Override
    public void visibleRoleNext()
    {
        addFragmentWithHideTag(constructorChangeActions);
    }

    private void addFragmentWithHideTag(Fragment f, String tag)
    {
        getConstructorTransaction().addHideTag(f, tag);
    }

    private void addFragmentWithHideTag(MafiaFragment f)
    {
        addFragmentWithHideTag(f, f.getFragmentTag());
        stateChange(f);
    }

    private ConstructorTransaction getConstructorTransaction()
    {
        return ((ConstructorTransaction) getFragmentTransaction());
    }

    private void stateChange(MafiaFragment mf)
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

    @Override
    public void getActionIDs(int[] actions)
    {

    }
}