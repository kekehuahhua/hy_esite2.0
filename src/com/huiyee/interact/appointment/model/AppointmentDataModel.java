
package com.huiyee.interact.appointment.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDataModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private long id;
	private long aptid;
	private String nickname;
	private long wbuid;
	private String name;
	private int age;
	private String gender;
	private Date birthday;
	private String city;
	private String province;
	private String district;
	private String address;
	private String idcard;
	private String email;
	private String phone;
	private Date date;
	private Date time;
	private String type;
	private String source;
	private String terminal;
	private String ip;
	private Date createtime;
	private String code;
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private String f5;
	private String f6;
	private String f7;
	private String f8;
	private String f9;
	private String f10;
	private String f11;
	private String f12;
	private String f13;
	private String f14;
	private String f15;
	private String f16;
	private String f17;
	private String f18;
	private String f19;
	private String f20;
	private String f21;
	private String f22;
	private String f23;
	private String f24;
	private String f25;
	private String f26;
	private String f27;
	private String f28;
	private String f29;
	private String f30;

	private List<String> list;

	private List<AptMapping> maps;
	private List<String> values;
	private String tel;
	private String wxnickname;
	private long pageid;

	public String getWxnickname()
	{
		return wxnickname;
	}

	public void setWxnickname(String wxnickname)
	{
		this.wxnickname = wxnickname;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getIdcard()
	{
		return idcard;
	}

	public void setIdcard(String idcard)
	{
		this.idcard = idcard;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getF1()
	{
		return f1;
	}

	public void setF1(String f1)
	{
		this.f1 = f1;
	}

	public String getF2()
	{
		return f2;
	}

	public void setF2(String f2)
	{
		this.f2 = f2;
	}

	public String getF3()
	{
		return f3;
	}

	public void setF3(String f3)
	{
		this.f3 = f3;
	}

	public String getF4()
	{
		return f4;
	}

	public void setF4(String f4)
	{
		this.f4 = f4;
	}

	public String getF5()
	{
		return f5;
	}

	public void setF5(String f5)
	{
		this.f5 = f5;
	}

	public String getF6()
	{
		return f6;
	}

	public void setF6(String f6)
	{
		this.f6 = f6;
	}

	public String getF7()
	{
		return f7;
	}

	public void setF7(String f7)
	{
		this.f7 = f7;
	}

	public String getF8()
	{
		return f8;
	}

	public void setF8(String f8)
	{
		this.f8 = f8;
	}

	public String getF9()
	{
		return f9;
	}

	public void setF9(String f9)
	{
		this.f9 = f9;
	}

	public String getF10()
	{
		return f10;
	}

	public void setF10(String f10)
	{
		this.f10 = f10;
	}

	public String getF11()
	{
		return f11;
	}

	public void setF11(String f11)
	{
		this.f11 = f11;
	}

	public String getF12()
	{
		return f12;
	}

	public void setF12(String f12)
	{
		this.f12 = f12;
	}

	public String getF13()
	{
		return f13;
	}

	public void setF13(String f13)
	{
		this.f13 = f13;
	}

	public String getF14()
	{
		return f14;
	}

	public void setF14(String f14)
	{
		this.f14 = f14;
	}

	public String getF15()
	{
		return f15;
	}

	public void setF15(String f15)
	{
		this.f15 = f15;
	}

	public String getF16()
	{
		return f16;
	}

	public void setF16(String f16)
	{
		this.f16 = f16;
	}

	public String getF17()
	{
		return f17;
	}

	public void setF17(String f17)
	{
		this.f17 = f17;
	}

	public String getF18()
	{
		return f18;
	}

	public void setF18(String f18)
	{
		this.f18 = f18;
	}

	public String getF19()
	{
		return f19;
	}

	public void setF19(String f19)
	{
		this.f19 = f19;
	}

	public String getF20()
	{
		return f20;
	}

	public void setF20(String f20)
	{
		this.f20 = f20;
	}

	public List<AptMapping> getMaps()
	{
		return maps;
	}

	public void setMaps(List<AptMapping> maps)
	{
		this.maps = maps;
	}

	public List<String> getValues()
	{
		return values;
	}

	public void setValues(List<String> values)
	{
		this.values = values;
	}

	public List<String> getList()
	{
		return list;
	}

	public void setList(List<String> list)
	{
		this.list = list;
	}

	public void composeList(List<AptMapping> cloum)
	{
		list = new ArrayList<String>();
		for (AptMapping mapp : cloum)
		{
			try
			{
				String name = toUpperCaseFirstOne(mapp.getMapping());
				Method m = this.getClass().getMethod("get" + name);
				Object value = m.invoke(this); // ����getter������ȡ����ֵ
				if (value != null)
				{
					list.add(value + "");
				} else
				{
					list.add("");
				}
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ����ĸת��д
	public static String toUpperCaseFirstOne(String s)
	{
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getDistrict()
	{
		return district;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public String getF21()
	{
		return f21;
	}

	public void setF21(String f21)
	{
		this.f21 = f21;
	}

	public String getF22()
	{
		return f22;
	}

	public void setF22(String f22)
	{
		this.f22 = f22;
	}

	public String getF23()
	{
		return f23;
	}

	public void setF23(String f23)
	{
		this.f23 = f23;
	}

	public String getF24()
	{
		return f24;
	}

	public void setF24(String f24)
	{
		this.f24 = f24;
	}

	public String getF25()
	{
		return f25;
	}

	public void setF25(String f25)
	{
		this.f25 = f25;
	}

	public String getF26()
	{
		return f26;
	}

	public void setF26(String f26)
	{
		this.f26 = f26;
	}

	public String getF27()
	{
		return f27;
	}

	public void setF27(String f27)
	{
		this.f27 = f27;
	}

	public String getF28()
	{
		return f28;
	}

	public void setF28(String f28)
	{
		this.f28 = f28;
	}

	public String getF29()
	{
		return f29;
	}

	public void setF29(String f29)
	{
		this.f29 = f29;
	}

	public String getF30()
	{
		return f30;
	}

	public void setF30(String f30)
	{
		this.f30 = f30;
	}

	
	public String getCode()
	{
		return code;
	}

	
	public void setCode(String code)
	{
		this.code = code;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
}
