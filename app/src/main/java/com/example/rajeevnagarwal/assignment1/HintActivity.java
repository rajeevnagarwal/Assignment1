package com.example.rajeevnagarwal.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class HintActivity extends AppCompatActivity {

    private Button mHintButton; //Button Object
    private TextView mHintView; //Hint TextView
    private int CurrentPrime;
    private Boolean taken_hint;
    private Boolean visible_view;
    private static String TAG="HintActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"In onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        initialize();
        if(savedInstanceState!=null)
        {
            CurrentPrime = savedInstanceState.getInt("CurrentPrime",0);
            visible_view = savedInstanceState.getBoolean("Visible",false);
            if(visible_view)
            {
                mHintView.setVisibility(VISIBLE);
                mHintView.setText("Check if "+CurrentPrime+" can be factorized");
                taken_hint = true;
                getBack();
            }
            else
            {
                mHintView.setVisibility(View.INVISIBLE);
            }

        }
        else
        {
            CurrentPrime = getIntent().getIntExtra("prime",0);
            visible_view = false;
        }
    }
    //Setting result for parent activity
    public void getBack()
    {
        Log.d(TAG,"In getBack()");
        Intent i = getIntent();
        i.putExtra("Hint",taken_hint);
        setResult(1,i);


    }
    //Instantiating activity components
    public void initialize()
    {
        Log.d(TAG,"In initialize()");
        mHintButton = (Button)findViewById(R.id.hint_button);
        mHintView = (TextView)findViewById(R.id.hint_view);
        visible_view = false;
        taken_hint = false;
        mHintView.setText("");

    }
    //Showing hint on button click
    public void hint(View v)
    {
        Log.d(TAG,"In hint");
        taken_hint = true;
        visible_view = true;
        mHintView.setVisibility(VISIBLE);
        mHintView.setText("Check if "+CurrentPrime+" can be factorized");
        getBack();

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"In onSaveInstanceState");
        savedInstanceState.putInt("CurrentPrime", CurrentPrime);
        savedInstanceState.putBoolean("Visible",visible_view);
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



