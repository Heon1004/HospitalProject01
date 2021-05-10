<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/join.css">
<link rel="stylesheet" href="./css/style.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.zip2addr.js"></script>
<title>キムくん病院・マイページ画面</title>
<script>

</script>
</head>
<body class="home">
	<header>
	<h1><a href="index.jsp">キム病院</a></h1>
		<nav>
			<ul>
				<li><a href="index.jsp">ホーム</a></li>
				<li><a href="#">病院について</a></li>
				<li><a href="#">診察科・部門一覧</a></li>
				<li><a href="./view?display=reservationInfoform">予約</a></li>
				<li><a href="./view?display=checkReservationform">予約確認</a></li>
				<li><a href="#">交通のご案内</a></li>
			</ul>
			<div class="dropdown">
				<button class="drop-btn">Menu</button>
				<div class="dropdown-content">
					<c:if test="${empty user }">
					<a href="./view?display=loginform">ログリン</a>
					<a href="./view?display=joinform">会員登録</a>
					</c:if>
					<c:if test="${!empty user}">
					<a href="./view?display=logoutform">ログアウト</a>
					<a href="./view?display=myprofileform">マイページ</a>
					</c:if>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<ul>
			<li><a href="./view?display=myprofileUpdateform">会員情報変更</a></li>
			<li><a href="./view?display=checkReservationform">予約確認</a></li>
			<li><a href=""></a></li>
			<li><a href=""></a></li>
		</ul>
	</main>
	<footer>
		<ul>
			<li><p>キム病院</p></li>
			<li><a href="#">お知らせ</a></li>
		</ul>

	</footer>

</body>
</html>