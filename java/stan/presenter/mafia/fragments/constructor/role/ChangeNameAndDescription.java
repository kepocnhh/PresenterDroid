package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;

import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeNameAndDescription
        extends ConstructorFragment
{

    public interface IChangeNameNDescrClick
            extends ConstructorFragment.IConstructorClick
    {
        void getNameAndDescription(String n, String d);
    }

    //______________Views

    public ChangeNameAndDescription()
    {
        super(R.layout.constructor_role_name_n_descr, R.string.ChangeNameAndDescription);
    }

    @Override
    protected int setWhyString()
    {
        return 0;
    }

    @Override
    protected int setLabelString()
    {
        return R.string.constructor_role_name_n_descr;
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IChangeNameNDescrClick) clickListener).getNameAndDescription(null, null);
            }
        };
    }
}
