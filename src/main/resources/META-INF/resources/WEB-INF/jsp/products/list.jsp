<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="Products" />
<%@ include file="../layout.jspf" %>
<section class="panel">
    <div class="panel-header">
        <h1>Products</h1>
        <a class="button" href="/products/new">Add Product</a>
    </div>
    <form class="search" method="get" action="/products">
        <input type="text" name="keyword" value="${keyword}" placeholder="Search by SKU, name, or description">
        <button type="submit">Search</button>
        <a href="/products">Clear</a>
    </form>
    <table>
        <thead><tr><th>SKU</th><th>Name</th><th>Category</th><th>Supplier</th><th>Qty</th><th>Price</th><th>Value</th><th>Stock</th><th>Actions</th></tr></thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.sku}</td>
                <td>${p.name}</td>
                <td>${p.category != null ? p.category.name : '-'}</td>
                <td>${p.supplier != null ? p.supplier.name : '-'}</td>
                <td>${p.quantity}</td>
                <td>₱ <fmt:formatNumber value="${p.unitPrice}" minFractionDigits="2" /></td>
                <td>₱ <fmt:formatNumber value="${p.inventoryValue}" minFractionDigits="2" /></td>
                <td><c:choose><c:when test="${p.lowStock}"><span class="badge danger">Low</span></c:when><c:otherwise><span class="badge ok">OK</span></c:otherwise></c:choose></td>
                <td class="actions">
                    <a href="/products/${p.id}/edit">Edit</a>
                    <form method="post" action="/products/${p.id}/stock"><input type="hidden" name="change" value="1"><button type="submit">+1</button></form>
                    <form method="post" action="/products/${p.id}/stock"><input type="hidden" name="change" value="-1"><button type="submit">-1</button></form>
                    <form method="post" action="/products/${p.id}/delete" onsubmit="return confirmDelete('Delete this product?')"><button class="link-danger" type="submit">Delete</button></form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty products}"><tr><td colspan="9" class="empty">No products found.</td></tr></c:if>
        </tbody>
    </table>
</section>
<%@ include file="../footer.jspf" %>
