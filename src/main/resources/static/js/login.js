$(document).ready(function() {

  // 用于处理登录和注册表单的显示切换
  function handleButtonClick(isRegister) {
    if (isRegister) {
      // 激活注册表单，移动表单并改变背景色
      $('.veen .wrapper').addClass('move');
      $('.body').css('background', '#ffa9ca');
      $(".veen .login-btn button").removeClass('active');
      $(".veen .rgstr-btn button").addClass('active');
    } else {
      // 恢复到登录表单状态
      $('.veen .wrapper').removeClass('move');
      $('.body').css('background', '#b49dff');
      $(".veen .rgstr-btn button").removeClass('active');
      $(".veen .login-btn button").addClass('active');
    }
  }

  // 处理点击注册按钮时的逻辑
  $(".veen .rgstr-btn button").click(function() {
    handleButtonClick(true);
  });

  // 处理点击登录按钮时的逻辑
  $(".veen .login-btn button").click(function() {
    handleButtonClick(false);
  });

  // 处理登录表单提交事件
  $("#login").submit(function(event) {
    event.preventDefault();  // 阻止默认提交，防止页面刷新

    var email = $("#login input[type='mail']").val();
    var password = $("#login input[type='password']").val();

    // 做一些基本的输入验证
    if (email && password) {
      var loginData = {
        email: email,
        password: password
      };

      // 发送 AJAX 请求到后端进行登录
      $.ajax({
        url: '/auth/login',  // 后端登录接口
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginData),
        success: function(response) {
          // 如果登录成功，处理响应（例如跳转到首页）
          alert("登录成功");
          window.location.href = '/home';  // 假设登录成功后跳转到首页
        },
        error: function(xhr, status, error) {
          // 如果登录失败，显示错误信息
          var errorMessage = xhr.responseJSON ? xhr.responseJSON.message : "登录失败";
          alert("登录失败: " + errorMessage);
        }
      });
    } else {
      alert("请输入邮箱和密码");
    }
  });

  // 处理注册表单提交事件
  $("#register").submit(function(event) {
    event.preventDefault();  // 阻止默认提交，防止页面刷新

    var fullName = $("#register input[name='name']").val();
    var email = $("#register input[type='mail']").val();
    var username = $("#register input[name='uid']").val();
    var password = $("#register input[type='password']").val();

    // 做一些基本的输入验证
    if (fullName && email && username && password) {
      // 客户端邮箱格式验证
      var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
      if (!emailRegex.test(email)) {
        alert("请输入有效的邮箱地址");
        return;
      }

      var registerData = {
        fullName: fullName,
        email: email,
        username: username,
        password: password
      };

      // 发送 AJAX 请求到后端进行注册
      $.ajax({
        url: '/auth/register',  // 后端注册接口
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(registerData),
        success: function(response) {
          // 如果注册成功，处理响应（例如跳转到登录页面）
          alert("注册成功，请登录");
          $(".veen .wrapper").removeClass('move');  // 切换回登录表单
          $(".veen .login-btn button").addClass('active');
          $(".veen .rgstr-btn button").removeClass('active');
        },
        error: function(xhr, status, error) {
          // 如果注册失败，显示错误信息
          var errorMessage = xhr.responseJSON ? xhr.responseJSON.message : "注册失败";
          alert("注册失败: " + errorMessage);
        }
      });
    } else {
      alert("请输入完整的注册信息");
    }
  });

});
