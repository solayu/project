<html>
<head>
    <title>卖家登陆系统</title>
</head>
<body>
<div class="top">
    <div class="topcontent">登陆系统</div>
    <!-- <div class="subtitle">
        <a href="http://pf.ahjtgps.com/web/login.jspx">运营商入口</a>
        <a href="http://pf.ahjtgps.com/web/login.jspx">企业入口</a>
    </div> -->
    <div class="timer"><div id="jnkc"></div></div>
</div>
<div class="center">
    <div class="left">
    </div>
    <div class="right">
        <div id="loginform" class="loginBox">
            <form method="post" id="ff" >
                <image class="icon-user" src="http://pf.ahjtgps.com/gnss/res/images/user_gray.png"></image>
                <input type="text" placeholder="账户" class="input_text username" name="j_username" style="background-color:white;" onfocus="userblue();" onblur="usergray();">
                <image class="icon-psw" src="http://pf.ahjtgps.com/gnss/res/images/psw_gray.png"></image>
                <input type="password" placeholder="密码" class="input_text password" name="j_password" style="background-color:white;" onfocus="pswblue();" onblur="pswgray();">
                <input id="vcode" name="vcode" placeholder="验证码" type="text" class="input_text verification" value="" onfocus="randblue();" onblur="randgray();">
                <div class="randomdiv"><img id="random" style="height:34px;width: 80px;" alt="验证码" src="randomcode" onclick="$('#random').attr('src','randomcode?curr='+Math.random())"></div>
                <label><input type="checkbox" name="_spring_security_remember_me" id="_spring_security_remember_me" value="true">&nbsp;&nbsp;记住密码</label>
                <input type="hidden" name="ip" id="ip">
                <input type="submit" value="登录" class="loginbtn"  onclick="loginUser()">
            </form>
        </div>
    </div>
</div>
<div class="bottom ">
    <div class="bottomcontentright" style="bottom: 10px;position: fixed;font-size: 12px;color: #94929f;text-align: right;width: 100%;right: 20px;">本系统只支持IE9以上、谷歌和火狐浏览器</div>
</div>
</body>
</html>