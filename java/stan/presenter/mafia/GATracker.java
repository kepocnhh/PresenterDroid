package stan.presenter.mafia;

import android.app.Activity;

import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class GATracker
{
    static public Tracker tracker;

    static public void initTracker(Activity a)
    {
        tracker = GoogleAnalytics.getInstance(a).getTracker(a.getResources().getString(R.string.ga_trackingId));
        //tracker.start("UA-YOUR-ACCOUNT-HERE", 30, a);
    }
}