package stan.presenter.mafia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import stan.db.ContentDriver;
import stan.db.DBHelper;
import stan.db.contract.*;
import stan.db.contract.Action;
import stan.presenter.core.ability.Ability;
import stan.presenter.core.ability.active.changeproperty.Block;
import stan.presenter.core.ability.active.changeproperty.HealDay;
import stan.presenter.core.ability.active.changeproperty.HealNight;
import stan.presenter.core.ability.active.changeproperty.Kill;
import stan.presenter.mafia.activities.constructor.Constructor;


public class Main extends Activity
{
    private Button buttest;//тестовая кнопка
    private Button butprop;//кнопка Настройки приложения
    private AdView adView = null;
    static String Dir = "/mnt/sdcard/Mafia";
    static String DBname = "mafiadb";
    //
    private static final String AD_UNIT_ID = "ca-app-pub-6916191358710501/3703876071";
    private static final String TEST_ID = "E1225DC326BAD8F88523050107F7D2E4";
    //
    DBHelper dbHelper;

    private void initDEBUGLog()
    {
        Date newDate = new Date();
        File myPath = new File(Dir + "/DEBUG/" + (newDate.getYear() + 1900) + "/" + (newDate.getMonth() + 1));
        myPath.mkdir();
        myPath.mkdirs();
        PrintStream st = null;
        try
        {
            st = new PrintStream(new FileOutputStream(Dir + "/DEBUG/" + (newDate.getYear() + 1900) + "/" + (newDate.getMonth() + 1) + "/" + "DEBUG_" + (newDate.getYear() + 1900) + "." + (newDate.getMonth() + 1) + "_" + (newDate.getDate() / 7 + 1) + ".txt",true));
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
        System.setErr(st);
        System.setOut(st);
        System.out.println("\n\t------------------------------------");
    }
    private void initViews()
    {
        buttest = (Button)findViewById(R.id.buttest);
        buttest.setVisibility(View.INVISIBLE);//скрытие тестовой кнопки
        butprop = (Button)findViewById(R.id.butprop);
        butprop.setVisibility(View.INVISIBLE);//скрытие кнопки настройки
    }
    private void initAdvert(final Activity act)
    {
        adView = new AdView(act);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed()
            {
                //Toast.makeText(Main.this, "onAdClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int error)
            {
                String message = "onAdFailedToLoad: " + getErrorReason(error);
                //Toast.makeText(Main.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication()
            {
                //Toast.makeText(Main.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened()
            {
                //Toast.makeText(Main.this, "onAdOpened", Toast.LENGTH_SHORT).show();
                //                track( "0", "onAdOpened");
                GATracker.track(act, Build.ID, GATracker.CAT_ADMOB, "onAdOpened", null, null);
            }

            @Override
            public void onAdLoaded()
            {
                //Toast.makeText(Main.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_main);
        layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(TEST_ID)
                .build();
        adView.loadAd(adRequest);
    }
    private void initDB()
    {
//        createComissarActionJail();
//        createComissarActionKill();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
//        createsimpleaction();
    }
    private void createComissarActionJail()
    {
        String name = "Посадить в тюрьму";
        String description = "Игрока, которого посадили в тюрьму, нельзя убить, но и он не выполняет никаких действий";
        List<Ability> curentAbilities = new ArrayList<>();
        curentAbilities.add(new HealNight(getResources().getString(R.string.heal_night)));
        curentAbilities.add(new Block(getResources().getString(R.string.block)));
        Ability[] abilities = new Ability[curentAbilities.size()];
        curentAbilities.toArray(abilities);
        stan.presenter.core.action.Action a = new stan.presenter.core.action.Action(name, description, null, abilities);
        DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_ACTION), ContentDriver.getContentValues(a));
    }
    private void createComissarActionKill()
    {
        String name = "Убить";
        String description = "Сделать попытку убить игрока";
        List<Ability> curentAbilities = new ArrayList<>();
        curentAbilities.add(new Kill(getResources().getString(R.string.kill)));
        Ability[] abilities = new Ability[curentAbilities.size()];
        curentAbilities.toArray(abilities);
        stan.presenter.core.action.Action a = new stan.presenter.core.action.Action(name, description, null, abilities);
        DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_ACTION), ContentDriver.getContentValues(a));
    }
    private void createsimpleaction()
    {
        String name = "blabla";
        String description = "blablabla";
        List<Ability> curentAbilities = new ArrayList<>();
        Ability[] abilities = new Ability[curentAbilities.size()];
        curentAbilities.toArray(abilities);
        stan.presenter.core.action.Action a = new stan.presenter.core.action.Action(name, description, null, abilities);
        DBHelper.getInstance(this).insert(Contract.getContract(Contract.TABLE_NAME_ACTION), ContentDriver.getContentValues(a));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        initViews();
        initAdvert(this);
        initDEBUGLog();
        //
//        Ability a = new Kill();
        //
            initDB();
        //
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if (adView != null)
        {
            adView.resume();
        }
    }
    @Override
    public void onPause()
    {
        if (adView != null)
        {
            adView.pause();
        }
        super.onPause();
    }
    @Override
    public void onDestroy()
    {
        if (adView != null)
        {
            adView.destroy();
        }
        super.onDestroy();
    }




    //функции тестовой кнопки
    public void buttest(View v)
    {

    }
    //кнопка Начать игру
    public void butenter(View v)
    {
//        track( "0", "begin game");
        GATracker.track(this, Build.ID, GATracker.CAT_CLICK, "begin game (to pretretment)", null, null);

//        say(Build.USER+ "  " + Build.ID);
        startActivity(new Intent(this,Pretreatment.class));
    }
    //кнопка Настройки приложения
    public void but_prop(View v)
    {

    }
    //кнопка Выход
    public void butexit(View v)
    {
        GATracker.track(this, Build.ID, GATracker.CAT_CLICK, "exit", null, null);
        System.exit(0);
    }



    static public void to_Debug(String u,String s)
    {
        System.out.println(Main.getDate(new Date()) + "\n" + u +" "+ s);
    }
    public static String p_t_p(Player p)
    {
        String s = "";
        s += p.name+" ["+p.role.name+"]";
        if(p.role.rang > 0)
        {
            s += " (ранг " +p.role.rang+")";
        }
        return s;
    }
    static String getDate(Date d)
    {
        return "[" + minutes(d.getDate()+"") + "/" + minutes(d.getHours()+"") + ":" + minutes(d.getMinutes()+"") + ":" + minutes(d.getSeconds()+"") + "] ";
    }
    static public String minutes(String s)
    {
        if (s.length() == 1)
        {
            s = "0" + s;
        }
        return s;
    }
    private String getErrorReason(int errorCode)
    {
        String errorReason = "";
        switch(errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No fill";
                break;
        }
        return errorReason;
    }
    private void say(String s)
    {
        Toast.makeText(Main.this, s, Toast.LENGTH_SHORT).show();
    }

    public void constructor(View view)
    {
        startActivityForResult(new Intent(this, Constructor.class), 0);
    }
}