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
		<h2>予約変更</h2>
		<form action="pushChangeTimeAction" method="POST">
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
					<tr>
						<td>${reservation.number}</td>
						<td>
							<select name="medicineCode">
								<c:choose>
									<c:when test="${reservation.medicineCode eq 0}">
										<option value="0" selected>相談</option>
										<option value="1" >外科</option>
										<option value="2" >内科</option>
										<option value="3" >整形外科</option>
										<option value="4" >眼科</option>
									</c:when>
									<c:when test="${reservation.medicineCode eq 1}">
										<option value="0" >相談</option>
										<option value="1" selected>外科</option>
										<option value="2" >内科</option>
										<option value="3" >整形外科</option>
										<option value="4" >眼科</option>
									</c:when>
									<c:when test="${reservation.medicineCode eq 2}">
										<option value="0" >相談</option>
										<option value="1" >外科</option>
										<option value="2" selected>内科</option>
										<option value="3" >整形外科</option>
										<option value="4" >眼科</option>
									</c:when>
									<c:when test="${reservation.medicineCode eq 3}">
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
						</td>
						<td>
							${reservation.date } 
							<button name="changeTime" value="1">時間変更</button>
						</td>
						<c:choose>
							<c:when test="${reservation.available eq 1}">
								<td><strong>予約中</strong></td>
							</c:when>
							<c:otherwise>
								<td><strong>診察済み</strong></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
			<div class="low-btn">
				<input type="submit" value="変更" />
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