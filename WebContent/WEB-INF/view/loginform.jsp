<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/login.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap" rel="stylesheet">
<title>キムくん病院・ログリン画面</title>
</head>
<body class="home">
	<header>
	<h1><a href="main.jsp">キム病院</a></h1>
		<nav>
			<ul>
				<li><a href="main.jsp">ホーム</a></li>
				<li><a href="#">病院について</a></li>
				<li><a href="#">診察科・部門一覧</a></li>
				<li><a href="./view?display=CheckTimeReservationForm">予約</a></li>
				<c:if test="${!empty user }">
				<li><a href="./view?display=CheckMyReservationForm">予約確認</a></li>
				</c:if>
				<li><a href="#">交通のご案内</a></li>
			</ul>
			<div class="dropdown">
				<button class="drop-btn">Menu</button>
				<div class="dropdown-content">
					<c:if test="${empty user }">
					<a href="./view?display=loginForm">ログリン</a>
					<a href="./view?display=joinForm">会員登録</a>
					</c:if>
					<c:if test="${!empty user}">
					<a href="./view?display=logoutForm">ログアウト</a>
					<a href="./view?display=myprofileForm">マイページ</a>
					</c:if>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<form action="memberloginAction" method="post">
			<h2>ログイン</h2>
			<dl>
				<dt>ID</dt>
				<dd>
					<input class="login" type="text" name="userID" required placeholder="ID" />
				</dd>
				<dt>Password</dt>
				<dd>
					<input class="login" type="password" name="userPW" required placeholder="Password" />
				</dd>
			</dl>
			<p class="link">
				<a class="link-link" href="./view?display=joinform">会員登録</a>
				<a class="link-link" href="#">ID・PASSWORD忘れた場合</a>
			</p>
			<p class="submit"><input type="submit" value="ログイン"/></p>
		</form>
	</main>
	<footer>

	</footer>

</body>
</html>