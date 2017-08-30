package com.example.sony.placepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION =99 ;
    TextView mselectedPlaces;
    Button mButton;
    private final int REQUEST_CODE_PLACEPICKER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= (Button) findViewById(R.id.placepickerbtn);
        mButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startPlacePickerActivity();
           }
       });



    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), REQUEST_CODE_PLACEPICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(data, this);
            String placeName = String.format("Place: %s", place.getAddress());
            mselectedPlaces= (TextView) findViewById(R.id.selectedPlaces);
            mselectedPlaces.setText(placeName);
        }
    }




}
