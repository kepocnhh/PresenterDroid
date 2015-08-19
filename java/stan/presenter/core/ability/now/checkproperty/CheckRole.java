package stan.presenter.core.ability.now.checkproperty;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class CheckRole
        extends CheckProperty
{
    public CheckRole()
    {
        super(Resources.getSystem().getString(R.string.check_role));
    }

    @Override
    public String engage(Player p)
    {
        return p.role.name;
    }
}
