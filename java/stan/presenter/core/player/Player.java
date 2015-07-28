package stan.presenter.core.player;

import stan.presenter.core.Mafia;
import stan.presenter.core.Role;

public class Player
        extends Mafia
{
    public Role role;
    public Properties tries;
    public Properties prop;
    public boolean bot;//бот или нет

    public Player(String n)
    {
        super(n);
        init(new Properties(), new Properties(), false);
    }
    public Player(String n, boolean b)
    {
        super(n);
        init(new Properties(), new Properties(), b);
    }
    private void init(Properties p, Properties t, boolean b)
    {
        this.prop = p;
        this.tries = t;
        this.bot = b;
        clear();
    }
    public void clear()
    {
        tries.clear();
    }
    public void clear_all()
    {
        clear();
        if(role!=null)
        {
            if(role.act!=null)
            {
                for(int i = 0; i < role.act.length; i++)
                {
                    if(role.act[i]!=null)
                    {
                        role.act[i].clear();
                    }
                }
            }
        }
    }
}