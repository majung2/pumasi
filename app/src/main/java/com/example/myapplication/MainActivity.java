package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapplication.Controller.BrandController;
import com.example.myapplication.Controller.PathController;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BrandController p = new BrandController();
        final ArrayList<String> moveList= new ArrayList<>();
        Button cosmeButton = (Button) findViewById(R.id.cosmeButton);
        Button sporButton = (Button) findViewById(R.id.sportsButton);
        Button clotButton = (Button) findViewById(R.id.clotButton);
        Button finButton = (Button) findViewById(R.id.finButton);

        cosmeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandController c = new BrandController();
                ArrayList<String> cosme = c.getCosmeBrNames();
                final String[] cosmeList = new String[cosme.size()];
                for(int i = 0; i<cosme.size(); i++){
                    cosmeList[i] = cosme.get(i);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)MainActivity.this);
                builder.setTitle((CharSequence)"화장품");
                builder.setItems((CharSequence[])cosmeList, (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface builder, int which) {
                        String selected = cosmeList[which];
                        moveList.add(selected);
                        Toast.makeText(MainActivity.this.getApplicationContext(), (CharSequence)(selected + " 브랜드를 선택!"), Toast.LENGTH_SHORT).show();
                    }
                }));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        sporButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BrandController c = new BrandController();
                ArrayList<String> spor = c.getSporBrNames();
                final String[] sporList = new String[spor.size()];
                for(int i = 0; i<spor.size(); i++){
                    sporList[i] = spor.get(i);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)MainActivity.this);
                builder.setTitle((CharSequence)"스포츠");
                builder.setItems((CharSequence[])sporList, (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface builder, int which) {
                        String selected = sporList[which];
                        moveList.add(selected);
                        Toast.makeText(MainActivity.this.getApplicationContext(), (CharSequence)(selected + " 브랜드를 선택!"), Toast.LENGTH_SHORT).show();
                    }
                }));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        clotButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BrandController c = new BrandController();
                ArrayList<String> clot = c.getClotBrNames();
                final String[] clotList = new String[clot.size()];
                for(int i = 0; i<clot.size(); i++){
                    clotList[i] = clot.get(i);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)MainActivity.this);
                builder.setTitle((CharSequence)"일반의류");
                builder.setItems((CharSequence[])clotList, (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface builder, int which) {
                        String selected = clotList[which];
                        moveList.add(selected);
                        Toast.makeText(MainActivity.this.getApplicationContext(), (CharSequence)(selected + " 브랜드를 선택!"), Toast.LENGTH_SHORT).show();
                    }
                }));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        finButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)MainActivity.this);
                builder.setTitle((CharSequence)"추천 동선");
                builder.setMessage(MainActivity.this.result(moveList));
                builder.setPositiveButton((CharSequence)"확인", (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface $noName_0, int which) {
                        PathController.savePath(moveList);
                        /*String file = "saveData1.txt";
                        String text = (moveList.toString());
                        if (TextUtils.isEmpty((CharSequence)text)) {
                            Toast.makeText(MainActivity.this.getApplicationContext(), (CharSequence)"저장할 내용이 없습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            p.getPath().setPath(moveList);
                            save(text, file);
                            moveList.clear();
                        }*/
                    }
                }));
                builder.show();

            }
        });
    }
    public final CharSequence result( List s) {
        String temp = "";
        Iterator var4 = s.iterator();

        String spot;
        while(var4.hasNext()) {
            spot = (String)var4.next();
            String tmp;
            if (s.size() < 4) {
                tmp = spot + " -> ";
                temp = temp + tmp;
            } else {
                tmp = "\n" + spot + " -> ";
                temp = temp + tmp;
            }

            if (spot.equals(s.get(s.size() - 1))) {
                temp = temp + spot;
            }
        }

        spot = "\n                선택하신 매장들을 바탕으로한 제일 빠른 쇼핑동선은\n                " + temp + " 입니다.\n            ";
        return (CharSequence)spot;
    }


}
