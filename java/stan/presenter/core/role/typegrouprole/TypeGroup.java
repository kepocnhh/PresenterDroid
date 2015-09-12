package stan.presenter.core.role.typegrouprole;

import stan.presenter.core.Mafia;

public abstract class TypeGroup
    extends Mafia
{
    private final String TAG;
    public String getTAG()
    {
        return TAG;
    }
    //
    boolean visible_in_group;
    private String description;
    public String getDescription()
    {
        return this.description;
    }

    public TypeGroup(String n, String d, String t,boolean vig)
    {
        super(n);
        this.description = d;
        this.visible_in_group = vig;
        this.TAG = t;
    }
}