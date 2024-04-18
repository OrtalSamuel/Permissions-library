package com.example.mypermissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManagerImpl implements PermissionManager {
    private static PermissionManagerImpl instance;
    private Context context;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 101;
    private static final int CONTACTS_PERMISSION_REQUEST_CODE = 102;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 103;


    public PermissionManagerImpl(Context context) {
        this.context = context;
    }

    public static synchronized PermissionManagerImpl getInstance(Context context) {
        if (instance == null) {
            instance = new PermissionManagerImpl(context);
        }
        return instance;
    }

    @Override
    public boolean checkPermissionStatus(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(Activity activity, String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void handlePermissionResponse(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case CAMERA_PERMISSION_REQUEST_CODE:
                  //  openCameraApp(this);
                    break;
                case CONTACTS_PERMISSION_REQUEST_CODE:
                    openContactsApp();
                    break;
                case LOCATION_PERMISSION_REQUEST_CODE:
                    openLocationSettings();
                    break;
            }
        }
    }

    public void openCameraApp(Context context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                // Handle non-Activity contexts if needed
            }
        }
    }

    private void openContactsApp() {
        Intent contactsIntent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
        context.startActivity(contactsIntent);
    }

    private void openLocationSettings() {
        Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(locationIntent);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // Delegate handling to the singleton instance
        getInstance(context).handlePermissionResponse(requestCode, permissions, grantResults);
    }
}