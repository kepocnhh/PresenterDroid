package stan.presenter.core.ability.passive.selfie;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class SelfMedication
    extends Selfie
{
    public SelfMedication()
    {
        super(Resources.getSystem().getString(R.string.self_medication));
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.heal_day = true;
        return p;
    }
}
