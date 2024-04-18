package com.example.directoryforpermissions;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.Manifest;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowInsetsCompat;

import com.example.mypermissions.PermissionManagerImpl;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    private static final int CONTACTS_PERMISSION_REQUEST_CODE = 102;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermission();
            }
        });

        findViewById(R.id.btnContacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestContactsPermission();
            }
        });

        findViewById(R.id.btnLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocationPermission();
            }
        });

    }

    private void requestCameraPermission() {
        PermissionManagerImpl permissionManager = new PermissionManagerImpl(this);
        if (!permissionManager.checkPermissionStatus(android.Manifest.permission.CAMERA)) {
            permissionManager.requestPermission(this, android.Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Camera permission already granted
            Toast.makeText(this, "Camera permission already granted", Toast.LENGTH_SHORT).show();
            permissionManager.openCameraApp(this);
        }
    }

    private void requestLocationPermission() {
        PermissionManagerImpl permissionManager = new PermissionManagerImpl(this);
        if (!permissionManager.checkPermissionStatus(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionManager.requestPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Location permission already granted
            Toast.makeText(this, "Location permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestContactsPermission() {
        PermissionManagerImpl permissionManager = new PermissionManagerImpl(this);
        if (!permissionManager.checkPermissionStatus(android.Manifest.permission.READ_CONTACTS)) {
            permissionManager.requestPermission(this, android.Manifest.permission.READ_CONTACTS, CONTACTS_PERMISSION_REQUEST_CODE);
        } else {
            // Contacts permission already granted
            Toast.makeText(this, "Contacts permission already granted", Toast.LENGTH_SHORT).show();
            openContactsApp();
        }
    }

//    private void openCameraApp() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
//            ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }

    private void openContactsApp() {
        Intent contactsIntent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
        if (contactsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(contactsIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Delegate permission handling to the singleton instance with the correct context
        PermissionManagerImpl.getInstance(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}