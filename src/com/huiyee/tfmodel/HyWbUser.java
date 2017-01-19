package com.huiyee.tfmodel;

import java.util.Date;

import net.sf.json.JSONObject;

public class HyWbUser implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	private String id; // �û�UID
	private String screenName; // ΢���ǳ�
	private String name; // �Ѻ���ʾ���ƣ���Bill Gates,�����м�Ŀո�������ʾ(�������ݲ�֧��)
	private int province; // ʡ�ݱ��루�ο�ʡ�ݱ����
	private int city; // ���б��루�ο����б����
	private String location; // ��ַ
	private String description; // ��������
	private String url; // �û����͵�ַ
	private String profileImageUrl; // �Զ���ͼ��
	private String userDomain; // �û����Ի�URL
	private String gender; // �Ա�,m--�У�f--Ů,n--δ֪
	private int followersCount; // ��˿��
	private int friendsCount; // ��ע��
	private int statusesCount; // ΢����
	private int favouritesCount; // �ղ���
	private Date createdAt; // ����ʱ��
	private boolean following; // �����ֶ�,�Ƿ��ѹ�ע(�������ݲ�֧��)
	private boolean verified; // ��V��ʾ���Ƿ�΢����֤�û�
	private int verifiedType; // ��֤����
	private boolean allowAllActMsg; // �Ƿ����������˸��ҷ�˽��
	private boolean allowAllComment; // �Ƿ����������˶��ҵ�΢����������
	private boolean followMe; // ���û��Ƿ��ע��
	private String avatarLarge; // ��ͷ���ַ
	private int onlineStatus; // �û�����״̬
	private HyWbSrc weibo = null; // �û�����һ��΢��
	private int biFollowersCount; // ������
	private String remark; // ��ע��Ϣ���ڲ�ѯ�û���ϵʱ�ṩ���ֶΡ�
	private String lang; // �û����԰汾
	private String verifiedReason; // ��֤ԭ��
	private String weihao; // ΢̖
	private String statusId;

	public HyWbUser()
	{
		super();
	}
	
	public HyWbUser(String jsonstr) {
		JSONObject json = JSONObject.fromObject(jsonstr);
		this.id = HyJsonUtils.pasString(json, "id");
		this.screenName = HyJsonUtils.pasString(json, "screenName");
		this.name = HyJsonUtils.pasString(json, "name");
		this.province = HyJsonUtils.pasInt(json, "province");
		this.city = HyJsonUtils.pasInt(json, "city");
		this.location = HyJsonUtils.pasString(json, "location");
		this.description = HyJsonUtils.pasString(json, "description");
		this.url = HyJsonUtils.pasString(json, "url");
		this.profileImageUrl = HyJsonUtils.pasString(json, "profileImageUrl");
		this.userDomain = HyJsonUtils.pasString(json, "userDomain");
		this.gender = HyJsonUtils.pasString(json, "gender");
		this.followersCount = HyJsonUtils.pasInt(json, "followersCount");
		this.friendsCount = HyJsonUtils.pasInt(json, "friendsCount");
		this.statusesCount = HyJsonUtils.pasInt(json, "statusesCount");
		this.favouritesCount = HyJsonUtils.pasInt(json, "favouritesCount");
		JSONObject createdAtObj =  HyJsonUtils.pasJsonobj(json,"createdAt");
		if (!createdAtObj.isEmpty()) {
			this.createdAt = new Date(createdAtObj.getLong("time"));
		}
		this.following = HyJsonUtils.pasBoolean(json,"following");
		this.verified = HyJsonUtils.pasBoolean(json,"verified");
		this.verifiedType = HyJsonUtils.pasInt(json, "verifiedType");
		this.allowAllActMsg = HyJsonUtils.pasBoolean(json,"allowAllActMsg");
		this.allowAllComment = HyJsonUtils.pasBoolean(json,"allowAllComment");
		this.followMe = HyJsonUtils.pasBoolean(json,"followMe");
		this.avatarLarge = HyJsonUtils.pasString(json, "avatarLarge");
		this.onlineStatus = HyJsonUtils.pasInt(json, "onlineStatus");
		String weiboStr = HyJsonUtils.pasString(json, "weibo");
		if (weiboStr != null && !"null".equals(weiboStr)) {
			this.weibo = new HyWbSrc(weiboStr);
		}
		this.biFollowersCount = HyJsonUtils.pasInt(json, "biFollowersCount");
		this.remark = HyJsonUtils.pasString(json, "remark");
		this.lang = HyJsonUtils.pasString(json, "lang");
		this.verifiedReason = HyJsonUtils.pasString(json, "verifiedReason");
		this.weihao = HyJsonUtils.pasString(json, "weihao");
		this.statusId = HyJsonUtils.pasString(json, "statusId");
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getScreenName()
	{
		return screenName;
	}

	public void setScreenName(String screenName)
	{
		this.screenName = screenName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getProvince()
	{
		return province;
	}

	public void setProvince(int province)
	{
		this.province = province;
	}

	public int getCity()
	{
		return city;
	}

	public void setCity(int city)
	{
		this.city = city;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getProfileImageUrl()
	{
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl)
	{
		this.profileImageUrl = profileImageUrl;
	}

	public String getUserDomain()
	{
		return userDomain;
	}

	public void setUserDomain(String userDomain)
	{
		this.userDomain = userDomain;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getFollowersCount()
	{
		return followersCount;
	}

	public void setFollowersCount(int followersCount)
	{
		this.followersCount = followersCount;
	}

	public int getFriendsCount()
	{
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount)
	{
		this.friendsCount = friendsCount;
	}

	public int getStatusesCount()
	{
		return statusesCount;
	}

	public void setStatusesCount(int statusesCount)
	{
		this.statusesCount = statusesCount;
	}

	public int getFavouritesCount()
	{
		return favouritesCount;
	}

	public void setFavouritesCount(int favouritesCount)
	{
		this.favouritesCount = favouritesCount;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public boolean isFollowing()
	{
		return following;
	}

	public void setFollowing(boolean following)
	{
		this.following = following;
	}

	public boolean isVerified()
	{
		return verified;
	}

	public void setVerified(boolean verified)
	{
		this.verified = verified;
	}

	public int getVerifiedType()
	{
		return verifiedType;
	}

	public void setVerifiedType(int verifiedType)
	{
		this.verifiedType = verifiedType;
	}

	public boolean isAllowAllActMsg()
	{
		return allowAllActMsg;
	}

	public void setAllowAllActMsg(boolean allowAllActMsg)
	{
		this.allowAllActMsg = allowAllActMsg;
	}

	public boolean isAllowAllComment()
	{
		return allowAllComment;
	}

	public void setAllowAllComment(boolean allowAllComment)
	{
		this.allowAllComment = allowAllComment;
	}

	public boolean isFollowMe()
	{
		return followMe;
	}

	public void setFollowMe(boolean followMe)
	{
		this.followMe = followMe;
	}

	public String getAvatarLarge()
	{
		return avatarLarge;
	}

	public void setAvatarLarge(String avatarLarge)
	{
		this.avatarLarge = avatarLarge;
	}

	public int getOnlineStatus()
	{
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus)
	{
		this.onlineStatus = onlineStatus;
	}

	public HyWbSrc getWeibo()
	{
		return weibo;
	}

	public void setWeibo(HyWbSrc weibo)
	{
		this.weibo = weibo;
	}

	public int getBiFollowersCount()
	{
		return biFollowersCount;
	}

	public void setBiFollowersCount(int biFollowersCount)
	{
		this.biFollowersCount = biFollowersCount;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public String getVerifiedReason()
	{
		return verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason)
	{
		this.verifiedReason = verifiedReason;
	}

	public String getWeihao()
	{
		return weihao;
	}

	public void setWeihao(String weihao)
	{
		this.weihao = weihao;
	}

	public String getStatusId()
	{
		return statusId;
	}

	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HyWbUser other = (HyWbUser) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [" + "id=" + id + ", screenName=" + screenName + ", name=" + name + ", province=" + province + ", city=" + city + ", location=" + location + ", description=" + description + ", url=" + url + ", profileImageUrl=" + profileImageUrl + ", userDomain=" + userDomain + ", gender="
				+ gender + ", followersCount=" + followersCount + ", friendsCount=" + friendsCount + ", statusesCount=" + statusesCount + ", favouritesCount=" + favouritesCount + ", createdAt=" + createdAt + ", following=" + following + ", verified=" + verified + ", verifiedType=" + verifiedType
				+ ", allowAllActMsg=" + allowAllActMsg + ", allowAllComment=" + allowAllComment + ", followMe=" + followMe + ", avatarLarge=" + avatarLarge + ", onlineStatus=" + onlineStatus +
				// ", status=" + status +
				", biFollowersCount=" + biFollowersCount + ", remark=" + remark + ", lang=" + lang + ", verifiedReason=" + verifiedReason + ", weihao=" + weihao + ", statusId=" + statusId + "]";
	}

}