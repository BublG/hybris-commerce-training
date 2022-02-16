<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="fontSize" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="numberOfQuestionsToShow" scope="request" type="java.lang.Integer"/>
<%--<jsp:useBean id="questions" scope="request" type="java.util.List"/>--%>
<div class="content">
    <p style="font-size: 24px">Questions and answers</p>
    <c:if test="${not empty questions}">
    <c:set var="numberOfQuestions" value="${numberOfQuestionsToShow < questions.size()
         ? numberOfQuestionsToShow : questions.size()}"/>
        <c:forEach var="i" begin="0" end="${numberOfQuestions - 1}">
            <p style="font-weight: bold">${questions[i].question}</p>
            <p>${questions[i].answer}</p>
        </c:forEach>
    </c:if>
</div>