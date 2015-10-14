package stan.presenter.core.role;

import stan.presenter.core.action.Action;
import stan.presenter.core.Mafia;
import stan.presenter.core.role.typegrouprole.TypeGroup;

public class Role
        extends Mafia
{
    public Role[] visibleRoles;//
    public Action[] actions;//
    public String description;

    public Action getCurrentAction()
    {
        if(actions == null)
        {
            return null;
        }
        for(Action anAct : actions)
        {
            if(anAct.from != -1)
            {
                return anAct;
            }
        }
        return null;
    }
    //
    public enum TypeVisibility//тип видимости для ролей со способностью проверки
    {
        mafia,
        peace
    }
    private TypeVisibility TVisibility;
    public TypeVisibility getTypeVisibility()
    {
        return this.TVisibility;
    }
    public void setTypeVisibility(TypeVisibility tv)
    {
        this.TVisibility = tv;
    }
    private TypeGroup typeGroup;
    public TypeGroup getTypeGroupRole()
    {
        return this.typeGroup;
    }
    public void setTypeGroupRole(TypeGroup tg)
    {
        this.typeGroup = tg;
    }
    private Team team;
    public Team getTeam()
    {
        return this.team;
    }
    public void setTeam(Team t)
    {
        this.team = t;
    }

    public Role(String name, String d)
    {
        super(name);
        init(d, null, null, null, null, null);
    }
    public Role(String name, String d, TypeVisibility tv, TypeGroup tg, Team cmd, Role[] rls, Action[] act)
    {
        super(name);
        init(d, tv, tg, cmd, rls, act);
    }
//    public Role clone()
//    {
//        Action[] new_act = null;
//        if(actions != null)
//        {
//            new_act = new Action[this.actions.length];
//            for(int i=0;i<this.actions.length;i++)
//            {
//                new_act[i] = this.actions[i].clone();
//            }
//        }
//        return new Role(this.name, this.description, this.getTypeVisibility(), this.getTypeGroupRole(), this.getTeam(), new_act);
//    }
    private void init(String d, TypeVisibility tv, TypeGroup tg, Team cmd, Role[] rls, Action[] act)
    {
        this.description = d;
        this.TVisibility = tv;
        this.typeGroup = tg;
        this.team = cmd;
        this.visibleRoles = rls;
        this.actions = act;
    }
}