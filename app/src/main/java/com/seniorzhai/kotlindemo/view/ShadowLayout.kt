package com.seniorzhai.kotlindemo.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import com.seniorzhai.kotlindemo.R

/**
 * Created by zhai on 16/3/24.
 */
class ShadowLayout : FrameLayout {

    private var mShadowColor = 0;
    private var mShadowRadius = 0f;
    private var mCornerRadius = 0f;
    private var mDx = 0f;
    private var mDy = 0f;

    private var mInvalidateShadowOnSizeChanged = true;
    private var mForceInvalidateShadow = true;

    fun initView(attrs: AttributeSet) {
        initAttrButes(context!!, attrs)
        var xPadding = (mShadowRadius + Math.abs(mDx)).toInt()
        var yPadding = (mShadowRadius + Math.abs(mDy)).toInt()
        setPadding(xPadding, yPadding, xPadding, yPadding)
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        initView(attrs)
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleRes: Int) : super(ctx, attrs, defStyleRes) {
        initView(attrs)
    }

    override fun getSuggestedMinimumWidth(): Int {
        return 0;
    }

    override fun getSuggestedMinimumHeight(): Int {
        return 0;
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && y > 0 && (getBackground() == null || mInvalidateShadowOnSizeChanged || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(w, h);
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(right - left, bottom - top);
        }
    }

    private fun setBackgroundCompat(w: Int, h: Int) {
        var bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowRadius, mDx, mDy, mShadowColor, Color.TRANSPARENT)
        var drawable = BitmapDrawable(resources, bitmap)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable)
        } else {
            background = drawable
        }
    }

    fun setInvalidateShadowOnSizeChanged(invalidateShadowOnSizeChanged: Boolean) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged;
    }

    fun invalidateShadow() {
        mForceInvalidateShadow = true;
        requestLayout();
        invalidate();
    }

    private fun initAttrButes(context: Context, attrs: AttributeSet?) {
        var attr = getTypedArray(context, attrs, R.styleable.ShadowLayout)
        if (attr == null) {
            return
        }
        try {
            mCornerRadius = attr.getDimension(R.styleable.ShadowLayout_sl_cornerRadius, 20f);
            mShadowRadius = attr.getDimension(R.styleable.ShadowLayout_sl_shadowRadius, 10f);
            mDx = attr.getDimension(R.styleable.ShadowLayout_sl_dx, 0f)
            mDy = attr.getDimension(R.styleable.ShadowLayout_sl_dy, 0f)
            mShadowColor = attr.getColor(R.styleable.ShadowLayout_sl_shadowColor, resources.getColor(R.color.default_shadow_color))
        } finally {
            attr.recycle()
        }
    }

    private fun getTypedArray(context: Context, attributeSet: AttributeSet?, attr: IntArray): TypedArray {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0)
    }

    private fun createShadowBitmap(shadowWith: Int, shadowHeight: Int, cornerRadius: Float, shadowRadius: Float, dx: Float, dy: Float, shadowColor: Int, fillColor: Int): Bitmap {
        var output = Bitmap.createBitmap(shadowWith, shadowHeight, Bitmap.Config.ALPHA_8)
        var canvas = Canvas(output)
        var shadowRect = RectF(shadowRadius, shadowRadius, shadowWith - shadowRadius, shadowHeight - shadowRadius)
        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy)
            shadowRect.bottom -= Math.abs(dy)
        }
        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx)
            shadowRect.right -= Math.abs(dx)
        }

        var shadowPaint = Paint()
        shadowPaint.isAntiAlias = true
        shadowPaint.color = fillColor
        shadowPaint.style = Paint.Style.FILL

        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }

        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)

        return output
    }


}
