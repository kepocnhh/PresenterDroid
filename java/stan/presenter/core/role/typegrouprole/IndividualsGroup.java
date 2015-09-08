package stan.presenter.core.role.typegrouprole;

import android.content.res.Resources;

import stan.presenter.mafia.R;

public abstract class IndividualsGroup
    extends TypeGroup
{

    public IndividualsGroup(String n, String d, boolean vig)
    {
        super(n, d, vig);
    }

    public static class Individuals
            extends IndividualsGroup
    {
        public Individuals()
        {
            super(Resources.getSystem().getString(R.string.individuals),
                    Resources.getSystem().getString(R.string.individuals_descr),
                    false);
        }
    }
}