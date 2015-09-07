package stan.presenter.mafia.fragments.constructor;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import stan.presenter.core.Action;
import stan.presenter.core.Role;
import stan.presenter.core.ability.Ability;
import stan.presenter.mafia.R;

public class ConstructorRole
        extends ConstructorFragment
{
    public interface IConstructorRoleClick
            extends IMafiaFragmentClick
    {
        void saveNewRole(Role r);
    }

    //______________Views
    private EditText nameRole;
    private EditText descriptionRole;
    private RadioButton mafiaRadio;

    public ConstructorRole()
    {
        super(R.layout.constructor_role, "constructorrole");
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        nameRole = (EditText) v.findViewById(R.id.nameRole);
        descriptionRole = (EditText) v.findViewById(R.id.descrRole);
        mafiaRadio = (RadioButton) v.findViewById(R.id.radioMafia);
        v.findViewById(R.id.constructorrolesave).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Role r = checkRole();
                if(r != null)
                {
                    ((IConstructorRoleClick) clickListener).saveNewRole(r);
                }
            }
        });
    }

    private String getName()
    {
        String name;
        if(nameRole == null)
        {
            return null;
        }
        name = nameRole.getText().toString();
        if(name.length() <= minNameLenght || name.length() > maxNameLenght)
        {
            return null;
        }
        return name;
    }
    private String getDescription()
    {
        String description;
        if(descriptionRole == null)
        {
            return null;
        }
        description = descriptionRole.getText().toString();
        if(description.length() <= minDescrLenght || description.length() > maxDescrLenght)
        {
            return null;
        }
        return description;
    }
    private Role.TypeVisibility getTypeVisibility()
    {
        Role.TypeVisibility tv;
        if(mafiaRadio.isChecked())
        {
            tv = Role.TypeVisibility.mafia;
        }
        else
        {
            tv = Role.TypeVisibility.peace;
        }
        return tv;
    }
    private Role.TypeRole getTypeRole()
    {
        Role.TypeRole tr = null;
        return tr;
    }
    private Action[] getActions()
    {
        String name = "Посадить в тюрьму";
        String description = "Игрока, которого посадили в тюрьму, нельзя убить, но и он не выполняет никаких действий";
        List<Ability> curentAbilities = new ArrayList<>();
        curentAbilities.add(new Ability("1"));
        curentAbilities.add(new Ability("2"));
        Ability[] abilities = new Ability[curentAbilities.size()];
        curentAbilities.toArray(abilities);
        Action a = new Action(name, description, false, abilities);
        return new Action[]{a};
    }
    private Role checkRole()
    {
//        String name = "Коммисар";
//        String description = "Сажает в тюрьму или убивает подозреваемого";
        String name = getName();
        if(name == null)
        {
            return null;
        }
        String description = getDescription();
        if(description == null)
        {
            return null;
        }
        Role.TypeVisibility tv = getTypeVisibility();
        if(tv == null)
        {
            return null;
        }
        Role.TypeRole tr = getTypeRole();
        if(tv == null)
        {
            return null;
        }
        Action[] acts = getActions();
        if(acts == null)
        {
            return null;
        }
        Role r = new Role(name, description, tv, tr, acts);
        return null;
    }
}
