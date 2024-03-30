<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Book shop - product</title>
            <style>

            .form {
                padding: 5px 30px;

                border: 1px solid #ddd;

                border-radius: 4px;
            }
            .shipping-form
            {

                padding-bottom: 30px;
                border: 1px solid #ddd;

                border-radius: 4px;
                position: relative;

            }

            .shipping-form h2 {
                padding-left: 5px;
                font-size: 1.5em;
                padding-bottom: 20px;
                margin-bottom: 10px;
            }

            .shipping-form label {
                padding-left:  60px;
                display: inline-block;

                font-weight: bold;

                margin-bottom: 5px;

                width: 120px;
            }
            label{
                width: 200px;
            }

            /* Style the input fields */
            .shipping-form input[type="text"] {
                width: 50%;

                padding: 8px;

                border: 1px solid #ddd;

                border-radius: 4px;
            }
            .inline-row {
                display: flex;
                align-items: center;
            }

            /* Adjust the width of the input fields to fit the layout */
            .inline-row input[type="text"] {
                width: 200px;
            }


            table {
                padding:  5px 20px;
                width: 100%;
                border-collapse: collapse;
                border-spacing: 0;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }
            th:first-child {
                width: 20px;
            }

            /*            tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }*/

            tr:hover {
                background-color: #ddd;
            }
            .item-img {
                width: auto;
                height: 100px;
            }
            .payment-method {
                padding: 50px;
                margin-top: 20px;
                position: relative; /* Đảm bảo các phần tử con được định vị tương đối đối với phần tử này */
            }

            .payment-method span {
                position: absolute; /* Đặt vị trí tuyệt đối cho thẻ span */
                top: 30px; /* Di chuyển thẻ span lên trên 30px */
                right: 80px; /* Đặt thẻ span ở phía bên phải của phần tử cha */
                font-size: 1.2em;
            }

            #order-button-container {
                position: relative; /* Đảm bảo nút đặt hàng sẽ được đặt theo vị trí tương đối với container */
                padding-bottom: 50px;
            }

            #order-button {
                width: 200px;
                height: 50px;
                margin-top: 30px;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                position: absolute; /* Đảm bảo nút đặt hàng sẽ được đặt theo vị trí tuyệt đối trong container */
                top: 0; /* Đặt nút ở phía trên cùng của container */
                right: 0; /* Đặt nút ở phía bên phải của container */
            }

            #order-button:hover {
                background-color: #0056b3; /* Màu nền của nút khi di chuột qua */
            }


        </style>
           <script>
            function submitOrder() {
                var recipientNameInput = document.getElementById("recipient-name");
                var phoneInput = document.getElementById("phone");
                var addressInput = document.getElementById("address");
                if (recipientNameInput.value.trim() === '') {
                    alert("Please fill in the recipient's name");
                    return;
                }
                const regex = /^(?:\+84|0)\d{9}$/
;
                if (phoneInput.value.trim() === '') {
                    alert("Please fill in the phone number");
                    if (regex.test(phoneInput.value.trim())) {
                        console.log("Valid phone number");
                    } else {
                        console.log("Invalid phone number");
                    }
                    return;
                }
                if (addressInput.value.trim() === '') {
                    alert("Please fill in the shipping address");
                    return;
                }
                var paymentMethod = document.getElementById("payment-method").value;
                var productIds = document.querySelectorAll('.productId');
                var selectedProducts = [];
                productIds.forEach(function (productIds) {
                    selectedProducts.push(productIds.value);
                });
                if (selectedProducts.length === 0)
                {
                    alert("There are no products to order, please return to the cart to select products");
                    return;
                }
                document.getElementById("selectedProducts").value = selectedProducts.join(',');
                document.getElementById("recipient-name-form").value = recipientNameInput.value.trim();
                document.getElementById("phone-form").value = phoneInput.value.trim();
                document.getElementById("address-form").value = addressInput.value.trim();
                document.getElementById("payment-method-form").value = paymentMethod;
                document.getElementById("order-form").submit();
            }
        </script>

          </head>
    <body>
        <div class="shipping-form">
            <h2>Shipping Information</h2>

            <div class="inline-row">
                <label for="recipient-name">Name:</label>
                <input type="text" id="recipient-name" name="recipient-name">
                <label for="phone" style="margin-left: 50px; ">Phone:</label>
                <input type="text" id="phone" name="phone">
            </div>
            <label for="address"style=" margin-top: 50px;">Address:</label>
            <input type="text" id="address" name="address">

        </div>
        <div >
              
            <h2>List of Product in cart</h2>
            <table>
                <tr>
                    <th>Number</th>
                    <th></th>
                    <th>Book name</th>
                    <th>Type</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
                <p:forEach items="${productDetails}" var="productDetail">
                    <tr>
                        <td style="display: none;"> 
                            <input type="hidden" id ="id_${productDetail.id}" class = "productId"value="${productDetail.id}">
                        </td>
                        <td >${productDetail.id}</td>
                        <th><image src ="${productDetail.img}" class ="item-img"/> </th>
                        <td>${productDetail.name}</td>
                        <td>${productDetail.type}</td>
                        <td>${productDetail.quantity}</td>
                        <td>${productDetail.price}</td>
                        <td>${productDetail.quantity * productDetail.price}</td>
                    </tr>
                </p:forEach>
            </table>
        </div>

        <div class="payment-method form">
            <label for="payment-method">Payment method:</label>
            <select id="payment-method" name="payment-method">
                <option value="cod">Cash on delivery</option>
                <option value="bank_transfer">Bank transfer</option>
            </select>
            <span>Total value: ${totalValue}</span>
            <div id="order-button-container">
                <form action="OrderServlet" method="post" id="order-form" >
                    <input type="hidden" id="formSubmitted" name="formSubmitted" value="${formSubmitted}">
                    <input type="hidden" id="selectedProducts" name="selectedProducts">
                    <input type="hidden" id="recipient-name-form" name="recipientName">
                    <input type="hidden" id="phone-form" name="phone">
                    <input type="hidden" id="address-form" name="address">
                    <input type="hidden" id="payment-method-form" name="paymentMethod">
                    <button id="order-button" type="button" onclick="submitOrder()">Place Order</button>
                </form>
            </div>
        </div>

          </body>
</html>
