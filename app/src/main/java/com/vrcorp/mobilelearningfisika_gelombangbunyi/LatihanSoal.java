package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vrcorp.mobilelearningfisika_gelombangbunyi.materi.MekanismePendengaran;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.lang.String.valueOf;

public class LatihanSoal extends AppCompatActivity {
    LinearLayout soal, a_isi, b_isi, c_isi, d_isi, e_isi, pembahasan_isi, bahas_ly;
    CardView a, b, c,d,e,jawaban, pembahasan, next, cardJawab, home, close;
    Integer nomor=0, siapBahas=0;
    String kunci,jawab;
    boolean visible, maximal;
    TextView nomorSoal;
    NodeList nList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_soal);
        nomorSoal = findViewById(R.id.nomor_soal);
        soal = findViewById(R.id.soal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        home = findViewById(R.id.btn_home);
        close = findViewById(R.id.btn_close);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LatihanSoal.this, MainActivity.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LatihanSoal.this, LatihanList.class);
                startActivity(intent);
            }
        });
        a_isi = findViewById(R.id.opsi_a_isi);
        b_isi = findViewById(R.id.opsi_b_isi);
        c_isi = findViewById(R.id.opsi_c_isi);
        d_isi = findViewById(R.id.opsi_d_isi);
        e_isi = findViewById(R.id.opsi_e_isi);
        jawaban = findViewById(R.id.cek_jawaban);
        pembahasan = findViewById(R.id.pembahasan);
        nomorSoal = findViewById(R.id.nomor_soal);
        bahas_ly = findViewById(R.id.jwb_ly);
        next = findViewById(R.id.next);
        pembahasan_isi = findViewById(R.id.pembahasan_isi);
        a = findViewById(R.id.opsi_a);
        b = findViewById(R.id.opsi_b);
        c = findViewById(R.id.opsi_c);
        d = findViewById(R.id.opsi_d);
        e = findViewById(R.id.opsi_e);
        ambilSoal(nomor);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maximal){
                    Toast.makeText(getApplicationContext(),"Sudah diakhir soal",Toast.LENGTH_LONG).show();
                }else{
                    nomor++;
                    siapBahas=0;
                    clearJawaban();
                    ambilSoal(nomor);
                    Toast.makeText(getApplicationContext(),valueOf(nomor),Toast.LENGTH_LONG).show();
                }
            }
        });
        pembahasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(siapBahas>1){
                    if(visible){
                        bahas_ly.setVisibility(View.GONE);
                        visible=false;
                    }else{
                        bahas_ly.setVisibility(View.VISIBLE);
                        visible=true;
                    }
                }else{
                    visible=false;
                    Toast.makeText(getApplicationContext(),"Harap Cek jawaban terlebih dahulu",Toast.LENGTH_LONG).show();
                    bahas_ly.setVisibility(View.GONE);
                }
            }
        });
        jawaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban(kunci,jawab,cardJawab);
                siapBahas=2;
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawab="a";
                cardJawab = a;
                pilihJawaban(a);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawab="b";
                pilihJawaban(b);
                cardJawab = b;
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawab="c";
                pilihJawaban(c);
                cardJawab = c;
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawab="d";
                pilihJawaban(d);
                cardJawab=d;
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jawab="e";
                pilihJawaban(e);
                cardJawab=e;
            }
        });
    }
    public void clearChild(LinearLayout ly){
        if( ly.getChildCount() > 0)
            ly.removeAllViews();
    }
    public void ambilSoal(Integer nom){
        if(maximal){
            Toast.makeText(getApplicationContext(),"Sudah diakhir soal",Toast.LENGTH_LONG).show();
        }else{
            clearChild(soal);
            clearChild(a_isi);
            clearChild(b_isi);
            clearChild(c_isi);
            clearChild(d_isi);
            clearChild(e_isi);
            clearChild(pembahasan_isi);
            bahas_ly.setVisibility(View.GONE);
            siapBahas=0;
            try {
                InputStream is = getAssets().open("soal_latihan.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);
                Element element = doc.getDocumentElement();
                nList = doc.getElementsByTagName("soal");
                Log.d("Total Data", "ambilSoal: "+nList.getLength());
                element.normalize();
                Log.d("TES2", "ambilSoal: "+nList.item(nom).getTextContent());
                if(nom<nList.getLength()){
                    Node node = nList.item(nom);
                    muatsoal("isi",node,soal,nom);
                    muatsoal("a",node,a_isi,nom);
                    muatsoal("b",node,b_isi,nom);
                    muatsoal("c",node,c_isi,nom);
                    muatsoal("d",node,d_isi,nom);
                    muatsoal("e",node,e_isi,nom);
                    muatsoal("kunci",node,e_isi,nom);
                    muatsoal("pembahasan",node,pembahasan_isi,nom);
                    Integer noso=nom+1;
                    nomorSoal.setText("Latihan Soal No : "+noso);
                    maximal=false;
                }else{
                    maximal=true;
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }
    public void cekJawaban(String kunci, String jawaban, CardView pilihian){
        if(siapBahas>0){
            if(kunci.equals(jawaban)){
                pilihian.setCardBackgroundColor(getResources().getColor(R.color.green_400));
            }else{
                pilihian.setCardBackgroundColor(getResources().getColor(R.color.red_400));
                if(a.getTag().equals(kunci)){
                    a.setCardBackgroundColor(getResources().getColor(R.color.green_400));
                }
                if(b.getTag().equals(kunci)){
                    b.setCardBackgroundColor(getResources().getColor(R.color.green_400));
                }
                if(c.getTag().equals(kunci)){
                    c.setCardBackgroundColor(getResources().getColor(R.color.green_400));
                }
                if(d.getTag().equals(kunci)){
                    d.setCardBackgroundColor(getResources().getColor(R.color.green_400));
                }
                if(e.getTag().equals(kunci)){
                    e.setCardBackgroundColor(getResources().getColor(R.color.green_400));
                }
            }
            siapBahas=2;
        }else{
            Toast.makeText(getApplicationContext(),"Harap pilih jawaban terlebih dahulu",Toast.LENGTH_LONG).show();
        }
    }
    public void pilihJawaban(CardView cd){
        clearJawaban();
        cd.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));
        siapBahas=1;
    }
    public void clearJawaban(){
        a.setCardBackgroundColor(getResources().getColor(R.color.white));
        b.setCardBackgroundColor(getResources().getColor(R.color.white));
        c.setCardBackgroundColor(getResources().getColor(R.color.white));
        d.setCardBackgroundColor(getResources().getColor(R.color.white));
        e.setCardBackgroundColor(getResources().getColor(R.color.white));
    }
    public void muatsoal(String tipe, Node node, LinearLayout apa, Integer nosoal){
        Log.d("TIPE2", "muatsoal: "+node.getNodeName());
        NodeList isi=node.getChildNodes();
        for (int o =0;o<isi.getLength();o++){
            if (isi.item(o).getNodeName().equals(tipe)) {
                if(isi.item(o).getNodeName().equals("kunci")){
                    kunci=isi.item(o).getTextContent();
                }else{
                    NodeList jenis = isi.item(o).getChildNodes();
                    for(int i=0;i<jenis.getLength();i++){
                        Node jeniss=jenis.item(i);
                        if(jeniss.getNodeName().equals("teks")){
                            // add TextView
                            TextView tv = new TextView(this);
                            tv.setText(jeniss.getTextContent());
                            tv.setId(i);
                            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            //tv.setTextSize(16);
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen._13sdp));
                            tv.setPadding(7, 3, 5, 3);
                            apa.addView(tv);
                        }
                        if(jeniss.getNodeName().equals("gambar")){
                            // add ImageView
                            Display display = getWindowManager().getDefaultDisplay();
                            ImageView iv = new ImageView(this);
                            int resourceId = getResources().getIdentifier (jeniss.getTextContent(), "drawable", "com.vrcorp.mobilelearningfisika_gelombangbunyi");
                            iv.setImageResource(resourceId);
                            iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            //iv.setLayoutParams(parms);
                            iv.setAdjustViewBounds(true);
                            // float in = getResources().getDimension(R.dimen._90sdp);
                            apa.addView(iv);
                        }
                    }
                }

            }
        }
    }
}