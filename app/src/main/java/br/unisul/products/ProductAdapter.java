package br.unisul.products;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.unisul.products.domain.DeleteProduct;
import br.unisul.products.domain.Product;
import br.unisul.products.domain.ProductRepository;
import br.unisul.products.infra.SQLiteProductRepository;

public class ProductAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Product> products;
    private static LayoutInflater inflater = null;
    private Button removeButton;
    private Button editButton;
    private DeleteProduct deleteProduct;

    public ProductAdapter(Activity context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ProductRepository productRepository = new SQLiteProductRepository(context);
        this.deleteProduct = new DeleteProduct(productRepository);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.product, null) : itemView;
        TextView textViewId = itemView.findViewById(R.id.textViewId);
        TextView textViewName = itemView.findViewById(R.id.textViewName);
        TextView textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        TextView textViewValue = itemView.findViewById(R.id.textViewValue);

        Product product = products.get(position);
        textViewId.setText("Id: " + product.getId());
        textViewName.setText("Nome: " + product.getName());
        textViewQuantity.setText("Quantidade: " + product.getQuantity());
        textViewValue.setText("Valor: " + product.getValue());
        System.out.println("Valor: " + product.getValue());

        removeButton = itemView.findViewById(R.id.removeButton);

        removeButton.setOnClickListener(v -> {
            this.deleteProduct.delete(product.getId());
            products.remove(position);
            notifyDataSetChanged();
        });
        editButton = itemView.findViewById(R.id.editButton);
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditProductActivity.class);
            intent.putExtra("productId", product.getId());
            context.startActivity(intent);
        });

        return itemView;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
