package com.example.raptor.shopping;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raptor.shopping.database.ShoppingCrud;
import com.example.raptor.shopping.database.ShoppingDbHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by android on 2017-03-10.
 */

public class MainFragment extends Fragment {
    public static boolean ISCHANGED = false;
    private List<ShoppingItem> list = new ArrayList<ShoppingItem>();
    private ShoppingAdapter shoppingAdapter;
    private RecyclerView recyclerView;
    private ShoppingCrud dbShopping;

    @Override
    public void onResume() {
        if(ISCHANGED){
            for(int i=0; i<list.size(); i++) shoppingAdapter.notifyItemChanged(i);
            ISCHANGED = false;
        }
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ShoppingDbHelper dbHelper = new ShoppingDbHelper(getActivity());
        dbShopping = new ShoppingCrud(dbHelper.getWritableDatabase());
        list = dbShopping.getAllItems();

        recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        shoppingAdapter = new ShoppingAdapter(list, getActivity(), dbShopping);
        recyclerView.setAdapter(shoppingAdapter);
        view.findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem();
            }
        });

        return view;
    }

    private void addItem(){
        final Dialog dialog=new Dialog(getActivity(),android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_item);

        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.np);
        final EditText edtext = (EditText) dialog.findViewById(R.id.name_editText);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
        TypedArray images = getResources().obtainTypedArray(R.array.icon_image);
        final Spinner spinner = (Spinner)dialog.findViewById(R.id.icons_spinner);
        int len = getResources().getInteger(R.integer.size_list_icons);
        final Integer[] resIds = new Integer[len];
        for (int i = 0; i < len; i++)
            resIds[i] = images.getResourceId(i, 0);
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(getActivity().getBaseContext(),
                resIds);
        spinner.setAdapter(adapter);

        Button btnSave = (Button) dialog.findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtext.getText().toString();
                ShoppingItem newItem = new ShoppingItem(0, name, np.getValue(), resIds[spinner.getSelectedItemPosition()]);
                list.add(list.size(),newItem);
                shoppingAdapter.notifyItemInserted(list.size() - 1);
                dialog.cancel();
                dbShopping.addNewItem(newItem);
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


