package stan.presenter.mafia;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Night_dlg
        extends Dialog
        implements View.OnClickListener
{
    private Activity activity;
    private Button   btnok;
    private SeekBar  seekBarok;
    private TextView name_tv;
    private TextView role_tv;
    private TextView group_tv;
    private Spinner  sp_act;
    private Spinner  sp_pl;
    private int      player;

    public Night_dlg(Activity activity)
    {
        super(activity);
        this.activity = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.night_dlg);
        setCancelable(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.FILL_PARENT;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void init(int p)
    {
        LinearLayout llact = (LinearLayout) findViewById(R.id.ndlg_ll_act);
        llact.setVisibility(View.VISIBLE);
        TextView tvnoact = (TextView) findViewById(R.id.ndlg_tv_noact);
        btnok = (Button) findViewById(R.id.nig_dlg_b_engage);
        btnok.setOnClickListener(this);
        seekBarok = (SeekBar) findViewById(R.id.nig_dlg_sb_engage);
        seekBarok.setVisibility(View.VISIBLE);
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
                    seekBar.setProgress(originalProgress);
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
                    seekBarok.setVisibility(View.GONE);
                    btnok.setVisibility(View.VISIBLE);
                }
                originalProgress = 0;
                seekBar.setProgress(originalProgress);
            }
        };
        seekBarok.setOnSeekBarChangeListener(sbcl);
        name_tv = (TextView) findViewById(R.id.nig_dlg_tv_name);
        name_tv.setText(Pretreatment.pl_list.get(p).name);
        role_tv = (TextView) findViewById(R.id.nig_dlg_tv_role);
        group_tv = (TextView) findViewById(R.id.nig_dlg_tv_group);
        String role = Pretreatment.pl_list.get(p).role.name;
        if(Pretreatment.pl_list.get(p).role.rang > 0)
        {
            role += " - " +getStringR(R.string.rang)+ " " + Pretreatment.pl_list.get(p).role.rang;
            show_role_group(p);
            group_tv.setVisibility(View.VISIBLE);
        }
        else
        {
            group_tv.setVisibility(View.GONE);
        }
        role_tv.setText(role);
        //
        sp_act = (Spinner) findViewById(R.id.nig_dlg_sp_act);
        sp_pl = (Spinner) findViewById(R.id.nig_dlg_sp_pl);
        if(Pretreatment.pl_list.get(p).role.act != null &&
                (Pretreatment.pl_list.get(p).role.rang_shot ||
                        Pretreatment.pl_list.get(p).role.rang == 1 ||
                        Pretreatment.pl_list.get(p).role.rang < 0))
        {
            List<String> actions = new ArrayList<String>();
            List<String> players = new ArrayList<String>();
            for(int i = 0; i < Pretreatment.pl_list.get(p).role.act.length; i++)
            {
                actions.add(Pretreatment.pl_list.get(p).role.act[i].name);
            }
            for(int i = 0; i < Pretreatment.pl_list.size(); i++)
            {
                players.add(Pretreatment.pl_list.get(i).name);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.n_sp_item,
                                                                    actions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_act.setAdapter(adapter);
            sp_act.setSelection(0);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(activity, R.layout.n_sp_item,
                                                                     players);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_pl.setAdapter(adapter2);
            sp_pl.setSelection(0);
            seekBarok.setVisibility(View.GONE);
            tvnoact.setVisibility(View.GONE);
        }
        else
        {
            Main.to_Debug(Main.p_t_p(Pretreatment.pl_list.get(p)), getStringR(R.string.doing_nothing)+ "\n");
            llact.setVisibility(View.GONE);
            btnok.setVisibility(View.GONE);
            tvnoact.setVisibility(View.VISIBLE);
            tvnoact.setText(role + " " + getStringR(R.string.doing_nothing));
        }
        player = p;
    }

    private void show_role_group(int me)
    {
        String res = "";
        for(int k = 0; k < Pretreatment.pl_list.size(); k++)
        {
            if(k == me)
            {
                continue;
            }
            if(Pretreatment.pl_list.get(k).role.UI == Pretreatment.pl_list.get(me).role.UI &&
                    Pretreatment.pl_list.get(k).role.rang > -1)
            {
                String m = Pretreatment.pl_list.get(k).name + " - " +getStringR(R.string.rang)+ " " + Pretreatment.pl_list.get(
                        k).role.rang + " ";
                if(res.length() > 0)
                {
                    res += "\n";
                }
                res += m;
                if(Pretreatment.pl_list.get(me).role.rang_shot || Pretreatment.pl_list.get(
                        k).role.rang == 1)
                {
                    String m2 = " " + getStringR(R.string.not_made_choice);
                    if(Pretreatment.pl_list.get(k).role.act != null)
                    {
                        for(int q = 0; q < Pretreatment.pl_list.get(k).role.act.length; q++)
                        {
                            if(Pretreatment.pl_list.get(k).role.act[q].to != -1)
                            {
                                m2 = Pretreatment.pl_list.get(
                                        k).role.act[q].name + " - " + Pretreatment.pl_list.get(
                                        Pretreatment.pl_list.get(k).role.act[q].to).name;
                                break;
                            }
                        }
                    }
                    else
                    {
                        m2 = " " + getStringR(R.string.doing_nothing);
                    }
                    res += "\n" + m2;
                }
            }
        }
        group_tv.setText(res);
    }

    private void result(int n, int act)
    {
        Pretreatment.pl_list.get(player).role.act[act].to = n;
        if(Pretreatment.pl_list.get(player).role.rang < 0 ||
                Pretreatment.pl_list.get(player).role.rang == 1 ||
                Pretreatment.pl_list.get(player).role.rang_shot)
        {
            Pretreatment.pl_list.get(player).role.act[act].from = player;
            if(Pretreatment.pl_list.get(player).role.act[act].try_stop)
            {
                Pretreatment.pl_list.get(n).try_stop = true;
            }
            Night.act_list.add(Pretreatment.pl_list.get(player).role.act[act]);
        }
        Main.to_Debug(Main.p_t_p(Pretreatment.pl_list.get(player)),
                      Pretreatment.pl_list.get(player).role.act[act].name + " " + Main.p_t_p(
                              Pretreatment.pl_list.get(n)) + "\n");
    }

    private void buton_Ok()
    {
        if(Pretreatment.pl_list.get(player).role.act != null && (
                Pretreatment.pl_list.get(player).role.rang < 0 ||
                        Pretreatment.pl_list.get(player).role.rang == 1 ||
                        Pretreatment.pl_list.get(player).role.rang_shot))
        {
            int n = sp_pl.getSelectedItemPosition();
            int act = sp_act.getSelectedItemPosition();
            if(n == player && !Pretreatment.pl_list.get(player).role.act[act].selfie)
            {
                say(Pretreatment.pl_list.get(player).role.name + " " + getStringR(R.string.can_not_act));
                return;
            }
            result(n, act);
        }
        cancel();
    }
    private String getStringR(int n)
    {
        return activity.getResources().getString(n);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.nig_dlg_b_engage:
                buton_Ok();
        }
    }

    public void say(String s)
    {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }
}