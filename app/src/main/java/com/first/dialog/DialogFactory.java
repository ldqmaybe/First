package com.first.dialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 对话框工厂类
 */
public class DialogFactory {
    private static final String DIALOG_PROGRESS_TAG = "progress";
    private static final String DIALOG_MODIFY_PASSWORD_TAG = "modify_password";

    /**
     * 为了不重复显示dialog，在显示对话框之前移除正在显示的对话框。
     */
    private static void removeFragment(FragmentManager fragmentManager, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (null != fragment) {
            ft.remove(fragment).commit();
        }
    }

    /**
     * @param title 进度条显示的信息
     */
    public static void showProgressDialog(FragmentManager fragmentManager, String title) {
        if (fragmentManager != null) {
            removeFragment(fragmentManager, DIALOG_PROGRESS_TAG);
            ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance(title);
            progressDialogFragment.show(fragmentManager, DIALOG_PROGRESS_TAG);
            fragmentManager.executePendingTransactions();
        }
    }

    public static void dissProgressDialog(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(DIALOG_PROGRESS_TAG);
        if (null != fragment) {
            ((ProgressDialogFragment) fragment).dismissAllowingStateLoss();
            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }

    /***
     * 修改密码对话框
     *
     * @param fragmentManager
     */
    public static final void showModifyPassWordDialog(FragmentManager fragmentManager, ModifyPassWordDialog.OnModifyPassWordListener listener) {
        if (fragmentManager != null) {
            removeFragment(fragmentManager, DIALOG_MODIFY_PASSWORD_TAG);
            ModifyPassWordDialog modifyPassWordDialog = new ModifyPassWordDialog();
            modifyPassWordDialog.show(fragmentManager, DIALOG_MODIFY_PASSWORD_TAG, listener);
            fragmentManager.executePendingTransactions();
        }
    }

}
