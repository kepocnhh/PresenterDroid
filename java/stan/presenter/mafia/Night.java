package stan.presenter.mafia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Night
        extends Activity
        implements View.OnClickListener
{
    private ListView n_lv;
    private ArrayList<HashMap<String, String>> hm = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    private int player_num;
    private Button btnok;
    private SeekBar seekBarok;
    private TextView asleeptv;
    TextView pl_name;
    List<Player> tmp_list;
    Random rand;
    public static List<Action> act_list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.night);
        //
        pl_name = (TextView) findViewById(R.id.nig_tv_name);
        n_lv = (ListView) findViewById(R.id.nig_lv);
        hm = new ArrayList<HashMap<String, String>>();
        sa = new SimpleAdapter(this,
                hm,
                R.layout.night_list_item,
                new String[]
                {
                    "name"
                },
                new int[]
                {
                    R.id.nli_tv_name,
                });
        n_lv.setAdapter(sa);
        //
        act_list = new ArrayList<Action>();
        //startActivityForResult(new Intent(Night.this, Night_dlg.class), 0);
        tmp_list = new ArrayList<Player>();
        for(int i=0;i<Pretreatment.pl_list.size();i++)
        {
            tmp_list.add(Pretreatment.pl_list.get(i));
        }
        rand = new Random();
        player_num = next_player();
        //
        btnok = (Button) findViewById(R.id.nig_b_engage);
        btnok.setOnClickListener(this);
        btnok.setVisibility(View.INVISIBLE);
        asleeptv = (TextView) findViewById(R.id.nig_tv_asleep);
        seekBarok = (SeekBar) findViewById(R.id.nig_sb_engage);
        SeekBar.OnSeekBarChangeListener sbcl = new SeekBar.OnSeekBarChangeListener()
        {
            int originalProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser && Math.abs(originalProgress - progress) < 10)
                {
                    originalProgress = progress;
                }
                else
                {
                    seekBar.setProgress( originalProgress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                originalProgress = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if(seekBar.getProgress() >= seekBar.getMax())
                {
                    wakeup();
                }
                originalProgress = 0;
                seekBar.setProgress(originalProgress);
                if(tmp_list.size()==0)
                {
                    seekBarok.setVisibility(View.GONE);
                    asleeptv.setVisibility(View.GONE);
                    btnok.setVisibility(View.VISIBLE);
                }
            }
        };
        seekBarok.setOnSeekBarChangeListener(sbcl);
    }
    private HashMap<String, String> add_hm(String n)
    {
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("name", n);
        return hm2;
    }
    private void Engage(List<Action> act_list)
    {
        int iter = 0;
        while(true)
        {
            if(act_list.size() == iter)
            {
                for(int i=0; i<act_list.size(); i++)
                {
                    int act = 0;
                    for(int q=0; q<Pretreatment.pl_list.get(act_list.get(i).from).role.act.length; q++)
                    {
                        if(Pretreatment.pl_list.get(act_list.get(i).from).role.act[q].from != -1)
                        {
                            act = q;
                            break;
                        }
                    }
                    if(Pretreatment.pl_list.get(act_list.get(i).from).role.act[act].try_stop)
                    {
                        Pretreatment.pl_list.get(act_list.get(i).to).try_stop = true;
                    }
                }
                iter = 0;
                continue;
            }
            int to = act_list.get(iter).to;
            int from = act_list.get(iter).from;
            if(!Pretreatment.pl_list.get(from).stop)
            {
                if(Pretreatment.pl_list.get(from).role.rang_shot ||
                        (!Pretreatment.pl_list.get(from).role.rang_shot && Pretreatment.pl_list.get(from).role.rang == 1)||
                        (Pretreatment.pl_list.get(from).role.rang < 0))
                {
                    if(Pretreatment.pl_list.get(from).try_stop)
                    {
                        Pretreatment.pl_list.get(from).try_stop = false;
                        iter++;
                        continue;
                    }
                    Pretreatment.pl_list.set(to, act_list.get(iter).engage(Pretreatment.pl_list.get(to)));
                }
            }
            act_list.remove(iter);
            if(act_list.isEmpty())
            {
                break;
            }
        }
    }
    public int next_player()
    {
        int ri = -1;
        while(tmp_list.get(tmp_list.size()-1)==null)
        {
            tmp_list.remove(tmp_list.size()-1);
            if(tmp_list.size() == 0)
            {
                pl_name.setText("Город просыпается");
                return -1;
            }
        }
        while(ri < 0 || tmp_list.get(ri)==null)
        {
            ri = rand.nextInt(tmp_list.size());
        }
        tmp_list.set(ri,null);
        pl_name.setText(Pretreatment.pl_list.get(ri).name);
        return ri;
    }
    public void say(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    public void wakeup()
    {
        Night_dlg nd = new Night_dlg(this);
        nd.init(player_num);
        nd.show();
        hm.add(add_hm(Pretreatment.pl_list.get(player_num).name));
        sa.notifyDataSetChanged();
        player_num = next_player();
    }
    public void wakeupAll()
    {
        Engage(Night.act_list);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
    public void wakeup2()
    {
        if(tmp_list.size()>0)
        {
            Night_dlg nd = new Night_dlg(this);
            nd.init(player_num);
            nd.show();
            hm.add(add_hm(Pretreatment.pl_list.get(player_num).name));
            sa.notifyDataSetChanged();
            player_num = next_player();
        }
        else
        {
            Engage(Night.act_list);
            setResult(RESULT_OK, this.getIntent());
            finish();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.nig_b_engage:
                wakeupAll();
        }
    }
}