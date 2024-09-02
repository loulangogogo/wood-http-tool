package io.github.loulangogogo.tool;

/*********************************************************
 ** 请求工具类
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class HttpTool {

    /*********************************************************
     ** GET请求静态内部类
     **
     ** @author loulan
     ** @since 8
     *********************************************************/
    public static class GET extends HttpToolGet {
    }

    /*********************************************************
     ** POST请求静态内部类
     **
     ** @author loulan
     ** @since 8
     *********************************************************/
    public static class POST extends HttpToolPost {
    }

    /*********************************************************
     ** PUT请求静态内部类
     **
     ** @author loulan
     ** @since 8
     *********************************************************/
    public static class PUT extends HttpToolPut {
    }

    /*********************************************************
     ** DELETE请求静态内部类
     **
     ** @author loulan
     ** @since 8
     *********************************************************/
    public static class DELETE extends HttpToolDelete {
    }
}
