package stan.presenter.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Day extends Activity
{
    private Button b_kill;
    private ListView events;
    private ArrayList<HashMap<String, Object>> hm = new ArrayList<HashMap<String, Object>>();
    //private SimpleAdapter sa;
    private MyAdapter sa;
    private boolean win;
    private boolean day_time;
    private Date cur_date;
    Spinner plrs;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        List<Player> tmp = Kills();
        String d = "";
        d = cur_date.getDate() +"."+ (cur_date.getMonth()+1) +"."+ (cur_date.getYear()+1900)+" НОЧЬ";
        String s = "";
        if(tmp.size()>0)
        {
            s = "не стало ";
            s+=tmp.get(0).name;
            for (int i = 1; i<tmp.size()-1; i++)
            {
                s+=", " + tmp.get(i).name;
            }
            if(tmp.size()>1)
            {
                s+=" и " + tmp.get(tmp.size()-1).name + ".";
            }
        }
        else
        {
            s = "никто не умер.";
        }
        hm.add(add_hm(d,"Этой ночью " + s, MyAdapter.NIGHTKEY));
        sa.notifyDataSetChanged();
        short st = check_win();
        if(st!=0)
        {
            d = "ИГРА ОКОНЧЕНА";
            win = true;
            if(st<0)
            {
                hm.add(add_hm(d,"Этой ночью " + "мафия побеждает город", MyAdapter.ENDBADKEY));
            }
            else
            {
                hm.add(add_hm(d,"Этой ночью " + "город побеждает мафию", MyAdapter.ENDGOODKEY));
            }
            sa.notifyDataSetChanged();
            setDayTime(false, " ВЫЙТИ ",View.GONE);
            return;
        }
        set_Spinner();
    }
    @Override
    public void onBackPressed()
    {
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        }
        else if(keyCode == KeyEvent.KEYCODE_HOME)
        {
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day);
        //
        events = (ListView) findViewById(R.id.day_lv);
        hm = new ArrayList<HashMap<String, Object>>();
        sa = new MyAdapter(this,hm,R.layout.day_list_item,new int[]{
                R.id.dli_tv_date,
                R.id.dli_tv_event});
        events.setAdapter(sa);
        //
        plrs = (Spinner) findViewById(R.id.day_sp);
        plrs.setVisibility(View.GONE);
        //
        b_kill = (Button) findViewById(R.id.day_b_kill);
        //
        win = false;
        cur_date = new Date();
        Begin_Game();
    }
    private void setDayTime(boolean b, String bt, int sv)
    {
        day_time = b;
        b_kill.setText(bt);
        plrs.setVisibility(sv);
    }
    private List<Player> Kills()
    {
        List<Player> tmp = new ArrayList<Player>();
        for(int i=0; i<Pretreatment.pl_list.size(); i++)
        {
            if(!Pretreatment.pl_list.get(i).life)
            {
                if(!Pretreatment.pl_list.get(i).heal_night)
                {
                    Main.to_Debug(Main.p_t_p(Pretreatment.pl_list.get(i)), "умирает");
                    tmp.add(Pretreatment.pl_list.get(i));
                    if(Pretreatment.pl_list.get(i).role.rang>0)
                    {
                        for(int k=0; k<Pretreatment.pl_list.size(); k++)
                        {
                            if(k==i)
                            {
                                continue;
                            }
                            if(Pretreatment.pl_list.get(k).role.act!=null && Pretreatment.pl_list.get(k).role.act.getClass() == Pretreatment.pl_list.get(i).role.act.getClass() && Pretreatment.pl_list.get(k).role.rang>-1)
                            {
                                if(Pretreatment.pl_list.get(k).role.rang > Pretreatment.pl_list.get(i).role.rang)
                                {
                                    Pretreatment.pl_list.get(k).role.rang --;
                                }
                            }
                        }
                    }
                    Pretreatment.pl_list.remove(i);
                }
            }
        }
        return tmp;
    }
    private HashMap<String, Object> add_hm(String d, String e, String c)
    {
        HashMap<String, Object> hm2 = new HashMap<String, Object>();
        hm2.put(MyAdapter.DATEKEY, d);
        hm2.put(MyAdapter.EVENTKEY, e);
        hm2.put(MyAdapter.COLKEY, c);
        return hm2;
    }
    private void set_Spinner()
    {
        List<String> tmp = new ArrayList<String>();
        for(int i=0;i<Pretreatment.pl_list.size();i++)
        {
            tmp.add(Pretreatment.pl_list.get(i).name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tmp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plrs.setAdapter(adapter);
        plrs.setSelection(0);
        plrs.setVisibility(View.VISIBLE);
    }
    private void Begin_Game()
    {
        List<Role> tmp_list = Pretreatment.rl_list;
        Random rand = new Random();
        Main.to_Debug("Ведущий",  "Сегодня играют:");
        for(int i=0; i<Pretreatment.pl_list.size(); i++)
        {
            int ri = rand.nextInt(tmp_list.size());
            Pretreatment.pl_list.get(i).role = tmp_list.get(ri);
            tmp_list.remove(ri);
            Main.to_Debug(Main.p_t_p(Pretreatment.pl_list.get(i)),  "");
        }
        Night();
    }
    private void Night()
    {
        plrs.setVisibility(View.GONE);
        startActivityForResult(new Intent(Day.this, Night.class), 0);
        setDayTime(true, "Казнить",View.VISIBLE);
    }
    private void Day()
    {
        if(!day_time)
        {
            Night();
            return;
        }
        String d = "";
        d = cur_date.getDate() +"."+ (cur_date.getMonth()+1) +"."+ (cur_date.getYear()+1900)+" ДЕНЬ";
        cur_date.setDate(cur_date.getDate()+1);
        String s = "";
        int n = plrs.getSelectedItemPosition();
        if(Pretreatment.pl_list.get(n).heal_day)
        {
            s = "никто не умер.";
        }
        else
        {
            Pretreatment.pl_list.get(n).life = false;
            s = "умирает " + Pretreatment.pl_list.get(n).name;
            Main.to_Debug(Main.p_t_p(Pretreatment.pl_list.get(n)), "умирает");
            if(Pretreatment.pl_list.get(n).role.rang>0)
            {
                for(int k=0; k<Pretreatment.pl_list.size(); k++)
                {
                    if(k==n)
                    {
                        continue;
                    }
                    if(Pretreatment.pl_list.get(k).role.UI == Pretreatment.pl_list.get(n).role.UI &&
                            Pretreatment.pl_list.get(k).role.rang > -1 &&
                            Pretreatment.pl_list.get(k).role.rang > Pretreatment.pl_list.get(n).role.rang)
                    {
                        Pretreatment.pl_list.get(k).role.rang --;
                    }
                }
            }
            Pretreatment.pl_list.remove(n);
        }
        hm.add(add_hm(d,"Этим днём " + s, MyAdapter.DAYKEY));
        sa.notifyDataSetChanged();
        short st = check_win();
        //windows
        if(st!=0)
        {
            d = "ИГРА ОКОНЧЕНА";
            win = true;
            if(st<0)
            {
                hm.add(add_hm(d,"Этим днём " + "мафия побеждает город", MyAdapter.ENDBADKEY));
            }
            else
            {
                hm.add(add_hm(d,"Этим днём " + "город побеждает мафию", MyAdapter.ENDGOODKEY));
            }
            sa.notifyDataSetChanged();
            setDayTime(false, " ВЫЙТИ ",View.GONE);
            return;
        }
        null_players();
        setDayTime(false, "Город засыпает",View.GONE);
    }
    private static void null_players()
    {
        for(int i=0; i<Pretreatment.pl_list.size(); i++)
        {
            Pretreatment.pl_list.get(i).clear_all();
        }
    }
    private short check_win()
    {
        int m = 0;
        int p = 0;
        for(int i=0; i<Pretreatment.pl_list.size(); i++)
        {
            if(Pretreatment.pl_list.get(i).role.Get_TV().equals(Role.TypeVisibility.mafia))
            {
                m++;
            }
            else
            {
                p++;
            }
        }
        if(m==0)
        {
            return 1;
        }
        if(m==p)
        {
            return -1;
        }
        return 0;
    }
    //
    public void kill_day(View v)
    {
        if(win)
        {
            finish();
        }
        else
        {
            Day();
        }
    }
}