package com.example.finalexamart.bean;
import com.example.finalexamart.R;
import java.util.HashMap;
public class Constans {
    private static HashMap<String, Integer> theme_img_src;
    //首页主题图片和文字
    static {
        theme_img_src=new HashMap<>();
        theme_img_src.put("中华民族", R.drawable.zhonghua);
        theme_img_src.put("其它民俗",R.drawable.other);
        theme_img_src.put("东南中南",R.drawable.zhejiang);
        theme_img_src.put("东北",R.drawable.dongbei);
        theme_img_src.put("西南",R.drawable.xizang);
        theme_img_src.put("西北",R.drawable.xinjiang);
    }
    public static HashMap<String, Integer> getTheme_img_src() {
        return theme_img_src;
    }
}
