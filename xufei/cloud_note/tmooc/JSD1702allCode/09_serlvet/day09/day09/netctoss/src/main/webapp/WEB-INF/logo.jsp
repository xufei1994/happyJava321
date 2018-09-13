<%@page pageEncoding="utf-8"%>
<img src="images/logo.png" alt="logo" class="left"/>
<!-- EL默认从4个隐含对象中取值:
	page,request,session,application
	它也有能力从cookie中取值,语法:
	cookie.name.value -->
<%-- <span>${cookie.user.value }</span> --%>
<span>${user }</span>
<a href="#">[退出]</a>