package br.unisul.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.unisul.products.domain.ListProducts;
import br.unisul.products.domain.Product;
import br.unisul.products.domain.ProductRepository;
import br.unisul.products.infra.SQLiteProductRepository;

public class ListProductsActivity extends AppCompatActivity {
    private ListView productsListView;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private ListProducts listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        ProductRepository productRepository = new SQLiteProductRepository(this);
        listProducts = new ListProducts(productRepository);

        populateListView();
    }

    public void populateListView() {
        productsListView = findViewById(R.id.productsListView2);
        products = (ArrayList<Product>) listProducts.list();
        adapter = new ProductAdapter(this, products);
        productsListView.setAdapter(adapter);
    }

    protected void onRestart() {

        super.onRestart();
        populateListView();
    }

    public void onClickPreviousPageButton(View view) {
        finish();
    }
}