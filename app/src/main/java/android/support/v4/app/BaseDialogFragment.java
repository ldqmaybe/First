package android.support.v4.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Admin
 * @time 2016/8/3 0003.14:37
 */
public abstract class BaseDialogFragment extends DialogFragment {

    public WindowManager windowManager;
    public Window window;

    /**
     * 设置对话框样式
     */
    protected abstract int getDialogTheme();

    /**
     * 子类布局文件ID
     */
    protected abstract int getLayoutID();

    /**
     * 用于初始化控件
     */
    protected abstract void initView(View layout);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, getDialogTheme());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //该变量的修改需要在同包名才行，所以建立了相同的包名
        mDismissed = false;
        mShownByMe = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();


        windowManager = getActivity().getWindowManager();
        window = getDialog().getWindow();
        windowManager.getDefaultDisplay().getMetrics(dm);
        window.setLayout((int) (dm.widthPixels * 0.8), getHeight(dm));
    }

    protected int getHeight(DisplayMetrics dm) {
        return window.getAttributes().height;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(getLayoutID(), null);
        initView(layout);
        return layout;
    }
}
