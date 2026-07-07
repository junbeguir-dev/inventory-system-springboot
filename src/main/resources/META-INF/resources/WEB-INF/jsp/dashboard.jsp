<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="Dashboard" />
<%@ include file="layout.jspf" %>
<section class="hero">
    <h1>Inventory Dashboard</h1>
    <p>Track stock levels, suppliers, categories, and inventory value.</p>
</section>
<section class="cards">
    <div class="card"><span>Total Products</span><strong>${productCount}</strong></div>
    <div class="card"><span>Total Quantity</span><strong>${totalQuantity}</strong></div>
    <div class="card"><span>Total Value</span><strong>₱ <fmt:formatNumber value="${totalValue}" type="number" minFractionDigits="2" /></strong></div>
    <div class="card warning"><span>Low Stock Items</span><strong>${lowStockProducts.size()}</strong></div>
</section>
<section class="panel">
    <div class="panel-header"><h2>Low Stock Products</h2><a class="button" href="/products/new">Add Product</a></div>
    <table>
        <thead><tr><th>SKU</th><th>Name</th><th>Qty</th><th>Reorder Level</th><th>Action</th></tr></thead>
        <tbody>
        <c:forEach var="p" items="${lowStockProducts}">
            <tr>
                <td>${p.sku}</td><td>${p.name}</td><td><span class="badge danger">${p.quantity}</span></td><td>${p.reorderLevel}</td>
                <td><a href="/products/${p.id}/edit">Edit</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty lowStockProducts}"><tr><td colspan="5" class="empty">No low stock products.</td></tr></c:if>
        </tbody>
    </table>
</section>
<%@ include file="footer.jspf" %>
