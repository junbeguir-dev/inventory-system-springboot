<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" value="Categories" />
<%@ include file="layout.jspf" %>
<section class="panel two-column">
    <div>
        <h1>Categories</h1>
        <table><thead><tr><th>Name</th><th>Description</th></tr></thead><tbody>
        <c:forEach var="c" items="${categories}"><tr><td>${c.name}</td><td>${c.description}</td></tr></c:forEach>
        </tbody></table>
    </div>
    <div>
        <h2>Add Category</h2>
        <form:form method="post" modelAttribute="category" cssClass="stack-form">
            <label>Name<form:input path="name" /><form:errors path="name" cssClass="field-error" /></label>
            <label>Description<form:textarea path="description" rows="4" /></label>
            <button type="submit">Save</button>
        </form:form>
    </div>
</section>
<%@ include file="footer.jspf" %>
