package stan.presenter.mafia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class Pretreatment extends Activity implements View.OnTouchListener
{
    ViewFlipper flipper;
    LayoutInflater inflater;
    private float fromPosition;
    private Activity ac;
    //
        private ListView n_lv;
        private EditText et_name;
        private CheckBox cb_bot;
        private TextView tv_to;
        private TextView tv_mess;
        //
        private ListView r_lv;
        //private TextView r_tv_mess;
    //
    private ArrayList<HashMap<String, String>> hm = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa;
    private ArrayList<HashMap<String, String>> hm_roles = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter sa_roles;
    //
    public static List<Player> pl_list;
    public static List<Role> rl_list;
    private static List<Role> rl_to_play;
    private static int rl_count;
////////////////////////////////////////////////////////////////////////////////////////
    //кнопка
    public void b_ret_main(View v)
    {
        finish();
    }
    //кнопка
    public void play_game(View v)
    {
        if(check_count() && check_count_roles())
        {
            while(rl_to_play.size()>0)
            {
                int roles = 0;
                roles = Integer.parseInt(hm_roles.get(hm_roles.size() - rl_to_play.size()).values().toArray()[0].toString());
                for(int i=0;i<roles;i++)
                {
                    add_role(rl_to_play.get(0));
                }
                rl_to_play.remove(0);
            }
            startActivityForResult(new Intent(Pretreatment.this, Day.class), 0);
        }
        else
        {
            say("players != roles");
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if(flipper.getDisplayedChild() == 0)
                {
                    if (fromPosition > toPosition)
                    {
                        flipper.showNext();
                    }
                }
                else
                {
                    if (fromPosition < toPosition)
                    {
                        flipper.showPrevious();
                    }
                }
                if(flipper.getDisplayedChild() == 0)
                {
                    tv_to.setText("к выбору ролей -->");
                }
                else
                {
                    tv_to.setText("<-- к добавлению игроков");
                }
            default:
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pret);
        ac = this;
        //
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);
        //
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        flipper.addView(inflater.inflate(R.layout.names, null));
        flipper.addView(inflater.inflate(R.layout.roles, null));
        //
        init_players();
        init_roles();
        //
    }
    private void init_players()
    {
        n_lv = (ListView) findViewById(R.id.n_lv1);
        hm = new ArrayList<HashMap<String, String>>();
        sa = new SimpleAdapter(this,
                hm,
                R.layout.n_list_item, new String[]{
                "name",
                "del"
        }, new int[]{
                R.id.text2,
                R.id.button})
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                Button b1;
                b1 = (Button) view.findViewById(R.id.button);
                final int p = position;
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pl_list.remove(p);
                        hm.remove(p);
                        sa.notifyDataSetChanged();
                        check_count();
                    }
                });
                return view;
            };
        };
        n_lv.setAdapter(sa);
        et_name = (EditText) findViewById(R.id.n_et_name);
        et_name.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                return false;
            }
        });
        cb_bot = (CheckBox) findViewById(R.id.n_cb_bot);
        tv_mess = (TextView) findViewById(R.id.p_tv_mess);
        tv_to = (TextView) findViewById(R.id.p_tv_to);
        tv_to.setText("к выбору ролей -->");
        //
        pl_list = new ArrayList<Player>();
        check_count();
    }
    public  void add_role(Role r)
    {
        boolean more = false;
        Role newrole = null;
        if(r.rang>-1)
        {
            for(int i=0; i<rl_list.size(); i++)
            {
                if(rl_list.get(i).UI == r.UI &&
                        rl_list.get(i).rang>-1 &&
                        rl_list.get(i).rang>=r.rang)
                {
                    more = true;
                    r.rang = rl_list.get(i).rang;
                    if(rl_list.get(i).act!=null && !r.rang_shot)
                    {
                        r.act = rl_list.get(i).act;
                    }
                }
            }
            newrole = r.clone(r.name, r.act);
            if(more||newrole.rang == 0)
            {
                newrole.rang ++;
            }
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
        Role maniac_group = new Role("Насильник", Role.TypeRole.peace, Role.TypeVisibility.peace, new Action.Violence(),true,0,2);
        Role maniac = new Role("Насильник", Role.TypeRole.peace, Role.TypeVisibility.peace, new Action.Violence());
        Role mafia = new Role("Мафия", Role.TypeRole.mafia, Role.TypeVisibility.mafia, new Action.Kill(true), false, 0,1);
        Role police = new Role("Коммисар", Role.TypeRole.peace, Role.TypeVisibility.peace, for_police, false, -1,-1);
        Role doctor = new Role("Доктор", Role.TypeRole.peace, Role.TypeVisibility.peace, new Action.Doctor_heal());
        rl_list = new ArrayList<Role>();
        rl_to_play = new ArrayList<Role>();
        rl_to_play.add(peace);
        rl_to_play.add(police);
        rl_to_play.add(doctor);
        rl_to_play.add(maniac);
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
//                NumberPicker np = (NumberPicker) view.findViewById(R.id.r_li_np);
//                np.setMinValue(0);
//                np.setOnScrollListener(new NumberPicker.OnScrollListener()
//                {
//                    @Override
//                    public void onScrollStateChange(NumberPicker numberPicker, int i)
//                    {
//                        int c = 0;
//                        String tmp = hm_roles.get(p).values().toArray()[0].toString();
//                        c = Integer.parseInt(tmp);
//                        rl_count = i;
//                        hm_roles.set(p,hm_roles((c-1)+"",hm_roles.get(p).values().toArray()[1].toString()));
//                        sa_roles.notifyDataSetChanged();
//                        check_count_roles();
//                    }
//                });
                Button b_min = (Button) view.findViewById(R.id.r_li_b_minus);
                Button b_plus = (Button) view.findViewById(R.id.r_li_b_plus);
                b_min.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        int c = 0;
                        String tmp = hm_roles.get(p).values().toArray()[0].toString();
                        c = Integer.parseInt(tmp);
                        if(c>0)
                        {
                            rl_count--;
                            hm_roles.set(p,hm_roles((c-1)+"",hm_roles.get(p).values().toArray()[1].toString()));
                            sa_roles.notifyDataSetChanged();
                            check_count_roles();
                        }
                    }
                });
                b_plus.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        int c = 0;
                        String tmp = hm_roles.get(p).values().toArray()[0].toString();
                        c = Integer.parseInt(tmp);
                        rl_count++;
                            hm_roles.set(p,hm_roles((c+1)+"",hm_roles.get(p).values().toArray()[1].toString()));
                            sa_roles.notifyDataSetChanged();
                            check_count_roles();
                    }
                });
                return view;
            };
        };
        r_lv.setAdapter(sa_roles);
        for(int i=0;i<rl_to_play.size();i++)
        {
            hm_roles.add(hm_roles("0",rl_to_play.get(i).name));
        }
        sa_roles.notifyDataSetChanged();
        //
        //r_tv_mess = (TextView) findViewById(R.id.r_tv_mess);
        rl_count = 0;
        check_count_roles();
    }
    //
    private HashMap<String, String> addar(String s)
    {
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("name", s);
        hm2.put("del", "X");
        return hm2;
    }
    private HashMap<String, String> hm_roles(String c,String r)
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
        if(name.length()==0)
        {
            say("empty name!");
            return;
        }
        Player p = new Player(name);
        name = "";
        if(cb_bot.isChecked())
        {
            p.bot = true;
            name = " (bot)";
        }
        pl_list.add(p);
        hm.add(addar(p.name+name));
        sa.notifyDataSetChanged();
        check_count();
        et_name.setText("");
        et_name.clearFocus();
        cb_bot.setChecked(false);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    //кнопка
    public void b_add(View v)
    {
        add_to_list();
    }
    //
    public void say(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    //
    public boolean check_count()
    {
        if(pl_list.size()<5)
        {
            tv_mess.setText("players < 5");
            return false;
        }
        else
        {
            tv_mess.setText("");
        }
        return true;
    }
    public boolean check_count_roles()
    {
        String res = "";
        boolean bres = true;
        if(pl_list.size()>=5)
        {
            if(rl_count != pl_list.size())
            {
                bres = false;
                if(rl_count<pl_list.size())
                {
                    res = "осталось выбрать "+(pl_list.size()-rl_count)+" ролей";
                }
                else if(rl_count>pl_list.size())
                {
                    res = "ролей больше чем игроков!";
                }
            }
        }
        else
        {
            res = "players < 5";
            bres = false;
        }
        tv_mess.setText(res);
        return bres;
    }
}