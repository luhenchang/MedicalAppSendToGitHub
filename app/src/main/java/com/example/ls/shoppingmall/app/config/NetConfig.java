package com.example.ls.shoppingmall.app.config;

/**
 * Created by 路很长~ on 2017/12/14.
 */

public interface NetConfig {
    //    "http://47.94.165.113:8081/resources/static/images/upload/2018-01-18/1516267518707.png"
    //首页：
    String HOME_ALL_BODY = "https://qy.healthinfochina.com:8080/DOC900010003";
    String HOME_BUWEI = "https://qy.healthinfochina.com:8080/DOC900010009?bodType=00";
    String HOME_BODY_EACHER = "https://qy.healthinfochina.com:8080/DOC900010004?bodyNo=";
    String HOME_SYMPTOMSEACHER = "https://qy.healthinfochina.com:8080/DOC900010010";
    String HOME_SYMPTOMSEND_SERVER = "https://qy.healthinfochina.com:8080/DOC900010013";
    String HOME_LAST_URL = "https://qy.healthinfochina.com:8080/DOC900010012?orgNo=";
    String HOME_ARTICAL_URL = "https://qy.healthinfochina.com:8080/DOC800010027?currentPage=1&pageSize=2";
    String HOME_ARTICAL_STRORE = "https://qy.healthinfochina.com:8080/DOC000020004";
    String HOME_ARTICAL_ISVISIBAL = "https://qy.healthinfochina.com:8080/DOC000020003";
    //商城文章收藏链接：
    String HOME_ARTICAL_SHOPPING = "http://www.51ququ.com:22000/article/addFavorites/";
    String HOME_ARTICAL_SHOPPING_END = "?userId=";
    //文章的取消收藏：userId=20180123151256558621
    String HOME_ARTICAL_CANSAL = "http://www.51ququ.com:22000/article/delFavorites/list/";
    String HOME_ARTICAL_CANSAL_END = "?userId=";
    //判断是否已经收藏接口
    String HOME_ARTICAL_COLLECTOR = "http://www.51ququ.com:22000/article/detail/";
    String HOME_ARTICAL_COLLECTOR_END = "?userId=";
    String SHOPPING_PICTOR="http://resource.51ququ.com/";


    //我的页面:
    String GLIDE_USRE = "https://qy.healthinfochina.com/resources/static/";
    //这个是注册的基本接口
    String BASE_URL = "https://qy.healthinfochina.com:8080/USR900010001";
    //这个是登录的基本接口
    String BASE_LOGIN_URL = "https://qy.healthinfochina.com:8080/USR900010002";
    //短信验证接口
    String MESSAGE_CHECKED = "https://qy.healthinfochina.com:8080/DOC000010008";
    //家庭传递数据
    String FAMILY_URL = "https://qy.healthinfochina.com:8080/USR000010003";
    //家庭查询列表
    String FAMILYS_URL = "https://qy.healthinfochina.com:8080/USR000010004";


    //医师团:
    String COMMUNITY_MEDICAL_TEAM = "https://qy.healthinfochina.com:8080/DOC000010041";
    //医师团详情
    String COMMUNITY_MEDICAL_INFOR = "https://qy.healthinfochina.com:8080/DOC000010061?dtmNo=";
    //这个是医师列表
    //http://47.94.165.113:8080/DOC000010006?level=0
    String MEDICALS_LIST = "https://qy.healthinfochina.com:8080/DOC000010006";
    //医师团收藏
    String MEDICAL_TEAM_COLLECT = "https://qy.healthinfochina.com:8080/DOC000020006";
    //医师团收藏列表
    String MEDICAL_TEAM_COLLECTLIST = "https://qy.healthinfochina.com:8080/DOC000020007";

    //收藏医师（DOC000020001）
    String COLLECT_DOCTOR = "https://qy.healthinfochina.com:8080/DOC000020001";
    //查询收藏（DOC000020002）
    String SELECTOR_DOCTOR = "https://qy.healthinfochina.com:8080/DOC000020002";
    //收藏按钮的显示预付
    String SELECTOR_DOCTOR_VISIBAL = "https://qy.healthinfochina.com:8080/DOC000020003";
    //--商品收藏--
    String GOODS_LIST = "https://qy.healthinfochina.com:22000/mall/favorites";
    //这里是对话列表webView要展示的接口
    String DIALOG_LIST = "https://qy.healthinfochina.com:8081/h5hunhekaifa/dialogue.html";
    //个人信息
    String USER_MESSAGE_SAVE = "https://qy.healthinfochina.com:8080/USR000010005";
    //个人信息获取
    String USER_MESSAGE_GET = "https://qy.healthinfochina.com:8080/USR000010006";
    //这个是搜索界面里面的
    String USER_LIST_SEARCHER = "https://qy.healthinfochina.com:8080/DOC000010024";
    //上传头像
    String USER_HEADER_URL = "https://qy.healthinfochina.com:8080/USR000010020";
    //对话web地址
    //http://47.94.165.113:8081/h5hunhekaifa/doctor.html?
    String MEDICAL_TALK = "http://47.94.165.113:8081/h5hunhekaifa/dialogue.html?";
    String MEDICAL_ARTICAL = "http://47.94.165.113:8081/h5hunhekaifa/article.html?";
    String MEDICAL_CASE = "http://47.94.165.113:8081/h5hunhekaifa/case.html?";

    //用户
    /*获取最新的三篇文章的列表：
www.51ququ.com:22000/article/list?pageIndex=0&pageSize=3*/
    //最新文章界面：
    String NEW_ARTICAL_URL = "http://www.51ququ.com:22000/article/list?pageIndex=0&pageSize=3";
    //文章收藏界面
    String ARTICAL_URL = "http://www.51ququ.com:22000/article/favorites";
    //商品收藏界面
    String SHOPPING_URL = "http://47.94.165.113:22000/mall/favorites";
    //案例收藏界面
    String CASE_LIST_URL = "https://qy.healthinfochina.com:8080/DOC000020005?userId=";
    //家人个人信息展示雷彪
    String FAMELY_INFOR = "https://qy.healthinfochina.com:8080/USR000010007?famNo=";
    String FAMELY_INFOR_update = "https://qy.healthinfochina.com:8080/USR000010008?famNo=";
    //优惠卡IntroducingActivity
    //一对一咨询
    String MyCALS_URL = "https://qy.healthinfochina.com:8080/DOC000010049?usrId=";
    //医师团咨询
    String MYCALS_TEAM_URL = "https://qy.healthinfochina.com:8080/DOC000010048?usrId=";
   //退款
    String BACK_MONEY_URL="https://qy.healthinfochina.com:8080/DOC800010003?sno=";
    //微信退款
    String BACK_MONEY_URLW="https://qy.healthinfochina.com:8080/DOC800010006?sno=";

}
