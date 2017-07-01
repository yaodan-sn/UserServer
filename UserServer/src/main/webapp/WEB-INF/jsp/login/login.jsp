<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <title>Login and Registration Form with HTML5 and CSS3</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/animate-custom.css" />
    </head>
	<body>
        <div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">
                <a href="">
                    <strong>&laquo; Previous Demo: </strong>Responsive Content Navigator
                </a>
                <span class="right">
                    <a href=" http://tympanus.net/codrops/2012/03/27/login-and-registration-form-with-html5-and-css3/">
                        <strong>Back to the Codrops Article</strong>
                    </a>
                </span>
                <div class="clr"></div>
            </div><!--/ Codrops top bar -->
            <header>
                <h1>Login and Registration Form <span>with HTML5 and CSS3</span></h1>
				<nav class="codrops-demos">
					<!-- <span>Click <strong>"Join us"</strong> to see the form switch</span>
					<a href="index.html" class="current-demo">Demo 1</a>
					<a href="index2.html">Demo 2</a>
					<a href="index3.html">Demo 3</a> -->
				</nav>
            </header>
            <section>				
                <div id="container_demo" >
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="" autocomplete="on" method="post"> 
                                <h1>Log in</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Your verifycode or username </label>
                                    <input id="username" name="username" required="required" type="text" placeholder="myusername or mymail@mail.com"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Your password </label>
                                    <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO" /> 
                                </p>
                                <p class="keeplogin"> 
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping">Keep me logged in</label>
								</p>
                                <p class="login button"> 
                                    <input type="button" value="Login" onclick="login()"/> 
								</p>
                                <p class="change_link">
									Not a member yet ?
									<a href="#toregister" class="to_register">Join us</a>
								</p>
                            </form>
                        </div>

                        <div id="register" class="animate form">
                            <form  action="" autocomplete="on"> 
                                <h1> Sign up </h1> 
                                <p> 
                                    <label for="usernamesignup" class="uname" data-icon="u">Your username</label>
                                    <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
                                </p>
                                <p> 
                                    <label for="verifycodesignup" class="youmail" data-icon="e" > Your verifycode</label>
                                    <input id="verifycodesignup" name="verifycodesignup" required="required" type="verifycode" placeholder="eg. 231032"/> 
                                </p>
                                <p class="signin button"> 
									<input type="button" value="Send" onclick="sendVerifyCode()"/> 
								</p>
                                <p> 
                                    <label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
                                    <input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="eg. X8df!90EO"/>
                                </p>
                                <p> 
                                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please confirm your password </label>
                                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>
                                </p>
                                <p class="signin button"> 
									<input type="button" value="Sign up" onclick="register()"/> 
								</p>
                                <p class="change_link">  
									Already a member ?
									<a href="#tologin" class="to_register"> Go and log in </a>
								</p>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
    <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.8.0.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/common/ajax.js"></script>
    <script type="text/javascript">
    
    var clientId = "fe5ef422cff64a088982c53e6a2e9c45";
    var clientSecret = "330953a9d0144110b81875979d480cef";
    var hostname = "v.lefu8.com";
    
    function login() {
    	var username = $("#username").val();
    	var password = $("#password").val();
    	ajaxRequest("${ctx}/operator/login.json",
    			"POST",
    			{"username": username, "password":password}, 
    			function(status, data){
        	if(status){
        		if(data.resultCode == "00"){
        			//登录成功
        			alert("登录成功")
        		} else {
        			alert(data.resultMessage);
        		}
        	}
        });	
    }
    
    function sendVerifyCode() {
    	var usernamesignup = $("#usernamesignup").val();
    	ajaxRequest("${ctx}/operator/verifyCode.json", 
    			"GET",
    			{"phone": usernamesignup}, 
    			function(status, data){
        	if(status){
        		if(data.resultCode == "00") {
        			//登录成功
        			alert("发送成功")
        		} else {
        			alert(data.resultMessage);
        		}
        	}
        });	
    }
    
    function register() {
    	var usernamesignup = $("#usernamesignup").val();
    	var passwordsignup = $("#passwordsignup").val();
    	var verifycodesignup = $("#verifycodesignup").val();
    	ajaxRequest("${ctx}/operator/register.json", 
    			"POST",
    			{"username": usernamesignup, "password":passwordsignup, "verifyCode":verifycodesignup}, 
    			function(status, data){
        	if(status){
        		if(data.resultCode == "00") {
        			//登录成功
        			alert("注册成功")
        		} else {
        			alert(data.resultMessage);
        		}
        	}
        });	
    }
    </script>
</html>