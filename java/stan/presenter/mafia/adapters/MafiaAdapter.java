package stan.presenter.mafia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public abstract class MafiaAdapter
        extends SimpleAdapter
{
    static public class MafiaHolder
    {
        View parent;
    }

    public interface MafiaAdapterListener
    {
        void pressItem(int pos);
    }

    private Activity activity;
    private MafiaAdapterListener listener;
    private LayoutInflater inflater;
    protected ArrayList data;
    protected int resourceID;

    public MafiaAdapter(Activity context,
                        ArrayList d,
                        MafiaAdapterListener l, int id)
    {
        super(context, d, id, new String[]{}, new int[]{});
        resourceID = id;
        activity = context;
        listener = l;
        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private MafiaHolder init(View vi)
    {
        MafiaHolder holder = initItems(vi);
        holder.parent = vi;
        return holder;
    }
    private void realize(MafiaHolder holder,final int p)
    {
        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pressItem(p);
                listener.pressItem(p);
            }
        });
        realizeItem(holder, p);
    }

    protected abstract void pressItem(int p);
    protected abstract MafiaHolder initItems(View vi);
    protected abstract void realizeItem(MafiaHolder holder, int p);

    @Override
    public View getView(int p, View convertView, ViewGroup parent)
    {
        MafiaHolder holder;
        if (convertView == null)
        {
            convertView = inflater.inflate(resourceID, parent, false);
            holder = init(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (MafiaHolder) convertView.getTag();
        }
        realize(holder, p);
        return convertView;
    }
}