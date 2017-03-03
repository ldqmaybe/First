package com.example.administrator.myapplication.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * @author Admin
 * @time 2017/2/24 0024.10:29
 */
@Module//标识该类提供依赖
public class UserModule {
    @Provides//告诉Dagger我们想要构造对象并提供这些依赖
    User ProviderUser() {
        return new User("yuecan", 10);
    }
}
