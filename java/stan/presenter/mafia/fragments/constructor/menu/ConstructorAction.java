package stan.presenter.mafia.fragments.constructor.menu;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import stan.presenter.core.ability.active.changeproperty.Block;
import stan.presenter.core.ability.active.changeproperty.Kill;
import stan.presenter.core.action.Action;
import stan.presenter.core.ability.Ability;
import stan.presenter.core.action.Restrictions;
import stan.presenter.mafia.R;
import stan.presenter.mafia.fragments.constructor.ConstructorMenuFragment;

public class ConstructorAction
        extends ConstructorMenuFragment
{
    public interface IConstructorActionClick
            extends IMafiaFragmentClick
    {
        void saveNewAction(Action r);
    }

    //__________VIEWS
    private EditText nameAction;
    private EditText descriptionAction;
    private CheckBox selfieCheck;
    private Spinner listAbilities;
    private List<Ability> allAbilities;

    public ConstructorAction()
    {
        super(R.layout.constructor_action, R.string.ConstructorAction);
        //
        allAbilities = getAllAbilities();
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        nameAction = (EditText) v.findViewById(R.id.nameaction);
        descriptionAction = (EditText) v.findViewById(R.id.descriptionaction);
        listAbilities = (Spinner) v.findViewById(R.id.listabilities);
        selfieCheck = (CheckBox) v.findViewById(R.id.selfiecheck);
        v.findViewById(R.id.constructoractionsave).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Action a = checkAction();
                if(a != null)
                {
                    ((IConstructorActionClick) clickListener).saveNewAction(a);
                }
            }
        });
    }

    private List<Ability> getAllAbilities()
    {
        return null;
    }

    private Action checkAction()
    {
        String name = "Посадить в тюрьму";
        String description = "Игрока, которого посадили в тюрьму, нельзя убить, но и он не выполняет никаких действий";
//        boolean selfie = selfieCheck.isChecked();
        List<Ability> curentAbilities = new ArrayList<>();
        curentAbilities.add(new Kill(getActivity().getResources().getString(R.string.kill)));
        curentAbilities.add(new Block(getActivity().getResources().getString(R.string.block)));
        Ability[] abilities = new Ability[curentAbilities.size()];
        curentAbilities.toArray(abilities);
        Action a = new Action(name, description, null, abilities);
        return null;
    }
}
