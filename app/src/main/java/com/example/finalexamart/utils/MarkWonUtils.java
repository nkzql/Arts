package com.example.finalexamart.utils;

import android.content.Context;
import android.text.Spanned;
import android.widget.TextView;

import org.commonmark.node.Node;

import io.noties.markwon.Markwon;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.linkify.LinkifyPlugin;

public class MarkWonUtils {
    public static void setMarkwon(Context mContext, String content, TextView textView){
        Markwon markwon= Markwon.builder(mContext)
                .usePlugin(GlideImagesPlugin.create(mContext))
                .usePlugin(LinkifyPlugin.create())
                .build();
        Node node= markwon.parse(content);
        Spanned markdown = markwon.render(node);
        markwon.setParsedMarkdown(textView, markdown);
    }
}
