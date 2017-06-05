package com.wumai.baseproject.config;

import android.os.Environment;

/**
 * 常用存储相关配置
 */
public class Settings {
    //获得sd卡路径
    public static final String SDCARDPATH = Environment.getExternalStorageDirectory().getPath();
    public final static String VIDEO_FILE_DEFAULT_PATH = SDCARDPATH + "/wumai/download/";
    public final static String PIC_CACHE = SDCARDPATH + "/wumai/cache/";
    public final static String SUB_PATH = "wumai/download";


    //==========接口目录
    public static final String LOGIN = "member/login"; //登录
    public static final String LOGOUT = "member/exitLogin"; //登出
    public static final String CLAN_IMG = "tribeimageText/showList"; //部落图文列表
    public static final String CLAN_VIDEO = "tribevideo/showList"; //部落视频列表
    public static final String CLAN_VIDEO_LIKE = "tribevideo/updateLikeCount"; //点赞接口
    public static final String POSITION_LIST = "position/showList"; //职位列表
    public static final String INTERVIEW_WAIT = "interview/preparelist"; //待面试
    public static final String INTERVIEW_END = "interview/finishlist"; //面试已结束
    public static final String POSITION_TYPE = "jobType/list"; //职位分类
    public static final String RELEASE_POSITION = "position/add"; //发布职位
    public static final String VIDEO_IS_LIKE = "tribevideo/isLike"; //查询部落视频是否点赞接口
    public static final String COMMENT_LIST = "tribevideocomment/showList"; //视频评论列表查询接口
    public static final String COMMENT = "tribevideocomment/saveTribevideocomment"; //视频评论接口
    public static final String DELETE_IMG = "tribeimageText/deleteTribeimageText"; //部落图文删除接口
    public static final String DELETE_VIDEO = "tribevideo/deleteTribevideo"; //部落视频删除接口
    public static final String FANS_ADD = "fans/add"; //关注的接口(视频详情界面)


    //xzw
    public static final String EnterPriseConcern = "membercompany/list"; //会员关注的企业
    public static final String FansBlist = "fans/blist";  //粉丝列表
    public static final String MyFollowPersonl = "fans/alist"; //关注的个人
    public static final String EvaluateFriends = "friendsevaluation/evaluation";  //评价好友
    public static final String InviteFriends = "friendsevaluation/add";  //邀请好友
    public static final String ADDRESUMEDETAILS = "workexperience/add";  //添加工作经历
    public static final String SELECTJOBTYPE = "jobType/list"; //职位类型
    public static final String RESUMEDETAILS_LIST = "workexperience/list";  //工作列表
    public static final String DELETERESUMEDETAILS = "workexperience/delete"; //删除工作经历
    public static final String UPIMGTOSERVER = "http://59.110.156.224/gjkxapi-thirdparty/uploadFile/uploadImageForBase64"; //公共上传图片地址
    public static final String ADDMEMBERPHOTO = "memberphoto/add"; //添加个人相册
    public static final String REQUESTPERSONALPHOTOLIST = "memberphoto/list"; //请求相册列表
    public static final String JOBINTENTION = "jobintension/list"; //求职意向列表
    public static final String City = "city/list"; //城市列表
    public static final String Province = "province/list";  //省份

    //登录方式, 一共两种
    public static final String LOGIN_TYPE_PWD = "loginPass"; //密码登录
    public static final String LOGIN_TYPE_CODE = "loginCode"; //验证码登录


    //网络请求返回的状态码
    public static final String CODE_SUCCESS = "000000"; //成功
    public static final String CODE_FAILURE = "000001"; //操作失败, 稍后重试
    public static final String CODE_NODATA = "000002";  //没有找到相应的数据
    public static final String CODE_PARAMS_ERROR = "000003";    //必传参数不能为空, 需传递正确参数
    public static final String CODE_VERIFICATION_TIMEOUT = "000004"; //验证码超时
    public static final String CODE_VERIFICATION_ERROR = "000005"; //验证码错误
    public static final String CODE_NO_USER = "000006"; //无此用户信息
    public static final String CODE_PWD_ERROR = "000007"; //输入的密码不正确，请重新尝试
    public static final String CODE_PHONE_ERROR = "000008"; //手机号格式不正确
    public static final String CODE_ACCOUNT_LOCK = "000009"; //您的账号已锁定，请联系管理员解锁
    public static final String CODE_HAS_REGISTED = "000010"; //手机号已注册
    public static final String CODE_NONE = "000011"; //手机号或密码不能为空
    public static final String CODE_LOGIN_ERROR = "1000001";  //账号在其他地方登录

