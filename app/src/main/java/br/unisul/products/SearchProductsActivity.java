package br.unisul.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.unisul.products.domain.FindProductsByName;
import br.unisul.products.domain.Product;
import br.unisul.products.domain.ProductRepository;
import br.unisul.products.infra.SQLiteProductRepository;

public class SearchProductsActivity extends AppCompatActivity {

    private ListView productsListView;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private FindProductsByName findProductsByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        ProductRepository productRepository = new SQLiteProductRepository(this);
        findProductsByName = new FindProductsByName(productRepository);
    }

    public void onClickSearchProductsButton(View view) {
        EditText productNameText3 = findViewById(R.id.productNameText3);
        populateListView(productNameText3.getText().toString());
    }

    public void populateListView(String name) {
        productsListView = findViewById(R.id.productsListView2);
        products = (ArrayList<Product>) findProductsByName.findByName(name);
        adapter = new ProductAdapter(this, products);
        productsListView.setAdapter(adapter);
    }

    public void onClickPreviousPageButton(View view) {
        finish();
    }

    protected void onRestart() {

        super.onRestart();
        EditText productNameText3 = findViewById(R.id.productNameText3);
        populateListView(productNameText3.getText().toString());
    }
}