<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/reservation.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap" rel="stylesheet">
<title>予約情報</title>
</head>
<body>
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
	<div class="reserve">
		<form action="checkTimeReservationAction" method="POST" class="serachform">
			<input type='date' name="date" id='currentDate' min="currentDate"/>
			<script>
				document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);
				/*下は過去の日付が選択できなくさせる*/
				let [today] = new Date().toISOString().split("T"); /*エラー発生しても上手く動いてる*/
				document.querySelector("input").setAttribute("min", today);
			</script>
			<select name="medicine">
				<option disabled>診察科</option>
				<option selected value="0">相談</option>
				<option value="1">外科</option>
				<option value="2">内科</option>
				<option value="3">整形外科</option>
				<option value="4">眼科</option>
			</select>
			<input type="submit" value="検索"/> <!-- 後で、条件文でdisabled書いたり消したりして、送信判断するように -->
		</form>
	</div>
	</main>
	<footer>
	</footer>
<script type="text/javascript" src="./js/common.js" ></script> 
</body>
</html>