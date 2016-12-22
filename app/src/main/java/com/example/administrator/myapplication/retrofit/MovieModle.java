package com.example.administrator.myapplication.retrofit;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @time 2016/12/8 0008.10:54
 */
public class MovieModle implements MovieContact.Model {
    @Override
    public void getMovie(MoviePresenter.MyCallBack callBack) {

        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < 0; i++) {
            Movie movie = new Movie();
            movie.setImg("http://maoyan.meituan.net/movie/videos/854x480780a6acfadc8407b917478f2072faf3c.mp4");
            movie.setNm("28岁未成年");
            movie.setScm("哭成小笨蛋，笑回长大前");
            movie.setShowInfo("2016-12-09 本周五上映");
            movieList.add(movie);
        }
        if (movieList.size() > 0) {
            callBack.onSuccess(movieList);
        } else {
            callBack.onFailure("null");
        }
    }
}
