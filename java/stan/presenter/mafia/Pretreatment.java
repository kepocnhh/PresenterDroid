package stan.presenter.mafia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pretreatment
        extends Activity
//        implements View.OnTouchListener
{
    ViewFlipper    flipper;
    ViewFlipper    flipper_mess;
    LayoutInflater inflater;
    private float          fromPosition;
    private Activity       ac;
    private RelativeLayout rl_roles;
    private RelativeLayout rl_names;
    //
    private ListView       n_lv;
    private EditText       et_name;
    private CheckBox       cb_bot;
    private TextView       tv_to;
    private ImageView      iv_arr;
    private TextView       tv_mess;
    private TextView       tv_nco;
    private TextView       tv_rco;
    //
    private ListView       r_lv;
    //private TextView r_tv_mess;
    //
    private ArrayList<HashMap<String, String>> hm = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    private ArrayList<HashMap<String, String>> hm_roles = new ArrayList<HashMap<String, String>>();
    private        SimpleAdapter sa_roles;
    //
    public static  List<Player>  pl_list;
    public static  List<Role>    rl_list;
    private static List<Role>    rl_to_play;
    private static int           rl_count;
    private final static int    min_players = 5;

    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        finish();
    }

    //кнопка
    public void b_ret_main(View v)
    {
        finish();
    }

    //кнопка
    public void play_game(View v)
    {
        if(check())
        {
            while(rl_to_play.size() > 0)
            {
                int roles = 0;
                roles = Integer.parseInt(hm_roles.get(
                        hm_roles.size() - rl_to_play.size()).get("count"));
                for(int i = 0; i < roles; i++)
                {
                    add_role(rl_to_play.get(0));
                }
                rl_to_play.remove(0);
            }
            startActivityForResult(new Intent(Pretreatment.this, Day.class), 0);
        }
    }

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
    private void flipprev(ViewFlipper f)
    {
        f.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_out));
        f.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.right));
        f.showPrevious();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        int t = ev.getAction();
        if(t == MotionEvent.ACTION_DOWN)
        {
            fromPosition = ev.getX();
        }
        else if(t == MotionEvent.ACTION_UP)
        {
            float toPosition = ev.getX();
            if(flipper.getDisplayedChild() == 0)
            {
                if(fromPosition > toPosition + 200)
                {
                    tv_to.setText(getResources().getString(R.string.to_names));
                    say(getString(R.string.slide_right));
                    iv_arr.setImageResource(R.drawable.arrowback);
                    flipnext(flipper);
                }
            }
            else
            {
                if(fromPosition < toPosition - 200)
                {
                    tv_to.setText(getResources().getString(R.string.to_roles));
                    say(getString(R.string.slide_left));
                    iv_arr.setImageResource(R.drawable.arrowfront);
                    flipprev(flipper);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pret);
        ac = this;
        //
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        //mainLayout.setOnTouchListener(this);
        //
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flipper.addView(inflater.inflate(R.layout.names, null));
        flipper.addView(inflater.inflate(R.layout.roles, null));
        //
        flipper_mess = (ViewFlipper) findViewById(R.id.flipper_mess);
        LayoutInflater inf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flipper_mess.addView(inf.inflate(R.layout.null_lay, null));
        flipper_mess.addView(inf.inflate(R.layout.pret_mess, null));
        //
        tv_nco = (TextView) findViewById(R.id.ncount);
        tv_rco = (TextView) findViewById(R.id.rcount);
        //
        init_players();
        init_roles();
        //TEST
            Player p;
            p = new Player("Принтер");
            pl_list.add(p);
            hm.add(addar(p.name));
            p = new Player("Чеков");
            pl_list.add(p);
            hm.add(addar(p.name));
            p = new Player("Бургер");
            pl_list.add(p);
            hm.add(addar(p.name));
            p = new Player("Кинг");
            pl_list.add(p);
            hm.add(addar(p.name));
            p = new Player("Технологический");
            pl_list.add(p);
            hm.add(addar(p.name));
            sa.notifyDataSetChanged();
        //TEST
        say(getString(R.string.slide_left));
    }
    public void say(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    private void init_players()
    {
        n_lv = (ListView) findViewById(R.id.n_lv1);
        hm = new ArrayList<HashMap<String, String>>();
        sa = new SimpleAdapter(this,
                               hm,
                               R.layout.n_list_item, new String[]{
                "name"
        }, new int[]{
                R.id.text2})
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                ImageButton b1;
                b1 = (ImageButton) view.findViewById(R.id.n_li_ib_x);
                final int p = position;
                b1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        pl_list.remove(p);
                        hm.remove(p);
                        sa.notifyDataSetChanged();
                        check();
                    }
                });
                return view;
            }

            ;
        };
        n_lv.setAdapter(sa);
        et_name = (EditText) findViewById(R.id.n_et_name);
        cb_bot = (CheckBox) findViewById(R.id.n_cb_bot);
        tv_mess = (TextView) findViewById(R.id.pr_ll_tv_mess);
        tv_to = (TextView) findViewById(R.id.p_tv_to);
        tv_to.setText(getResources().getString(R.string.to_roles));
        iv_arr = (ImageView) findViewById(R.id.arrow);
        iv_arr.setImageResource(R.drawable.arrowfront);
        //
        pl_list = new ArrayList<Player>();
    }

    public void add_role(Role r)
    {
        boolean more = false;
        Role newrole = null;
        if(r.rang > -1)
        {
            for(int i = 0; i < rl_list.size(); i++)
            {
                if(rl_list.get(i).UI == r.UI &&
                        rl_list.get(i).rang > -1 &&
                        rl_list.get(i).rang >= r.rang)
                {
                    more = true;
                    r.rang = rl_list.get(i).rang;
                    if(rl_list.get(i).act != null && !r.rang_shot)
                    {
                        r.act = rl_list.get(i).act;
                    }
                }
            }
            newrole = r.clone(r.name, r.act);
            if(more || newrole.rang == 0)
            {
                newrole.rang++;
            }
        }
        else if(r.act != null)
        {
            newrole = r.clone();
        }
        else
        {
            newrole = r;
        }
        rl_list.add(newrole);
    }

    private void init_roles()
    {
        //create actions
        Action[] for_police = new Action[]{
                new Action.Jail(),
                new Action.Kill(false)};
        //create roles
        Role peace = new Role("Мирный житель", Role.TypeRole.peace, Role.TypeVisibility.peace);
        Role maniac_group = new Role("Насильник", Role.TypeRole.peace, Role.TypeVisibility.peace,
                                     new Action.Violence(), true, 0, 2);
        Role maniac = new Role("Насильник", Role.TypeRole.peace, Role.TypeVisibility.peace,
                               new Action.Violence());
        Role mafia = new Role("Мафия", Role.TypeRole.mafia, Role.TypeVisibility.mafia,
                              new Action.Kill(true), false, 0, 1);
        Role police = new Role("Коммисар", Role.TypeRole.peace, Role.TypeVisibility.peace,
                               for_police, false, -1, -1);
        Role doctor = new Role("Доктор", Role.TypeRole.peace, Role.TypeVisibility.peace,
                               new Action.Doctor_heal());
        rl_list = new ArrayList<Role>();
        rl_to_play = new ArrayList<Role>();
        rl_to_play.add(peace);
        rl_to_play.add(police);
        rl_to_play.add(doctor);
        //rl_to_play.add(maniac);
        rl_to_play.add(mafia);
        //
        r_lv = (ListView) findViewById(R.id.r_lv_roles);
        sa_roles = new SimpleAdapter(this,
                                     hm_roles,
                                     R.layout.r_list_item, new String[]{
                "count",
                "role"
        }, new int[]{
                R.id.r_li_tv_rcount,
                R.id.r_li_tv_rname})
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                final int p = position;

                ImageButton b_min = (ImageButton) view.findViewById(R.id.r_li_b_minus);
                ImageButton b_plus = (ImageButton) view.findViewById(R.id.r_li_b_plus);
                b_min.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        int c = 0;
                        String tmp = hm_roles.get(p).get("count");
                        c = Integer.parseInt(tmp);
                        if(c > 0)
                        {
                            rl_count--;
                            hm_roles.set(p, hm_roles((c - 1) + "", hm_roles.get(
                                    p).get("role")));
                            sa_roles.notifyDataSetChanged();
                            check();
                        }
                    }
                });
                b_plus.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        int c = 0;
                        String tmp = hm_roles.get(p).get("count");
                        c = Integer.parseInt(tmp);
                        if(c < 9)
                        {
                            rl_count++;
                            hm_roles.set(p, hm_roles((c + 1) + "",
                                                     hm_roles.get(
                                                             p).get("role")));
                            sa_roles.notifyDataSetChanged();
                            check();
                        }
                    }
                });
                return view;
            }

            ;
        };
        r_lv.setAdapter(sa_roles);
        for(int i = 0; i < rl_to_play.size(); i++)
        {
            hm_roles.add(hm_roles("0", rl_to_play.get(i).name));
        }
        sa_roles.notifyDataSetChanged();
        rl_count = 0;
    }

    //
    private HashMap<String, String> addar(String s)
    {
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("name", s);
        return hm2;
    }

    private HashMap<String, String> hm_roles(String c, String r)
    {
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("count", c);
        hm2.put("role", r);
        return hm2;
    }

    //кнопка
    private void add_to_list()
    {
        String name = et_name.getText().toString();
        if(name.length() == 0)
        {
            flipmess(getResources().getString(R.string.empty_name));
            return;
        }
        Player p = new Player(name);
        name = "";
        if(cb_bot.isChecked())
        {
            p.bot = true;
            name = " (" +getResources().getString(R.string.bot)+ ")";
        }
        pl_list.add(p);
        hm.add(addar(p.name + name));
        sa.notifyDataSetChanged();
        check();
        et_name.setText("");
        cb_bot.setChecked(false);
    }

    //кнопка
    public void b_add(View v)
    {
        add_to_list();
    }

    public boolean check()
    {
        boolean p = true;
        boolean r = false;
        //
            tv_nco.setText(pl_list.size() + "");
            if(pl_list.size() < min_players)
            {
                tv_nco.setTextColor(getResources().getColor(R.color.cred));
                flipmess(getResources().getString(R.string.players_less) + " " + min_players);
                p = false;
            }
            else
            {
                tv_nco.setTextColor(getResources().getColor(R.color.cnewgreen));
                flipper_mess.setDisplayedChild(0);
            }
        //
            tv_rco.setText(rl_count + "");
        int mafnum = 0;
        for(int i=0; i<rl_to_play.size(); i++)
        {
            if(rl_to_play.get(i).Get_TV() == Role.TypeVisibility.mafia)
            {
                mafnum = Integer.parseInt(hm_roles.get(i).get("count"));
                break;
            }
        }
            if(rl_count == pl_list.size() && rl_count >= min_players && mafnum > 0)
            {
                tv_rco.setTextColor(getResources().getColor(R.color.cnewgreen));
            }
            else
            {
                tv_rco.setTextColor(getResources().getColor(R.color.cred));
            }
            if(p)
            {
                String res = "";
                if(rl_count >= min_players)
                {
                    if(mafnum == 0)
                    {
                        res = getResources().getString(R.string.zero_mafia);
                    }
                    else if(mafnum >= (rl_count/2))
                    {
                        res = getResources().getString(R.string.too_many_mafia);
                    }
                    else if(rl_count == pl_list.size())
                    {
                        r = true;
                    }
                    else if(rl_count < pl_list.size())
                    {
                        res = getResources().getString(R.string.remains_roles) + " " + (pl_list.size() - rl_count);
                    }
                    else if(rl_count > pl_list.size())
                    {
                        res = getResources().getString(R.string.role_more);
                    }
                }
                else
                {
                    res = getResources().getString(R.string.role_less) + " " + min_players;
                }
                flipmess(res);
            }
        //
        return p && r;
    }
}