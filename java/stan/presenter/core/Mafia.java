package stan.presenter.core;

import java.io.Serializable;

public abstract class Mafia
        implements Serializable
{
    public String name;
    public String UID;
    public Mafia(String n)
    {
        this.name = n;
    }
}