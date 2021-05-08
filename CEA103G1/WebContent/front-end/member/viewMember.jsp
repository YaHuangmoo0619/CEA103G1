<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>

<jsp:include flush="true" page="../header.jsp"></jsp:include>


<div id="main">

	<!-- Thread Start -->
	<div class="t" style="margin-bottom:0px; border-bottom:0">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<th class="h">
					<strong class="fl w">�˵��ϥΪ̸��</strong> &nbsp;
					<span style="color: red; font-weight: bold; ">${ message }</span>
				</th>
			</tr>
		</table>
	</div>

	<html:form action="/person">
		<html:hidden property="action" value="add" />
		<div class="t t2">
			<table cellspacing="0" cellpadding="0" width="100%"
				style="table-layout:fixed;border-top:0">


				<tr class="tr3">
					<td style="width: 120px; ">
						�b��:
					</td>
					<td>
						${ person.account }
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�m�W:
					</td>
					<td>
						${ person.name } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�ʧO:
					</td>
					<td>
						${ person.sex } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�q�l�l��:
					</td>
					<td>
						${ person.email } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�ͤ�:
					</td>
					<td>
						${ person.birthday } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						���U�ɶ�:
					</td>
					<td>
						${ person.dateCreated } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�W���n�J�ɶ�:
					</td>
					<td>
						${ person.dateLastActived } &nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td>
						�t�d������:
					</td>
					<td>
						<c:forEach items="${ person.boardsAdministrated }" var="board">
							<a
								href="<html:rewrite action="/board?action=list" />&board.id=${ board.id }">${
								board.name }</a>
						</c:forEach>
						&nbsp;
					</td>
				</tr>
			</table>
		</div>
	</html:form>


	<jsp:include flush="true" page="../footer.jsp" />