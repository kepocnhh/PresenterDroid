package stan.presenter.mafia;

import stan.presenter.core.Action;
import stan.presenter.core.role.Role;
import stan.presenter.mafia.fragments.constructor.ConstructorAction;
import stan.presenter.mafia.fragments.constructor.ConstructorMenu;
import stan.presenter.mafia.fragments.constructor.ConstructorRole;

public class Constructor
        extends MafiaActivity
        implements ConstructorMenu.IConstructorMenuClick, ConstructorAction.IConstructorActionClick, ConstructorRole.IConstructorRoleClick
{
    //__________FRAGMENTS
    private ConstructorMenu constructorMenu;
    private ConstructorRole constructorRole;
    private ConstructorAction constructorAction;

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
        addFragment(constructorMenu);
//        addFragmentWithTag(constructorMenu);
    }

    @Override
    public void initViews()
    {

    }

    @Override
    public void toRole()
    {
        addFragmentWithTag(constructorRole);
    }

    @Override
    public void toAction()
    {
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
    public void saveNewRole(Role r)
    {

    }
}