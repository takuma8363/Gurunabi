package net.example.nwwuser.gurunabi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.Window;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Iterator;

public class MainActivity extends Activity implements LocationListener {

    private final static String BR = System.getProperty("line.separator");
    private LocationManager manager; //ロケーションマネージャ
    private TextView textView;
    private TextView latitude;
    private TextView longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //ロケーションマネージャの取得
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    //アクティビティ再開時の処理
    @Override
    protected void onResume() {

        super.onResume();
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    //アクティビティ一時停止時の処理
    @Override
    protected void onPause() {

        manager.removeUpdates(this);
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        TextView longitudeTextView = (TextView) findViewById(R.id.longitude_id);
        longitudeTextView.setText("経度:" + String.valueOf(longitude));

        TextView latitudeTextView = (TextView) findViewById(R.id.latitude_id);
        latitudeTextView.setText("緯度:" + String.valueOf(latitude));

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_segue:
                Intent intent = new Intent(this,SubActivity.class);
                startActivity(intent);
                break;
        }
    }
}/*
    public static void main(String[] args) {

        String acckey = "a8bfd45de615f9e5a244ab7f12787fc2";
        String lat = "35.670082";
        String lon = "139.763267";
        String range = "1";
        String format = "json";
        String gnaviRestUri = "http://api.gnavi.co.jp/RestSearchAPI/20150630";
        String prmFormat = "?format=" + format;
        String prmKeyid = "&keyid=" + acckey;
        String prmLat = "&latitude=" + lat;
        String prmLon = "&longitude=" + lon;
        String prmRange = "&range=" + range;

        //URL組み立て
        StringBuffer uri = new StringBuffer();
        uri.append(gnaviRestUri);
        uri.append(prmFormat);
        uri.append(prmKeyid);
        uri.append(prmLat);
        uri.append(prmLon);
        uri.append(prmRange);

        //API実行、結果を取得し出力
        getNodeList(uri.toString());
    }

    private static void getNodeList(String url) {
        try {
            URL restSearch = new URL(url);
            HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            objctMapper mapper = new ObjectMapper();
            viewJsonNode(mapper.readTree(http.getInputStream()));

        } catch (Exception e) {

        }
    }

    private static void viewJsonNode(JsonNode nodeList) {
        if (nodeList != null) {
            //トータルヒット件数
            String hitcount = "total:" + nodeList.path("total_hit_count").asText();
            System.out.println(hitcount);
            //restのみ取得
            JsonNode restList = nodeList.path("rest");
            Iterator<JsonNode> rest = restList.iterator();
            //店舗番号、店舗名、最寄の路線、最寄の駅、最寄駅から店までの時間、店舗の小業態を出力
            while (rest.hasNext()) {
                JsonNode r = rest.next();
                String id = r.path("id").asText();
                String name = r.path("name").asText();
                String line = r.path("access").path("line").asText();
                String station = r.path("access").path("station").asText();
                String walk = r.path("access").path("walk").asText() + "分";
                String categorys = "";

                for (JsonNode n : r.path("code").path("category_name_s")) {
                    categorys += n.asText();
                }
                System.out.println(id + "¥t" + name + "¥t" + line + "¥t" + station + "¥t" + walk + "¥t" + categorys);

            }
        }
    }*/
