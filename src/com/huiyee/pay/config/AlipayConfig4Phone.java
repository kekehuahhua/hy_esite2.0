package com.huiyee.pay.config;

/* *
 *������AlipayConfig4Phone
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�汾��3.3
 *���ڣ�2012-08-10
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
	
 *��ʾ����λ�ȡ��ȫУ����ͺ��������ID
 *1.������ǩԼ֧�����˺ŵ�¼֧������վ(www.alipay.com)
 *2.������̼ҷ���(https://b.alipay.com/order/myOrder.htm)
 *3.�������ѯ���������(PID)��������ѯ��ȫУ����(Key)��

 *��ȫУ����鿴ʱ������֧�������ҳ��ʻ�ɫ��������ô�죿
 *���������
 *1�������������ã������������������������
 *2���������������ԣ����µ�¼��ѯ��
 */

public class AlipayConfig4Phone {
	
	//�����������������������������������Ļ�����Ϣ������������������������������
	// ���������ID����2088��ͷ��16λ��������ɵ��ַ���
//	public static String partner = "";
//	
//	//�տ�֧�����ʻ�
//	public static String seller_email = "";
	
	// ���װ�ȫ�����룬�����ֺ���ĸ��ɵ�32λ�ַ���
	// ���ǩ����ʽ����Ϊ��MD5��ʱ�������øò���
	public static String key2 = "";
	
    // �̻���˽Կ
    // ���ǩ����ʽ����Ϊ��0001��ʱ�������øò���
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPB6e4nOMjhTBJBx"
+"w57Xv11pa3wrJW3DhwX0mSvsxnxpeAFKpGqX2IKYWcZW2+osyq9g7biYzDEWBRLI"
+"2QmWm4OT3H4cZ6ZW72lJUH2MTHHIovfrxfi9+XMKbi39JcT9dXIY1keulJgx5cd/"
+"0gBHTWeGpZ7buVGN7HLHMmUWCXT7AgMBAAECgYEAipwevwSh/8MiO1vHhLQYsnAE"
+"K1L3RPhRyIX/D8lvcuCb8iqi8GnLuyKzhFYmdikRPttgymkQMS7fB7/LEfLEeYnK"
+"xo0r/XYOx1cFr/DAg2w865tVeEkCjOfwrvfG4gQ6EP7nhJc454WsXVj2wSIoIB/I"
+"NoDzu5lnCMhbhWqCWhECQQD+9AF09DENiIeCKN+gOkbT3OeWW91PjoHKp47sxJtK"
+"2pSKDu9+vZxiterA5cKuB6SGzOFekmZT99qI5ktAbHG5AkEA8XdDBC7yCyrXkrwW"
+"Q7wZXyENbLMavlwn1ZOnAp+RDW+j9CUl3qDFBThy1UXG+2a5FdenaIxmdTc7O8tk"
+"7ghGUwJAYcHb4SVUvbSxCNZ1ULGnwMq+PegBhuTEnZj784s7ZEtFk15AtJ8tnKfW"
+"WJqAuEwT6OAcNi/6bo3lzIJJLm2meQJBAKm4GfKmzJns0vjgGwYg//RNVxJAEVwb"
+"iiQkVA8T+PBot+Sy95szhUfWFWCyWtn5qb4ghhqGFL3uxEOTAq4MQuECQQDsEjTp"
+"l0+TldrD8i0TesD24H5zuXwyDdoce2r/CDSbnM0K6QP8fOME3lLZd0Fn+KnVJvh4"
+"CyuBwILTN8aEM++M";

    // ֧�����Ĺ�Կ
    // ���ǩ����ʽ����Ϊ��0001��ʱ�������øò���
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8V4xK/lorvg5HWBys0PyXEi9QHNrk5a3Kli+d /ZWEUUgYProe6cLvLtSjjPeKFmkAAx/r+1LScEdc5RSPNZTetIom/BJvfs0zrtfEBdXNMk7LIHUS dZUE4xPCGVvDICuVpWDA3Wr5SkZZHfMSxO60fcEQNnzPQZsApMLnUsOVTQIDAQAB";

	//�����������������������������������Ļ�����Ϣ������������������������������
	

	// �����ã�����TXT��־�ļ���·��
	public static String log_path = "D:\\";

	// �ַ������ʽ Ŀǰ֧��  utf-8
	public static String input_charset = "utf-8";
	
	// ǩ����ʽ��ѡ���0001(RSA)��MD5
	public static String sign_type = "MD5";
	// ���ߵĲ�Ʒ�У�ǩ����ʽΪrsaʱ��sign_type�踳ֵΪ0001������RSA

}
