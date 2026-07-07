<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" value="Suppliers" />
<%@ include file="layout.jspf" %>
<section class="panel two-column">
    <div>
        <h1>Suppliers</h1>
        <table><thead><tr><th>Name</th><th>Email</th><th>Phone</th></tr></thead><tbody>
        <c:forEach var="s" items="${suppliers}"><tr><td>${s.name}</td><td>${s.email}</td><td>${s.phone}</td></tr></c:forEach>
        </tbody></table>
    </div>
    <div>
        <h2>Add Supplier</h2>
        <form:form method="post" modelAttribute="supplier" cssClass="stack-form">
            <label>Name<form:input path="name" /><form:errors path="name" cssClass="field-error" /></label>
            <label>Email<form:input path="email" /><form:errors path="email" cssClass="field-error" /></label>
            <label>Phone<form:input path="phone" /></label>
            <label>Address<form:textarea path="address" rows="4" /></label>
            <button type="submit">Save</button>
        </form:form>
    </div>
</section>
<%@ include file="footer.jspf" %>
