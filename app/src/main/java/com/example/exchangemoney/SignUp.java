package com.example.exchangemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword, logoText, sloganText;
    Button regBtn, regToLoginBtn;
    ImageView image;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        image = findViewById(R.id.logo_image);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
    }//onCreate Method End

    private Boolean validateName(){

        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()){
            regName.setError("Fild Name Can't be Empty!");
            return false;
        }else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername(){

        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace ="\\A\\w{4,20}\\z";

        if (val.isEmpty()){
            regUsername.setError("Fild Name Can't be Empty!");
            return false;

        }else if (val.length() >= 15) {

            regUsername.setError("Username is too Long");
            return false;

        }else if (!val.matches(noWhiteSpace)) {

            regUsername.setError("White Spaces is not alwoed");
            return false;

        }else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){

        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            regEmail.setError("Fild Name Can't be Empty!");
            return false;
        }else if (!val.matches(emailPattern)) {

            regEmail.setError("Invalid email address");
            return false;

        }else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone(){

        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()){
            regPhoneNo.setError("Fild Name Can't be Empty!");
            return false;
        }else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){

        String val = regUsername.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()){
            regUsername.setError("You must have 4 characters in your password");
            return false;
        }else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }

    public void registerUser(View view){

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePhone() | !validatePassword()){

            return;

        }else {

            //Get all the values
            String name = regName.getEditText().getText().toString();
            String username = regUsername.getEditText().getText().toString();
            String email = regEmail.getEditText().getText().toString();
            String phoneNo = regPhoneNo.getEditText().getText().toString();
            String password = regPassword.getEditText().getText().toString();
            UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
            reference.child(username).setValue(helperClass);
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);

        }

    }

    //Call SignUp Screen
    public void callSignInScreen(View view) {
        //To call next activity
        Intent intent = new Intent(SignUp.this, Login.class);
//        startActivity(intent);
        Pair[] pairs = new Pair[5];
        pairs[0] = new Pair<View, String>(image, "logo_image");
//1st one is the element and 2nd is the transition name of animation.
        pairs[1] = new Pair<View, String>(regUsername, "username_tran");
        pairs[2] = new Pair<View, String>(regPassword, "password_tran");
        pairs[3] = new Pair<View, String>(regBtn, "button_tran");
        pairs[4] = new Pair<View, String>(regToLoginBtn, "login_signup_tran");
//Call next activity by attaching the animation with it.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        }

    }


}