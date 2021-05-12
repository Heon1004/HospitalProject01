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
<title>キムくん病院・会員登録画面</title>
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
	
		<form action="memberjoinAction" method="post">
		<h2>会員登録</h2>
			<dl>
				<dt>
					ID<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="userID" required placeholder="ID" />
				</dd>
				<dt>
					Password<span class="must">必須</span>
				</dt>
				<dd>
					<input type="password" name="userPW" required placeholder="Password" />
				</dd>
				<dt>
					お名前<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="userName" required placeholder="お名前" />
				</dd>
				<dt>
					ふりがな<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="hiragana" required placeholder="ふりがな" />
				</dd>
				<dt>Email</dt>
				<dd>
					<input type="email" name="userEmail" required placeholder="Email" />
				</dd>
				<dt>性別</dt>
				<dd>
					<label><input type="radio" name="gender" value="M" checked/>男</label>
					<label><input type="radio" name="gender" value="F"/>女</label>
				</dd>
				<dt>住所</dt>
				<dd>
					<input type="text" name="address" required placeholder="住所" />
				</dd>
				<dt>
					電話番号<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="userTel" required placeholder="電話番号" 
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
				</dd>
				<dt>
					生年月日<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="yy" maxlength="4" placeholder="年度"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
					<select name="mm">
						<option>月</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select> 
					<input type="text" name="dd"maxlength="2" placeholder="日"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
				</dd>
			</dl>
			<p class="submit"><input type="submit" value="会員登録"/></p>
		</form>
	</main>
	<footer>
		<ul>
			<li><p>キム病院</p></li>
			<li><a href="#">お知らせ</a></li>
		</ul>

	</footer>

</body>
</html>