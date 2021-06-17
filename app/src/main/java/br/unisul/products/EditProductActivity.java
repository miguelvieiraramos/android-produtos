package br.unisul.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.unisul.products.domain.FindProductById;
import br.unisul.products.domain.Product;
import br.unisul.products.domain.ProductRepository;
import br.unisul.products.domain.UpdateProduct;
import br.unisul.products.infra.SQLiteProductRepository;

public class EditProductActivity extends AppCompatActivity {
    private EditText productIdText;
    private EditText productNameText;
    private EditText productQuantityText;
    private EditText productValueText;
    private UpdateProduct updateProduct;
    private FindProductById findProductById;
    private ProductValidation productValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        productValidation = new ProductValidation();
        ProductRepository productRepository = new SQLiteProductRepository(this);
        findProductById = new FindProductById(productRepository);
        updateProduct = new UpdateProduct(productRepository);

        Bundle extras = getIntent().getExtras();
        int productId = extras.getInt("productId");

        productIdText = findViewById(R.id.productIdText2);
        productIdText.setEnabled(false);
        productNameText = findViewById(R.id.productNameText2);
        productQuantityText = findViewById(R.id.productQuantityText2);
        productValueText = findViewById(R.id.productValueText2);
        Product product = findProductById.findById(productId);
        productIdText.setText(String.valueOf(product.getId()));
        productNameText.setText(product.getName());
        productQuantityText.setText(String.valueOf(product.getQuantity()));
        productValueText.setText(String.valueOf(product.getValue()));
    }

    public void updateProduct(View view) {
        if (productValidation.validate(productNameText, productQuantityText, productValueText)) {
            int id = Integer.parseInt(productIdText.getText().toString());
            String name = productNameText.getText().toString();
            int quantity = Integer.parseInt(productQuantityText.getText().toString());
            double value = Double.parseDouble(productValueText.getText().toString());
            Product product = new Product(name, quantity, value);
            product.setId(id);
            this.updateProduct.update(product);
        }
    }

    public void onClickPreviousPageButton(View view) {
        finish();
    }
}