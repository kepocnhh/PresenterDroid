package stan.presenter.core;

import android.content.ContentValues;

import stan.db.contract.Contract;

public abstract class Mafia
{
    public String name;
    public int UID;
    public Mafia(String n)
    {
        this.name = n;
        this.UID = -1;
    }

//    protected abstract ContentValues getContentValues(ContentValues cv);
//    public ContentValues getContentValues()
//    {
//        ContentValues cv = new ContentValues();
//        cv.put(Contract.ID, UID);
//        cv.put(Contract.NAME, name);
//        cv = getContentValues(cv);
//        return cv;
//    }
}