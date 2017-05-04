package com.tong.util.base;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

public abstract class BaseHandler<T> extends Handler
{

    private static final String TAG = BaseHandler.class.getSimpleName();

    private WeakReference<T> mWeakReference;

    public BaseHandler(Looper looper, T obj)
    {
        super(looper);
        mWeakReference = new WeakReference<T>(obj);
    }

    public BaseHandler(T obj)
    {
        super();
        mWeakReference = new WeakReference<T>(obj);
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);
        T obj = mWeakReference.get();
        if (null == obj) {
            this.removeCallbacksAndMessages(null);
            return;
        }

        if (obj instanceof Activity) {
            if (((Activity) obj).isFinishing()) {
                this.removeCallbacksAndMessages(null);
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (obj instanceof Activity) {
                if (((Activity) obj).isDestroyed()) {
                    this.removeCallbacksAndMessages(null);
                    return;
                }
            }
        }

        if (obj instanceof Fragment) {
            if (!(((Fragment) obj).isAdded())) {
                this.removeCallbacksAndMessages(null);
                return;
            }
        }
        handleMessageWhenReferenceNotNull(obj, msg);
    }

    protected abstract void handleMessageWhenReferenceNotNull(T obj, Message msg);
}
