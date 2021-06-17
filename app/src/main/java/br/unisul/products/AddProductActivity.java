package br.unisul.products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import br.unisul.products.domain.AddProduct;
import br.unisul.products.domain.ProductRepository;
import br.unisul.products.infra.SQLiteProductRepository;

public class AddProductActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private AddProduct addProduct;
    private EditText productNameText;
    private EditText productQuantityText;
    private EditText productValueText;
    private ProductValidation productValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productValidation = new ProductValidation();

        ProductRepository productRepository = new SQLiteProductRepository(this);
        this.addProduct = new AddProduct(productRepository);

        this.productNameText = findViewById(R.id.productNameText);
        this.productQuantityText = findViewById(R.id.productQuantityText);
        this.productValueText = findViewById(R.id.productValueText);

        this.productNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }

        });
    }

    public void onClickAddProductButton(View view) {
        if (productValidation.validate(productNameText, productQuantityText, productValueText)) {
            String productName = this.productNameText.getText().toString();
            int productQuantity = Integer.parseInt(this.productQuantityText.getText().toString());
            double productValue = Double.parseDouble(this.productValueText.getText().toString());

            this.addProduct.add(productName, productQuantity, productValue);

            clearTextFields();
        }
    }

    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo.");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case  REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.productNameText.setText(result.get(0));
                }
                break;
            }
        }
    }

    public void onClickPreviousPageButton(View view) {
        finish();
    }

    public void clearTextFields() {
        this.productNameText.setText("");
        this.productValueText.setText("");
        this.productQuantityText.setText("");
    }
}