<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="border:1px solid #0092ff;margin-left:91px;">
<iframe id="data" src="${mcrmDomain }/data/site_chart.action?ticket=${sessionScope.ticket}&siteid=${siteid}&source=${source}" width="100%" height="1430px" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"> </iframe>
</div>