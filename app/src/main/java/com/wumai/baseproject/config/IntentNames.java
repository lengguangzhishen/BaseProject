package com.wumai.baseproject.config;

public interface IntentNames {

    String ID = "id";
    String MESSAGE_DATA = "message_data";
    String EDITION = "edition";
    String INDEX_STRING = "index";
    String RADIO_INDEX = "radio_index";
    String ISFROMLOGOUT = "isfromlogout";
    String READ_USERID = "userId";
    String PERSONAL_TITIE = "personal_title";
    String PERSONAL_FIX_CONTENT = "content";
    String FIXRESUMEDETAILSNAME = "fixname";
    String FIXRESUMEDETAILSTAY = "fixtay";
    String RESUMEDETAILSITEMID = "itemId";
    String RESUMEDETAILSBEAN = "bean";
    String RepresentativeWorksBean = "bean";
    String EducationBean = "bean";
    String JobIntentionBean = "bean";
    String INTERVIEWERNAME = "name";

    /**
     * 是否是进入到招人的主界面
     * 否的话就是进入到求职的界面
     */
    String IS_RECRUIT = "is_recruit";

    String POSITION_TYPE = "position_type"; //选择职位列表item之后返回发布职位页面的intentname
    String POSITION_ID = "position_id"; //同上
    String POSITION_NAME = "position_name"; //选择职位名称item之后返回发布职位页面的intentname
    String POSITION_SALARY = "position_salary"; //输入工资标准之后返回发布职位页面的intentname
    String ENTER_TYPE = "enter_type"; //进入到输入工资等类似需求页面时候的类型
    String POSITION_DESCRIPTION = "position_description"; //输入职位描述之后返回发布职位页面的intentname
    String IS_ADDRESS = "is_address"; //是否是工作地点

    String PICResult = "pic_result";  //发布图片时，选择图片生成的Tresult对象
    String VIDIOREQUESTID = "video_requestId";  //上传视频成功后返回的id
    String VIDIOURL = "video_url";  //上传视频成功后返回的URL
    String VIDEOIMAGE = "video_image";  //上传视频的封面图片

    //跳转到聊天内容界面
    String EXTRA_USER_ID = "userId";
    String myAdvantage = "myAdvantage"; //我的优势的文字

    String USERName = "userName";  //求职者的姓名
    String USERID = "userId"; //求职者的id

}
