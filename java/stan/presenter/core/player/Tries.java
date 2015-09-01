package stan.presenter.core.player;

import java.util.ArrayList;
import java.util.List;

public class Tries
{
    private class Leverage
    {
        public int who;
        public Properties how;

        public Leverage(int w, Properties h)
        {
            this.who = w;
            this.how = h;
        }
    }

    public List<Leverage> leverages;

    public void clear()
    {
        leverages = new ArrayList<>();
    }
    public void add(int w, Properties h)
    {
        leverages.add(new Leverage(w,h));
    }

    public Tries()
    {
        clear();
    }

    public boolean tryKill()
    {
        for(int i=0; i<leverages.size(); i++)
        {
            if(!leverages.get(i).how.life)
            {
                return true;
            }
        }
        return false;
    }
    public boolean tryStop()
    {
        for(int i=0; i<leverages.size(); i++)
        {
            if(leverages.get(i).how.stop)
            {
                return true;
            }
        }
        return false;
    }
    public void cleanTriesStop()
    {
        List<Leverage> newLeverages = new ArrayList<>();
        for(int i=0; i<leverages.size(); i++)
        {
            if(!leverages.get(i).how.stop)
            {
                newLeverages.add(leverages.get(i));
            }
        }
        leverages = newLeverages;
    }
}
