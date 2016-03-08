<!DOCTYPE html>
<html lang="cn" class="app">
<meta content-type="text/html" charset="UTF-8">
<head>
	<style>
		html,
		body {
			margin: 0;
			padding: 0;
			height: 100%;
		}
	</style>
	<script src="${basePath}/web/js/jquery.js"></script>
</head>
<body>

URL:<input id="url" type="text" style="width: 500px;">
TYPE:<select id="type">
	<option value="GET">GET</option>
	<option value="POST">POST</option>
	<option value="PUT">PUT</option>
	<option value="DELETE">DELETE</option>
</select>
SEND:<input id="send" type="button" value="SEND"/>
<br/>
<style>
	#wrapper {
		position: relative;
		height: 90%;
	}
	#wrapper #json_text {
		position: absolute;
		top: 0;
		left: 0;
		width: 49%;
		height: 100%;
	}
	#wrapper #result_value {
		width: 49%;
		height: 100%;
		margin-left: 50%;
	}
</style>
<div id="wrapper">
	<textarea id="json_text"></textarea>
	<textarea id="result_value"></textarea>
</div>

</body>

<script>
	$("#send").on("click",function(){

		$("#result_value").val("");

		var type = $("#type").val();

		var jsonData = {};
		var jsonStr = $("#json_text").val();

		if(null != jsonStr && ""!=jsonStr){
			jsonData = eval("("+$("#json_text").val().toString()+")");
		}

		if("GET" == type){
			$.ajax({
				type: type,
				url: "${basePath}" + $("#url").val() ,
				data: jsonData,
				dataType: 'json',
				success : function(data) {
					$("#result_value").val(JSON.stringify(data));
					console.log(data);
				},error: function (data) {
					try{
						$("#result_value").val(JSON.stringify(data));
						console.log(data);
					}catch(e){
					}
				}
			})
		}else{
			$.ajax({
				type: type,
				url: "${basePath}" + $("#url").val() ,
				data: JSON.stringify(jsonData),
				dataType: 'json',
				contentType: 'application/json',
				success : function(data) {
					$("#result_value").val(JSON.stringify(data));
					console.log(data);
				},error: function (data) {
					try{
						$("#result_value").val(JSON.stringify(data));
						console.log(data);
					}catch(e){
					}
				}
			});

		}
	});

	/*$(document).ready(function(){
		var jsonData = {};
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/assess_template/",
			data: JSON.stringify(jsonData),
			dataType: 'json',
			contentType: 'application/json',
			success : function(data) {
				console.log(obj2string(data));
			}
		})
	});*/
</script>

</html>