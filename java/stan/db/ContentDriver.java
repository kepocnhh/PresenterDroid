package stan.db;

import android.content.ContentValues;

import stan.db.contract.Contract;

public class ContentDriver
{
    static private final String ACTION_ABILITIES = "abilities";
    static public ContentValues getContentValues(stan.presenter.core.action.Action a)
    {
        ContentValues cv = new ContentValues();
        cv.put(Contract.ID, a.UID);
        cv.put(Contract.NAME, a.name);
        cv.put(Contract.DESCRIPTION, a.description);
        String abilities = "";
        cv.put(ACTION_ABILITIES, abilities);
        return cv;
    }
}