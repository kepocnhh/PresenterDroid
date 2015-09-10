package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeSide
        extends ConstructorFragment
{
    public interface IChangeSideClick
            extends IConstructorClick
    {
        void sideNext(boolean peace_side);
    }

    //______________Views
    private Button peaceSide;
    private Button mafiaSide;
    private TextView constructorWhatText;

    private boolean peace_side = true;

    public ChangeSide()
    {
        super(R.layout.constructor_role_side, R.string.ChangeSide);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        peaceSide = (Button) v.findViewById(R.id.peaceSide);
        peaceSide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                peaceSide();
            }
        });
        mafiaSide = (Button) v.findViewById(R.id.mafiaSide);
        mafiaSide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mafiaSide();
            }
        });
        constructorWhatText = (TextView) v.findViewById(R.id.constructorWhatText);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeSideClick) clickListener).sideNext(peace_side);
            }
        };
    }

    private void peaceSide()
    {
        peace_side = true;
        constructorWhatText.setText(R.string.peace);
    }

    private void mafiaSide()
    {
        peace_side = false;
        constructorWhatText.setText(R.string.mafia);
    }
}