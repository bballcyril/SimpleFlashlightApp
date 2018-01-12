package com.example.maand.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(hasFlash()) {
                            if (!isChecked) {
                                flashLightOff();
                            } else {
                                flashLightOn();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Flashlight not available",Toast.LENGTH_SHORT);
                        }
                    }
                }
        );
    }
    //Checks if user has a flashlight
    private boolean hasFlash(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
    private void flashLightOn(){
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID = cm.getCameraIdList()[0];
            cm.setTorchMode(cameraID,true);
        }catch(CameraAccessException e){
            e.printStackTrace();
        }
    }
    private void flashLightOff(){
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID = cm.getCameraIdList()[0];
            cm.setTorchMode(cameraID,false);
        }catch(CameraAccessException e){
            e.printStackTrace();
        }
    }
}
