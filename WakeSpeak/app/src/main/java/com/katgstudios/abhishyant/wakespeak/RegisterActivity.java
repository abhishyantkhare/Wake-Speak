package com.katgstudios.abhishyant.wakespeak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;


public class RegisterActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RegisterFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class RegisterFragment extends Fragment {

        EditText email;
        EditText password;
        EditText passwordConfirm;

        public RegisterFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_register, container, false);
            email = (EditText)rootView.findViewById(R.id.emailText);
            password = (EditText)rootView.findViewById(R.id.passText);
            passwordConfirm = (EditText) rootView.findViewById(R.id.passTextConfirm);
            Button registerButton = (Button) rootView.findViewById(R.id.register_button);
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(passwordsMatch()&&nothingNull()) {
                    Log.d("BUTTON CLICKED","butt is clicked");
                   final String userEmail = email.getText().toString();
                    String psswd = password.getText().toString();
                    ParseUser user = new ParseUser();
                    user.setUsername(userEmail);
                    user.setPassword(psswd);
                   // ArrayList<AlarmObject> alarmList = new ArrayList<AlarmObject>();


                   // user.put("Alarm List", alarmList);

                    user.signUpInBackground(new SignUpCallback() {

                        public void done(ParseException e) {
                            Log.d("PARSE STuFF CALLED","parse stuff is bein callled");
                            if (e == null) {

                                Intent newUserIntent = new Intent(getActivity(),AlarmActivity.class);
                                newUserIntent.putExtra("userEmail",userEmail);
                                startActivity(newUserIntent);
                            } else {
                                e.printStackTrace();
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                            }
                        }
                    });
                }
                else if(nothingNull()){
                    Toast.makeText(getActivity(), "Passwords do not match!", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getActivity(), "All fields must be filled in!", Toast.LENGTH_LONG).show();
                }
                }
            });
            return rootView;
        }
        private boolean passwordsMatch(){
            return password.getText().toString().equals(passwordConfirm.getText().toString());
        }
        private boolean nothingNull(){
            return !(email.getText().equals("") || password.getText().toString().equals("") || passwordConfirm.getText().toString().equals(""));
        }
    }
}
