<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/checkReservation.css">
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
	<h2>${user.userName }様 予約状況</h2>
	<div style="font-size:0;text-align:center; margin: 20px;">
		<form action="getMyReservationListAction" method="POST">
		<input type="hidden" name="userID" value="${user.userID }"/>
			<select name="available">
				<c:choose>
					<c:when test="${available eq 0}">
						<option value="0" selected>診察済み</option>
						<option value="1" >予約中</option>
						<option value="2">全体</option>
					</c:when>
					<c:when test="${available eq 1}">
						<option value="0" >診察済み</option>
						<option value="1" selected>予約中</option>
						<option value="2">全体</option>
					</c:when>
					<c:otherwise>
						<option value="0" >診察済み</option>
						<option value="1" >予約中</option>
						<option value="2" selected>全体</option>
					</c:otherwise>
				</c:choose>
			</select>
			<input type="submit" value="検索"/>
		</form>
	</div>
		<form action=pushUpdateButtonAction method="POST">
			<table border="1">
				<thead>
					<tr>
						<th>予約番号</th>
						<th>診察科</th>
						<th>予約時間</th>
						<th>予約状態</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${reservationList }"  varStatus="st">
						<tr>
							<td>${reservationList[st.index].number}</td>
							<c:choose>
								<c:when test="${list.medicineCode eq 1}">
									<td>外科</td>
								</c:when>
								<c:when test="${list.medicineCode eq 2}">
									<td>内科</td>
								</c:when>
								<c:when test="${list.medicineCode eq 3}">
									<td>整形外科</td>
								</c:when>
								<c:when test="${list.medicineCode eq 4}">
									<td>眼科</td>
								</c:when>
								<c:otherwise>
									<td>相談</td>
								</c:otherwise>
							</c:choose>
							<td>${list.date }</td>
							<c:choose>
								<c:when test="${list.available eq 1}">
									<td><strong>予約中</strong></td>
									<td><input type="submit" value="修正" onclick="getNumber(${list.number})"/></td>
								</c:when>
								<c:otherwise>
									<td><strong>診察済み</strong></td>
								</c:otherwise>
							</c:choose>
							
						</tr> 
						<input type="hidden" name="number" id="number"/>
					</c:forEach>
				</tbody>
			</table>
			<div class="low-btn">
				<input type="button" value="キャンセル" onclick="deleted()"/>
			</div>
			</form>
	</main>
	<footer>
		<ul>
			<li><p>キム病院</p></li>
			<li><a href="#">お知らせ</a></li>
		</ul>
	</footer>
<script type="text/javascript" src="./js/common.js" ></script> 
</body>
</html>