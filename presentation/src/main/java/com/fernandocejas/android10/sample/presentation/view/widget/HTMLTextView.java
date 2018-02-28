package com.fernandocejas.android10.sample.presentation.view.widget;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;

public class HTMLTextView extends android.support.v7.widget.AppCompatTextView {

  public HTMLTextView(Context context) {
    super(context);
  }

  public HTMLTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HTMLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public void setText(CharSequence text, BufferType type) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      super.setText(Html.fromHtml(text.toString(), Html.FROM_HTML_MODE_LEGACY), type);
    } else {
      super.setText(Html.fromHtml(text.toString()), type);
    }
  }
}