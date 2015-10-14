package stan.presenter.mafia.activities.constructor;

import android.content.Intent;
import android.widget.TextView;

import stan.presenter.core.action.Action;
import stan.presenter.mafia.activities.MafiaActivity;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.menu.ConstructorAction;
import stan.presenter.mafia.fragments.constructor.menu.ConstructorMenu;
import stan.presenter.mafia.fragments.constructor.menu.ConstructorRoleList;

public class Constructor
        extends MafiaActivity
        implements ConstructorMenu.IConstructorMenuClick, ConstructorAction.IConstructorActionClick, ConstructorRoleList.IConstructorRoleClick
{
    //__________FRAGMENTS
    private ConstructorMenu constructorMenu;
    private ConstructorRoleList constructorRoleList;
    private ConstructorAction constructorAction;

    //______________Views
    private TextView lableConstructor;

    public Constructor()
    {
        super(R.layout.constructor_main, R.id.constructorframe);
    }

    @Override
    public void onActivityResult(int req, int res, Intent data)
    {
        super.onActivityResult(req, res, data);
        if(req == ConstructorRole.request)
        {
            if(res == ConstructorRole.result_new_role)
            {
                constructorRoleList.updateData();
            }
        }
    }

    @Override
    public void initFragments()
    {
        constructorMenu = new ConstructorMenu();
        constructorRoleList = new ConstructorRoleList();
        constructorAction = new ConstructorAction();
        addFragment(constructorMenu);
    }

    @Override
    public void initViews()
    {
        lableConstructor = (TextView) findViewById(R.id.lableConstructor);
        lableConstructor.setText(R.string.constructor);
    }

    @Override
    public void toRole()
    {
        lableConstructor.setText(R.string.constructor_role);
        addFragmentWithTag(constructorRoleList);
    }

    @Override
    public void toAction()
    {
        lableConstructor.setText(R.string.constructor_actions);
        addFragmentWithTag(constructorAction);
    }

    @Override
    public void saveNewAction(Action r)
    {

    }

    @Override
    public void addRole()
    {
        startActivityForResult(new Intent(this, ConstructorRole.class), ConstructorRole.request);
    }

    @Override
    public void editRole(String id)
    {
        Intent intent = new Intent(this, ConstructorRole.class);
        intent.putExtra(ConstructorRole.ID_ROLE, id);
        startActivityForResult(intent, ConstructorRole.request);
    }

    @Override
    public void deleteRole(String id)
    {

    }
}