/* scripts/login.js 编码为 utf-8  */

$(function(){
	//console.log('Hello World!');
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPassword);
});

function checkName(){
	var name = $('#count').val();
	var rule = /^\w{4,10}$/;
	if(! rule.test(name)){
		$('#count').next().html('4~10个字符');
		return false;
	}
	$('#count').next().empty();
	return true;
}

function checkPassword(){
	var password = $('#password').val();
	var rule = /^\w{4,10}$/;
	if(! rule.test(password)){
		$('#password').next().html('4~10个字符');
		return false;
	}
	$('#password').next().empty();
	return true;
}


function loginAction(){
	//console.log("loginAction");
	//获取用户输入的用户名和密码
	var name = $('#count').val();
	var password = $('#password').val();
	
	var n=checkName()+checkPassword();
	if(n!=2){
		return;
	}
	
	//data 对象中的属性名要与服务器控制器的参数
	// 名一致! login(name, password)
	var data = {"name":name, 
				"password":password};
	$.ajax({
		url:'user/login.do',
		data:data,
		type:'post',
		dataType:'json',
		success: function(result){
			console.log(result);
			if(result.state==0){
				//登录成功!
				var user = result.data;
				console.log(user);
				//跳转到 edit.html
				location.href='edit.html';
			}else{
				var msg = result.message;
				$('#count').next().html(msg);
			}
		},
		error: function(e){
			alert("通信失败!");
		}
	});
}








