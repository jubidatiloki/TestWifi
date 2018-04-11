package lacroiseeds.testwifi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benja on 19/03/2018.
 */

public class Fragment1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Button boutonRechercher;
    private ListView listeViewWifi;
    private ArrayList<WifiItem> listeWifiItem = new ArrayList<>();
    private WifiAdapter wifiAdapter;
    private WifiManager wifiManager;
    private WifiBroadcastReceiver broadcastReceiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listeViewWifi = findViewById(R.id.listViewWifi);
        boutonRechercher = findViewById(R.id.buttonRefresh);

        boutonRechercher.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(wifiManager != null) {
                    wifiManager.startScan();
                }else{
                    Toast.makeText(Fragment1.this, "le wifiManager est null, pas de scan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // On récupère le service WiFi d'Android
        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //Toast.makeText(this, "wm: " + wifiManager, Toast.LENGTH_SHORT).show();
        // Gestion de la liste des AP WiFi (voir tuto sur les adapters et les
        // listviews)
        wifiAdapter = new WifiAdapter(this, listeWifiItem);
        listeViewWifi.setAdapter(wifiAdapter);

        // Création du broadcast Receiver
        broadcastReceiver = new WifiBroadcastReceiver();

        // On attache le receiver au scan result
        registerReceiver(broadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    // On arrête le receiver quand on met en pause l'application
    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    // On remet en route le receiver quand on revient sur l'application
    @Override
    protected void onResume() {
        registerReceiver(broadcastReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }


    public WifiManager getCurrentWifiManager() {
        return wifiManager;
    }


    public WifiAdapter getWifiAdapter() {
        return wifiAdapter;
    }


    public List<WifiItem> getListeWifiItem() {
        return listeWifiItem;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_1) {
            intent = new Intent(this, Fragment1.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
