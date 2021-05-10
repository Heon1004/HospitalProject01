<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/update.css">
<link rel="stylesheet" href="./css/style.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap" rel="stylesheet">
<title>キムくん病院・会員登録画面</title>
<script>
	function send(){
		if(confirm("変更しますか？") == true){
			document.action.submit();
		}else{
			return false; /*キャンセルできる*/
		}
	}
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
				<li><a href="./view?display=reservationform">予約</a></li>
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
		<form action="memberupdateAction" method="post" name="action">
		<h2>会員情報</h2>
			<dl>
				<dt>
					ID<span class="must">必須</span>
				</dt>
				<dd>
					<input type="text" name="userID" required placeholder="ID" value="${user.userID }"  readonly/>
				</dd>
				<dt>
					Password
				</dt>
				<dd>
					<input type="password" name="userPW" required placeholder="パスワード" value="${user.userPW }" />
				</dd>
				<dt>
					お名前
				</dt>
				<dd>
					<input type="text" name="userName" required placeholder="お名前" value="${user.userName }" />
				</dd>
				<dt>
					ふりがな
				</dt>
				<dd>
					<input type="text" name="hiragana" required placeholder="ふりがな" value="${user.hiragana }"/>
				</dd>
				<dt>Email</dt>
				<dd>
					<input type="email" name="userEmail" required placeholder="Email" value="${user.userEmail }"/>
				</dd>
				<dt>性別</dt>
				<dd>
					<c:if test="${user.gender eq 'M'}">
						<label><input type="radio" name="gender" value="M" checked/>男</label>
						<label><input type="radio" name="gender" value="F"/>女</label>
					</c:if>
					<c:if test="${user.gender eq 'F'}">
						<label><input type="radio" name="gender" value="M"/>男</label>
						<label><input type="radio" name="gender" value="F"checked/>女</label>
					</c:if>
				</dd>
				<dt>住所</dt>
				<dd>
					<input type="text" name="address" required placeholder="住所" value="${user.address }"/>
				</dd>
				<dt>
					電話番号
				</dt>
				<dd>
					<input type="number" name="userTel" required placeholder="電話番号" value="${user.userTel }"/>
				</dd>
				<dt>
					生年月日
				</dt>
				<dd>
					<input type="text" name="yy" maxlength="4" placeholder="年度" value="${user.dob }" readonly/>
				</dd>
			</dl>
			<p class="submit"><input type="button" value="会員情報変更" onclick="send()"/></p>
			
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