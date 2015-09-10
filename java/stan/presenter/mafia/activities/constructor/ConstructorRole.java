package stan.presenter.mafia.activities.constructor;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import stan.presenter.core.role.Command;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.MafiaActivity;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.MafiaFragment;
import stan.presenter.mafia.fragments.constructor.role.ChangeCommand;
import stan.presenter.mafia.fragments.constructor.role.ChangeSide;
import stan.presenter.mafia.fragments.constructor.role.ChangeTypeGroup;
import stan.presenter.mafia.fragments.transaction.ConstructorTransaction;
import stan.presenter.mafia.fragments.transaction.FragmentTransactionPattern;

public class ConstructorRole
        extends MafiaActivity
    implements ChangeSide.IChangeSideClick, ChangeTypeGroup.IChangeTypeGroupClick, ChangeCommand.IChangeCommandClick
{
    //__________FRAGMENTS
    private ChangeSide constructorChangeSide;
    private ChangeTypeGroup constructorChangeTypeGroup;
    private ChangeCommand constructorChangeCommand;

    //______________Views
    private TextView lableConstructor;
    private TextView constructorWhyText;
    private Button constructorNext;
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
        super.onBackPressed();
    }

    @Override
    public void initFragments()
    {
        constructorChangeSide = new ChangeSide();
        constructorChangeTypeGroup = new ChangeTypeGroup();
        constructorChangeCommand = new ChangeCommand();
        addFragment(constructorChangeSide);
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
    }

    @Override
    public View getViewNext()
    {
        return constructorNext;
    }

    @Override
    public void sideNext(boolean peace_side)
    {
        lableConstructor.setText(R.string.constructor_role_typegroup);
        addFragmentWithHideTag(constructorChangeTypeGroup);
    }

    @Override
    public void typeGroupNext(TypeGroup tg)
    {
        lableConstructor.setText(R.string.constructor_role_command);
        addFragmentWithHideTag(constructorChangeCommand);
    }

    @Override
    public void commandNext(Command command)
    {
    }

    public void addFragmentWithHideTag(Fragment f, String tag)
    {
        ((ConstructorTransaction)getFragmentTransaction()).addHideTag(f, tag);
    }
    public void addFragmentWithHideTag(MafiaFragment f)
    {
        addFragmentWithHideTag(f, f.getFragmentTag());
    }
}