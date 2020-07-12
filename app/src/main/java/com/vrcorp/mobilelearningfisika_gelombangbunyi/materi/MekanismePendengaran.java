package com.vrcorp.mobilelearningfisika_gelombangbunyi.materi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.vrcorp.mobilelearningfisika_gelombangbunyi.MainActivity;
import com.vrcorp.mobilelearningfisika_gelombangbunyi.MateriListActivity;
import com.vrcorp.mobilelearningfisika_gelombangbunyi.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MekanismePendengaran extends AppCompatActivity {
    LinearLayout konten;
    CardView home, close;
    SimpleExoPlayer player;
    Integer cobaa=0;
    Intent intent;
    String materi, judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mekanisme_pendengaran);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        intent = getIntent();
        materi = intent.getStringExtra("materi");
        judul = intent.getStringExtra("judul");
        konten = findViewById(R.id.konten);
        home = findViewById(R.id.btn_home);
        close = findViewById(R.id.btn_close);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MekanismePendengaran.this, MainActivity.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MekanismePendengaran.this, MateriListActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MATERI", "onCreate: "+materi);
        try {
            InputStream is = getAssets().open(materi+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("materi");
            Log.d("Kompetensi", "onCreate: "+nList.item(0).getTextContent());
            Integer nomor = 1;
            NodeList node = nList.item(0).getChildNodes();
            for (int i=0; i<node.getLength(); i++) {
                Node anak = node.item(i);
                Log.d("MODE", "onCreate: "+anak.getNodeName());
                if(anak.getNodeName().equals("judul")){
                    // add TextView
                    TextView tv = new TextView(this);
                    tv.setText(anak.getTextContent());
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setId(nomor);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    //tv.setTextSize(16);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            getResources().getDimension(R.dimen._16sdp));
                    tv.setPadding(5, 3, 0, 3);
                    konten.addView(tv);
                }
                if(anak.getNodeName().equals("subjudul")){
                    // add TextView
                    TextView tv = new TextView(this);
                    tv.setText(anak.getTextContent());
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setId(nomor);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //tv.setTextSize(16);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            getResources().getDimension(R.dimen._14sdp));
                    tv.setPadding(7, 3, 0, 3);
                    konten.addView(tv);
                };
                if(anak.getNodeName().equals("teks")){
                    // add TextView
                    TextView tv = new TextView(this);
                    tv.setText(anak.getTextContent());
                    tv.setId(nomor);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //tv.setTextSize(16);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            getResources().getDimension(R.dimen._13sdp));
                    tv.setPadding(7, 3, 5, 3);
                    konten.addView(tv);
                }
                if(anak.getNodeName().equals("ket")){
                    Log.d("MODE", "onCreate: "+anak.getTextContent());
                    // add TextView
                    TextView tv = new TextView(this);
                    tv.setText(anak.getTextContent());
                    tv.setGravity(Gravity.CENTER);
                    tv.setId(nomor);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //tv.setTextSize(16);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            getResources().getDimension(R.dimen._13sdp));
                    tv.setPadding(5, 3, 0, 3);
                    konten.addView(tv);
                }
                if(anak.getNodeName().equals("gambar")){
                    // add ImageView
                    Display display = getWindowManager().getDefaultDisplay();
                    ImageView iv = new ImageView(konten.getContext());
                    int resourceId = getResources().getIdentifier (anak.getTextContent(), "drawable", "com.vrcorp.mobilelearningfisika_gelombangbunyi");
                    iv.setImageResource(resourceId);
                    iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    //iv.setLayoutParams(parms);
                    iv.setAdjustViewBounds(true);
                   // float in = getResources().getDimension(R.dimen._90sdp);
                    konten.addView(iv);
                }
                if(anak.getNodeName().equals("video")){
                    //add video
                    PlayerView vv = new PlayerView(this);
                    int resourceId = getResources().getIdentifier (anak.getTextContent(), "raw", "com.vrcorp.mobilelearningfisika_gelombangbunyi");
                    String path = "android.resource://" + getPackageName() + "/" + resourceId;
                    //vv.setVideoURI(Uri.parse(path));
                    //MediaController mediaController = new MediaController(vv.getContext());
                    //mediaController.setAnchorView(vv);
                    //vv.setMediaController(mediaController);
                    vv.setLayoutParams(new FrameLayout.LayoutParams(1000, 500));
                    konten.addView(vv);
                     player = ExoPlayerFactory.newSimpleInstance(
                            new DefaultRenderersFactory(this),
                            new DefaultTrackSelector(), new DefaultLoadControl());

                    String videoPath = RawResourceDataSource.buildRawResourceUri(resourceId).toString();
                    Uri uri = RawResourceDataSource.buildRawResourceUri(resourceId);
                    ExtractorMediaSource audioSource = new ExtractorMediaSource(
                            uri,
                            new DefaultDataSourceFactory(this, "MyExoplayer"),
                            new DefaultExtractorsFactory(),
                            null,
                            null
                    );
                    player.prepare(audioSource);
                    vv.setPlayer(player);
                    vv.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
                    final Integer[] z = {0};
                    vv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            z[0]++;
                            if(z[0]>0){
                                player.setPlayWhenReady(true);
                            }
                        }
                    });
                }
                if(anak.getNodeName().equals("web")){
                    LinearLayout linearLayout = new LinearLayout(this);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    konten.addView(linearLayout);
                    WebView wb = new WebView(konten.getContext());
                    wb.setWebChromeClient(new WebChromeClient());
                    wb.clearCache(true);
                    wb.clearHistory();
                    wb.getSettings().setJavaScriptEnabled(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        wb.getSettings().setMediaPlaybackRequiresUserGesture(false);
                    }
                    wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                    wb.getSettings().setPluginState(WebSettings.PluginState.ON);
                    wb.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    String path = "file:///android_asset/www/"+anak.getTextContent()+".html";
                    wb.loadUrl(path);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        linearLayout.addView(wb, 0, new LinearLayout.LayoutParams(
                                new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT)));
                    }
                }
                if(anak.getNodeName().equals("coba")){
                    cobaa++;
                    NodeList anak2 = anak.getChildNodes();
                    LinearLayout linearLayout2 =new LinearLayout(this);
                    linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout2.setBackgroundColor(getResources().getColor(R.color.orange_400));
                    linearLayout2.setPadding(3,3,3,3);
                    konten.addView(linearLayout2);
                    // add TextView
                    TextView tv2 = new TextView(this);
                    tv2.setText("   Coba Yuk!");
                    //tv.setTypeface(null, Typeface.BOLD);
                    tv2.setId(nomor);
                    LinearLayout.LayoutParams ly2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    ly2.setMargins(10,3,2,3);
                    tv2.setLayoutParams(ly2);
                    tv2.setTypeface(Typeface.DEFAULT_BOLD);
                    tv2.setTextColor(getResources().getColor(R.color.white));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            getResources().getDimension(R.dimen._16sdp));
                    tv2.setPadding(10, 3, 0, 3);
                    linearLayout2.addView(tv2);
                    for(int u=0;u<anak2.getLength();u++){
                        Node anaknya = anak2.item(u);
                        if(anaknya.getNodeName().equals("judul")){
                            LinearLayout linearLayout =new LinearLayout(this);
                            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayout.setBackgroundColor(getResources().getColor(R.color.blue_400));
                            linearLayout.setPadding(3,3,3,3);
                            konten.addView(linearLayout);
                            // add TextView
                            TextView tv = new TextView(this);
                            tv.setText(anaknya.getTextContent());
                            //tv.setTypeface(null, Typeface.BOLD);
                            tv.setId(u);
                            LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            ly.setMargins(10,3,2,3);
                            tv.setLayoutParams(ly);
                            //tv.setTextSize(16);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen._14sdp));
                            tv.setPadding(4, 3, 0, 3);
                            linearLayout.addView(tv);
                        }
                        if(anaknya.getNodeName().equals("soal")){
                            LinearLayout linearLayout3 =new LinearLayout(this);
                            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayout3.setBackgroundColor(getResources().getColor(R.color.blue_400));
                            linearLayout3.setPadding(3,3,3,3);
                            konten.addView(linearLayout3);
                            LinearLayout linearLayout4 =new LinearLayout(this);
                            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayout4.setBackgroundColor(getResources().getColor(R.color.white));
                            linearLayout4.setPadding(3,3,3,3);
                            linearLayout3.addView(linearLayout4);

                            TextView tv = new TextView(this);
                            tv.setText(anaknya.getTextContent());
                            //tv.setTypeface(null, Typeface.BOLD);
                            tv.setId(u);
                            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            //tv.setTextSize(16);
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen._13sdp));
                            tv.setPadding(10, 5, 0, 3);
                            linearLayout4.addView(tv);

                        }
                        if(anak.getNodeName().equals("web")){
                            LinearLayout linearLayout = new LinearLayout(this);
                            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            konten.addView(linearLayout);
                            WebView wb = new WebView(konten.getContext());
                            wb.setWebChromeClient(new WebChromeClient());
                            wb.clearCache(true);
                            wb.clearHistory();
                            wb.getSettings().setJavaScriptEnabled(true);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                wb.getSettings().setMediaPlaybackRequiresUserGesture(false);
                            }
                            wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
                            wb.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                            String path = "file:///android_asset/www/"+anak.getTextContent()+".html";
                            wb.loadUrl(path);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                linearLayout.addView(wb, 0, new LinearLayout.LayoutParams(
                                        new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT)));
                            }
                        }
                        if(anak.getNodeName().equals("ket")){
                            Log.d("MODE", "onCreate: "+anak.getTextContent());
                            // add TextView
                            TextView tv = new TextView(this);
                            tv.setText(anak.getTextContent());
                            tv.setGravity(Gravity.CENTER);
                            tv.setId(nomor);
                            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                            //tv.setTextSize(16);
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen._13sdp));
                            tv.setPadding(5, 3, 0, 3);
                            konten.addView(tv);
                        }
                    }
                    final EditText editText2 = new EditText(this);
                    editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText2.setMinLines(1);
                    editText2.setHint("Nama Lengkap");
                    konten.addView(editText2);
                    final EditText editText3 = new EditText(this);
                    editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText3.setMinLines(1);
                    editText3.setHint("Kelas");
                    konten.addView(editText3);
                    final EditText editText = new EditText(this);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setMinLines(4);
                    editText.setHint("Masukan Jawaban");
                    konten.addView(editText);
                    Button button = new Button(this);
                    LinearLayout.LayoutParams paramsx = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsx.setMargins(5,2,2,5);
                    button.setLayoutParams(paramsx);
                    button.setText("Kirim Jawaban");
                    button.setBackgroundColor(getResources().getColor(R.color.blue_400));
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setTextSize(getResources().getDimension(R.dimen._9sdp));
                    button.setPadding(4,3,3,4);
                    button.setGravity(Gravity.CENTER);
                    konten.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String isi = editText.getText().toString();
                            String nama = editText2.getText().toString();
                            String kelas = editText3.getText().toString();
                            sendEmail("#"+cobaa+" "+judul+" Coba Ke "+cobaa,"Nama Lengkap : "+nama+"\n Kelas : "+kelas+" \n "+isi);
                        }
                    });
                }
                nomor++;
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void ukuranGambar(ImageView imageView){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,  RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
    }
    protected void sendEmail(String subject, String isi) {
        Log.i("Send email", "");
        String[] TO = {"veridetta@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, isi);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MekanismePendengaran.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}