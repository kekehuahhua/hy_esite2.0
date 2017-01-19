<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content">
   <dl>
   	 <dt>1、	标题</dt>
   	 	<dd>必填，不会在前台显示，填写一个方便您记忆的标题即可。</dd>
   	 <dt>2、	简介</dt>
   	 	<dd>非必填，也不会在前台显示，仅为方便记忆。</dd>
   	 <dt>3、	活动开始时间/结束时间</dt>
   	 	<dd>必填，系统将根据设定的时间判断用户是否可以参加大转盘抽奖。</dd>
   	 <dt>4、	开始前提示语</dt>
   	 	<dd>非必填，可以设置为“大转盘活动还未开始！”，用户在活动开始前点击大转盘，将会弹出此提示。</dd>
   	 <dt>5、	结束后提示语</dt>
   	 	<dd>非必填，可以设置为“大转盘活动已结束！”，用户在活动结束后点击大转盘，将会弹出此提示。</dd>
   	 <dt>6、	抽奖资格</dt>
   	 	<dd>默认为所有用户，系统支持设置微信粉丝或者积分用户才有资格抽奖，微信粉丝是指微信公众号的关注者，若选择此项，在站点信息处必须将页面发布到微信。</dd>
   	 <dt>7、	抽奖机会</dt>
   	 	<dd>用户初始抽奖机会是指商家默认给用户的抽奖机会，若设置为1次/人，则有抽奖资格的用户第一次登录大转盘抽奖页面都将获得1次抽奖机会。</dd>
   	 	<dd>每日抽奖次数上限是指用户每日最多可抽奖的次数。若活动抽奖机会可以通过投票、调研、申请等互动增加，可以根据需求对每日抽奖的上限进行控制。</dd>
   	 <dt>8、	活动中奖率</dt>
   	 	<dd>指用户参与大转盘中奖的概率，而不是特定指一等奖或二等奖的中奖概率。若活动准备了10个奖品，大约有1000人参加，则中奖率设置为1%，即万分之100。
中奖率为1%，一等奖奖品数量：1个；二等奖奖品数量：4个；三等奖奖品数量：5个。则一等奖的中奖概率为：（1/10）*1%*100%;二等奖的中奖概率为：（4/10）*1%*100%；三等奖的中奖概率为：（5/10）*1%*100%。
</dd>
   </dl>
</div>