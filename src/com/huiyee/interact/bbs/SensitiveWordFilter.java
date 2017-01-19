package com.huiyee.interact.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: ���дʹ���
 * @Project��test
 * @Author : chenming
 * @Date �� 2014��4��20�� ����4:17:15
 * @version 1.0
 */
public class SensitiveWordFilter {
	public static Map<Object, Object> sensitiveWordMap = null;
	public static int minMatchTYpe = 1; // ��Сƥ�����
	public static int maxMatchType = 2; // ���ƥ�����

	/**
	 * ���캯������ʼ�����дʿ�
	 */

	/**
	 * ��ȡ���дʿ⣬�����дʷ���HashSet�У�����һ��DFA�㷨ģ�ͣ�<br>
	 * �� = { isEnd = 0 �� = {<br>
	 * isEnd = 1 �� = {isEnd = 0 �� = {isEnd = 1} } �� = { isEnd = 0 �� = { isEnd =
	 * 1 } } } } �� = { isEnd = 0 �� = { isEnd = 0 �� = { isEnd = 0 �� = { isEnd = 1 } } } }
	 * 
	 * @author chenming
	 * @date 2014��4��20�� ����3:04:20
	 * @param keyWordSet
	 *            ���дʿ�
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public static void addSensitiveWordToHashMap(List<String> keyWords) {
		sensitiveWordMap = new HashMap<Object, Object>(keyWords.size()); // ��ʼ�����д��������������ݲ���
		// ����keyWords
		for (String key : keyWords) {
			Map<Object, Object> nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // ת����char��
				Object wordMap = nowMap.get(keyChar); // ��ȡ

				if (wordMap != null) { // ������ڸ�key��ֱ�Ӹ�ֵ
					nowMap = (Map<Object, Object>) wordMap;
				} else { // ���������򹹽�һ��map��ͬʱ��isEnd����Ϊ0����Ϊ���������һ��
					Map<Object, Object> newWord = new HashMap<Object, Object>();
					newWord.put("isEnd", "0"); // �������һ��
					nowMap.put(keyChar, newWord);
					nowMap = newWord;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // ���һ��
				}
			}
		}

		// Iterator<String> iterator = keyWordSet.iterator();
		// while (iterator.hasNext()) {
		// key = iterator.next(); // �ؼ���
		// nowMap = sensitiveWordMap;
		// for (int i = 0; i < key.length(); i++) {
		// char keyChar = key.charAt(i); // ת����char��
		// Object wordMap = nowMap.get(keyChar); // ��ȡ
		//
		// if (wordMap != null) { // ������ڸ�key��ֱ�Ӹ�ֵ
		// nowMap = (Map) wordMap;
		// } else { // ���������򹹽�һ��map��ͬʱ��isEnd����Ϊ0����Ϊ���������һ��
		// newWorMap = new HashMap<String, String>();
		// newWorMap.put("isEnd", "0"); // �������һ��
		// nowMap.put(keyChar, newWorMap);
		// nowMap = newWorMap;
		// }
		//
		// if (i == key.length() - 1) {
		// nowMap.put("isEnd", "1"); // ���һ��
		// }
		// }
		// }
	}

	/**
	 * �滻�������ַ�
	 * 
	 * @author chenming
	 * @date 2014��4��20�� ����5:12:07
	 * @param txt
	 * @param matchType
	 * @param replaceChar
	 *            �滻�ַ���Ĭ��*
	 * @version 1.0
	 */
	public static String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
		String resultTxt = txt;
		Set<String> set = getSensitiveWord(txt, matchType); // ��ȡ���е����д�
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}

		return resultTxt;
	}

	/**
	 * ��ȡ�����е����д�
	 * 
	 * @author chenming
	 * @date 2014��4��20�� ����5:10:52
	 * @param txt
	 *            ����
	 * @param matchType
	 *            ƥ�����&nbsp;1����Сƥ�����2�����ƥ�����
	 * @return
	 * @version 1.0
	 */
	private static Set<String> getSensitiveWord(String txt, int matchType) {
		Set<String> sensitiveWordList = new HashSet<String>();

		for (int i = 0; i < txt.length(); i++) {
			int length = checkSensitiveWord(txt, i, matchType); // �ж��Ƿ���������ַ�
			if (length > 0) { // ����,����list��
				sensitiveWordList.add(txt.substring(i, i + length));
				i = i + length - 1; // ��1��ԭ������Ϊfor������
			}
		}

		return sensitiveWordList;
	}

	/**
	 * ����������Ƿ���������ַ������������£�<br>
	 * 
	 * @author chenming
	 * @date 2014��4��20�� ����4:31:03
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return��������ڣ��򷵻����д��ַ��ĳ��ȣ������ڷ���0
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
		boolean flag = false; // ���дʽ�����ʶλ���������д�ֻ��1λ�����
		int matchFlag = 0; // ƥ���ʶ��Ĭ��Ϊ0
		char word = 0;
		Map<Object, Object> nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map<Object, Object>) nowMap.get(word); // ��ȡָ��key
			if (nowMap != null) { // ���ڣ����ж��Ƿ�Ϊ���һ��
				matchFlag++; // �ҵ���Ӧkey��ƥ���ʶ+1
				if ("1".equals(nowMap.get("isEnd"))) { // ���Ϊ���һ��ƥ�����,����ѭ��������ƥ���ʶ��
					flag = true; // ������־λΪtrue
					if (SensitiveWordFilter.minMatchTYpe == matchType) { // ��С����ֱ�ӷ���,���������������
						break;
					}
				}
			} else { // �����ڣ�ֱ�ӷ���
				break;
			}
		}
		if (matchFlag < 2 || !flag) { // ���ȱ�����ڵ���1��Ϊ��
			matchFlag = 0;
		}
		return matchFlag;
	}

	/**
	 * ��ȡ�滻�ַ���
	 * 
	 * @author chenming
	 * @date 2014��4��20�� ����5:21:19
	 * @param replaceChar
	 * @param length
	 * @return
	 * @version 1.0
	 */
	private static String getReplaceChars(String replaceChar, int length) {
		String resultReplace = replaceChar;
		for (int i = 1; i < length; i++) {
			resultReplace += replaceChar;
		}

		return resultReplace;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		List<String> sets = new ArrayList<String>();
		sets.add("����");
		sets.add("�򰡵�");
		sets.add("̫��");

		addSensitiveWordToHashMap(sets);
		System.out.println("���дʵ�������" + sensitiveWordMap.size());
		String string = "����̫����˸��黳Ҳ��ֻ�������������� ӫĻ�е���ڣ����˹�������ȥ��ĳ�ַ�ʽ�����ĺ�����������ɱָ�ϻ���Щ�Լ��������˸С�" + "Ȼ���ֹ� ���ǵİ��ݵĽ�ɫ���Ǹ��������˹���ϲ������� ŭ���ֶ�����ǣǿ�İ��Լ������Ҳ��������Ļ����У�Ȼ��ж������ᣬ"
				+ "�ѹ�������ĳһ���˵Ļ��ﾡ��Ĳ�����������ֻ���������һ����һ�����һ����Ӱ��ҹ����Ƭ ���˾������ϣ����ϵ绰�����ķ����š�";
		System.out.println("��������������" + string.length());
		long beginTime = System.currentTimeMillis();
		Set<String> set = getSensitiveWord(string, 1);
		long endTime = System.currentTimeMillis();
		System.out.println("����а������дʵĸ���Ϊ��" + set.size() + "��������" + set);
		System.out.println("�ܹ�����ʱ��Ϊ��" + (endTime - beginTime));

		string = replaceSensitiveWord(string, 1, "*");
		System.out.println(string);

	}
}
