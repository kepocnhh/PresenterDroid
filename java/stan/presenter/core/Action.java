package stan.presenter.core;

public class Action
        extends Mafia
{
    public Ability[] abilities;//
    public boolean selfie;//возможность действовать на себя
    public int to;
    public int from;
    public boolean try_stop;

    public Player engage(Player p)
    {
        if(abilities == null || abilities.length==0)
        {
            return p;
        }
        for(int i=0; i<abilities.length; i++)
        {
            p = abilities[i].engage(p);
        }
        return p;
    }
    //
    private void init(Ability[] abs, boolean ts, int t, int f, boolean s)
    {
        abilities = abs;
        try_stop = ts;
        to = t;
        from = f;
        selfie = s;
    }
    private void init(Ability[] abs, boolean ts, boolean s)
    {
        init(abs, ts, -1, -1, s);
    }
    private void init(Ability abs, boolean ts, int t, int f, boolean s)
    {
        abilities = new Ability[1];
        abilities[0] = abs;
        try_stop = ts;
        to = t;
        from = f;
        selfie = s;
    }
    public Action(String n, Ability[] abs)
    {
        super(n);
        init(abs, false, false);
    }
    private Action(String n, Ability[] abs, boolean ts, int t, int f, boolean s)
    {
        super(n);
        init(abs, ts, t, f, s);
    }
    private Action(String n, Ability abs, boolean ts, int t, int f, boolean s)
    {
        super(n);
        init(abs, ts, t, f, s);
    }
    //
    public Action clone()
    {
        return new Action(this.name, this.abilities, this.try_stop, this.to, this.from, this.selfie);
    }
}