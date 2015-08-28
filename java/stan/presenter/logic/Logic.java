package stan.presenter.logic;

import java.util.List;

import stan.presenter.core.Action;
import stan.presenter.core.player.Player;

public class Logic
{
    static public void engageAllActions(List<Action> act_list)
    {
        int iter = 0;
        while(act_list.size() > 0)
        {
            if(act_list.size() == iter)
            {
                setTriesForPlayers(act_list);
                iter = 0;
                continue;
            }
            if(!engagePlayerActionWithCheck(Core.pl_list.get(act_list.get(iter).from), act_list.get(iter)))
            {
                iter++;
                continue;
            }
            act_list.remove(iter);
        }
    }

    static private void engagePlayerAction(Action action)
    {
        //TODO make method for engage player action
    }

    static private boolean engagePlayerActionWithCheck(Player p, Action action)
    {
        if(!stopped(p) && canDo(p))
        {
            if(tryStop(p))
            {
                cleanTriesStop(p);
                return false;
            }
            engagePlayerAction(action);
        }
        return true;
    }

    static private Action getCurrentAction(Player p)
    {
        return p.role.getCurrentAction();
    }

    static private boolean canDo(Player p)
    {
        //TODO make can do check (can shot in rang always, can shot in rang with condition, can shot another)
        //        if(Core.pl_list.get(from).role.rang_shot ||
        //                (!Core.pl_list.get(from).role.rang_shot && Core.pl_list.get(from).role.rang == 1)||
        //                (Core.pl_list.get(from).role.rang < 0))
        return true;
    }

    static private void cleanTriesStop(Player p)
    {
        p.tries.cleanTriesStop();
    }

    static private boolean tryStop(Player p)
    {
        return p.tries.tryStop();
    }

    static private void setTriesForPlayers(List<Action> act_list)
    {
        for(int i = 0; i < act_list.size(); i++)
        {
            Action act = getCurrentAction(Core.pl_list.get(act_list.get(i).from));
            if(act != null)
            {
                setTriesForPlayer(act);
            }
        }
    }

    static private void setTriesForPlayer(Action act)
    {
        //TODO set all tries for player
        //        if(Pretreatment.pl_list.get(from).role.act[act].try_stop)
        //        {
        //            Pretreatment.pl_list.get(to).try_stop = true;
        //        }
    }

    static private boolean stopped(Player p)
    {
        return p.prop.stop;
    }
}