package com.mycompany.countrydictionary2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by mamram on 9/7/2015.
 */
public class ResultCountry extends Fragment {
    final static String ARG = null  ;
    String iCurrent ="Ha Noi";
    String iCurrent1 ="Chau A";
    private DbAdapter mDbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null){
            iCurrent=savedInstanceState.getString(ARG);

        }
        View view = inflater.inflate(R.layout.activity_result, container, false);
        return view;
    }

    public void onStart (){
        super.onStart();
        Bundle args = getArguments();
        mDbHelper = new DbAdapter(this.getContext());
        try {
            mDbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(args!=null){
            String mess = args.getString(ARG);
            Cursor country = null;
            try {
                country = mDbHelper.fetchCountry(mess);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(country.getCount()!=0){
                String var = country.getString(country.getColumnIndexOrThrow(DbAdapter.key_capital));
                String var1 = country.getString((country.getColumnIndexOrThrow(DbAdapter.key_continent)));
                updateFrag(var,var1);
            }
            else {
                updateFrag("Khong tim thay","Khong tim thay 1");

            }

        }
        else if ((iCurrent!="Ha Noi")&&(iCurrent1 !="Chau A")){
            updateFrag(iCurrent,iCurrent1);
        }
    }

    public void updateFrag  (String name, String name1){


        TextView textView = (TextView) getActivity().findViewById(R.id.text_result);
        textView.setText(name);
        iCurrent = name;
        TextView textView1 = (TextView) getActivity().findViewById(R.id.text_result1);
        textView1.setText(name1);
        iCurrent1 = name1;
    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(ARG,"VietNam");
    }

}
