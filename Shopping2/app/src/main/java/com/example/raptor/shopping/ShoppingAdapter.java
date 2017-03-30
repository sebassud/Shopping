package com.example.raptor.shopping;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.raptor.shopping.database.ShoppingCrud;

import java.util.List;

/**
 * Created by android on 2017-03-10.
 */

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingViewHolder> {

    private List<ShoppingItem> list;
    private Context mContext;
    private ShoppingCrud dbShopping;

    public ShoppingAdapter(List<ShoppingItem> list, Context mContext, ShoppingCrud dbShopping) {
        this.list = list;
        this.mContext = mContext;
        this.dbShopping = dbShopping;
    }


    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_shopping_item, null);
        ShoppingViewHolder shoppingViewHolder = new ShoppingViewHolder(view);
        return shoppingViewHolder;
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, final int position) {

        ShoppingItem shoppingItem = list.get(position);
        holder.mTitleView.setText(shoppingItem.getTitle());
        holder.mQualityView.setText(String.format("%d",shoppingItem.getQuality()));
        holder.mIcon.setImageResource(shoppingItem.getIdImage());
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingItem item = list.get(position);
                list.remove(position);
                dbShopping.deleteItem(item);
                notifyItemRemoved(position);
                Toast.makeText(mContext, String.format("Produkt %s został kupiony",item.getTitle()),
                        Toast.LENGTH_SHORT).show();
                notifyItemRangeChanged(position,list.size());

            }
        });

        holder.mContainerItems.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editItem(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list!=null) ? list.size() : 0;
    }

    private void editItem(final int position){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_item);
        final ShoppingItem item = list.get(position);

        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.np);
        final EditText edtext = (EditText) dialog.findViewById(R.id.name_editText);
        edtext.setText(item.getTitle());
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
        np.setValue(item.getQuality());
        TypedArray images = mContext.getResources().obtainTypedArray(R.array.icon_image);
        final Spinner spinner = (Spinner)dialog.findViewById(R.id.icons_spinner);
        int len = mContext.getResources().getInteger(R.integer.size_list_icons);
        int cur = 3;
        final Integer[] resIds = new Integer[len];
        for (int i = 0; i < len; i++) {
            resIds[i] = images.getResourceId(i, 0);
            if((int)resIds[i] == (int)item.getIdImage()) cur = i;
        }
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(mContext,
                resIds);
        spinner.setAdapter(adapter);
        spinner.setSelection(cur);

        Button btnSave = (Button) dialog.findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTitle(edtext.getText().toString());
                item.setQuality(np.getValue());
                item.setIdImage(resIds[spinner.getSelectedItemPosition()]);
                notifyItemChanged(position);
                dialog.cancel();
                dbShopping.updateItem(item);
            }
        });
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
