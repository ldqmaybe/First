package com.first.login;


import com.cn.baselib.base.BaseHttpResult;
import com.first.bean.TimeStampResp;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Admin
 * @time 2016/11/24 0024.14:13
 */
public interface ApiService {

    @GET("api_boss.php?cmd=get_date")
    Observable<BaseHttpResult<TimeStampResp>> getTime();
}