    //教育背景 学历 1、高中 2、大专 3、本科 4、硕士 5、博士
    public static final int EDUCATION_HIGH_SCHOOL = 1;
    public static final int EDUCATION_JUNIOR_COLLEGE = 2;
    public static final int EDUCATION_UNIVERSITY = 3;
    public static final int EDUCATION_MASTER = 4;
    public static final int EDUCATION_DOCTOR = 5;


    public static final int MODIFY_PWD_JUMPER = -100;//由修改密码跳转到登录界面,便于以后的数据统计


    //个别用到的缓存json串的key
    /**
     * homeactivity list的key
     */
    public static final String HOME_LIST_RESULT_KEY = "home";

    /**
     * homebanner list的key
     */
    public static final String HOME_LIST_BANNER_KEY = "banner";

    /**
     * 是否是招人的标示的key
     */
    public static final String IS_RECRUIT_KEY = "is_recruit_key";


    //列表接口
    public static final String QUALIFICAIONS = "qualifications/list"; //个人资历列表
    public static final String EDUCATIONLIST = "education/list";  //教育经历
    public static final String ADDEDUCAIOTN = "education/add";  //增加教育经历
    public static final String DELETEEUDCATION = "education/delete";  //删除教育经历
    public static final String PERSONALSHOW = "member/show";  //个人信息查询
    public static final String FIXPERSONALINFO = "member/update";  //修改个人资料
    public static final String ADDJOBINTENTION = "jobintension/add"; //添加求职意向
    public static final String DELETEJOBINTENTION = "jobintension/delete"; //删除求职意向
    public static final String DELETEWORKS = "qualifications/delete";  //删除资历
    public static final String ADDWORKS = "qualifications/add";//添加资历
    public static final String ADDINTERVIEW = "interview/add";  //添加面试邀约
    public static final String getUploadVideoToken = "http://59.110.156.224/gjkxapi-thirdparty/uploadFile/getVedioToken"; //获取上传视频的Token, 啥参数都不用传
    public static final String ADDPICTEXT = "tribeimageText/saveTribeimageText"; //添加图文信息
    public static final String SavevideoToSc = "tribevideo/saveTribevideo"; //添加视频信息
    public static final String AuthenticationState = "member/update";  //会员个人认证
    public static final String EnterpriseCertification = "member/update";  //会员企业认证
//    public RequestCodeResultListener requestCodeResultListener;
//
//    public static void requestCodeResult(String code, RequestCodeResultListener requestCodeResultListener) {
//        switch (code) {
//            case CODE_SUCCESS:
//                requestCodeResultListener.requestSuccess();
//                break;
//            case CODE_FAILURE: //操作失败, 稍后重试
//                break;
//            case CODE_NODATA:
//                break;  //没有找到相应的数据
//            case CODE_PARAMS_ERROR:
//                break;    //必传参数不能为空, 需传递正确参数
//            case CODE_VERIFICATION_TIMEOUT:
//                break; //验证码超时
//            case CODE_VERIFICATION_ERROR:
//                break; //验证码错误
//            case CODE_NO_USER:
//                break; //无此用户信息
//            case CODE_PWD_ERROR:
//                break; //输入的密码不正确，请重新尝试
//            case CODE_PHONE_ERROR:
//                break; //手机号格式不正确
//            case CODE_ACCOUNT_LOCK:
//                break; //您的账号已锁定，请联系管理员解锁
//            case CODE_HAS_REGISTED:
//                break; //手机号已注册
//            case CODE_NONE:
//                break; //手机号或密码不能为空
//        }
//    }
//
//    public interface RequestCodeResultListener {
//        void requestSuccess();
//    }
}
