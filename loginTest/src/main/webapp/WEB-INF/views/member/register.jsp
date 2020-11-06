<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 취소
		$(".cancel").on("click", function(){
			location.href = "/";	    
		})
	
		$("#submit").on("click", function(){
			if($("#userId").val()==""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			if($("#pwd").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#pwd").focus();
				return false;
			}
			if($("#userName").val()==""){
				alert("성명을 입력해주세요.");
				$("#userName").focus();
				return false;
			}
		});
	})
</script>
<body>
	<form action="member/register" method="post">
		아이디<input type="text" id="userId" name="userId"><br>
		패스워드<input type="password" id="pwd" name="pwd"><br>
		이름<input type="text" id="userName" name="userName"><br>

		<button type="submit" id="submit">회원가입</button>
		<button class="cancel" type="button">취소</button>
	</form>
</body>
</html>