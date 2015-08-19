package stan.presenter.core.ability.active.changeproperty;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class Kill
        extends ChangeProperty
{
    public Kill()
    {
        super(Resources.getSystem().getString(R.string.kill));
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.life = false;
        return p;
    }
}
