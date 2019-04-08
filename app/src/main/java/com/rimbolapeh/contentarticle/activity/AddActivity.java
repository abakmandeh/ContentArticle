package com.rimbolapeh.contentarticle.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rimbolapeh.contentarticle.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {


    RadioGroup rg_gender;
    RadioButton rb_male, rb_female;
    EditText et_judul, et_content, et_tanggal, et_phone;
    Button btn_save;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

//        // start menambahkan tombol back
//        ActionBar menu = getSupportActionBar();
//        menu.setDisplayHomeAsUpEnabled(true);
//        menu.setDisplayShowCustomEnabled(true);
//        // end menambahkan tombol back

        rg_gender = (RadioGroup) findViewById(R.id.rg_gender);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        et_judul = (EditText) findViewById(R.id.et_judul);
        et_content = (EditText) findViewById(R.id.et_content);
        et_tanggal = (EditText) findViewById(R.id.et_tanggal);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_save = (Button) findViewById(R.id.btn_save);

        et_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateLabel();

        }
    };

    private void updateLabel(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_tanggal.setText(sdf.format(myCalendar.getTime()));
    }
}
