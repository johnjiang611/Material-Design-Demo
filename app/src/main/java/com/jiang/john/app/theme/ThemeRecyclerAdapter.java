package com.jiang.john.app.theme;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.john.app.R;

/**
 * Created by jiajun.jiang on 2016/2/17.
 */
public class ThemeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private Activity context;
        private ThemeTool themeTool;
        private int idex;

        public ThemeRecyclerAdapter(Activity context){
            this.context = context;
            themeTool = ThemeTool.getInstance();
            idex = ThemeTool.getThemeIndex(context);
        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.theme_item_layout,null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.setIsRecyclable(true);
            return viewHolder;
        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(context.getResources().getColor(ThemeTool.themeColors[position]));
        gradientDrawable.setCornerRadius(10);
        viewHolder.imageView.setBackground(gradientDrawable);
        if (idex == position){
            viewHolder.textView.setText("");
        }else {
            viewHolder.textView.setText(ThemeTool.themeNames[position]);
            viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        dismiss();
                    themeTool.onTheme(context,position);
                    themeTool.refreshTheme(context);
                }
            });
        }
    }

        @Override
        public int getItemCount() {
            return ThemeTool.themeIds.length;
        }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardview;
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
            textView = (TextView) itemView.findViewById(R.id.tv);
            imageView = (ImageView)itemView.findViewById(R.id.image);

//            cardview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PhoneUtils.getInstance(context).get720WScale(500)));
        }
    }
}
