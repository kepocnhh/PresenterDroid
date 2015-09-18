package stan.db;

import android.content.ContentValues;

import org.json.JSONArray;

import java.util.Date;

import stan.db.contract.Action;
import stan.db.contract.Contract;

public class ContentDriver
{
    static private ContentValues getContentValues(stan.presenter.core.Mafia m)
    {
        ContentValues cv = new ContentValues();
        if(m.UID == null)
        {
            Date d = new Date();
            int yea = d.getYear() - 100;
            int mon = d.getMonth()+1;
            int day = d.getDate();
            int hou = d.getHours();
            int min = d.getMinutes();
            int sec = d.getSeconds();
            long msec = d.getTime();
            String ui = yea + getTwoChars(mon) + getTwoChars(day)
                    + getTwoChars(hou) + getTwoChars(min) + getTwoChars(sec);
//            m.UID =  Integer.parseInt(ui);
            ui = msec + "";
            m.UID =  ui;
        }
            cv.put(Contract.ID, m.UID);
        cv.put(Contract.NAME, m.name);
        return cv;
    }
    static private String getTwoChars(int i)
    {
        if(i<10)
        {
            return "0"+i;
        }
        return i+"";
    }

    static public ContentValues getContentValues(stan.presenter.core.action.Action a)
    {
        ContentValues cv = getContentValues((stan.presenter.core.Mafia)a);
        cv.put(Contract.DESCRIPTION, a.description);
        JSONArray abilitieArray = new JSONArray();
        for(int i=0; i<a.abilities.length; i++)
        {
            JSONArray abilitieMap = new JSONArray();
            int[] map = a.abilities[i].getMap();
            for(int j=0; j<map.length; j++)
            {
                abilitieMap.put(map[j]);
            }
            abilitieArray.put(abilitieMap);
        }
        String abilities = abilitieArray.toString();
        cv.put(Action.ABILITIES, abilities);
        return cv;
    }
    static public ContentValues getContentValues(stan.presenter.core.role.Role r)
    {
        ContentValues cv = new ContentValues();
        return cv;
    }
}