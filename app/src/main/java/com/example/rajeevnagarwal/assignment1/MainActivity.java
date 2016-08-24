package com.example.rajeevnagarwal.assignment1;

import android.app.Activity;
import android.content.Intent;
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

    private Button mCorrectButton,mIncorrectButton,mNextButton; //Button Objects
    private TextView mQuestionView; //Question TextView
    private Integer QuestionIndex=1; //Index for maintaining the question number
    private Integer CurrentPrime=0; //Keep track of current prime number in the question
    private String TAG = "MainActivity"; //Tag to identify activity in the logs
    private int[] prime; //Array to track which indexes are prime numbers
    private Boolean correct_visible;
    private Boolean incorrect_visible;
    private Boolean next_visible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"In OnCreate");
        setContentView(R.layout.activity_main);
        intialize();    //Initializing all the widgets in the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Checking for already saved question index and prime number
        if(savedInstanceState!=null)
        {
            QuestionIndex = savedInstanceState.getInt("CurrentQuestion",0);
            CurrentPrime = savedInstanceState.getInt("CurrentPrime",0);
            prime = savedInstanceState.getIntArray("prime");
            correct_visible = savedInstanceState.getBoolean("correct_visible");
            incorrect_visible = savedInstanceState.getBoolean("incorrect_visible");
            next_visible = savedInstanceState.getBoolean("next_visible");
            setButtons();

        }
        else {
            QuestionIndex = 1;
            CurrentPrime = 0;
            prime = Seive();

        }
        // Generate the question
        getQuestion();

    }
    //Setting buttons enabled or disabled
    public void setButtons()
    {
        Log.d(TAG,"In setButtons()");
        if(correct_visible)
        {
            mCorrectButton.setEnabled(true);
        }
        else
        {
            mCorrectButton.setEnabled(false);
        }
        if(incorrect_visible)
        {
            mIncorrectButton.setEnabled(true);
        }
        else
        {
            mIncorrectButton.setEnabled(false);
        }
        if(next_visible)
        {
            mNextButton.setEnabled(true);
        }
        else
        {
            mNextButton.setEnabled(false);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"In onSaveInstanceState");
        //Saving question index and the prime number
        savedInstanceState.putInt("CurrentQuestion",QuestionIndex);
        savedInstanceState.putInt("CurrentPrime", CurrentPrime);
        savedInstanceState.putIntArray("prime", prime);
        savedInstanceState.putBoolean("correct_visible",correct_visible);
        savedInstanceState.putBoolean("incorrect_visible",incorrect_visible);
        savedInstanceState.putBoolean("next_visible",next_visible);
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
    //Applying Seive method
    public int[] Seive()
    {
        Log.d(TAG, "In Seive()");
        int[] prime = new int[1001];
        for(int i=0;i<=1000;i++)
            prime[i] = 1;
        for(int i=2;i*i<=1000;i++)
        {
            if(prime[i]==1)
            {
                for(int j=i*2;j<=1000;j+=i)
                    prime[j] = 0;
            }
        }
        prime[1]=0;
        return prime;

    }

    public void getQuestion() {
        Log.d(TAG, "In getQuestion()");
        if(correct_visible==true&&incorrect_visible==true) {
            mNextButton.setEnabled(false);
            next_visible = false;
        }
        else
        {
            mNextButton.setEnabled(true);
            next_visible = true;

        }
        if(CurrentPrime==0) {
            CurrentPrime = generatePrime(); //Getting a random number
        }
        // Setting the text for TextView
        mQuestionView.setText(QuestionIndex + ". " + "Is " + CurrentPrime + " a Prime number?");

    }
    // Function used for instantiating all the widgets
    public void intialize()
    {
        Log.d(TAG,"In initialize()");
        mCorrectButton = (Button)findViewById(R.id.buttonCorrect);
        mIncorrectButton = (Button)findViewById(R.id.buttonIncorrect);
        mNextButton = (Button)findViewById(R.id.buttonNext);
        mQuestionView = (TextView)findViewById(R.id.QuestionView);
        mIncorrectButton.setEnabled(true);
        mCorrectButton.setEnabled(true);
        correct_visible = true;
        incorrect_visible = true;

    }
    // This will generate a random number in the range [1,1000]
    public Integer generatePrime()
    {
        Log.d(TAG, "In generatePrime()");
        return (1+(int)(Math.random()*(1000-1)+1));
    }
    // ActionListener for Next Question button
    public void onNext(View v)
    {
        Log.d(TAG,"In onNext()");
        mIncorrectButton.setEnabled(true);
        mCorrectButton.setEnabled(true);
        correct_visible=true;
        incorrect_visible=true;
        CurrentPrime = 0;
        QuestionIndex = ((QuestionIndex+1)%1001);
        getQuestion();
    }
    // ActionListener for Correct button
    public void onCorrect(View v)
    {

        Log.d(TAG,"In onCorrect()");
        mIncorrectButton.setEnabled(false);
        incorrect_visible = false;
        mNextButton.setEnabled(true);
        next_visible = true;
        if(checkPrime(CurrentPrime)) {
            Toast.makeText(getApplicationContext(), "Your answer is Correct", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Your answer is Incorrect", Toast.LENGTH_SHORT).show();

        }
    }
    // ActionListener for Incorrect button
    public void onIncorrect(View v)
    {
        Log.d(TAG,"In onIncorrect()");
        mCorrectButton.setEnabled(false);
        correct_visible = false;
        mNextButton.setEnabled(true);
        next_visible = true;
        if(checkPrime(CurrentPrime)) {
            Toast.makeText(getApplicationContext(), "Your answer is Incorrect", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Your answer is Correct", Toast.LENGTH_SHORT).show();

        }
    }
    // Function to handle Hint button click
    public void onHint(View v)
    {
            Intent i = new Intent(this,HintActivity.class);
            i.putExtra("prime",CurrentPrime);
            startActivityForResult(i,2);

    }
    // Function to handle Cheat button click
    public void onCheat(View v)
    {
            Intent i = new Intent(this,CheatActivity.class);
            i.putExtra("prime",CurrentPrime);
            i.putExtra("result",checkPrime(CurrentPrime));
            startActivityForResult(i,1);
    }


    // Function to check whether a given integer is prime or not
    public Boolean checkPrime(Integer n)
    {
        Log.d(TAG,"In checkPrime()");
        if(prime[n]==0)
            return false;
        else
            return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG,"in onActivityResult");
        if (requestCode == 1) {
            if (resultCode == 1) {
                if(data.getBooleanExtra("Cheated",false))
                {
                    Toast.makeText(this,"You have cheated",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(this,"You did not cheat",Toast.LENGTH_SHORT).show();
                }
            }
            else if(resultCode==0)
            {
                Toast.makeText(this,"You did not cheat",Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==2)
        {
            if(resultCode==1)
            {
                if(data.getBooleanExtra("Hint",false))
                {
                    Toast.makeText(this,"You have taken hint",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"You did not take hint",Toast.LENGTH_SHORT).show();
                }
            }
            else if(resultCode==0)
            {
                Toast.makeText(this,"You did not take hint",Toast.LENGTH_SHORT).show();
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
