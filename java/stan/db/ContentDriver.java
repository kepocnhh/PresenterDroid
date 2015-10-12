package stan.db;

import android.content.ContentValues;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stan.db.contract.Action;
import stan.db.contract.Contract;
import stan.db.contract.Role;
import stan.db.contract.RolesAndActions;
import stan.db.contract.VisibleRoles;

public class ContentDriver
{
    static private ContentValues getContentValues(stan.presenter.core.Mafia m)
    {
        ContentValues cv = new ContentValues();
        if(m.UID == null)
        {
            m.UID =  getUI();
        }
        cv.put(Contract.ID, m.UID);
        cv.put(Contract.NAME, m.name);
        return cv;
    }
    static private String getUI()
    {
        return new Date().getTime()+ "";
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
        ContentValues cv = getContentValues((stan.presenter.core.Mafia)r);
        cv.put(Contract.DESCRIPTION, r.description);
        cv.put(Role.SIDE, r.getTypeVisibility().ordinal());
        cv.put(Role.TYPEGROUP, r.getTypeGroupRole().getTAG());
        cv.put(Role.TEAM, r.getTeam().UID);
        return cv;
    }
    static public List<ContentValues> getContentValuesFRolesActions(stan.presenter.core.role.Role r)
    {
        List<ContentValues> cvList = new ArrayList<>();
        for(int i=0; i< r.actions.length; i++)
        {
            cvList.add(getContentValues(r, r.actions[i]));
        }
        return cvList;
    }
    static public ContentValues getContentValuesFRolesActions(String ruid, String auid, int s, int v)
    {
        ContentValues cv = new ContentValues();
        cv.put(Contract.ID, getUI());
        cv.put(RolesAndActions.ROLE_ID, ruid);
        cv.put(RolesAndActions.ACTION_ID, auid);
        cv.put(RolesAndActions.SELFIE, s);
        cv.put(RolesAndActions.VISIBLES, v);
        return cv;
    }
    static public ContentValues getContentValues(stan.presenter.core.role.Role r, stan.presenter.core.action.Action a)
    {
        int s = 0;
        if(a.getRestrictions().canSelfie())
        {
            s = 1;
        }
        int v = 0;
        if(a.getRestrictions().canVisibles())
        {
            v = 1;
        }
        return getContentValuesFRolesActions(r.UID, a.UID, s, v);
    }
    static public ContentValues getContentValues(stan.presenter.core.role.Role r, stan.presenter.core.role.Role rw)
    {
        ContentValues cv = new ContentValues();
        cv.put(Contract.ID, getUI());
        cv.put(VisibleRoles.ROLE_ID, r.UID);
        cv.put(VisibleRoles.ROLE_WHOM, rw.UID);
        return cv;
    }
}