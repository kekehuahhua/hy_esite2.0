package com.huiyee.esite.constants;


public interface IPageConstants {
	
	public static long ONE_DAY = 24 * 60 * 60 * 1000;
	public static long TWO_WEEK = 14 * ONE_DAY;
	public static long ONE_MONTH = 30 * ONE_DAY;

    public static final String REDIRECT_URL = "redirectUrl";

    public static final int SITE_LIMIT = 6;
    public static final int PAGE_LIMIT = 5;
    public static final String SITE_TYPE_W = "W";
    public static final String SITE_TYPE_C = "C";

    public static final int CONTENT_LIMIT = 20;

    public static final int CONTENT_SIZE=5;
    public static final int CONTENT_SIZES=15;

    public static final int CONTENT_MOUNTS = 10;
    
    public static final int PERSONAL_TAILOR = 10;

    public static final String CONTENT_PRODUCT = "T";
    public static final String CONTENT_PICTURE = "P";
    public static final String CONTENT_NEW = "N";
    public static final String CONTENT_VIDEO = "V";
    public static final String CONTENT_FORM = "F";

    public static final String CONTENT_EDT = "EDT";
    public static final String CONTENT_DEL = "DEL";
    public static final String CONTENT_CMP = "CMP";
    public static final String CONTENT_INP = "INP";
    public static final String CONTENT_DSH = "DSH";

    public static final String FILE_UPLOAD_PATH = "/uploadfiles/";

    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_FEATURE = 2;
    public static final int TYPE_INTERACT = 4;
    public static final int TYPE_TEMP = 3;
    public static final int TYPE_NEWS = 5;
    public static final int TYPE_PAGETEMP=6;
    
    public static final int TYPE_WX_PIC = 7;
	public static final int TYPE_WX_VIDEO = 8;

    public static final String CONTENT_PRODUCT_FILEFATH = "product";
    public static final String CONTENT_PICTURE_FILEFATH = "picture";
    public static final String CONTENT_NEW_FILEFATH = "news";
    public static final String CONTENT_VIDEO_FILEFATH = "video";
    public static final String INTERACT_APPOINTMENT = "appointment";

    public static final String FEATURE_MGR_RESULT_SUCCESS = "Y";

    public static final int RENUM = 3;

    public static final int ZRETIME = 1000;

    public static final long FEATURE_ZAM = 103;
    public static final long FEATURE_UPLOAD = 106;
    public static final long SINA_ACCREDIT = 111;
    public static final long USER_SINA_SHARE = 113;

    public static final String LOGIN_NORMAL = "W";// ������½
    public static final String LOGIN_AUTO = "C";// �Զ���½
    public static final String LOGIN_UNIFY = "U";// ͳһ��¼
    public static final String LOGIN_ADMIN_MANARGE = "A";// admin mgr��½

    public static final String TICKET = "ticket";

    public static final int SINA_USER_DETAIL_LIMIT = 20;
    public static final int SINA_SHARE_LIMIT = 20;
    
    public static final String CACHE_TAGS = "cacheTags";
    
    public static final int REPORT_DAY_NUM = 14;
    
    public static final int DANYMIC_USER_RECORD_LIMIT = 20;
    
    public static final int DANYMIC_USER_RECORD_DETAIL_LIMIT = 10;
    
    public static final int HD_103_DETAIL_LIMIT = 10;
    public static final int HD_106_DETAIL_LIMIT = 10;
    public static final int HD_111_DETAIL_LIMIT = 10;
    public static final int HD_113_DETAIL_LIMIT = 10;
    public static final int HD_121_DETAIL_LIMIT = 10;
    public static final int HD_131_DETAIL_LIMIT = 10;
    public static final int HD_133_DETAIL_LIMIT = 10;
    public static final int HD_135_DETAIL_LIMIT = 10;
    
    public static final int FANS_ANALYSE_LIMIT=10;
    
    public static final int VOTE_LIMIT = 20;
    
    public static final int CATEGORY_LIMIT =20;
    
    public static final long WB_CACHE_PEROID=60*60*1000;
    
    public static final int OWNER_BALANCE_LIMIT=20;
    public static final int OWNER_BALANCE_RECORD_LIMIT=50;
    public static final int OWNER_JF_LIMIT = 10;
    
