package com.vrcorp.mobilelearningfisika_gelombangbunyi.materi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

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
    SimpleExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mekanisme_pendengaran);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        konten = findViewById(R.id.konten);
        try {
            InputStream is = getAssets().open("mekanisme_pendengaran.xml");
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
                    Log.d("MODE", "onCreate: "+anak.getTextContent());
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
                nomor++;
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void ukuranGambar(ImageView imageView){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,  RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
    }

}