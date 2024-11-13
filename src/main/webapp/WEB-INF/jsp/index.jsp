<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" >
<head>
<meta charset="UTF-8">
<title>主页</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}css/index.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}css/photo.css"/>

</head>
<body>

<nav>
  <div class="menubar">
    <a href="#0" class="home">室友选择</a>
    <div class="icons">
      <div class="secondary-icons">
        <i class="icon-second"><img src="${pageContext.request.contextPath}svg/icon-171.svg"></i>
        <i class="icon-second"><img src="${pageContext.request.contextPath}svg/icon-198.svg"></i>
      <i class="icon-second"><img src="${pageContext.request.contextPath}svg/icon-178.svg"></i>
      </div>
      
      <i class="icon-menu"><span></span></i>
    </div>
  </div>
  <ul class="menu">
     
    <li class="menu-link"><a href="#0" class="text-item">About</a></li>
    <li class="menu-link sub">
      <a href="#0" class="text-item">Clients<span class="icon"></span></a>
      <ul class="submenu">
        <li class="sub-item"><a href="#0">Burger King</a></li>
        <li class="sub-item"><a href="#0">Southwest Airlines</a></li>
        <li class="sub-item"><a href="#0">Levi Strauss</a></li>
      </ul>
    </li>
    <li class="menu-link sub">
      <a href="#0" class="text-item">Services<span class="icon"></span></a>
      <ul class="submenu">
        <li class="sub-item"><a href="#0">Print Design</a></li>
        <li class="sub-item"><a href="#0">Web Design</a></li>
        <li class="sub-item"><a href="#0">Mobile App Development</a></li>
      </ul>
    </li>
    <li class="menu-link"><a href="#0" class="text-item">Contact</a></li>
  </ul>
</nav>

<article>
<div class="gallery">
  <div class="gallery__strip__wrapper">
    <div class="gallery__strip one">
      <div class="photo">
        <div class="photo__image"><img src="img/gg-kyoto.jpg"></div>
        <div class="photo__name">Kyoto</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-austria.jpg"></div>
        <div class="photo__name">Halstatt</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-peru.jpg"></div>
        <div class="photo__name">Peru</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-rio.jpg"></div>
        <div class="photo__name">Rio</div>
      </div>
    </div>
  </div>
  <div class="gallery__strip__wrapper">
    <div class="gallery__strip two">
      <div class="photo">
        <div class="photo__image"><img src="img/gg-italy.jpg"></div>
        <div class="photo__name">Italy</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-osaka.jpg"></div>
        <div class="photo__name">Osaka</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-bali.jpg"></div>
        <div class="photo__name">Bali</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-paris2.jpg"></div>
        <div class="photo__name">Paris</div>
      </div>
    </div>
  </div>
  <div class="gallery__strip__wrapper">
    <div class="gallery__strip three">
      <div class="photo">
        <div class="photo__image"><img src="img/gg-cusco.jpg"></div>
        <div class="photo__name">Cusco</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-rome.jpg"></div>
        <div class="photo__name">Rome</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-paris.jpg"></div>
        <div class="photo__name">Paris</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-bali2.jpg"></div>
        <div class="photo__name">Bali</div>
      </div>
    </div>
  </div>
  <div class="gallery__strip__wrapper">
    <div class="gallery__strip four">
      <div class="photo">
        <div class="photo__image"><img src="img/gg-milan.jpg"></div>
        <div class="photo__name">Milan</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-budapest.jpg"></div>
        <div class="photo__name">Budapest</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-vienna.jpg"></div>
        <div class="photo__name">Vienna</div>
      </div>
      <div class="photo">
        <div class="photo__image"><img src="img/gg-mexico.jpg"></div>
        <div class="photo__name">Mexico</div>
      </div>
    </div>
  </div>
</div>


</article>

<script  src="${pageContext.request.contextPath}js/index.js"></script>

</body>
</html>