function updated(){
		if(confirm("変更しますか？") == true){
			document.action.submit();
		}else{
			return false; /*キャンセルできる*/
		}
	}

function deleted(){
		if(confirm("キャンセルしますか？") == true){
			document.action.submit();
		}else{
			return false;
		}
	}

function goUpdate(number){
	if(confirm("変更しますか？") == true){
		$("input[name='reservationNumber]").val(number);
		document.action.submit();
	}else{
		return false; /*キャンセルできる*/
	}
}

function updateSubmit(){
	if(confirm("予約情報を変更しますか？") == true){
		document.getElementById("submit-btn").value = "1";
		document.action.submit();
	}else{
		return false;
	}
}
/** 
 input hiddenにし、valueを${list.number or reservation[st.index].number}にしてもvalueがパラメータにはいらなかった
 それで、submitやtextなど値を確認した結果正常だった。提案はforeachの中の値は全部正常だからsubmitを押したら
 **/
function getNumber(reservationNumber){ 
	document.getElementById("number").value = reservationNumber;
	document.action.submit();
	
}

function setChangeTime(){ 
	document.getElementById("changeTime").value = "1";
	
}

function getPage(num){ 
	document.getElementById("pageNum").value = num;
}


