console.log("this is script file");

const toggleSidebar = () => {
	
	if($(".sidebar").is(":visible")){
		//true
		//band karna hai
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}else{
		//false
		//show karna hai
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
};

$(document).ready(
		 function(){
			 
			 $("#viewExpense").submit(function(event){
				 
				 event.preventDefault();
				 ajaxPost();
			 });
			 
			 function ajaxPost(){
				 
				 //prepare Form data
				 
				 var formData={
						 startDate : $("#startDate").val(),
						 endDate : $("#endDate").val()
				 }
				 
				 
				 //Do Post
				 var tableData="";
				 $.ajax({
					 type : "POST",
					 contentType : "application/json",
					 url : "/admin/viewExpenses",
					 data : JSON.stringify(formData),
					 dataType : 'json',
					 success : function(result){
						 console.log(result);
						 
						 result.forEach(function(item){
							tableData += '<tr>' +
							   '<td>' + item.openingBalance + '</td>' +
							   '<td>' + item.onlinePayment + '</td>' +
							   '<td>' + item.cashPayment + '</td>' +
							   '<td>' + item.rdOne + '</td>' +
							   '<td>' + item.rdTwo + '</td>' +
							   '<td>' + item.rdThree + '</td>' +
							   '<td>' + item.rdFour + '</td>' +
							   '<td>' + item.receive + '</td>' +
							   '<td>' + item.credit + '</td>' +
							   '<td>' + item.saleReturn + '</td>' +
							   '<td>' + item.tea + '</td>' +
							   '<td>' + item.remark + '</td>' +
							   '<td>' + item.expenseDate + '</td>' +
							   
							   
							   
							   '</tr>';
							   
						 });
						 
						 $("#user-table>tbody").html(tableData);
					 },
					 error :function(result){
						 
						 console.log(result);
					 }
				 });
				 
				 
			 }
			 
		 }
);