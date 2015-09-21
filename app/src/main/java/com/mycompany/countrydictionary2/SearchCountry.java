package com.mycompany.countrydictionary2;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

/**
 * Created by mamram on 9/7/2015.
 */
public class SearchCountry extends Fragment {
    private DbAdapter mDbHelper;

    OnBtnSelectedListener mCallback;
    AutoCompleteTextView textSearch;
    Button btnSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view   = inflater.inflate(R.layout.activity_search, container, false);
        btnSearch = (Button) view.findViewById(R.id.button_search);
        mDbHelper = new DbAdapter(this.getContext());
        try {
            mDbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // Cursor notesCursor = mDbHelper.fetchAllNotes();
        String[] from = new String[]{"Somaliland","South Georgia and South Sandwich Islands","French Southern and Antarctic Lands","Palestine","Aland Islands","Nauru",
                "Saint Martin","Tokelau","Western Sahara","Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Antigua and Barbuda","Argentina","Armenia",
                "Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina"
                ,"Botswana","Brazil","British Virgin Islands","Brunei Darussalam","Bulgaria","Burkina Faso","Myanmar","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands",
                "Central African Republic","Chad","Chile","China","Christmas Island","Cocos Islands","Colombia","Comoros","Democratic Republic of the Congo","Republic of Congo","Cook Islands",
                "Costa Rica","Cote d'Ivoire","Croatia","Cuba","Curaçao","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador",
                "Equatorial Guinea","Eritrea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","Gabon","The Gambia","Georgia","Germany",
                "Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea-Bissau","Guyana","Haiti","Vatican City","Honduras","Hungary","Iceland","India",
                "Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","North Korea","South Korea","Kosovo",
                "Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives",
                "Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Federated States of Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco",
                "Mozambique","Namibia","Nepal","Netherlands","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Norfolk Island","Northern Mariana Islands","Norway","Oman",
                "Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Romania","Russia","Rwanda",
                "Saint Barthelemy","Saint Helena","Saint Kitts and Nevis","Saint Lucia","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa","San Marino",
                "Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Sint Maarten","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa",
                "South Sudan","Spain","Sri Lanka","Sudan","Suriname","Svalbard","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo",
                "Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States",
                "Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","US Virgin Islands","Wallis and Futuna","Yemen","Zambia","Zimbabwe","US Minor Outlying Islands","Antarctica",
                "Northern Cyprus","Hong Kong","Heard Island and McDonald Islands","British Indian Ocean Territory","Macau"};
       // int[] to = new int[]{R.id.text1};
        //SimpleCursorAdapter countries = new SimpleCursorAdapter(this.getContext(),R.layout.country_row,notesCursor,from,to);
        textSearch = (AutoCompleteTextView) view.findViewById(R.id.edit_search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.country_row,from);
        textSearch.setAdapter(adapter);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // textSearch = (AutoCompleteTextView)view.findViewById(R.id.edit_search);
                String var = textSearch.getText().toString();
                mCallback.onBtnselected(var);
                textSearch.setText(null);


            }
        });
        return view;
    }

    public interface OnBtnSelectedListener {
        public void onBtnselected (String name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback = (OnBtnSelectedListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OnBtnSelectedListener");
        }

    }


}
