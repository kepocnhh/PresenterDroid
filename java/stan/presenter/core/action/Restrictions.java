package stan.presenter.core.action;

public class Restrictions
{
    boolean selfie = true;
    boolean visibles = true;

    public void setSelfieRestriction(boolean s)
    {
        this.selfie = s;
    }
    public void setVisiblesRestriction(boolean v)
    {
        this.visibles = v;
    }
}