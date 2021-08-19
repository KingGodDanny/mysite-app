package com.javaex.mysite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.javaex.vo.GuestbookVo;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lvGuestbookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //데이터를 가져온다.(서버로부터) -- 가상
        List<GuestbookVo> guestbookList = getListFromServer();
        Log.d("javaStudy", "서버로부터 데이터 수신........옼케이!");
        Log.d("javaStudy", "list:" + guestbookList.toString());

        //리스트뷰를 객체화 한다.
        lvGuestbookList = (ListView) findViewById(R.id.lvGuestbookList);

        //어댑터를 생성
        GuestbookListAdapter guestbookListAdapter =
                new GuestbookListAdapter(getApplicationContext(), R.id.lvGuestbookList, guestbookList);

        //리스트뷰에 어댑터를 세팅한다.
        lvGuestbookList.setAdapter(guestbookListAdapter);

        //해당 방명록의 컨텐츠를 읽기위한 클릭이벤트
        lvGuestbookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //현재 클릭한 뷰의 리스트의 index 값
                Log.d("javaStudy", "index= " + i);  //데이터가 몇번인지 찍음

                // 화면에 있는 값을 읽어온다
                TextView txtContent = (TextView)view.findViewById(R.id.txtContent);
                Log.d("javaStudy", "txtContent= " + txtContent.getText().toString());

                //화면에 출력되지않은 데이터를 가져올때  --- > 리스트의 값을 사용할때
                //포지션값(i)을 주면 담겨있는 아이템(데이터)준다.
                GuestbookVo guestbookVo = (GuestbookVo)adapterView.getItemAtPosition(i);

                Log.d("javaStudy", "vo= " + guestbookVo.toString());
                Log.d("javaStudy", "vo.regDate= " + guestbookVo.getRegDate());


                //클릭한 아이템의 pk값을 읽어온다.
                int no = guestbookVo.getNo();
                Log.d("javaStudy", "vo.no= " + no);



            }
        });

    }

    //리스트북 정보 만들기(가상)
    public List<GuestbookVo> getListFromServer() {
      List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();

      for(int i=1; i<50; i++) {
          GuestbookVo guestbookVo = new GuestbookVo();
          guestbookVo.setNo(i);
          guestbookVo.setName("성우정");
          guestbookVo.setRegDate("2021-08-19-" + i);
          guestbookVo.setContent(i+"번째 본문입니다.");

          guestbookList.add(guestbookVo);


      }

      return guestbookList;

    }



}