package lacroiseeds.testwifi;

/**
 * Created by benja on 19/03/2018.
 */

public class WifiItem {

    private String APName;
    private String AdresseMac;
    private int ForceSignal;

    public String getAPName() {
        return APName;
    }
    public void setAPName(String aPName) {
        APName = aPName;
    }
    public String getAdresseMac() {
        return AdresseMac;
    }
    public void setAdresseMac(String adresseMac) {
        AdresseMac = adresseMac;
    }
    public int getForceSignal() {
        return ForceSignal;
    }
    public void setForceSignal(int forceSignal) {
        ForceSignal = forceSignal;
    }

}
