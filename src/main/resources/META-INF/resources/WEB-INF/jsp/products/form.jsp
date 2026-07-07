<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" value="Product Form" />
<%@ include file="../layout.jspf" %>
<section class="panel form-panel">
    <h1>${product.id == null ? 'Add Product' : 'Edit Product'}</h1>
    <form:form method="post" action="/products" modelAttribute="product" cssClass="form-grid">
        <form:hidden path="id" />
        <label>SKU<form:input path="sku" /><form:errors path="sku" cssClass="field-error" /></label>
        <label>Name<form:input path="name" /><form:errors path="name" cssClass="field-error" /></label>
        <label>Quantity<form:input path="quantity" type="number" min="0" /><form:errors path="quantity" cssClass="field-error" /></label>
        <label>Reorder Level<form:input path="reorderLevel" type="number" min="0" /><form:errors path="reorderLevel" cssClass="field-error" /></label>
        <label>Unit Price<form:input path="unitPrice" type="number" step="0.01" min="0" /><form:errors path="unitPrice" cssClass="field-error" /></label>
        <label>Category
            <form:select path="category.id">
                <form:option value="" label="-- None --" />
                <form:options items="${categories}" itemValue="id" itemLabel="name" />
            </form:select>
        </label>
        <label>Supplier
            <form:select path="supplier.id">
                <form:option value="" label="-- None --" />
                <form:options items="${suppliers}" itemValue="id" itemLabel="name" />
            </form:select>
        </label>
        <label class="full">Description<form:textarea path="description" rows="4" /></label>
        <div class="full form-actions"><button type="submit">Save Product</button><a href="/products">Cancel</a></div>
    </form:form>
</section>
<%@ include file="../footer.jspf" %>
