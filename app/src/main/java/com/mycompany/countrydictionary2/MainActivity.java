package com.mycompany.countrydictionary2;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;

public class MainActivity extends FragmentActivity implements SearchCountry.OnBtnSelectedListener  {
    private DbAdapter mDbHelper;
    private Cursor mCountryCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // mDbHelper = new DbAdapter(this);
       // try {
         //   mDbHelper.open();
        //} catch (SQLException e) {
          //  e.printStackTrace();
       // }
        if (findViewById(R.id.fragment_search)!=null){
            if(savedInstanceState!=null){
                return;
            }

            SearchCountry searchCountry = new SearchCountry();
            searchCountry.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_search, searchCountry).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBtnselected (String name){

        ResultCountry resultCountry = new ResultCountry();
        Bundle args = new Bundle();


        args.putString(ResultCountry.ARG, name);

        resultCountry.setArguments(args);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_result,resultCountry);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
