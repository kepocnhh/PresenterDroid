package stan.presenter.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;


public class Main extends Activity
{
    Button buttest;//тестовая кнопка
    static String Dir = "/mnt/sdcard/Mafia";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttest = (Button)findViewById(R.id.buttest);
        buttest.setVisibility(View.INVISIBLE);//скрытие тестовой кнопки
        //
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
    //функции тестовой кнопки
    public void buttest(View v)
    {

    }
    //кнопка Начать игру
    public void butenter(View v)
    {
        startActivity(new Intent(this,Pretreatment.class));
    }
    //кнопка Настройки приложения
    public void but_prop(View v)
    {

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
    //кнопка Выход
    public void butexit(View v)
    {
        System.exit(0);
    }
}