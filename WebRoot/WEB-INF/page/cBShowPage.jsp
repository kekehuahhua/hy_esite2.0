<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
		var oldSg;
		$.post("/${oname}/setting/findUsedSg.action",{"appid":"2"},function(data){
				oldSg=data;
				$('input[name="useSite"]').each(function(){
					if($(this).val()==data){
						$(this).prop("checked",true);
					}
				});
		});
		
		
		$('input[name="useSite"]').click(function(){
			var sgid = $(this).val();
			layer.confirm('确认使用此站点吗?',function(){
				if(sgid>0){
					$.ajax({
						url : "/${oname}/setting/setUsedSg.action",
						type : "post",
						data : {
							"appid" : "2",
							"sitegroupid" : sgid
						},
						cache : false,
						success : function(res) {
							if (res > 0) {
								oldSg=sgid;
								layer.alert("应用成功");
							}
						}
					});
				}
			},function(){
				$('input[name="useSite"]').each(function(){
					if($(this).val()==oldSg){
						$(this).prop("checked",true);
					}
				});
			});
		});
	});
	
</script>
<div class="wrap_content">
<div class="toolbar pb10">
	<a href="javascript:void(0);" onclick="showAppSite()" class="btn btn-primary">创建站点</a>
</div>
<s:if test="dto.list.size>0">
	<s:iterator value="dto.list" var="s" status="sta">
	<div class="sitegroup_List sg_${s.id }" style="max-height: 150px">
		<div style="float:left">
			<img  alt="" <s:if test='#s.wps.pic != ""'>src="${s.wps.pic }"</s:if><s:else>src="/images/nopic.png"</s:else>  width="150px"/>
		</div>
		<div class="sitegroup_List_left2">
		    <div class="title"><b>${s.groupname}<s:if test='#s.istemplate=="Y"'><span class="glyphicon glyphicon-star" aria-hidden="true" title="活动模板"></span></s:if></b> <a href="javascript:void(0)" onclick="changeSiteName1(${s.id},'${s.groupname }')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></div>
		    <div class="m_badge"><input type="radio" name="useSite" value="${s.id }"/>使用此站点</div>
			<div class="updatetime"><s:date name="updatetime" format="yyyy-MM-dd HH:mm"/> 更新</div>
	    	<div>分享标题：${s.wps.title}</div>
	    	<div>分享描述：${s.wps.description}</div>
		    <div class="operations">
		    	<s:iterator value="#s.site" var="a" status="sta">
		    		<a href="/${oname }/page/pageconfig_app.action?siteid=${a.id }&stype=${a.type }&lightType=7&appId=2"><span title="编辑" class="glyphicon glyphicon-edit"></span></a><i class="split">|</i>
		        </s:iterator>
		        <s:if test='#s.istemplate!="Y"'><a href="javascript:void(0)" onclick="delete_site(${s.id})"><span class="glyphicon glyphicon-trash" title="删除"></span></a></s:if>
		    </div>
	    </div>
	</div>
	</s:iterator>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</s:if>
<s:else>
	<p style="font-size:20px;text-align:center;">点击 创建站点，开启您的营销活动。</p>
</s:else>
</div>
<%@include file="/WEB-INF/page/includeAppSites.jsp" %>