package com.hdin.jdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        println("메인액티비티 호출됨");
        println(" ::person 데이터베이스 생성됨");
//        if (sqLiteDatabase == null) {
//            sqLiteDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
//        }
//        if (TB_NAME != "person") {
//            println("테이블생성 시작");
//            String query = "create table " + TB_NAME + " (_id primary key autoincrement, name text, age integer, mobil text)";
//            sqLiteDatabase.execSQL(query);
//            println(TB_NAME + " ::테이블생성");
//        }
//        println("테이블 이름은:: "+TB_NAME+" 입니다.");

//########### 데이터 넣기
        Button insertBtn = findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                println("데이타를 저장루틴입니다.");
                sqLiteDatabase = databaseHelper.getWritableDatabase();
                String sql = "insert into person ("+DatabaseHelper.PERSON_ID+","+DatabaseHelper.PERSON_NAME+","+DatabaseHelper.PERSON_MEMO+","+DatabaseHelper.PERSON_DATETIME+","+DatabaseHelper.PERSON_MOBILE+") values (null,'jonh','I love you',190200,'010-7596-8888')";
                sqLiteDatabase.execSQL(sql);

                displayContracts();
            }
        });
//############  쿼리문 실행
        Button queryBtn = findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayContracts();
            }
        });

//##########자료 수정
        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                update();
            }
        });
//############자료 삭제
        Button deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    public void insertPerson() {
    }

//############ 디스플레이 문
    private void displayContracts() {
        println("디스플레이 호출됨");
        String sqlR = "select * from person";
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlR,null);
        int recordCount = cursor.getCount();
        for (int i=0; i<recordCount; i++){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String memo = cursor.getString(2);
            int dateTime = cursor.getInt(3);
            String mobil = cursor.getString(4);
            println(i+"번째 이름:: "+name+"  메모:: "+memo+"  날자:: "+dateTime+" 전번:: "+mobil);
        }
    }

//######### 삭제 문
    private void delete() {
        textView.setText("");
    }

    //########## 출력문 보이기
    public void println(String data){
        textView.append(" -> "+data+"\n");

    }
}