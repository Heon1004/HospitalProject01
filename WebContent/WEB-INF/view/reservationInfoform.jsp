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
	<div class="reserve">
		<form action="reservationInfoAction" method="POST" class="serachform">
			<input type='date' name="date" id='currentDate'min="currentDate"/>
		<script>
			document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);;
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
		<ul>
			<li><p>キム病院</p></li>
			<li><a href="#">お知らせ</a></li>
		</ul>

	</footer>

</body>
</html>