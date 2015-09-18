package stan.presenter.mafia.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public abstract class MafiaCursorAdapter
        extends CursorAdapter
{
    public interface IMafiaCursorClick
    {
//        void click(Cursor cursor);
    }
    protected static class  MafiaCursorHolder
    {
        public View parent;
        public  MafiaCursorHolder(View v)
        {
            parent = v;
        }
    }

    protected IMafiaCursorClick clickListener;
    private Context context;
    protected int resourceID;
    public MafiaCursorAdapter(Context context, Cursor c, int r, IMafiaCursorClick click)
    {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.context = context;
        this.resourceID = r;
        this.clickListener = click;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(resourceID, parent, false);
        MafiaCursorHolder holder = initHolder(view);
//        holder.parent.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
////                clickListener.click(cursor);
//                click(cursor, holder);
//            }
//        });
        view.setTag(holder);
        return newView(view, cursor);
    }
    protected abstract View newView(View view, Cursor cursor);
    protected abstract MafiaCursorHolder initHolder(View view);
//    protected abstract void click(Cursor cursor, MafiaCursorHolder holder);

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        bindView((MafiaCursorHolder)view.getTag(), cursor);
    }

    public abstract void bindView(MafiaCursorHolder h, Cursor cursor);

    public Context getContext()
    {
        return this.context;
    }
}