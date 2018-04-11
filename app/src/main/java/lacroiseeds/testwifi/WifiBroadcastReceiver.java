package lacroiseeds.testwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by benja on 19/03/2018.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private WifiManager wifiManager;
    private WifiAdapter wifiAdapter;
    private List<WifiItem> listeWifiItem;

    @Override
    public void onReceive(Context context, Intent intent) {
        wifiManager = ((Fragment1) context).getCurrentWifiManager();
        wifiAdapter = ((Fragment1) context).getWifiAdapter();
        listeWifiItem = ((Fragment1) context).getListeWifiItem();



        // On vérifie que notre objet est bien instancié
        if (wifiManager != null) {

            // On vérifie que le WiFi est allumé
            if (wifiManager.isWifiEnabled()) {
                // On récupère les scans
                List<ScanResult> listeScan = wifiManager.getScanResults();

                Toast.makeText(context, "wifiManager: " + wifiManager.getConnectionInfo(), Toast.LENGTH_LONG).show();
                // On vide notre liste
                listeWifiItem.clear();

                // Pour chaque scan
                for (int i = 0; i < listeScan.size(); i++) {
                    WifiItem item = new WifiItem();

                    Toast.makeText(context, listeScan.get(i).toString(), Toast.LENGTH_SHORT).show();

                    item.setAdresseMac(listeScan.get(i).BSSID);
                    item.setAPName(listeScan.get(i).SSID);
                    item.setForceSignal(listeScan.get(i).level);

                    //Toast.makeText(context, "FormationWifi: " + scanResult.SSID + ", LEVEL: " + scanResult.level, Toast.LENGTH_SHORT).show();
                    //Log.d("FormationWifi", scanResult.SSID + " LEVEL " + scanResult.level);

                    listeWifiItem.add(item);
                }

                // On rafraichit la liste
                wifiAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Vous devez activer votre WiFi", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "le wifiManager est null", Toast.LENGTH_SHORT).show();
        }

    }

}
