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
<title>キムくん病院・予約画面</title>
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
	<div class="reserve">
	<form action="checkTimeReservationAction" method="POST" class="serachform">
		<input type='date' name="date" id='currentDate' value="${fulldate }" min="today"/>
		<script>
			let [today] = new Date().toISOString().split("T");
			document.querySelector("input").setAttribute("min", today);
		</script>
		<select name="medicine">
			<option disabled>診察科</option>
			<c:choose>
				<c:when test="${medicine eq 0 }">
					<option value="0" selected>相談</option>
					<option value="1" >外科</option>
					<option value="2" >内科</option>
					<option value="3" >整形外科</option>
					<option value="4" >眼科</option>
				</c:when>
				<c:when test="${medicine eq 1 }">
					<option value="0" >相談</option>
					<option value="1" selected>外科</option>
					<option value="2" >内科</option>
					<option value="3" >整形外科</option>
					<option value="4" >眼科</option>
				</c:when>
				<c:when test="${medicine eq 2 }">
				<option value="0" >相談</option>
					<option value="1" >外科</option>
					<option value="2" selected>内科</option>
					<option value="3" >整形外科</option>
					<option value="4" >眼科</option>
				</c:when>
				<c:when test="${medicine eq 3 }">
					<option value="0" >相談</option>
					<option value="1" >外科</option>
					<option value="2" >内科</option>
					<option value="3" selected>整形外科</option>
					<option value="4" >眼科</option>
				</c:when>
				<c:otherwise>
					<option value="0" >相談</option>
					<option value="1" >外科</option>
					<option value="2" >内科</option>
					<option value="3" >整形外科</option>
					<option value="4" selected>眼科</option>
				</c:otherwise>
			</c:choose>
		</select>
		<input type="submit" value="検索"/>
	</form>
		<form action="reservationAction" method="POST">
		<input type="hidden" name="medicine" value="${medicine }" />
		<input type="hidden" name="fulldate" value="${fulldate }" />
			<table>
			<caption>予約状況</caption>
				<thead>
					<tr >
						<th>日付</th>
						<th colspan="2">09</th>
						<th colspan="2">10</th>
						<th colspan="2">11</th>
						<th colspan="2">12</th>
						<th colspan="2">13</th>
						<th colspan="2">14</th>
						<th colspan="2">15</th>
						<th colspan="2">16</th>
						<th colspan="2">17</th>
						<th colspan="2">18</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<th rowspan="2">${date} </th>
					<c:forEach var="n" items="${time }" >
							<c:if test="${empty n}">
								<th>可</th> <!-- 文字のせいで一旦"th"使用 -->
							</c:if>
							<c:if test="${!empty n }">
								<th>X</th>
							</c:if>
					</c:forEach>
					</tr>
					<tr>
					<c:forEach var="n" items="${time }" varStatus="st">
							<c:if test="${empty n}">
								<th><input type="radio" name="checked" value="${st.index }" /></th>
							</c:if>
							<c:if test="${!empty n}">
								<th>X</th>
							</c:if>
					</c:forEach>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="予約する" />
		</form>
	</div>
	</main>
	<footer>
	</footer>
<script type="text/javascript" src="./js/common.js" ></script> 
</body>
</html>