package stan.presenter.mafia;

import android.app.Activity;

import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class GATracker
{
    static public final String CAT_CLICK = "Click button";
    static public final String CAT_ADMOB = "AdMob";
    static public final String CAT_MAFIA = "Mafia";
    static public final String CAT_PL_COUNT = "players count";

    static public void track(Activity act, String id, String category, String action, String label, Long value)
    {
        Tracker t = GoogleAnalytics.getInstance(act).getTracker(act.getResources().getString(R.string.ga_trackingId));
        t.set("&uid", id);
        t.send(MapBuilder
                        .createEvent(category,       // Event category (required)
                                action,  // Event action (required)
                                label,       // Event label
                                value)       // Event value
                        .build()
        );
    }
}