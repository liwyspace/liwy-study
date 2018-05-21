/*
 * 验证方法
 * 
 * @version: 0.0.1
 * @author： liwy
 * @date: 2016-10-26
 */

/**
 * 文本框必填项验证方法
 * @param obj 文本框对象
 **/
function textRequired(obj){
	if(obj.val() == ''){
		obj.addClass("wrongstyle");
		obj.removeClass("rightstyle");
		
		return false;
	}else{
		obj.removeClass("wrongstyle");
		obj.addClass('rightstyle');
		return true;
	}
}
/**
 * 复选框必填项验证方法
 * @param tips 显示提示信息对象
 * @param obj 文本框对象
 **/
function checkRequired(tips,obj){
	if(obj.is(':checked')){
		$('#errts').text('');
		tips.removeClass("wrongstyle");
		return true;
	}else{
		$('#errts').text('请同意并勾选服务条款');
		tips.addClass("wrongstyle");
		tips.removeClass("rightstyle");
		return false;
	}
}
/**
 * 鼠标离开文本框必填项验证方法
 * @param obj 文本框对象
 */
function textMouseleave(obj){
	obj.blur(function(){
		textRequired(obj);
	});
}
/**
 * Ajax验证用户名
 * @param url 验证地址
 * @param parm 参数名称
 * @param obj 对象名称
 */
function ajaxVerifyName(url,parm,obj){	
	obj.blur(function(){
		if(obj.val()){
			$.get(url, { parm: obj.val()},
					function(data){
						if(data){
							$('#namewrong').val(0);
							$('#errts').text(data);
							obj.addClass("wrongstyle");
							obj.removeClass("rightstyle");
							obj.select();
						}else{
							$('#namewrong').val(1);
							$('#errts').text(data);
						}
					}
			)
		}
	});	
}
/**
 * Ajax验证邮箱
 * @param url 验证地址
 * @param parm 参数名称
 * @param obj 对象名称
 */
function ajaxVerifyEmail(url,parm,obj){	
	obj.mouseleave(function(){
		if(obj.val()){
			$.get(url, { parm: obj.val()},
					function(data){
						if(data){
							$('#emailwrong').val(0);
							$('#errts').text(data);
							obj.addClass("wrongstyle");
							obj.removeClass("rightstyle");
							obj.select();
						}else{
							$('#emailwrong').val(1);
							$('#errts').text(data);
						}
					}
			)
		}
	});	
}

/**
 * Ajax验证手机号
 * @param url 验证地址
 * @param parm 参数名称
 * @param obj 对象名称
 */
function ajaxVerifyPhone(url,sendurl,parm,obj,evenid){	
	evenid.click(function(){
		if(obj.val()){
			$.get(url, { parm: obj.val()},
					function(data){
						if(data){
							$('#errts').text(data);
							obj.css({borderColor:'#FF6060',color:'#FF6060'});
						}else{
							$('#errts').text('');
							obj.css({borderColor:'#AAAAAA',color:'#000'});
							
								$.get(sendurl, 
										function(data){
												$('#errts').text('验证码已发送，请注意接收');
												var count = 10;
												function countDown() {
													evenid.attr("disabled", true);
													evenid.val( count + "秒后可重新获取");
													if (count == 0) {
														evenid.val("重新获取验证码").removeAttr("disabled");
														clearInterval(evn);
														return ;
													}else{
														count--;
													}
												}
												countDown();
												var evn = setInterval(countDown, 1000);
										}
								)
								
							
						}
					}
			)
		}
	});	
}
/**
 * Ajax提交验证用户名
 * @param url 验证地址
 * @param parm 参数名称
 * @param obj 对象名称
 */
function ajaxFormName(url,parm,obj){	
		if(obj.val()){
			$.get(url, { parm: obj.val()},
					function(data){
						if(data){
							$('#namewrong').val(0);
							$('#errts').text(data);
							obj.addClass("wrongstyle");
							obj.removeClass("rightstyle");
							obj.select();
						}else{
							$('#namewrong').val(1);
							$('#errts').text(data);
						}
					}
			)
		}
}

/**
 * Ajax提交验证邮箱
 * @param url 验证地址
 * @param parm 参数名称
 * @param obj 对象名称
 */
function ajaxFormEmail(url,parm,obj){	
		if(obj.val()){
			$.get(url, { parm: obj.val()},
					function(data){
						if(data){
							$('#emailwrong').val(0);
							$('#errts').text(data);
							obj.addClass("wrongstyle");
							obj.removeClass("rightstyle");
							obj.select();
						}else{
							$('#emailwrong').val(1);
							$('#errts').text(data);
						}
					}
			)
		}
}
/**
 * 确认密码验证是否一致
 * @param upass 第一次密码
 * @param qpass 重复密码
 */
function queryPassword(upass,qpass){
	qpass.blur(function(){
		if(upass.val() != qpass.val()){
			$('#iswrong').val(0);
			$('#errts').text('确认密码不正确');
			qpass.addClass("wrongstyle");
			qpass.removeClass("rightstyle");
			
		}else{
			$('#errts').text('');
		}
	});
	if(upass.val() == qpass.val()){
		return true;
	}else{
		$('#errts').text('确认密码不正确');
		qpass.addClass("wrongstyle");
		qpass.removeClass("rightstyle");
		return false;
	}
}

function emailVerify(url,geturl,email,emailCaptcha){
	emailCaptcha.click(function(){
		var email_value = email.val();
		if(email_value){
			var emailReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;;
			if(!emailReg.test(email.val())){
				email.val('邮箱不正确');
				email.addClass("wrongstyle");
				email.removeClass("rightstyle");
				email.select();
			}else{
				email.removeClass("wrongstyle");
				email.addClass('rightstyle');
				
				$.get(url, { 'email': email_value},
						function(data){
							if(data){
								email.val(data);
								email.addClass("wrongstyle");
								email.removeClass("rightstyle");
							}else{
								
								var count = 10;
								function countDown() {
									emailCaptcha.attr("disabled", true);
									emailCaptcha.val( count + "秒后可重新获取");
									if (count == 0) {
										clearInterval(countDown);
										emailCaptcha.val("重新获取验证码").removeAttr("disabled");
										
									}else{
										count--;
									}
									
								}
								countDown();
								setInterval(countDown, 1000);
								$.get(geturl);
								
							}
						}
				)
			}
		}
	});
}



