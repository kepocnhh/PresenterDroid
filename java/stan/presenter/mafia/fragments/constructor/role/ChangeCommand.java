package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import stan.presenter.core.role.Command;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeCommand
        extends ConstructorFragment
{
    public interface IChangeCommandClick
            extends IConstructorClick
    {
        void commandNext(Command command);
    }

    //______________Views
    private ListView listRoles;
    private Button constructorNext;

    Command command;

    public ChangeCommand()
    {
        super(R.layout.constructor_role_command, R.string.ChangeCommand);
        command = new Command("Город");
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        constructorNext = (Button) v.findViewById(R.id.constructorNext);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeCommandClick) clickListener).commandNext(command);
            }
        };
    }
}