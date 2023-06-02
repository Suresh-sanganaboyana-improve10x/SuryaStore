package com.example.suryastore;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suryastore.network.FakeApi;
import com.example.suryastore.network.FakeApiService;

public class BaseActivity extends AppCompatActivity {
    protected FakeApiService fakeApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFakeApiService();
    }

    // private
    protected void setupFakeApiService() {
        FakeApi fakeApi = new FakeApi();
        fakeApiService = fakeApi.createFakeApiService();
    }

    // TODO : protected
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
