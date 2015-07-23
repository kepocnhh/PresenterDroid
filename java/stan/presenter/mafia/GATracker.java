package stan.presenter.mafia;

import android.app.Activity;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders;

public class GATracker
{
    private static Tracker mTracker;

    static public final String CAT_CLICK = "Click button";
    static public final String CAT_ADMOB = "AdMob";
    static public final String CAT_MAFIA = "Mafia";
    static public final String CAT_PL_COUNT = "players count";

    synchronized static public Tracker getDefaultTracker(Activity act)
    {
        if (mTracker == null)
        {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(act);
            mTracker = analytics.newTracker(R.string.ga_trackingId);
            mTracker.enableAdvertisingIdCollection(true);
        }
        return mTracker;
    }

    static public void track(Activity act, String id, String category, String action, String label, Long value)
    {
        getDefaultTracker(act).send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .build());
    }
}