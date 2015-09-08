package stan.presenter.core.role;

import stan.presenter.core.Action;
import stan.presenter.core.Mafia;
import stan.presenter.core.role.typegrouprole.TypeGroup;

public class Role
        extends Mafia
{
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
        peace;
    }
    private TypeVisibility TVisibility;
    public TypeVisibility getTypeVisibility()
    {
        return this.TVisibility;
    }
    private TypeGroup typeGroup;
    public TypeGroup getTypeGroupRole()
    {
        return this.typeGroup;
    }
    private Command command;
    public Command getCommand()
    {
        return this.command;
    }

    public Role(String name, String d, TypeVisibility tv, TypeGroup tg, Command cmd, Action[] act)
    {
        super(name);
        init(d, tv, tg, cmd, act);
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
//        return new Role(this.name, this.description, this.getTypeVisibility(), this.getTypeGroupRole(), this.getCommand(), new_act);
//    }
    private void init(String d, TypeVisibility tv, TypeGroup tg, Command cmd, Action[] act)
    {
        this.description = d;
        this.TVisibility = tv;
        this.typeGroup = tg;
        this.command = cmd;
        this.actions = act;
    }
}