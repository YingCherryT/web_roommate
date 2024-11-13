<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zn">
<head>
  <meta charset="UTF-8">
  <title>登录</title>
  <link rel="stylesheet" href="/css/login.css">

</head>
<body>
<!-- partial:index.partial.html -->
<script src="/js/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="/css/bootstrap.min.css" >
<div class="body">
		<div class="veen">
			<div class="login-btn splits">
				<p>已经有账户?</p>
				<button class="active">登录</button>
			</div>
			<div class="rgstr-btn splits">
				<p>没有账户?</p>
				<button>注册</button>
			</div>
			<div class="wrapper">
				<form id="login" tabindex="500">
					<h3>登录</h3>
					<div class="mail">
						<input type="mail" name="">
						<label>邮箱/用户名</label>
					</div>
					<div class="passwd">
						<input type="password" name="">
						<label>密码</label>
					</div>
					<div class="submit">
						<button class="dark">登录</button>
					</div>
				</form>
				<form id="register" tabindex="502">
					<h3>注册</h3>
					<div class="name">
						<input type="text" name="">
						<label>姓名</label>
					</div>
					<div class="mail">
						<input type="mail" name="">
						<label>邮箱</label>
					</div>
					<div class="uid">
						<input type="text" name="">
						<label>用户名</label>
					</div>
					<div class="passwd">
						<input type="password" name="">
						<label>密码</label>
					</div>
					<div class="submit">
						<button class="dark">注册</button>
					</div>
				</form>
			</div>
		</div>	
	</div>


	<style type="text/css">
		.site-link{
      padding: 5px 15px;
			position: fixed;
			z-index: 99999;
			background: #fff;
			box-shadow: 0 0 4px rgba(0,0,0,.14), 0 4px 8px rgba(0,0,0,.28);
			right: 30px;
			bottom: 30px;
			border-radius: 10px;
		}
		.site-link img{
			width: 30px;
			height: 30px;
		}
	</style>
<!-- partial -->
  <script src="/js/login.js"></script>

</body>
</html>
