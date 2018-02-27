package com.danielcampu.formulario;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; //logt y luego autocompleto
    private TextView tDisplayDate, tshowUser, tshowPassword, tshowEmail, tshowGender, tshowDate, tshowCity, tshowHobbies;
    private DatePickerDialog.OnDateSetListener dDateSetListener;
    private int _year,_month,_day;
    private Spinner sCities;
    private String city, gender = "Male", string1 = "", string2 = "", string3 = "", string4 = "";
    private EditText eUser, ePassword, eConfirmPassword, eEmail;
    private CheckBox cCars, cFilms, cSoccer, cTech;
    private boolean flagCheckbox = false, flagDate = false, flagRadio = false;
    private RadioGroup buttonGroup;
    LinearLayout lDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tDisplayDate = findViewById(R.id.tBirthday);
        sCities = findViewById(R.id.sCiudad);
        eUser = findViewById(R.id.eUser);
        ePassword = findViewById(R.id.ePassword);
        eConfirmPassword = findViewById(R.id.eConfirmPassword);
        eEmail = findViewById(R.id.eEmail);
        cCars = findViewById(R.id.cCars);
        cFilms = findViewById(R.id.cFilms);
        cSoccer = findViewById(R.id.cSoccer);
        cTech = findViewById(R.id.cTech);
        tshowUser = findViewById(R.id.tshowUser);
        tshowPassword = findViewById(R.id.tshowPassword);
        tshowEmail = findViewById(R.id.tshowEmail);
        tshowDate = findViewById(R.id.tshowDate);
        tshowCity = findViewById(R.id.tshowCity);
        tshowGender = findViewById(R.id.tshowGender);
        tshowHobbies = findViewById(R.id.tshowHobbies);
        buttonGroup = findViewById(R.id.buttonGroup);
        lDatos = findViewById(R.id.lDatos);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sCities.setAdapter(adapter);

        sCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //adapterView recibe el adaptar, el view, la posicion
                city = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDate = true;
                Calendar cal = Calendar.getInstance();
                _year = cal.get(Calendar.YEAR);
                _month = cal.get(Calendar.MONTH);
                _day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dDateSetListener, _year, _month, _day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                _month = month +1;
                _day   = dayOfMonth;
                _year  = year;
                String msg = _day + "/" + _month + "/" + _year;
                tDisplayDate.setText(msg);
            }
        };

    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        flagRadio = true;

        if(id == R.id.rFemale) {
            gender = "Female";
        }else if(id == R.id.rMale){
            gender = "Male";
        }

    }

    public void onCheckboxClicked(View view) {

        flagCheckbox = true;

        int id = view.getId();

        switch (id){
            case R.id.cCars:
                if(cCars.isChecked()){
                    string1 = "Cars";
                }else{
                    string1 = "";    //If is not checked, so the value is empty
                }
                break;
            case R.id.cFilms:
                if(cFilms.isChecked()){
                    string2 = "Films";
                }else{
                    string2 = "";
                }
                break;
            case R.id.cSoccer:
                if(cSoccer.isChecked()){
                    string3 = "Soccer";
                }else {
                    string3 = "";
                }
                break;
            case R.id.cTech:
                if(cTech.isChecked()){
                    string4 = "Tech";
                }else {
                    string4 = "";
                }
                break;
        }

    }

    public void onButtonClicked(View view) {
        //Get Values
        String user, password, password1, email;

        user = eUser.getText().toString();
        password = ePassword.getText().toString();
        password1 = eConfirmPassword.getText().toString();
        email = eEmail.getText().toString();

        //Look for empty values
        if(user.isEmpty() || password.isEmpty() || password1.isEmpty() || email.isEmpty() || !flagCheckbox || !flagDate || !flagRadio){
            Toast.makeText(this, "Por favor llene todos los campos" , Toast.LENGTH_LONG).show();
        }else {
            //Check passwords

                if (!password.equals(password1)) {
                    Toast.makeText(this, "Passwords doesn't match" , Toast.LENGTH_LONG).show();
                }else{
                    flagCheckbox = false;
                    flagDate = false;
                    flagRadio = false;

                    lDatos.setVisibility(View.VISIBLE);

                    //Toast.makeText(this, "Bien" , Toast.LENGTH_LONG).show();
                    //Set values...Set Values into TextViews
                    tshowUser.setText(" "+user);
                    tshowPassword.setText(" "+password);
                    tshowEmail.setText(" "+email);
                    tshowGender.setText(" "+gender);
                    String msg = _day + "/" + _month + "/" + _year;
                    tshowDate.setText(" "+msg);
                    tshowCity.setText(" "+city);
                    tshowHobbies.setText(" "+string1+" "+string2+" "+string3+" "+string4);


                    //Vaciar
                    eUser.setText("");
                    ePassword.setText("");
                    eConfirmPassword.setText("");
                    eEmail.setText("");
                    tDisplayDate.setText("00/0/0000");
                    buttonGroup.clearCheck();
                    cCars.setChecked(false);
                    cTech.setChecked(false);
                    cSoccer.setChecked(false);
                    cFilms.setChecked(false);



                }

        }


    }
}
