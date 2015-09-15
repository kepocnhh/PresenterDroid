package stan.presenter.core.ability.active.changeproperty;

import android.content.ContentValues;
import android.content.res.Resources;

import stan.db.contract.Contract;
import stan.presenter.core.player.Player;
import stan.presenter.mafia.R;

public class Kill
        extends ChangeProperty
{

    public Kill(String n)
    {
        super(n);
    }

    public ContentValues getContentValues()
    {
        ContentValues cv = new ContentValues();
        cv.put(Contract.ID, UID);
        cv.put(Contract.NAME, name);
        return cv;
    }

    @Override
    protected TypeChangeProperty setTypeChangeProperty()
    {
        return TypeChangeProperty.Kill;
    }

    @Override
    public Player engage(Player p)
    {
        p.prop.life = false;
        return p;
    }
}
