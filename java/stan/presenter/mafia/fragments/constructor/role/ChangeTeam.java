package stan.presenter.mafia.fragments.constructor.role;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import stan.presenter.core.role.Team;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeTeam
        extends ConstructorFragment
{
    public interface IChangeCommandClick
            extends IConstructorClick
    {
        void teamNext(Team team);
    }

    //______________Views
    private Button good;
    private Button bad;
    private Button neutral;

    Team team;
    Team teamGood;
    Team teamBad;
    Team teamNeutral;

    public ChangeTeam()
    {
        super(R.layout.constructor_role_command, R.string.ChangeCommand);
        setGood();
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        teamGood = new Team(getActivity().getResources().getString(R.string.teamGood),
                getActivity().getResources().getString(R.string.teamGoodDescription));
        teamGood.UID = "0";
        teamBad = new Team(getActivity().getResources().getString(R.string.teamBad),
                getActivity().getResources().getString(R.string.teamBadDescription));
        teamBad.UID = "1";
        teamNeutral = new Team(getActivity().getResources().getString(R.string.teamNeutral),
                getActivity().getResources().getString(R.string.teamNeutralDescription));
        teamNeutral.UID = "2";
    }

    @Override
    protected int setWhyString()
    {
        return 0;
    }

    @Override
    protected int setLabelString()
    {
        return R.string.constructor_role_command;
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        setGood();
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
        team = teamNeutral;
    }

    private void setBad()
    {
        team = teamBad;
    }

    private void setGood()
    {
        team = teamGood;
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeCommandClick) clickListener).teamNext(team);
            }
        };
    }
}