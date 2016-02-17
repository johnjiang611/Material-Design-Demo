package com.jiang.john.app.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.jiang.john.app.R;

/**
 * Created by jiajun.jiang on 2016/2/17.
 */
public class ThemeSelectDiaolg extends Dialog {
    private RecyclerView recyclerview;
    private Activity context;

    public ThemeSelectDiaolg(Activity context) {
        super(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_theme_layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 600;
        layoutParams.height = 700;
        window.setAttributes(layoutParams);
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL, false));
        recyclerview.addItemDecoration(new DividerGridItemDecoration(context,10));
        ThemeRecyclerAdapter adapter = new ThemeRecyclerAdapter(context);
        recyclerview.setAdapter(adapter);
    }
}
