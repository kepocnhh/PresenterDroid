package stan.presenter.core;

public class Player
        extends Mafia
{
    public Role role;
    public boolean bot;//бот или нет
    //
    public boolean life;//состояние здоровья
    public boolean stop;//возможность действовать
    public boolean try_stop;//возможность действовать
    public boolean heal_night;//исцеление ночью
    public boolean heal_day;//исцеление днём
    
    //
    public Player(String n)
    {
        super(n);
        //
        this.bot = false;
        clear();
    }
    public void clear()
    {
        this.life = true;
        this.stop = false;
        this.try_stop = false;
        this.heal_night = false;
        this.heal_day = false;
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
                        role.act[i].from = -1;
                        role.act[i].to = -1;
                    }
                }
            }
        }
    }
}