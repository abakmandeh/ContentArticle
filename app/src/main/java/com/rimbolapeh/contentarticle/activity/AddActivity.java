package com.rimbolapeh.contentarticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rimbolapeh.contentarticle.R;
import com.rimbolapeh.contentarticle.helper.DatabaseClient;
import com.rimbolapeh.contentarticle.model.room.Content;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {


    RadioGroup rg_gender;
    RadioButton rb_male, rb_female;
    EditText et_judul, et_content, et_tanggal, et_phone;
    Button btn_save;

    String status; //nampung radio button male/female

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

        status = ""; //nilai awal status/gender radio button

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        status = "Male";
                        break;
                    case R.id.rb_female:
                        status = "Female";
                        break;
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_judul.getText().toString().equals("") ||
                        status.equals("") ||
                        et_content.getText().toString().equals("") ||
                        et_tanggal.getText().toString().equals("") ||
                        et_phone.getText().toString().equals("")
                        )
                {
                    Toast.makeText(AddActivity.this, "Harap lengkapi form", Toast.LENGTH_SHORT).show();
                } else {
                    //startActivity berfungsi utk berpindah halaman
                    save();
                }

            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_tanggal.setText(sdf.format(myCalendar.getTime()));
    }

    private void save() {

        final String mJudul = et_judul.getText().toString();
        final String mContent = et_content.getText().toString();
        final String mGender = status;
        final String mPhone = et_phone.getText().toString();
        final String mTanggal = et_tanggal.getText().toString();

        class SaveContent extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) { //memproses data

                Content content = new Content();
                content.setJudul(mJudul);
                content.setMycontent(mContent);
                content.setCategory(mGender);
                content.setTanggal(mTanggal);
                content.setPhone(mPhone);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().contentDao().insert(content);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(AddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(AddActivity.this, HomeActivity.class));
                finish();
            }
        }

        SaveContent saveContent = new SaveContent();
        saveContent.execute();

    }

}
