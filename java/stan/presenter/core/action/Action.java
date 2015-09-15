package stan.presenter.core.action;

import stan.presenter.core.Mafia;
import stan.presenter.core.ability.Ability;
import stan.presenter.core.player.Player;

public class Action
        extends Mafia
{
    public Ability[] abilities;//
    public String description;
    private Restrictions restrictions = new Restrictions();//ограничения
    public void setRestrictions(Restrictions r)
    {
        restrictions = r;
    }
    public Restrictions getRestrictions()
    {
        return this.restrictions;
    }
//    public boolean selfie;//возможность действовать на себя
    public int to;
    public int from;

    public void clear()
    {
        this.from = -1;
        this.to = -1;
    }

    public Player engage(Player p)
    {
        //TODO remake engage method for new other actions
        if(abilities == null || abilities.length==0)
        {
            return p;
        }
        for(int i=0; i<abilities.length; i++)
        {
//            p = abilities[i].engage(p);
        }
        return p;
    }

    /**
     *          for role without actions
     * @param n name action
     * @param d descriptions action
     */
    public Action(String n, String d)
    {
        super(n);
        init(d, null, -1, -1, null);
    }
    public Action(String n, String d, Restrictions r, Ability[] abs)
    {
        super(n);
        init(d, r, -1, -1, abs);
    }
    private Action(String n, String d, Restrictions r, int t, int f, Ability[] abs)
    {
        super(n);
        init(d, r, t, f, abs);
    }

    private void init(String d, Restrictions r, int t, int f, Ability[] abs)
    {
        this.description = d;
        this.restrictions = r;
        this.to = t;
        this.from = f;
        this.abilities = abs;
    }
    public Action clone()
    {
        return new Action(this.name,this.description,this.restrictions,this.to,this.from,this.abilities);
    }
}