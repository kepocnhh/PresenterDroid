package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import stan.presenter.core.role.Command;
import stan.presenter.mafia.Night;
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
    private Button good;
    private Button bad;
    private Button neutral;

    Command command;
    Command commandGood;
    Command commandBad;
    Command commandNeutral;

    public ChangeCommand()
    {
        super(R.layout.constructor_role_command, R.string.ChangeCommand);
        commandGood = new Command(getActivity().getResources().getString(R.string.commandGood));
        commandBad = new Command(getActivity().getResources().getString(R.string.commandBad));
        commandNeutral = new Command(getActivity().getResources().getString(R.string.commandNeutral));
        setGood();
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        good = (Button) v.findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setGood();
            }
        });
        bad = (Button) v.findViewById(R.id.bad);
        bad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setBad();
            }
        });
        neutral = (Button) v.findViewById(R.id.neutral);
        neutral.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setNeutral();
            }
        });
    }

    private void setNeutral()
    {
        command = commandNeutral;
    }

    private void setBad()
    {
        command = commandBad;
    }

    private void setGood()
    {
        command = commandGood;
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