<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文8 -->
<link href="/css/tuwen/8_n/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="top_150313_menu block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050797443">
  <header class="ui-header_150313" style="background:rgba(${blocks[0].col},${blocks[0].tm});">
    <h1 hyvar="title" style="color:${blocks[0].fontcol}" class_data="hy2015050797204">${blocks[0].title}</h1>
    <div class="ui-header-left" class_data="hy2015050797495"><a class="ui-header-ico-home" href="${blocks[0].link6}" target="_blank" hyvar="link6"><img hyvar="img1" src="${blocks[0].img1}" width="30" height="30" /></a></div>
    <div class="ui-header-right" class_data="hy2015050797783"><a id="openMenu${pcid}" class="ui-header-ico-menu"><img hyvar="img2" src="${blocks[0].img2}" width="30" height="30" /></a> </div>
  </header>
<ul class="menu_150313" id="menu_150313${pcid}" class_data="hy2015050797656">
    <li class="icon" class_data="hy2015050797305"><a href="${blocks[0].link1}" target="_blank" hyvar="link1"><span hyvar="title1" class_data="hy2015050797309">${blocks[0].title1}</span></a></li>
    <li class="icon" class_data="hy2015050797323"><a href="${blocks[0].link2}" target="_blank" hyvar="link2"><span hyvar="title2" class_data="hy2015050797792">${blocks[0].title2}</span></a></li>
    <li class="icon" class_data="hy2015050797511"><a href="${blocks[0].link3}" target="_blank" hyvar="link3"><span hyvar="title3" class_data="hy2015050797206">${blocks[0].title3}</span></a></li>
    <li class="icon" class_data="hy2015050797805"><a href="${blocks[0].link4}" target="_blank" hyvar="link4"><span hyvar="title4" class_data="hy2015050797734">${blocks[0].title4}</span></a></li>
    <li class="icon" class_data="hy2015050797953"><a href="${blocks[0].link5}" target="_blank" hyvar="link5"><span hyvar="title5" class_data="hy2015050797302">${blocks[0].title5}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="gywsm_150313_top block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050797722"><img hyvar="img" src="${blocks[1].img}"/></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="gysm_banner_150313 block ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050797492">
  <ul class_data="hy2015050797368">
    <li style="border-bottom:3px ${blocks[2].borcol1} solid;" class_data="hy2015050797350"><a style="color:${blocks[2].fontcol1};" hyvar="link1" href="${blocks[2].link1}"><span hyvar="title1" class_data="hy2015050797183">${blocks[2].title1}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol2} solid;" class_data="hy2015050797943"><a style="color:${blocks[2].fontcol2};" hyvar="link2" href="${blocks[2].link2}"><span hyvar="title2" class_data="hy2015050797716">${blocks[2].title2}</span></a></li>
    <li style="border-bottom:3px ${blocks[2].borcol3} solid; border-right:none" class_data="hy2015050797772"><a style="color:${blocks[2].fontcol3};" hyvar="link3" href="${blocks[2].link3}"><span hyvar="title3" class_data="hy2015050797808">${blocks[2].title3}</span></a></li>
  </ul>
</div>
</s:if>
<s:if test='blocks[3].display=="Y"'>
<div class="gysm_center_150313 block clearfix ${blocks[3].ctname}" status="0" hyct="Y" style="${blocks[3].hyct};" hyblk="S" hydata="${blocks[3].rid}" class_data="hy2015050797583">
	<span hyvar="desc" class_data="hy2015050797565">${blocks[3].desc}</span>
</div>
</s:if>
<script type="text/javascript">
	var timer = null;
	$('#openMenu${pcid}').click(function(){
		var iH = $(window).height();
		if($('#menu_150313${pcid}').css('display')=='none'){
			var msk = $('<div id="msk${pcid}"></div>');
			
			$('body').append(msk);			
			$('#menu_150313${pcid}').show().animate({'right':0},500);
		}else{
			$('#menu_150313${pcid}').animate({'right':'-200px'},500);
			
			clearTimeout(timer);
			timer = setTimeout(function(){
				$('#msk${pcid}').remove();
				$('#menu_150313${pcid}').hide();
			},500);
		}
	});
	
	$('#menu_150313${pcid}').click(function(){
		$('#menu_150313${pcid}').animate({'right':'-200px'},500);
			
		clearTimeout(timer);
		timer = setTimeout(function(){
			$('#msk${pcid}').remove();
			$('#menu_150313${pcid}').hide();
		},500);
	});
	
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
