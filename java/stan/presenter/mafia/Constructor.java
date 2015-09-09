package stan.presenter.mafia;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stan.presenter.core.Action;
import stan.presenter.core.role.Command;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.fragments.constructor.ConstructorAction;
import stan.presenter.mafia.fragments.constructor.ConstructorMenu;
import stan.presenter.mafia.fragments.constructor.role.ChangeCommand;
import stan.presenter.mafia.fragments.constructor.role.ChangeSide;
import stan.presenter.mafia.fragments.constructor.role.ChangeTypeGroup;
import stan.presenter.mafia.fragments.constructor.role.ConstructorRole;

public class Constructor
        extends MafiaActivity
        implements ConstructorMenu.IConstructorMenuClick, ConstructorAction.IConstructorActionClick, ConstructorRole.IConstructorRoleClick, ChangeSide.IChangeSideClick, ChangeTypeGroup.IChangeTypeGroupClick, ChangeCommand.IChangeCommandClick
{
    //__________FRAGMENTS
    private ConstructorMenu constructorMenu;
    private ConstructorRole constructorRole;
    private ChangeSide constructorChangeSide;
    private ChangeTypeGroup constructorChangeTypeGroup;
    private ChangeCommand constructorChangeCommand;
    private ConstructorAction constructorAction;

    //______________Views
    private TextView lableConstructor;
    private TextView constructorWhyText;

    public Constructor()
    {
        super(R.layout.constructor, R.id.constructorframe);
    }

    @Override
    public void initFragments()
    {
        constructorMenu = new ConstructorMenu();
        constructorRole = new ConstructorRole();
        constructorAction = new ConstructorAction();
        constructorChangeSide = new ChangeSide();
        constructorChangeTypeGroup = new ChangeTypeGroup();
        constructorChangeCommand = new ChangeCommand();
        addFragment(constructorMenu);
//        addFragmentWithTag(constructorMenu);
    }

    @Override
    public void initViews()
    {
        lableConstructor = (TextView) findViewById(R.id.lableConstructor);
        lableConstructor.setText(R.string.constructor);
        constructorWhyText = (TextView) findViewById(R.id.constructorWhyText);
        constructorWhyText.setVisibility(View.GONE);
    }

    @Override
    public void toRole()
    {
        lableConstructor.setText(R.string.constructor_role);
        addFragmentWithTag(constructorRole);
    }

    @Override
    public void toAction()
    {
        lableConstructor.setText(R.string.constructor_actions);
        addFragmentWithTag(constructorAction);
    }

    @Override
    public void backFromConstructorMenu()
    {
        finish();
    }

    @Override
    public void saveNewAction(Action r)
    {

    }

    @Override
    public void addRole()
    {
        constructorWhyText.setVisibility(View.VISIBLE);
        lableConstructor.setText(R.string.constructor_role_side);
        addFragmentWithTag(constructorChangeSide);
    }

    @Override
    public void peaceSide()
    {

    }

    @Override
    public void mafiaSide()
    {

    }

    @Override
    public void sideNext(boolean peace_side)
    {
        lableConstructor.setText(R.string.constructor_role_typegroup);
        addFragmentWithTag(constructorChangeTypeGroup);
    }

    @Override
    public void typeGroupNext(TypeGroup tg)
    {
        lableConstructor.setText(R.string.constructor_role_command);
        addFragmentWithTag(constructorChangeCommand);
    }

    @Override
    public void commandNext(Command command)
    {

    }
}