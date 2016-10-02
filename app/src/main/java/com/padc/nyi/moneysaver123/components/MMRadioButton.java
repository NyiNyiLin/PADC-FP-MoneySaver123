package com.padc.nyi.moneysaver123.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.padc.nyi.moneysaver123.util.MMFontUtils;


/**
 * Created by aung on 6/25/16.
 */
public class MMRadioButton extends RadioButton {

    public MMRadioButton(Context context) {
        super(context);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }
}
