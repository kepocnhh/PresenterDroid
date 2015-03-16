package stan.presenter.mafia;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter
        extends SimpleAdapter
        implements Filterable
{
    static final String DATEKEY     = "date";
    static final String EVENTKEY    = "event";
    static final String COLKEY      = "color";

    static final String     NIGHTKEY        = R.color.cnight + "";
    static final String     DAYKEY          = R.color.cnewye + "";
    static final String     ENDGOODKEY      = R.color.cnewgreen + "";
    static final String     ENDBADKEY       = R.color.cred + "";

    private final Activity activity;
    private ArrayList<HashMap<String, Object>> values;

    public MyAdapter(Activity activity,
                     ArrayList<HashMap<String, Object>> list,
                     int id, int[] t)
    {
        super(activity,list,id,
            new String[]
                {
                    DATEKEY,
                    EVENTKEY
                },
            t);
        this.values = list;
        this.activity = activity;
    }
    @Override
    public View getView(int p, View convertView, ViewGroup parent)
    {
        View rowView = super.getView(p, convertView, parent);
        RelativeLayout rl       = (RelativeLayout)  rowView.findViewById(R.id.dli_rl);
        TextView tvDate         = (TextView)        rowView.findViewById(R.id.dli_tv_date);
        TextView tvEvent        = (TextView)        rowView.findViewById(R.id.dli_tv_event);
        //
        int textc = R.color.cblack;
        int background;
        if(values.get(p).get(COLKEY).toString().equals(NIGHTKEY))
        {
            textc = R.color.cwhite;
        }
        background = Integer.parseInt(values.get(p).get(COLKEY).toString());
        tvDate.setTextColor(activity.getResources().getColor(textc));
        tvEvent.setTextColor(activity.getResources().getColor(textc));
        rl.setBackgroundResource(background);
        return rowView;
    }
}