package home.mihir.project.myapplication;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TimeLog extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS

    int number =0;
    String s ="";
    public SharedPreferences prefs;
    public TimeLog() {
        super("TimeLog");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        while(true)
        {
            try {
//                prefs = getApplicationContext().getSharedPreferences("SharedPref",MODE_APPEND);
//                SharedPreferences.Editor edit = prefs.edit();
//
//                edit.putString("time", getTimeStemp());
//                edit.commit();



                ///

                final Intent i =new Intent();
                i.setAction("com.data");
                s = getTimeStemp();
                i.putExtra("time",s);
                i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                i.setComponent(new ComponentName("home.mihir.project.standalone_app","home.mihir.project.standalone_app.MainActivity"));
                sendBroadcast(i);
                Log.d("LOG",s);
                ///
                new Thread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public String getTimeStemp(){
        String pattern="000";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(number);

        String DATE_FORMAT = "yyyy-MM-dd'_'HH:mm:ss" ;
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        number++;
        return  output+"_"+sdf.format(new Date())+"_mli_test";
    }


}
