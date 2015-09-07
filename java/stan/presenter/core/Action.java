package stan.presenter.core;

import java.util.List;

import stan.presenter.core.ability.Ability;
import stan.presenter.core.player.Player;

public class Action
        extends Mafia
{
    public Ability[] abilities;//
    public String description;
    public boolean selfie;//возможность действовать на себя
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
        init(d, false, -1, -1, null);
    }
    public Action(String n, String d, boolean s, Ability[] abs)
    {
        super(n);
        init(d, s, -1, -1, abs);
    }
    private Action(String n, String d, boolean s, int t, int f, Ability[] abs)
    {
        super(n);
        init(d, s, t, f, abs);
    }

    private void init(String d, boolean s, int t, int f, Ability[] abs)
    {
        this.description = d;
        this.selfie = s;
        this.to = t;
        this.from = f;
        this.abilities = abs;
    }
    public Action clone()
    {
        return new Action(this.name,this.description,this.selfie,this.to,this.from,this.abilities);
    }
}