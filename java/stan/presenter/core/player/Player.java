package stan.presenter.core.player;

import stan.presenter.core.Mafia;
import stan.presenter.core.Role;

public class Player
        extends Mafia
{
    public Role role;
    public Tries tries;
    public Properties prop;
    public boolean bot;//бот или нет

    public Player(String n)
    {
        super(n);
        init(false);
    }
    public Player(String n, boolean b)
    {
        super(n);
        init(b);
    }
    private void init(boolean b)
    {
        this.prop = new Properties();
        this.tries = new Tries();
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