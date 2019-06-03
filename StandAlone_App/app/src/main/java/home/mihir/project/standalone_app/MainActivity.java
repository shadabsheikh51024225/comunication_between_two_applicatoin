package home.mihir.project.standalone_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView Logs;
    private MyBroadcastReceiver MyReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logs =(TextView) findViewById(R.id.TextView);
        Context myContext = null;
//        try {
//            myContext = getApplicationContext().createPackageContext("home.mihir.project.myapplication", Context.CONTEXT_IGNORE_SECURITY);
//            SharedPreferences testPrefs = myContext.getSharedPreferences("SharedPref", Context.MODE_MULTI_PROCESS);
//            String prefString = testPrefs.getString("time", "Couldn't find");
//            Log.d("--------------",prefString);
//            Logs.setText(prefString);
//            new Thread().sleep(3000);
//        } catch (PackageManager.NameNotFoundException e) {
//        e.printStackTrace();
//    } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        IntentFilter intentFilter = new IntentFilter("com.pkg.perform.Ruby");
        MyReceiver =new MyBroadcastReceiver();

        if(intentFilter!=null)
        {
            registerReceiver(MyReceiver,intentFilter);

        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            Logs.setText(intent.getStringExtra("time").toString());

            Log.d("-------",intent.getStringExtra("time").toString());
            Toast.makeText(MainActivity.this,"Data Received from External App",Toast.LENGTH_SHORT).show();

        }


    } @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(MyReceiver!= null)
            unregisterReceiver(MyReceiver);
    }

}

