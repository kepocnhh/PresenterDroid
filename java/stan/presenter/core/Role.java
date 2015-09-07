package stan.presenter.core;

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
    public enum TypeRole//тип отнесения роли к определенной команде
    {
        mafia,
        peace,
        neutral;
    }
    private TypeVisibility TVisibility;
    private TypeRole TRole;
    public TypeVisibility getTypeVisibility()
    {
        return this.TVisibility;
    }
    public TypeRole getTypeRole()
    {
        return this.TRole;
    }
    //
    public boolean rang_shot;//возможность стрелять при разделении на ранги
    public int rang;//степень ранга ( 1 - главный / 5 - второстепенный / -1 - нет ранга)
    //
//    public TypeVisibility Get_TV()
//    {
//        return this.TV;
//    }
//    public TypeRole Get_TR()
//    {
//        return this.TR;
//    }
//    public void Set_TV(TypeVisibility tv)
//    {
//        this.TV = tv;
//    }
//    public void Set_TR(TypeRole tr)
//    {
//        this.TR = tr;
//    }
//    public Role clone(String n, int r)
//    {
//        Action[] new_act = new Action[this.act.length];
//        for(int i=0;i<this.act.length;i++)
//        {
//            new_act[i] = this.act[i].clone();
//        }
//        return new Role(n, this.TR, this.TV, new_act, this.rang_shot, r, this.UI);
//    }
//    public Role clone(String n, Action[] a)
//    {
//        return new Role(n, this.TR, this.TV, a, this.rang_shot, this.rang, this.UI);
//    }
//    public Role clone()
//    {
//        Action[] new_act = new Action[this.act.length];
//        for(int i=0;i<this.act.length;i++)
//        {
//            new_act[i] = this.act[i].clone();
//        }
//        return new Role(this.name, this.TR, this.TV, new_act, this.rang_shot, this.rang, this.UI);
//    }
    //
    
//    private void set_config(Role.TypeRole tr, Role.TypeVisibility tv, int ui)
//    {
//        this.TR = tr;
//        this.TV = tv;
//        this.UI = ui;
//    }
//    private void set_rang(boolean rs, int r)
//    {
//        this.rang_shot = rs;
//        this.rang = r;
//    }
//    public Role(String n, Role.TypeRole tr, Role.TypeVisibility tv, Action[] a, boolean rs, int r, int ui)
//    {
//        super(n);
//        set_config(tr,tv,ui);
//        set_rang(rs,r);
//        this.act = a;
//    }
//    public Role(String n, Role.TypeRole tr, Role.TypeVisibility tv, Action a, boolean rs, int r, int ui)
//    {
//        super(n);
//        set_config(tr, tv, ui);
//        set_rang(rs,r);
//        this.act = new Action[]{a};
//    }
//    public Role(String n, Role.TypeRole tr, Role.TypeVisibility tv, Action a)
//    {
//        super(n);
//        set_config(tr,tv,-1);
//        set_rang(false,-1);
//        this.act = new Action[]{a};
//    }
//    public Role(String n, Role.TypeRole tr, Role.TypeVisibility tv)
//    {
//        super(n);
//        set_config(tr,tv,-1);
//        set_rang(false,-1);
//        this.act = null;
//    }

    public Role(String name, String d, boolean rs, int r, TypeVisibility tv, TypeRole tr, Action[] act)
    {
        super(name);
        init(d, rs, r, tv, tr, act);
    }
    public Role(String name, String d, TypeVisibility tv, TypeRole tr, Action[] act)
    {
        super(name);
        init(d, false, -1, tv, tr, act);
    }
    public Role(String name, String d, TypeVisibility tv, TypeRole tr, Action act)
    {
        super(name);
        Action[] acts = new Action[]{act};
        init(d, false, -1, tv, tr, acts);
    }
    public Role(String name, String d, TypeVisibility tv, TypeRole tr)
    {
        super(name);
        init(d, false, -1, tv, tr, null);
    }
    public Role clone()
    {
        Action[] new_act = null;
        if(actions != null)
        {
            new_act = new Action[this.actions.length];
            for(int i=0;i<this.actions.length;i++)
            {
                new_act[i] = this.actions[i].clone();
            }
        }
        return new Role(this.name, this.description, this.rang_shot, this.rang, this.TVisibility, this.TRole, new_act);
    }
    private void init(String d, boolean rs, int r, TypeVisibility tv, TypeRole tr, Action[] act)
    {
        this.description = d;
        this.rang_shot = rs;
        this.rang = r;
        this.TVisibility = tv;
        this.TRole = tr;
        this.actions = act;
    }
}