package stan.presenter.core;

import android.content.res.Resources;

import stan.presenter.mafia.R;

public class Ability
        extends Mafia
{
    public boolean try_stop = false;

    //__________________ACTIVE__________________//
    public static class Active
            extends Ability
    {
        public Active(String n)
        {
            super(n);
        }
    }
        public static class Kill
                extends Active
        {
            public Kill()
            {
                super(Resources.getSystem().getString(R.string.kill));
            }
            @Override
            public Player engage(Player p)
            {
                p.life = false;
                return p;
            }
        }
        public static class Block
                extends Active
        {
            public Block()
            {
                super(Resources.getSystem().getString(R.string.block));
                try_stop = true;
            }
            @Override
            public Player engage(Player p)
            {
                p.stop = true;
                return p;
            }
        }

    //__________________PASSIVE_________________//
    public static class Passive
            extends Ability
    {
        public Passive(String n)
        {
            super(n);
        }
    }
        public static class ProtectSelf
                extends Passive
        {
            public ProtectSelf()
            {
                super(Resources.getSystem().getString(R.string.protect_self));
            }
            @Override
            public Player engage(Player p)
            {
                p.heal_night = true;
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