<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%-- 자바 코드 넣기 --%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");
    // request, response는 자동으로 사용 가능하다. - jsp 문법 상 지원해줌
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age")); // getParameter은 항상 string만 입력받기 때문에 숫자로 변환시켜주어야한다.

    // 멤버 생성자에 이름, 나이를 입력한 후
    Member member = new Member(username, age);
    // 생성된 멤버를 레퍼지토리에 저장한다.
    memberRepository.save(member);
%>

<%-- html 작성 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
 성공
 <ul>
     <li>id=<%=member.getId()%></li>
     <li>username=<%=member.getUsername()%></li>
     <li>age=<%=member.getAge()%></li>
 </ul>
<a href="/index.html">메인화면</a>
</body>
</html>
