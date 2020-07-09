package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class KompetensiActivity extends AppCompatActivity {

    TextView kognitif_judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompetensi);
        kognitif_judul = findViewById(R.id.kognitif_judul);
        try {
            InputStream is = getAssets().open("indikator_kognitif.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("isi");
            Log.d("Kompetensi", "onCreate: "+nList.item(0).getTextContent());
            Integer nomor = 1;
            for (int i=0; i<nList.getLength(); i++) {
                Node node = nList.item(i);
                kognitif_judul.setText(kognitif_judul.getText()+"\n"+nomor+". " + node.getTextContent()+"\n");
                nomor++;
            }
        }catch (Exception e){e.printStackTrace();}
    }
   // private static String getValue(String tag) {
        //NodeList nodeList = element.getElementsByTagName(tag);
        //Node node = nodeList.item(0);
        //return node.getNodeValue();
    //}
}