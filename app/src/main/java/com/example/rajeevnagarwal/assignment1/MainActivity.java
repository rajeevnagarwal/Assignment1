package com.example.rajeevnagarwal.assignment1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    Button mCorrectButton,mIncorrectButton,mNextButton;
    TextView mQuestionView;
    Integer QuestionIndex=1;
    Integer CurrentPrime=0;
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"In OnCreate");
        setContentView(R.layout.activity_main);
        intialize();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState!=null)
        {
            QuestionIndex = savedInstanceState.getInt("CurrentQuestion",0);
            CurrentPrime = savedInstanceState.getInt("CurrentPrime",0);
        }
        else {
            QuestionIndex = 1;
            CurrentPrime = 0;
        }
        getQuestion();

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"In onSaveInstanceState");
        savedInstanceState.putInt("CurrentQuestion",QuestionIndex);
        savedInstanceState.putInt("CurrentPrime",CurrentPrime);
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
    public void getQuestion() {
        if(CurrentPrime==0) {
            CurrentPrime = generatePrime();
        }
        mQuestionView.setText(QuestionIndex + ". " + "Is " + CurrentPrime + " a Prime number?");

    }
    public void intialize()
    {
        Log.d(TAG,"In initialize()");
        mCorrectButton = (Button)findViewById(R.id.buttonCorrect);
        mIncorrectButton = (Button)findViewById(R.id.buttonIncorrect);
        mNextButton = (Button)findViewById(R.id.buttonNext);
        mQuestionView = (TextView)findViewById(R.id.QuestionView);
    }
    public Integer generatePrime()
    {
        return (1+(int)(Math.random()*(1000-1)+1));
    }
    public void onNext(View v)
    {
        CurrentPrime = 0;
        QuestionIndex = ((QuestionIndex+1)%1001);
        getQuestion();
    }
    public void onCorrect(View v)
    {
        if(checkPrime(CurrentPrime)==true) {
            Toast.makeText(getApplicationContext(), "Your answer is Correct", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Your answer is Incorrect", Toast.LENGTH_SHORT).show();

        }
    }
    public void onIncorrect(View v)
    {
        if(checkPrime(CurrentPrime)==true) {
            Toast.makeText(getApplicationContext(), "Your answer is Incorrect", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Your answer is Correct", Toast.LENGTH_SHORT).show();

        }
    }
    public Boolean checkPrime(Integer n)
    {
        if(n==1)
        {
            return false;
        }
        else if(n==2)
        {
            return true;
        }
        else if(n==3)
        {
            return true;
        }
        else
        {
            int flag=0;
            for(int i=2;i<=Math.sqrt(n);i++)
            {
                if(n%i==0)
                {
                   flag = 1;
                    break;
                }

            }
            if(flag==0)
            {
                return true;
            }
            else
            {
                return false;
            }
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
}
