package com.ayj.chunmiao.utils.txl;

import android.content.Context;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

import java.io.File;

/**
 * Created by zht-pc-04 on 2017/8/8.
 */

public class LxsbImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load(new File((String)path)).placeholder(R.mipmap.banner_loading).error(
                R.mipmap.banner_error).into(imageView);
    }
}
