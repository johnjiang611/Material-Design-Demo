package com.jiang.john.app.theme;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.WindowManager;

import com.jiang.john.app.R;


public class ThemeTool {
    public static final int[] themeIds = new int[]{
            R.style.AppTheme_light_blue, R.style.AppTheme_amber,
            R.style.AppTheme_red, R.style.AppTheme_blue_grey,
            R.style.AppTheme_brown, R.style.AppTheme_cyan,
            R.style.AppTheme_deep_orange, R.style.AppTheme_deep_purple,
            R.style.AppTheme_green, R.style.AppTheme_grey,
            R.style.AppTheme_indigo, R.style.AppTheme_orange,
            R.style.AppTheme_teal, R.style.AppTheme_yellow,
            R.style.AppTheme_lime, R.style.AppTheme_blue,
            R.style.AppTheme_light_green, R.style.AppTheme_pink,
            R.style.AppTheme_purple
    };
    public static final int[] themeColors = new int[]{
            R.color.light_blue_600, R.color.amber_600,
            R.color.red_600, R.color.blue_grey_600,
            R.color.brown_600, R.color.cyan_600,
            R.color.deep_orange_600, R.color.deep_purple_600,
            R.color.green_600, R.color.grey_600,
            R.color.indigo_600, R.color.orange_600,
            R.color.teal_600, R.color.yellow_600,
            R.color.lime_600, R.color.blue_600,
            R.color.light_green_600, R.color.pink_600,
            R.color.purple_600
    };
    public static final String[] themeNames = new String[]{
            "light_blue", "amber",
            "red", "blue_grey",
            "brown", "cyan",
            "deep_orange", "deep_purple",
            "green", "grey",
            "indigo", "orange",
            "teal", "yellow",
            "lime", "blue",
            "light_green", "pink",
            "purple"
    };
    private int themeIndex = 0;

    public static ThemeTool instance = null;

    public static ThemeTool getInstance(){
        if(instance == null){
            instance = new ThemeTool();
        }
        return instance;
    }

    public void setPageTheme(Activity activity, Bundle savedInstanceState){
        boolean night_theme = isThemeNight(activity);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("themeIndex")) {
                themeIndex = savedInstanceState.getInt("themeIndex");
            }
        }else {
            try {
                themeIndex = Integer.parseInt(PreferenceManager
                        .getDefaultSharedPreferences(activity).getString("theme_setting", "0"));
            }catch (Exception e){
                themeIndex = 0;
            }
        }
        if(themeIndex < 0 || themeIndex > themeIds.length){
            themeIndex = 0;
        }
        if(night_theme){
            activity.setTheme(R.style.AppTheme_night);
        }else {
            activity.setTheme(themeIds[themeIndex]);  //设置主题皮肤
        }
    }

    public static int getThemeIndex(Context context){
        try {
            return Integer.parseInt(PreferenceManager
                    .getDefaultSharedPreferences(context).getString("theme_setting", "0"));
        }catch (Exception e){
           return 0;
        }
    }

    public boolean isThemeNight(Activity activity){
        return PreferenceManager.getDefaultSharedPreferences(activity)
                .getBoolean("night_theme", false);
    }

    public void setThemeNight(Activity activity, boolean night){
        PreferenceManager
                .getDefaultSharedPreferences(activity).edit()
                .putBoolean("night_theme", night).apply();
        activity.recreate();
    }

    public void onTheme(Activity activity, int themeIndex){
        this.themeIndex = themeIndex;
        PreferenceManager
                .getDefaultSharedPreferences(activity).edit()
                .putString("theme_setting", themeIndex + "").apply();
        activity.recreate();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("themeIndex", themeIndex);
    }

    public void refreshTheme(Activity activity){
        try {
            themeIndex = Integer.parseInt(PreferenceManager
                    .getDefaultSharedPreferences(activity).getString("theme_setting", "0"));
        }catch (Exception e){
            themeIndex = 0;
        }
        activity.recreate();
    }

    public void setStatusBarView(Activity activity) {
        setStatusBarView(activity, getDarkColorPrimary(activity));
    }
    /**
     * 设置状态栏的颜色，目前只是在4.4上面有效
     */
    public void setStatusBarView(Activity activity, int color) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(false);
            tintManager.setTintColor(color);
        }
    }

    public int getColorPrimary(Activity activity){
        TypedValue typedValue = new  TypedValue();
        activity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public int getDarkColorPrimary(Activity activity){
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    public int getDarkColorAccent(Activity activity){
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return typedValue.data;
    }
}