    public static final String USER_AGENT_PC="C";
    public static final String USER_AGENT_PHONE="P";
    public static final String USER_AGENT_PAD="A";
    
    public static final String PAGE_UPLOAD_TYPE_ZIP="ZIP";
    
    public static final String CARD_MOVE_STYLE_L = "template_blank1.jsp";
    public static final String CARD_MOVE_STYLE_Q = "template_blank2.jsp";
    public static final String CARD_MOVE_STYLE_K = "template_blank.jsp";

    
    public static final String SITE_TYPE_PHONE = "P";
    public static final String SITE_TYPE_PC = "C";
    
    public static final String ACCOUNT_HIDE_LEFT="L";
    public static final String ACCOUNT_HIDE_CONTENTTYPE="T";
    public static final String ACCOUNT_HIDE_INTERACT="I";
    
    public static final int CB_SENDER_LIMIT=20;
    public static final int CB_IMPEL_LIMIT=20;
    public static final int CB_SENDER_RECORD=10;
    
    public static final String POSITION_ABSOLUTE="absolute";
    public static final String POSITION_FIXED="fixed";
    
    public static final String SITEGROUP_TYPE_EVENT="E";//�
    public static final String SITEGROUP_TYPE_WEBSITE="W";//��վ
    public static final String SITEGROUP_TYPE_XIANCHANG="X";//΢�ֳ�
    public static final String SITEGROUP_TYPE_APP="A";//Ӧ��
    
    
    public static final int POSITIONID_1 = 1; 
    public static final int POSITIONID_2 = 2;
    public static final int POSITIONID_3 = 3;
    public static final int POSITIONID_4 = 4;
    public static final int POSITIONID_5 = 5;
    public static final int POSITIONID_6 = 6;
    
    
    public static final String STATUS_EDT="EDT";
    public static final String STATUS_CMP="CMP";
    public static final String STATUS_FLD="FLD";
    
    
    public static final String KV_APT="kv-apt";
    public static final String KV_VOTE="kv-vote";
    
    
    public static final String PRODUCT_WSC="W";//΢�̳ǲ�Ʒ
    public static final String PRODUCT_JF="J";//���ֲ�Ʒ
    
    public static int ORDER_LIMIT = 20;
    
    public static int OFF_CHECK_LIMIT = 20;
    public static int YU_YUE_LIMIT = 20;
    
    public static final String PRODUCT_SUBTYPE_SHIWU="S";//ʵ���Ʒ
    public static final String PRODUCT_SUBTYPE_KAQUAN="K";//��ȯ
    public static final String PRODUCT_SUBTYPE_COUPON="C";//ȯ
    
    public static final String SITE_GROUP_W="W";//΢�ֳ�
    public static final String SITE_GROUP_J="J";//������
    public static final String SITE_GROUP_N="N";//��ͨ
    public static final String SITE_GROUP_V="V";//ͶƱ
    public static final String SITE_GROUP_G="G";//΢����
    public static final String SITE_GROUP_B="B";//΢����
    public static final String SITE_GROUP_L="L";//�ҽ�
    public static final String SITE_GROUP_Z="Z";//��ת��
    public static final String SITE_GROUP_C="C";//�ϻ���
    public static final String SITE_GROUP_P="P";//��������
    public static final String SITE_GROUP_A="A";//����ǩ��
    public static final String SITE_GROUP_S="S";//΢�̳�
    public static final String SITE_GROUP_F="F";//�����̳�
    public static final String SITE_GROUP_D="D";//���
    public static final String SITE_GROUP_H="H";//��������
    public static final String SITE_GROUP_E="E";//�û�����
    
    
    public static final int INTERACT_AD_LIMIT=20;
    
    public static final String SITE_SOURCE_TYPE_DH="IDH";//����
    public static final String SITE_SOURCE_TYPE_YT="IYT";//ҳͷ
    public static final String SITE_SOURCE_TYPE_YJ="IYJ";//ҳ��
    
    public static final int OWNER_SOURCE_LEVEL_PAGE = 1;
    public static final int OWNER_SOURCE_LEVEL_CARD = 2;
    
    
    public static final int OWNER_INTERACT_SHOW=1;
    public static final int OWNER_INTERACT_HIDE=1;
    
}
