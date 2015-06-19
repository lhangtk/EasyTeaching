package com.stone.EasyTeaching.utilities;

import android.animation.TypeEvaluator;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class ArrowEvaluator implements TypeEvaluator {
    @Override
    public Float evaluate(float fraction, Object o, Object o2) {
        return (Float)o+fraction*((Float)o2-(Float)o);
    }
}
