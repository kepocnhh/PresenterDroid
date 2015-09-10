package stan.presenter.mafia.fragments.constructor.role;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stan.presenter.core.role.typegrouprole.IndividualsGroup;
import stan.presenter.core.role.typegrouprole.TypeGroup;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorFragment;

public class ChangeTypeGroup
        extends ConstructorFragment
{

    public interface IChangeTypeGroupClick
            extends IConstructorClick
    {
        void typeGroupNext(TypeGroup tg);
    }

    //______________Views
    private Button individuals;
    private Button organized;
    private Button clan;
    private Button sect;
    private TextView constructorWhatText;
    private TextView constructorWhatDescription;

    private TypeGroup typeGroup;

    public ChangeTypeGroup()
    {
        super(R.layout.constructor_role_typegroup, "constructor_role_typegroup");
        typeGroup = new IndividualsGroup.Individuals();
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        individuals = (Button) v.findViewById(R.id.individuals);
        individuals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                individuals();
            }
        });
        constructorWhatText = (TextView) v.findViewById(R.id.constructorWhatText);
        constructorWhatDescription = (TextView) v.findViewById(R.id.constructorWhatDescription);
    }

    @Override
    protected View.OnClickListener setNextClickListener()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IChangeTypeGroupClick) clickListener).typeGroupNext(typeGroup);
            }
        };
    }

    private void individuals()
    {
        typeGroup = new IndividualsGroup.Individuals();
        constructorWhatText.setText(typeGroup.name);
        constructorWhatDescription.setText(typeGroup.getDescription());
    }
}