package stan.presenter.mafia;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Night
        extends Activity
        implements View.OnClickListener
{
    private ViewFlipper flipper_mess;
    private TextView       tv_mess;
    //
    private Night_dlg nd;
    //
    private ListView n_lv;
    private LinearLayout nightbot;
    private ArrayList<HashMap<String, String>> hm = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    private int           player_num;
    private Button        btnok;
    private SeekBar       seekBarok;
    private TextView      asleeptv;
    private ImageView     back;
    TextView     pl_name;
    List<Player> tmp_list;
    Random       rand;
    public static List<Action> act_list;

//
    private void flipmess(String m)
    {
        flipper_mess.setDisplayedChild(0);
        flipnext(flipper_mess);
        tv_mess.setText(m);
    }
    private void flipnext(ViewFlipper f)
    {
        f.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.left));
        f.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
        f.showNext();
    }
//
    @Override
    public void onBackPressed()
    {
    }
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
        nightbot = (LinearLayout) findViewById(R.id.night_bot);
        nightbot.setVisibility(View.VISIBLE);
        //
        btnok = (Button) findViewById(R.id.nig_b_engage);
        btnok.setOnClickListener(this);
        btnok.setVisibility(View.INVISIBLE);
        asleeptv = (TextView) findViewById(R.id.nig_tv_asleep);
        seekBarok = (SeekBar) findViewById(R.id.nig_sb_engage);
        back = (ImageView) findViewById(R.id.night_back);
        back.setImageResource(R.drawable.sleep0);
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
                    return;
                }
                if(progress>95)
                {
                    back.setImageResource(R.drawable.normal_night);
                    return;
                }
                if(progress>76)
                {
                    back.setImageResource(R.drawable.sleep4);
                    return;
                }
                if(progress>57)
                {
                    back.setImageResource(R.drawable.sleep3);
                    return;
                }
                if(progress>38)
                {
                    back.setImageResource(R.drawable.sleep2);
                    return;
                }
                if(progress>19)
                {
                    back.setImageResource(R.drawable.sleep1);
                    return;
                }
                if(progress>0)
                {
                    back.setImageResource(R.drawable.sleep0);
                    return;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                originalProgress = seekBar.getProgress();
                int i = 0;
                i = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if(seekBar.getProgress() >= seekBar.getMax()-10)
                {
                    wakeup();
                }
                back.setImageResource(R.drawable.sleep0);
                originalProgress = 0;
                seekBar.setProgress(originalProgress);
            }
        };
        seekBarok.setOnSeekBarChangeListener(sbcl);
        //
        flipper_mess = (ViewFlipper) findViewById(R.id.night_mess);
        LayoutInflater inf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flipper_mess.addView(inf.inflate(R.layout.null_lay, null));
        flipper_mess.addView(inf.inflate(R.layout.pret_mess, null));
        tv_mess = (TextView) findViewById(R.id.pr_ll_tv_mess);
        flipmess("Пролистните вправо чтобы проснуться");
        //
        nd = new Night_dlg(this);
        nd.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialog)
            {
                nightbot.setVisibility(View.VISIBLE);
                player_num = next_player();
                flipmess("Пролистните вправо чтобы проснуться");
                if(tmp_list.size()==0)
                {
                    nightbot.setVisibility(View.GONE);
                    btnok.setVisibility(View.VISIBLE);
                }
            }
        });
        //
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
//    public void say(String s)
//    {
//        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//    }
    public void wakeup()
    {
        flipper_mess.setDisplayedChild(0);
        nightbot.setVisibility(View.INVISIBLE);
        nd.init(player_num);
        nd.show();
        hm.add(add_hm(Pretreatment.pl_list.get(player_num).name));
        sa.notifyDataSetChanged();
    }
    public void wakeupAll()
    {
        Engage(Night.act_list);
        setResult(RESULT_OK, this.getIntent());
        finish();
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