package Utils;

public class Error {
    public static final String errorCode_NoAction = "404";
    private static final String errorMsg_NoAction = "没有Action参数";

    public static String getNoActionMsg() {
        return errorMsg_NoAction;
    }

    public static final String errorCode_NoRequestParam = "405";
    private static final String errorMsg_NoRequestParam = "缺少必要参数";

    public static String getNoRequestParamMsg(String requestParam) {
        return errorMsg_NoRequestParam + ":" + requestParam;
    }

    public static final String errorCode_Exception = "500";
    private static final String errorMsg_Exception = "服务器异常";

    public static String getExceptionMsg(String e) {
        return errorMsg_Exception + ":" + e;
    }

    // 以上是通用的error

    // 以下是特定action的error,600,610,620,630
    public static final String errorCode_LoginFail = "600";
    private static final String errorMsg_LoginFail = "登陆失败，账号与密码不匹配";

    public static final String errorCode_Register_repeat = "801";

    public static String getErrorMsg_Register_repeat() {
        return errorMsg_Register_repeat;
    }

    private static final String errorMsg_Register_repeat = "注册失败，该账号已注册";



    public static final String errorCode_Register_fail = "802";

    public static String getErrorMsg_Register_fail() {
        return errorMsg_Register_repeat;
    }

    private static final String errorMsg_Register_fail = "插入数据库异常";


    public static final String errorCode_Edit_fail = "803";

    public static String getErrorMsg_Edit_fail() {
        return errorMsg_Edit_fail;
    }

    private static final String errorMsg_Edit_fail = "修改失败";


    public static final String errorCode_Sign_fail = "804";

    public static String getErrorMsg_Sign_fail() {
        return errorMsg_Sign_fail;
    }

    private static final String errorMsg_Sign_fail = "报名失败";






    public static final String errorCode_Sign_Invalid = "805";

    public static String getErrorMsg_Sign_Invalid() {
        return errorMsg_Sign_Invalid;
    }

    private static final String errorMsg_Sign_Invalid = "非法调用借口";



    public static final String errorCode_Sign_hasDone = "806";

    public static String getErrorMsg_Sign_hasDone() {
        return errorMsg_Sign_hasDone;
    }

    private static final String errorMsg_Sign_hasDone = "已经报名";





    public static String getCreateFailMsg() {
        return errorMsg_LoginFail;
    }


}
