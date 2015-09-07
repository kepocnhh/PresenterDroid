package stan.presenter.core.ability.checktwoplayers;

import android.content.res.Resources;

import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class CheckCommand
        extends CheckTwoPlayers
{
    public CheckCommand()
    {
        super(Resources.getSystem().getString(R.string.check_command));
    }

    @Override
    public boolean engage(Player p1, Player p2)
    {
        return p1.role.getTypeRole().equals(p2.role.getTypeRole());
    }
}
