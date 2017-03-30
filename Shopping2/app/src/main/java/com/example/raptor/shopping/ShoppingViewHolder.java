package com.example.raptor.shopping;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by android on 2017-03-10.
 */

public class ShoppingViewHolder extends RecyclerView.ViewHolder {

    float size = 1;
    TextView mTitleView;
    TextView mQualityView;
    TextView mTextView1;
    TextView mTextView2;
    FloatingActionButton mDeleteButton;
    RelativeLayout mContainerItems;
    ImageView mIcon;
    private int normalSizeFont = 28;

    public ShoppingViewHolder(View itemView) {
        super(itemView);
        this.mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        this.mQualityView = (TextView) itemView.findViewById(R.id.item_quality);
        this.mTextView1 = (TextView) itemView.findViewById(R.id.text_view1);
        this.mTextView2 = (TextView) itemView.findViewById(R.id.text_view2);
        this.mDeleteButton = (FloatingActionButton) itemView.findViewById(R.id.remove_item);
        this.mContainerItems = (RelativeLayout) itemView.findViewById(R.id.container_items);
        this.mIcon = (ImageView) itemView.findViewById(R.id.item_icon);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        ChangeColor(sharedPref.getString(SettingsActivity.MyPreferenceFragment.COLOR_FONT_PREF_LIST, ""));
        ChangeSizeFont(sharedPref.getString(SettingsActivity.MyPreferenceFragment.SIZE_FONT_PREF_LIST,""));
    }

    private void ChangeColor(String color){
        switch (color){
            case "red":
                ChangeRedColor();
                break;
            case "blue":
                ChangeBlueColor();
                break;
            case "green":
                ChangeGreenColor();
                break;
        }
    }

    private void ChangeRedColor() {
        mTitleView.setTextColor(Color.parseColor("#D32F2F"));
        mQualityView.setTextColor(Color.parseColor("#D32F2F"));
        mTextView1.setTextColor(Color.parseColor("#B71C1C"));
        mTextView2.setTextColor(Color.parseColor("#B71C1C"));
        mContainerItems.setBackgroundColor(Color.parseColor("#FFEBEE"));
    }

    private void ChangeBlueColor() {
        mTitleView.setTextColor(Color.parseColor("#1976D2"));
        mQualityView.setTextColor(Color.parseColor("#1976D2"));
        mTextView1.setTextColor(Color.parseColor("#0D47A1"));
        mTextView2.setTextColor(Color.parseColor("#0D47A1"));
        mContainerItems.setBackgroundColor(Color.parseColor("#E3F2FD"));
    }

    private void ChangeGreenColor() {
        mTitleView.setTextColor(Color.parseColor("#388E3C"));
        mQualityView.setTextColor(Color.parseColor("#388E3C"));
        mTextView1.setTextColor(Color.parseColor("#1B5E20"));
        mTextView2.setTextColor(Color.parseColor("#1B5E20"));
        mContainerItems.setBackgroundColor(Color.parseColor("#E8F5E9"));
    }

    private void ChangeSizeFont(String sizes){
        switch (sizes){
            case "small":
                size = 0.8f;
                break;
            case "normal":
                size = 1f;
                break;
            case "huge":
                size = 1.2f;
                break;
        }

        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * normalSizeFont);;
        mQualityView.setTextSize(TypedValue.COMPLEX_UNIT_PX , size * normalSizeFont);
        mTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX , size * (normalSizeFont + 5));
        mTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX , size * (normalSizeFont + 5));
        mIcon.getLayoutParams().width=(((int)(size * 80 * 1.3)));
        mIcon.getLayoutParams().height=(((int)(size * 80 * 1.3)));
    }

}
