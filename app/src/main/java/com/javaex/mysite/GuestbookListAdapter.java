package com.javaex.mysite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.javaex.vo.GuestbookVo;

import java.util.List;

public class GuestbookListAdapter extends ArrayAdapter<GuestbookVo> {

    private TextView txtNo;
    private TextView txtName;
    private TextView txtRegDate;
    private TextView txtContent;

    //생성자
    public GuestbookListAdapter( Context context, int resource,  List<GuestbookVo> items) {
        super(context, resource, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("javaStudy", "position=" + position);

        //첫 포지션은 0이다 아무것도 사용하지않았기때문에 0번째의 포지션이 찍히고
        //만든적이 없기때문에 convertView(틀)은 null이기때문에 밑에 view ==null에 들어가서
        //틀을 만들고 데이터를 받아서 0번째 포지션의 틀에 넣는것이다.

        //스크롤을 내리면 계속 새로운 포지션을 만들지만
        //새로운 틀을 만드는것이아니고 만들어넣은 기존의 틀에
        //새로운 데이터만 넣어서 출력한다. position=7 ,틀(뻥튀기)을 새로 만듬 > position=8 ,틀(뻥튀기)을 새로 만듬 >position=9 >position=10


        View view = convertView;

        if(view == null) {  //만들어놓은 view가 없을때 --> 만들어야한다.
            //레이아웃 뻥튀기 기계 받아오기
            LayoutInflater layoutInflater = (LayoutInflater)LayoutInflater.from(getContext());

            //레이아웃을 뻥튀기 한다
            view = layoutInflater.inflate(R.layout.activity_list_item, null);

            Log.d("javaStudy", "틀(뻥튀기)을 새로 만듬");

        }


        //1개의 방명록 데이터가 있다.
        //1개의 데이터 처리(xml 데이터 매칭)
        txtNo = view.findViewById(R.id.txtNo);
        txtName = view.findViewById(R.id.txtName);
        txtRegDate = view.findViewById(R.id.txtRegDate);
        txtContent = view.findViewById(R.id.txtContent);

        //데이터 가져오기(1개 데이터)  -->부모쪽(super)에 전체리스트가 있다.
       GuestbookVo guestbookVo = super.getItem(position);
       txtNo.setText("" + guestbookVo.getNo()); //얘는 인트여서 문자열로 만들어줘야한다.
       txtName.setText(guestbookVo.getName());
       txtRegDate.setText((guestbookVo.getRegDate()));
       txtContent.setText(guestbookVo.getContent());

        return view;
    }
}
