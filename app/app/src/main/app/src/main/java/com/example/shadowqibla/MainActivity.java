package com.example.shadowqibla;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);

        String html =
        "<!DOCTYPE html><html lang='ar'><head><meta charset='UTF-8'>"
        +"<title>تحديد القبلة بالظل</title>"

        +"<style>"
        +"body{margin:0;background:#f2f2f2;font-family:Arial;text-align:center}"
        +"#canvas{position:relative;width:100vw;height:85vh}"
        +"#point{width:10px;height:10px;background:black;border-radius:50%;"
        +"position:absolute;top:50%;left:50%;transform:translate(-50%,-50%)}"

        +"#shadow{width:3px;height:220px;background:black;"
        +"position:absolute;top:50%;left:50%;"
        +"transform:translate(-50%,-220px)}"

        +"#qibla{width:3px;height:220px;background:red;"
        +"position:absolute;top:50%;left:50%;"
        +"transform-origin:bottom center}"
        +"select{margin:10px;padding:8px;font-size:16px}"
        +"</style></head>"

        +"<body>"
        +"<select id='wilaya'></select>"
        +"<div id='canvas'>"
        +"<div id='point'></div>"
        +"<div id='shadow'></div>"
        +"<div id='qibla'></div>"
        +"</div>"

        +"<script>"
        +"const wilayas=["
        +"['Adrar',27.97,0.27],['Chlef',36.17,1.33],['Laghouat',33.8,2.88],"
        +"['Oum El Bouaghi',35.87,7.11],['Batna',35.55,6.17],['Bejaia',36.75,5.07],"
        +"['Biskra',34.85,5.73],['Bechar',31.62,-0.62],['Blida',36.47,2.83],"
        +"['Bouira',36.38,3.9],['Tamanrasset',22.79,5.52],['Tebessa',35.41,8.13],"
        +"['Tlemcen',34.88,-1.32],['Tiaret',35.37,1.32],['Tizi Ouzou',36.71,4.05],"
        +"['Algiers',36.77,3.06],['Djelfa',34.68,3.26],['Jijel',36.81,5.76],"
        +"['Setif',36.17,5.41],['Saida',34.83,0.15],['Skikda',36.87,6.91],"
        +"['Sidi Bel Abbes',35.19,-0.63],['Annaba',36.9,7.76],['Guelma',36.46,6.57],"
        +"['Constantine',36.36,6.61],['Medea',36.26,2.75],['Mostaganem',35.93,0.09],"
        +"['Msila',35.71,4.56],['Mascara',35.38,0.14],['Ouargla',31.95,5.32],"
        +"['Oran',35.7,-0.63],['El Bayadh',33.68,8.12],['Illizi',26.5,8.15],"
        +"['Bordj',36.07,4.73],['Boumerdes',36.76,3.91],['El Tarf',36.77,8.3],"
        +"['Tindouf',27.23,-8.13],['Tissemsilt',35.6,1.8],['El Oued',33.36,6.86],"
        +"['Khenchela',35.43,7.1],['Souk Ahras',36.28,7.95],['Tipaza',36.58,2.45],"
        +"['Mila',36.45,6.26],['Ain Defla',36.25,1.91],['Naama',32.93,0.46],"
        +"['Ain Temouchent',35.3,-1.14],['Ghardaia',32.49,3.67],['Relizane',35.73,0.57]"
        +"];"

        +"let sel=document.getElementById('wilaya');"
        +"wilayas.forEach((w,i)=>{"
        +"let o=document.createElement('option');o.value=i;o.text=w[0];sel.add(o);});"

        +"function rad(d){return d*Math.PI/180;}"

        +"function qiblaAngle(lat,lon){"
        +"let kaabaLat=rad(21.4225),kaabaLon=rad(39.8262);"
        +"lat=rad(lat);lon=rad(lon);"
        +"let d=kaabaLon-lon;"
        +"return Math.atan2(Math.sin(d),"
        +"Math.cos(lat)*Math.tan(kaabaLat)-Math.sin(lat)*Math.cos(d));}"

        +"function shadowAngle(lat){"
        +"let d=new Date();"
        +"let n=Math.floor((d-new Date(d.getFullYear(),0,0))/86400000);"
        +"let decl=rad(23.44)*Math.sin(2*Math.PI*(n-81)/365);"
        +"let t=d.getHours()+d.getMinutes()/60;"
        +"let H=rad(15*(t-12));"
        +"return Math.atan2(Math.sin(H),"
        +"Math.cos(H)*Math.sin(rad(lat))-Math.tan(decl)*Math.cos(rad(lat)));}"

        +"function update(lat,lon){"
        +"let q=qiblaAngle(lat,lon);"
        +"let s=shadowAngle(lat);"
        +"let angle=q-(s+Math.PI);"
        +"document.getElementById('qibla').style.transform="
        +"'translate(-50%, -220px) rotate('+angle+'rad)';}"

        +"sel.onchange=()=>{let w=wilayas[sel.value];update(w[1],w[2]);};"

        +"navigator.geolocation.getCurrentPosition(p=>"
        +"update(p.coords.latitude,p.coords.longitude));"

        +"</script></body></html>";

        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
