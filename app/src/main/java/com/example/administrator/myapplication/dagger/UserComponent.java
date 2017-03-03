package com.example.administrator.myapplication.dagger;

import dagger.Component;

/**
 * @author Admi
 * @time 2017/2/24 0024.10:31
 */
//Component 链接被注入的地方和提供依赖的地方
@Component(modules = UserModule.class)
public interface UserComponent {
    void inject(Dagger2Activity dagger2Activity);
}
