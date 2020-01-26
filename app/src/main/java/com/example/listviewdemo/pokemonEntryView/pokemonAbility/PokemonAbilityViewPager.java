package com.example.listviewdemo.pokemonEntryView.pokemonAbility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.listviewdemo.R;

import androidx.viewpager.widget.ViewPager;

public class PokemonAbilityViewPager extends ViewPager {
    public PokemonAbilityViewPager(Context context) {
        super(context);
    }

    public PokemonAbilityViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth(), height = getMeasuredHeight();

        // Use the previously measured width but simplify the calculations
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);

        /* If the pager actually has any children, take the first child's
         * height and call that our own */
        height = 0;

        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //107mDesiredHeightAtMeasure

            /* The child was previously measured with exactly the full height.
             * Allow it to wrap this time around. */
            child.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

            height = ((TextView) child).getLayout().getHeight() > height ? ((TextView) child).getLayout().getHeight() : height;
            height += 20;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
