package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TesActivity extends AppCompatActivity {
    LinearLayout ly_soal;
    EditText nama, kelas;
    CardView selesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ly_soal = findViewById(R.id.soal_ly);
        try {
            InputStream is = getAssets().open(  "tes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("soal");
            Log.d("TOTAL", "onCreate: "+nList.getLength());
            for (int i=0;i<nList.getLength();i++){
              NodeList isi = nList.item(i).getChildNodes();
              list("isi",isi);
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void list(String tipe, NodeList nodeList){
        for(int a=0;a<nodeList.getLength();a++){
            Node jenis = nodeList.item(0);
            if(tipe.equals("isi")){
                if(jenis.getNodeName().equals("isi")){
                    getRinci(ly_soal,jenis.getChildNodes());
                }
            }
        }
    }
    public void getRinci(LinearLayout linearLayout, NodeList list){
        for(int u=0;u<list.getLength();u++){
            Node rinci = list.item(u);
            if(rinci.getNodeName().equals("teks")){
                TextView textView = new TextView(this);
                textView.setText(rinci.getTextContent());
                textView.setId(u);
                LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                ly.setMargins(10,3,2,3);
                textView.setLayoutParams(ly);
                //tv.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen._13sdp));
                textView.setPadding(4, 3, 0, 3);
                linearLayout.addView(textView);
            }
            if(rinci.getNodeName().equals("gambar")){
                // add ImageView
                Display display = getWindowManager().getDefaultDisplay();
                ImageView iv = new ImageView(this);
                int resourceId = getResources().getIdentifier (rinci.getTextContent(), "drawable", "com.vrcorp.mobilelearningfisika_gelombangbunyi");
                iv.setImageResource(resourceId);
                iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //iv.setLayoutParams(parms);
                iv.setAdjustViewBounds(true);
                // float in = getResources().getDimension(R.dimen._90sdp);
                linearLayout.addView(iv);
            }
        }
    }
}