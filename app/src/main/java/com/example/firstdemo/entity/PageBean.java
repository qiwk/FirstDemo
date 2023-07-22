package com.example.firstdemo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PageBean {
    /**
     * curPage : 2
     * datas : [{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26848,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/ulrFSuwutq_UOtXhPoAD8A","niceDate":"2023-07-13 00:00","niceShareDate":"2023-07-13 23:01","origin":"","prefix":"","projectLink":"","publishTime":1689177600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689260517000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"OutOfMemoryError是如何产生的？有这么多情况？","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26849,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/7sXsfbN-n99HjPjBPxIGTw","niceDate":"2023-07-13 00:00","niceShareDate":"2023-07-13 23:02","origin":"","prefix":"","projectLink":"","publishTime":1689177600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689260531000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"使用 Gradle 解决 Android 模块化项目中的多语言支持","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26841,"isAdminAdd":false,"link":"https://juejin.cn/post/7254811936290029627","niceDate":"2023-07-12 17:48","niceShareDate":"2023-07-12 17:48","origin":"","prefix":"","projectLink":"","publishTime":1689155320000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1689155320000,"shareUser":"彭旭锐","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Gradle 构建工具 #5 又冲突了！如何理解依赖冲突与版本决议？","type":0,"userId":30587,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26840,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/HPykdoqkw0JrA-HaRAtxkw","niceDate":"2023-07-12 15:33","niceShareDate":"2023-07-12 15:33","origin":"","prefix":"","projectLink":"","publishTime":1689147210000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1689147210000,"shareUser":"小海Poseidon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"什么？SingleInstance Activity被销毁了？","type":0,"userId":88354,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26839,"isAdminAdd":false,"link":"https://juejin.cn/post/7254472309284388923","niceDate":"2023-07-12 09:49","niceShareDate":"2023-07-12 09:49","origin":"","prefix":"","projectLink":"","publishTime":1689126589000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1689126589000,"shareUser":"rmfone","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"手写Looper（二）---- 使用epoll","type":0,"userId":3710,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26847,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/3a2FfPKGGHROymH4dOVD_g","niceDate":"2023-07-12 00:00","niceShareDate":"2023-07-13 23:01","origin":"","prefix":"","projectLink":"","publishTime":1689091200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689260494000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"字节跳动开源神器：btrace 2.0 技术原理大揭秘","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26850,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/4PlJSpoPkklT0oLNnXF4-Q","niceDate":"2023-07-12 00:00","niceShareDate":"2023-07-13 23:02","origin":"","prefix":"","projectLink":"","publishTime":1689091200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689260544000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Jetpack Compose 实现验证码输入框","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":634,"chapterName":"京东云技术团队","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26834,"isAdminAdd":false,"link":"https://juejin.cn/post/7254200330492870711","niceDate":"2023-07-11 19:37","niceShareDate":"2023-07-11 19:37","origin":"","prefix":"","projectLink":"","publishTime":1689075425000,"realSuperChapterId":604,"selfVisible":0,"shareDate":1689075425000,"shareUser":"","superChapterId":605,"superChapterName":"大厂对外分享 - 学习路径","tags":[],"title":"移动端APP组件化架构实践","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":633,"chapterName":"百度Geek说","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26832,"isAdminAdd":false,"link":"https://juejin.cn/post/7251780413454778427","niceDate":"2023-07-11 19:36","niceShareDate":"2023-07-11 19:36","origin":"","prefix":"","projectLink":"","publishTime":1689075372000,"realSuperChapterId":604,"selfVisible":0,"shareDate":1689075372000,"shareUser":"","superChapterId":605,"superChapterName":"大厂对外分享 - 学习路径","tags":[],"title":"扫光动效在移动端应用实践","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26835,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/RnlSXZjVXjAcy_BtqSz-jw","niceDate":"2023-07-11 00:00","niceShareDate":"2023-07-11 23:57","origin":"","prefix":"","projectLink":"","publishTime":1689004800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689091031000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 冷启优化的3个黑科技","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26836,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/olocdhk8P9u9LXXddimphA","niceDate":"2023-07-11 00:00","niceShareDate":"2023-07-11 23:57","origin":"","prefix":"","projectLink":"","publishTime":1689004800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689091043000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"原创：写给初学者的Jetpack Compose教程，基础控件和布局","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26825,"isAdminAdd":false,"link":"https://juejin.cn/post/7253764065305428028","niceDate":"2023-07-10 09:19","niceShareDate":"2023-07-10 09:19","origin":"","prefix":"","projectLink":"","publishTime":1688951963000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1688951963000,"shareUser":"张风捷特烈","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"FlutterUnit 周边 | 深入分析 iOS 手势回退问题","type":0,"userId":31634,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26837,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/ujUwuW2qSwUi64OZ3kmXSg","niceDate":"2023-07-10 00:00","niceShareDate":"2023-07-11 23:57","origin":"","prefix":"","projectLink":"","publishTime":1688918400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1689091059000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"深度解读 Android 14 重要的 8 个新特性～","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26817,"isAdminAdd":false,"link":"https://juejin.cn/post/7253005591194878011","niceDate":"2023-07-09 22:13","niceShareDate":"2023-07-09 22:10","origin":"","prefix":"","projectLink":"","publishTime":1688911984000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1688911848000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"弄懂Android 源码中那些巧妙位运算","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26816,"isAdminAdd":false,"link":"https://juejin.cn/post/7253673249158234169","niceDate":"2023-07-09 22:13","niceShareDate":"2023-07-09 22:09","origin":"","prefix":"","projectLink":"","publishTime":1688911982000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1688911763000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"有人说，Kotlin Flow是Sequence更好的替代品？","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26815,"isAdminAdd":false,"link":"https://juejin.cn/post/7253442601856090169","niceDate":"2023-07-09 22:12","niceShareDate":"2023-07-09 22:05","origin":"","prefix":"","projectLink":"","publishTime":1688911979000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1688911550000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android进阶宝典 -- WindowManager原理深度分析","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26818,"isAdminAdd":false,"link":"https://juejin.cn/post/7252707383356702781","niceDate":"2023-07-09 22:12","niceShareDate":"2023-07-09 22:11","origin":"","prefix":"","projectLink":"","publishTime":1688911974000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1688911899000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android之调节屏幕亮度","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":619,"chapterName":"流畅性","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26819,"isAdminAdd":false,"link":"https://juejin.cn/post/7253062314307223611","niceDate":"2023-07-09 22:12","niceShareDate":"2023-07-09 22:12","origin":"","prefix":"","projectLink":"","publishTime":1688911965000,"realSuperChapterId":617,"selfVisible":0,"shareDate":1688911965000,"shareUser":"","superChapterId":618,"superChapterName":"Android 性能优化 - 进阶篇 - 学习路径","tags":[],"title":"速度优化：CPU 优化（上）","type":0,"userId":2,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"张鸿洋","canEdit":false,"chapterId":543,"chapterName":"Android技术周报","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26812,"isAdminAdd":false,"link":"https://www.wanandroid.com/blog/show/3542","niceDate":"2023-07-09 00:00","niceShareDate":"2023-07-09 00:10","origin":"","prefix":"","projectLink":"","publishTime":1688832000000,"realSuperChapterId":542,"selfVisible":0,"shareDate":1688832600000,"shareUser":"","superChapterId":543,"superChapterName":"技术周报","tags":[],"title":"Android 技术周刊 （2023-07-02 ~ 2023-07-09）","type":0,"userId":-1,"visible":1,"zan":0},{"adminAdd":false,"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":26820,"isAdminAdd":false,"link":"https://mp.weixin.qq.com/s/StwVjmjWhusbRI0Gr-_kUw","niceDate":"2023-07-07 00:00","niceShareDate":"2023-07-09 23:40","origin":"","prefix":"","projectLink":"","publishTime":1688659200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1688917220000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"关于Jetpack DataStore(Proto)的六点疑问","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 20
     * over : false
     * pageCount : 722
     * size : 20
     * total : 14426
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    @SerializedName("datas")
    private List<Article> articleList;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
