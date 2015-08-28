package stan.presenter.game;

import java.util.Date;

import stan.presenter.core.Mafia;

public class Human
    extends Mafia
{
    public Human(String n)
    {
        super(n);
    }

    public enum Sex
    {
        nosex,
        male,
        female
    }

    String family;
    String nickname;
    Sex sex;
    Date birth;
    //
    Statistics statistics;
}