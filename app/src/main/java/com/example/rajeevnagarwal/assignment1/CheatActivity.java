package com.example.rajeevnagarwal.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class CheatActivity extends AppCompatActivity {

    private Button mCheatButton;//Button Object
    private TextView mCheatView;//Cheat TextView
    private int CurrentPrime;
    private Boolean Result;
    private Boolean Cheated;
    private Boolean visible_view;
    private static String TAG="CheatActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"In onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        initialize();
        if(savedInstanceState!=null)
        {
            CurrentPrime = savedInstanceState.getInt("CurrentPrime",0);
            Result = savedInstanceState.getBoolean("Result",false);
            visible_view = savedInstanceState.getBoolean("Visible",false);
            Cheated = savedInstanceState.getBoolean("Cheated",false);
            if(visible_view)
            {
                mCheatView.setVisibility(VISIBLE);
                if(Result)
                {
                    mCheatView.setText(CurrentPrime+ " "+getResources().getString(R.string.yes_prime));

                }
                else
                {
                    mCheatView.setText(CurrentPrime+ " "+getResources().getString(R.string.no_prime));

                }
                Cheated = true;
                getBack();
            }
            else
            {
                mCheatView.setVisibility(View.INVISIBLE);
            }

        }
        else
        {
            CurrentPrime = getIntent().getIntExtra("prime",0);
            Result = getIntent().getBooleanExtra("result",false);
            visible_view = false;
            Cheated = false;
        }
    }
    //Instantiating objects
    public void initialize()
    {
        Log.d(TAG,"In initialize()");
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatView = (TextView)findViewById(R.id.cheat_view);
        visible_view = false;
        Cheated = false;
        mCheatView.setText("");
    }
    //Setting result for the parent activity
    public void getBack()
    {
        Log.d(TAG,"In getBack()");
        Intent i = getIntent();
        i.putExtra("Cheated",Cheated);
        setResult(1,i);


    }
    // Showing cheat on button click
    public void cheat(View v)
    {
        Log.d(TAG,"In cheat");
        Cheated = true;
        visible_view = true;
        mCheatView.setVisibility(VISIBLE);
        if(Result)
        {
            mCheatView.setText(CurrentPrime+" "+getResources().getString(R.string.yes_prime));
        }
        else
        {
            mCheatView.setText(CurrentPrime+" "+getResources().getString(R.string.no_prime));
        }
        getBack();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"In onSaveInstanceState");
        savedInstanceState.putInt("CurrentPrime", CurrentPrime);
        savedInstanceState.putBoolean("Result", Result);
        savedInstanceState.putBoolean("Visible",visible_view);
        savedInstanceState.putBoolean("Cheated",Cheated);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "In onResume()");
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "In onStop()");
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "In onStart()");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"In onPause()");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "In onDestroy()");
    }
}
