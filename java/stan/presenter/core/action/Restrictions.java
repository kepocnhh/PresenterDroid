package stan.presenter.core.action;

import java.io.Serializable;

public class Restrictions
    implements Serializable
{
    private boolean selfie = true;
    private boolean visibles = true;

    public void setSelfieRestriction(boolean s)
    {
        this.selfie = s;
    }
    public void setVisiblesRestriction(boolean v)
    {
        this.visibles = v;
    }

    public boolean canSelfie()
    {
        return this.selfie;
    }
    public boolean canVisibles()
    {
        return this.visibles;
    }

    public boolean changeSelfie()
    {
        selfie = !selfie;
        return selfie;
    }
    public boolean changeVisibleRole()
    {
        visibles = !visibles;
        return visibles;
    }
}