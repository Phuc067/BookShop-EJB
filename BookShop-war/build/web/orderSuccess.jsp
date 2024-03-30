<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Success</title>

        <style>
            .image-container {
                text-align: center;
            }

            .image-container img {
                width: auto;
                max-height: 600px;
                display: inline-block;
            }
            #confirm-button-container {
                position: relative; /* Đảm bảo nút đặt hàng sẽ được đặt theo vị trí tương đối với container */
                padding-bottom: 50px;
            }

            #confirm-button {
                width: 200px;
                height: 50px;
                margin-top: 30px;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                position: absolute;
                top: 0;
                right: 0;/* Đảm bảo nút đặt hàng sẽ được đặt theo vị trí tuyệt đối trong container */
                display: none; /* Ẩn nút ban đầu */
            }
            #confirm-button:hover {
                background-color: #0056b3; /* Màu nền của nút khi di chuột qua */
            }
        </style>

        <script>
            function paymentConfirmation() {
                var orderId = document.getElementById("orderId").value;
                var total = document.getElementById("total").value;
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "ReceiptServlet", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 202) {
                            alert("Payment successful");
                        } else if (xhr.status === 400) {
                            alert("Did not receive order information");
                        } else if (xhr.status === 409)
                        {
                            alert("There is an invoice for this order");
                        }
                    }
                };
                xhr.send("orderId=" + orderId + "&total=" + total);
            }

            // Kiểm tra paymentMethod khi trang tải xong
            window.onload = function () {
                var paymentMethod = "${paymentMethod}"; // Lấy giá trị paymentMethod từ JSP
                if (paymentMethod === "bank_transfer") {
                    document.getElementById("confirm-button").style.display = "block"; // Hiển thị nút "Payment confirmation"
                }
            };
        </script>
    </head>
    <body>

        <a href="AllDataServlet">All data</a>
        <div id="confirm-button-container">
            <button id="confirm-button" type="button" onclick="paymentConfirmation()">Payment confirmation</button>
        </div>
        <h1>Order Information</h1>
        <input type="hidden" id="orderId" name="orderId" value="${orderId}">
        <input type="hidden" id="paymentMethod" name="paymentMethod" value="${paymentMethod}">
        <input type="hidden" id="total" name="total" value="${total}">
        <p>
            <% if ("cod".equals(request.getAttribute("paymentMethod"))) { %>
            Your order has been successfully placed. We will contact you for confirmation.
            <% } else if ("bank_transfer".equals(request.getAttribute("paymentMethod"))) {%>
            Please transfer the total amount of  <fmt:formatNumber value='<%= request.getAttribute("total")%>' type="currency" currencyCode="USD"/>
            to the following bank account:
        <div class="image-container">
            <img src="image\\payment.jpg"/>
        </div>
        <% }%>
    </p>

</body>
</html>
