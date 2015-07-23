package stan.presenter.core;

public class Ability
        extends Mafia
{
    public boolean try_stop = false;

    public static class Kill
            extends Ability
    {
        public Kill()
        {
            super("Убить");
        }
        @Override
        public Player engage(Player p)
        {
            p.life = false;
            return p;
        }
    }
    public static class ProtectDay
            extends Ability
    {
        public ProtectDay()
        {
            super("Защитить от убийства днём");
        }
        @Override
        public Player engage(Player p)
        {
            p.heal_day = true;
            return p;
        }
    }
    public static class ProtectNight
            extends Ability
    {
        public ProtectNight()
        {
            super("Защитить от убийства ночью");
        }
        @Override
        public Player engage(Player p)
        {
            p.heal_night = true;
            return p;
        }
    }
    public static class RoleBlock
            extends Ability
    {
        public RoleBlock()
        {
            super("Заблокировать действие роли");
            try_stop = true;
        }
        @Override
        public Player engage(Player p)
        {
            p.stop = true;
            return p;
        }
    }
    //
    public Ability(String n)
    {
        super(n);
    }
    public Player engage(Player p)
    {
        return p;
    }
    /*
        Проверить на мафию
        Забрать с собой при смерти
    */
}