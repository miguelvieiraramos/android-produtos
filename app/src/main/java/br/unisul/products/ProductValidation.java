package br.unisul.products;

import android.text.TextUtils;
import android.widget.EditText;

public class ProductValidation {

    public boolean validate(EditText name, EditText quantity, EditText value) {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("Name is required.");
            return false;
        } else if (TextUtils.isEmpty(quantity.getText())) {
            quantity.setError("Quantity is required.");
            return false;
        } else if (TextUtils.isEmpty(value.getText())) {
            value.setError("Value is required.");
            return false;
        }
        return true;
    }
}
