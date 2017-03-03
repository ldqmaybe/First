package com.first.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils
{

	/**
	 * 保存上次打开的摄像头
	 * 
	 * @param context
	 * @param cameraId
	 */
	public static void setCameraId(Context context, int cameraId)
	{
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("camera", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// 用putString的方法保存数据
		editor.putInt("cameraid", cameraId);
		editor.commit();
	}

	public static int getCameraId(Context context)
	{
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("camera", Activity.MODE_PRIVATE);
		return sharedPreferences.getInt("cameraid", -1);
	}

}
