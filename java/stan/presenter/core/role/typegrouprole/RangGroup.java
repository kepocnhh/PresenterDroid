package stan.presenter.core.role.typegrouprole;

import android.content.res.Resources;

import stan.presenter.mafia.R;

public abstract class RangGroup
    extends TypeGroup
{
    int rang;
    boolean rangShot;
    boolean visibleRang;

    public RangGroup(String n, String d, boolean vig, boolean rs, boolean vr)
    {
        super(n, d, vig);
        this.rangShot = rs;
        this.visibleRang = vr;
    }

    public static class Clan
            extends RangGroup
    {
        public Clan(boolean vr)
        {
//            super(Resources.getSystem().getString(R.string.clan),
//                    Resources.getSystem().getString(R.string.clan_descr),
//                    true, false, vr);
            super("Клан во главе с боссом",
                    "знают о существовании друг друга и понимают, кто есть их предводитель. И только он может действовать от лица всего клана",
                    true, false, vr);
        }
    }

    public void setRang(int r)
    {
        rang = r;
    }
    public void upRang()
    {
        rang--;
    }
}