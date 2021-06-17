package br.unisul.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddProductActivityButton(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    public void onClickSearchProductsActivityButton(View view) {
        Intent intent = new Intent(this, SearchProductsActivity.class);
        startActivity(intent);
    }

    public void onClickProductsListActivityButton(View view) {
        Intent intent = new Intent(this, ListProductsActivity.class);
        startActivity(intent);
    }
}