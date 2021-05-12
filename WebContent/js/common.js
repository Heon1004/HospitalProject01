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
/** 
 input hiddenにし、valueを${list.number or reservation[st.index].number}にしてもvalueがパラメータにはいらなかった
 それで、submitやtextなど値を確認した結果正常だった。提案はforeachの中の値は全部正常だからsubmitを押したら
 
  
 **/
function getNumber(reservationNumber){ 
	document.getElementById("number").value = reservationNumber;
	document.action.submit();
	
}
function getDate(){
	document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);
	/*下は過去の日付が選択できなくさせる*/
	let [today] = new Date().toISOString().split("T"); /*エラー発生しても上手く動いてる*/
	document.querySelector("input").setAttribute("min", today);
}