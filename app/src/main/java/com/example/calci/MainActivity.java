package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttondelete,buttonplus,buttonminus,
            buttonmultiply,buttondivide,buttondot,buttonequals;
    TextView maintext;
    Button[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = new Button[11];
        maintext = (TextView) findViewById(R.id.numdisplay);
        maintext.setMovementMethod(new ScrollingMovementMethod());
        buttondelete = (Button)findViewById(R.id.button2);
        buttonequals = (Button) findViewById(R.id.button22);
        buttondot = (Button) findViewById(R.id.button);
        buttons[0] = (Button) findViewById(R.id.button3);
        buttons[1] = (Button) findViewById(R.id.button4);
        buttons[2] = (Button) findViewById(R.id.button5);
        buttons[3] = (Button) findViewById(R.id.button6);
        buttons[4] = (Button) findViewById(R.id.button7);
        buttons[5] = (Button) findViewById(R.id.button8);
        buttons[6] = (Button) findViewById(R.id.button9);
        buttons[7] = (Button) findViewById(R.id.button10);
        buttons[8] = (Button) findViewById(R.id.button11);
        buttons[9] = (Button) findViewById(R.id.button12);
        buttons[10] = buttondot;
        buttonplus = (Button) findViewById(R.id.button24);
        buttonminus = (Button) findViewById(R.id.button21);
        buttonmultiply = (Button) findViewById(R.id.button23);
        buttondivide = (Button) findViewById(R.id.button25);
        for(int i =0 ;i<11;i++){
            if(i==10){
               buttons[i].setTag(".");
            }
            else{
                buttons[i].setTag(i);
            }
        }
        activatelisteners();
    }
    public void activatelisteners(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(String.valueOf(maintext.getText().toString().charAt(maintext.length()-1)).equals(" ")){
                        maintext.setText(maintext.getText().toString().substring(0,maintext.getText().toString().length()-3));
                    }
                    else{
                        maintext.setText(maintext.getText().toString().substring(0,maintext.getText().toString().length()-1));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        buttondelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                maintext.setText("");
                return false;
            }
        });
        for(int i = 0 ; i < 11;i++){
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    maintext.append(view.getTag().toString());
                }
            });
        }
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintext.getText().toString().length()>0){
                    if(maintext.getText().length()==1){
                        maintext.append(" + ");
                    }else{
                        String r=maintext.getText().toString().substring(maintext.getText().toString().length()-2,maintext.getText().toString().length());
                        if(r.contains("+")||r.contains("-")||r.contains("×")||r.contains("÷")) {
                        }else{
                            maintext.append(" + ");

                        }
                    }

                }
            }
        });
        buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintext.getText().toString().length()>0){
                    if(maintext.getText().length()==1){
                        maintext.append(" - ");
                    }else{
                        String r=maintext.getText().toString().substring(maintext.getText().toString().length()-2,maintext.getText().toString().length());
                        if(r.contains("+")||r.contains("-")||r.contains("×")||r.contains("÷")) {                        }else{
                            maintext.append(" - ");
                        }
                    }
                }
            }
        });
        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintext.getText().toString().length()>0){
                    if(maintext.getText().length()==1){
                        maintext.append(" × ");
                    }else{
                        String r=maintext.getText().toString().substring(maintext.getText().toString().length()-2,maintext.getText().toString().length());
                        if(r.contains("+")||r.contains("-")||r.contains("×")||r.contains("÷")) {                        }else{
                            maintext.append(" × ");
                        }
                    }
                }
            }
        });
        buttondivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintext.getText().toString().length()>0){
                    if(maintext.getText().length()==1){
                        maintext.append(" ÷ ");
                    }else{
                        String r=maintext.getText().toString().substring(maintext.getText().toString().length()-2,maintext.getText().toString().length());
                        if(r.contains("+")||r.contains("-")||r.contains("×")||r.contains("÷")) {                        }else{
                            maintext.append(" ÷ ");
                        }
                    }
                }
            }
        });
        buttonequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintext.getText().toString().length()!=0&&maintext.getText().toString().charAt(maintext.length()-1)!=' '){
                    evaluateexpression();
                }
            }
        });
    }
    public void evaluateexpression(){
        String s = maintext.getText().toString();
        ArrayList<String> arrayList = new ArrayList();
        String[] strings = s.split(" ");
        for(int i =0 ; i<strings.length;i++){
            arrayList.add(strings[i]);
        }
        while (Boolean.TRUE){
            if(arrayList.contains("÷")){
                int j = arrayList.indexOf("÷");
                Float a,b;
                a=Float.parseFloat(arrayList.get(j-1));
                b=Float.parseFloat(arrayList.get(j+1));
                a=a/b;
                arrayList.set(j-1,String.valueOf(a));
                arrayList.remove(j);
                arrayList.remove(j);
            }else{
                break;
            }
        }
        while (Boolean.TRUE){
            if(arrayList.contains("×")){
                int j = arrayList.indexOf("×");
                Float a,b;
                a=Float.parseFloat(arrayList.get(j-1));
                b=Float.parseFloat(arrayList.get(j+1));
                a=a*b;
                arrayList.set(j-1,String.valueOf(a));
                arrayList.remove(j);
                arrayList.remove(j);
            }else{
                break;
            }
        }
        while (Boolean.TRUE){
            if(arrayList.contains("+")){
                int j = arrayList.indexOf("+");
                Float a,b;
                a=Float.parseFloat(arrayList.get(j-1));
                b=Float.parseFloat(arrayList.get(j+1));
                a=a+b;
                arrayList.set(j-1,String.valueOf(a));
                arrayList.remove(j);
                arrayList.remove(j);
            }else{
                break;
            }
        }
        while (Boolean.TRUE){
            if(arrayList.contains("-")){
                int j = arrayList.indexOf("-");
                Float a,b;
                a=Float.parseFloat(arrayList.get(j-1));
                b=Float.parseFloat(arrayList.get(j+1));
                a=a-b;
                arrayList.set(j-1,String.valueOf(a));
                arrayList.remove(j);
                arrayList.remove(j);
            }else{
                break;
            }
        }
        String g="";
        for(int i =0;i<arrayList.size();i++){
            g=g+arrayList.get(i);
        }
        maintext.setText(g);
    }
}
