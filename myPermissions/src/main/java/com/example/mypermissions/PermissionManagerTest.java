package com.example.mypermissions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class PermissionManagerTest {
//    @Mock
//    Context context;
//
//    @Test
//    public void testCheckPermissionStatus() {
//        // Mock the context behavior
//        when(context.checkSelfPermission(Manifest.permission.CAMERA))
//                .thenReturn(PackageManager.PERMISSION_GRANTED);
//        when(context.checkSelfPermission(Manifest.permission.READ_CONTACTS))
//                .thenReturn(PackageManager.PERMISSION_DENIED);
//
//        // Test checkPermissionStatus method
//        PermissionManagerImpl permissionManager = new PermissionManagerImpl(context);
//        assertTrue(permissionManager.checkPermissionStatus(Manifest.permission.CAMERA));
//        assertFalse(permissionManager.checkPermissionStatus(Manifest.permission.READ_CONTACTS));
//    }
}