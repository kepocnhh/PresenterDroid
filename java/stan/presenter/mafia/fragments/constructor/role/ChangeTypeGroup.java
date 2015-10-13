package stan.presenter.mafia.fragments.constructor.role;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stan.presenter.core.role.typegrouprole.IndividualsGroup;
import stan.presenter.core.role.typegrouprole.RangGroup;
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

    private TypeGroup typeGroup = null;
    private static IndividualsGroup.Individuals individualsType;
    private static IndividualsGroup.Organized organizedType;
    private static RangGroup.Clan clanType;
    private static RangGroup.Sect sectType;
    public static void setTypeGroups(Context c)
    {
        individualsType = new IndividualsGroup.Individuals(c.getResources().getString(R.string.individuals),
                c.getResources().getString(R.string.individuals_descr));
        organizedType = new IndividualsGroup.Organized(c.getResources().getString(R.string.organized),
                c.getResources().getString(R.string.organized_descr));
        clanType = new RangGroup.Clan(c.getResources().getString(R.string.clan),
                c.getResources().getString(R.string.clan_descr));
        sectType = new RangGroup.Sect(c.getResources().getString(R.string.sect),
                c.getResources().getString(R.string.sect_descr));
    }

    public void setTypeGroup(TypeGroup tg)
    {
        typeGroup = tg;
    }

    public ChangeTypeGroup()
    {
        super(R.layout.constructor_role_typegroup, R.string.ChangeTypeGroup);
    }

    @Override
    protected int setWhyString()
    {
        return 0;
    }

    @Override
    protected int setLabelString()
    {
        return R.string.constructor_role_typegroup;
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
//        individuals = (Button) v.findViewById(R.id.individuals);
        v.findViewById(R.id.individuals).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                individuals();
            }
        });
        v.findViewById(R.id.organized).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                organized();
            }
        });
        v.findViewById(R.id.clan).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clan();
            }
        });
        v.findViewById(R.id.sect).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sect();
            }
        });
        constructorWhatText = (TextView) v.findViewById(R.id.constructorWhatText);
        constructorWhatDescription = (TextView) v.findViewById(R.id.constructorWhatDescription);
        if(typeGroup == null)
        {
            setTypeGroup(individualsType);
        }
        setNewTypeGroup();
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

    private void setNewTypeGroup()
    {
        constructorWhatText.setText(typeGroup.name);
        constructorWhatDescription.setText(typeGroup.getDescription());
    }
    private void setNewTypeGroup(TypeGroup tg)
    {
        setTypeGroup(tg);
        setNewTypeGroup();
    }
    private void individuals()
    {
        setNewTypeGroup(individualsType);
    }
    private void organized()
    {
        setNewTypeGroup(organizedType);
    }
    private void clan()
    {
        setNewTypeGroup(clanType);
    }
    private void sect()
    {
        setNewTypeGroup(sectType);
    }
}