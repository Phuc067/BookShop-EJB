<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Book shop - product</title>
            <style>

            table {
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



            .quantity-button {
                background-color: #fff; /* Màu nền trắng */
                color: #000; /* Màu chữ đen */
                padding: 5px 10px; /* Độ dày và độ rộng của nút */
                border: none; /* Không có đường viền */
                border-radius: 3px; /* Bo tròn các góc */
                cursor: pointer; /* Con trỏ khi di chuột qua */
            }

            .quantity-button:hover {
                background-color: #ffc107; /* Màu vàng khi di chuột qua */
            }

            .quantity {
                padding: 0 5px; /* Khoảng cách giữa nút "-" và "+" với số lượng */
            }

            .danger-button {
                background-color: #dc3545; /* Màu đỏ */
                color: #fff; /* Màu chữ trắng */
                padding: 5px 10px; /* Độ dày và độ rộng của nút */
                border: none; /* Không có đường viền */
                border-radius: 3px; /* Bo tròn các góc */
                cursor: pointer; /* Con trỏ khi di chuột qua */
            }

            .danger-button:hover {
                background-color: #c82333; /* Màu đỏ khi di chuột qua */
            }


            .checkbox-large {
                transform: scale(1.5); /* Làm cho checkbox lớn hơn 1.5 lần */
            }
            #check-out-button-container {
                position: fixed; /* Cố định vị trí của div */
                top: 30px; /* Khoảng cách từ dưới lên là 20px */
                right: 20px; /* Khoảng cách từ phải sang là 20px */

            }

            #check-out-button {
                /* Định dạng nút đặt hàng */
                width: 200px;
                height: 50px;
                padding: 10px 20px; /* Độ dày và độ rộng của nút */
                background-color: #007bff; /* Màu nền của nút */
                color: #fff; /* Màu chữ của nút */
                border: none; /* Không có đường viền */
                border-radius: 5px; /* Bo tròn các góc của nút */
                cursor: pointer; /* Biểu tượng con trỏ khi di chuột qua nút */
            }

            #check-out-button:hover {
                background-color: #0056b3; /* Màu nền của nút khi di chuột qua */
            }

            .remove-book {
                width: 16px;
                height: auto;
                border-radius: 4px;
            }
        </style>
            <script>
            function increaseQuantity(id) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "IncreaseQuantityServlet", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 202) {
                            var currentQuantity = parseInt(document.getElementById("quantity_" + id).innerText);
                            var newQuantity = currentQuantity + 1;
                            document.getElementById("quantity_" + id).innerText = newQuantity;
                            var pricePerItem = parseFloat(document.getElementById("price_" + id).innerText);
                            var totalPrice = newQuantity * pricePerItem;
                            var formatter = new Intl.NumberFormat('en-US', {
                                style: 'currency',
                                currency: 'USD',
                                maximumFractionDigits: 1,
                            });
                            document.getElementById("total_" + id).innerText = formatter.format(totalPrice);
                        } else if (xhr.status === 400) {
                            alert("The number of products you selected exceeds the total product quantity");
                        }
                    }
                };
                xhr.send("id=" + id);
            }

            function decreaseQuantity(id) {
                var currentQuantity = parseInt(document.getElementById("quantity_" + id).innerText);
                if (currentQuantity == 1) {
                    removeBookFromCart(id);
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "DecreaseQuantityServlet", true);
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 202) {
                                var newQuantity = currentQuantity - 1;
                                document.getElementById("quantity_" + id).innerText = newQuantity;
                                var pricePerItem = parseFloat(document.getElementById("price_" + id).innerText);
                                var totalPrice = newQuantity * pricePerItem;
                                var formatter = new Intl.NumberFormat('en-US', {
                                    style: 'currency',
                                    currency: 'USD',
                                    maximumFractionDigits: 1,
                                });
                                document.getElementById("total_" + id).innerText = formatter.format(totalPrice);
                            } else if (xhr.status === 400) {
                                alert("The number of products you selected exceeds the total product quantity");
                            }
                        }
                    };
                    xhr.send("id=" + id);
                }
            }
            
            function removeBookFromCart(id)
            {
                var confirmation = confirm("Do you want to remove this product from your cart?");
                if (confirmation) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "RemoveBookFromCartServlet", true);
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 202) {
                                var cartDetail = document.getElementById("cart_detail_" + id);
                                if (cartDetail) {
                                    cartDetail.remove();
                                } else {
                                    console.error("this book does not exist.");
                                }
                            } else if (xhr.status === 500) {
                                alert("Server Error");
                            }
                        }
                    };
                    xhr.send("id=" + id);
                }
            }

            function checkOut() {
                var checkboxes = document.querySelectorAll('.checkbox-large:checked');
                var selectedProducts = [];

                checkboxes.forEach(function (checkbox) {
                    selectedProducts.push(checkbox.value);
                });
                if(checkboxes.length === 0)
                {
                    alert("please choose product to order");
                    return;
                }

                document.getElementById('selectedProducts').value = selectedProducts.join(',');

                document.getElementById('check-out-form').submit();
            }
            </script>
          </head>
      <body>



        <h1>List of Product in cart</h1>
        <table>
            <tr>
                <th>Image</th>
                <th>Book name</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <p:forEach items="${cartDetails}" var="cartDetail">

                <tr id="cart_detail_${cartDetail.id}">
                    <td><img src="${cartDetail.img}" class ="item-img"/></td>
                    <td>${cartDetail.name}</td>
                    <td>${cartDetail.type}</td>
                    <td>
                        <button onclick="decreaseQuantity(${cartDetail.id})" class="quantity-button">-</button>
                        <span id="quantity_${cartDetail.id}" class="quantity">${cartDetail.quantity}</span>
                        <button onclick="increaseQuantity(${cartDetail.id})" class="quantity-button">+</button>

                    </td>
                    <td id="price_${cartDetail.id}">${cartDetail.price}</td>
                    <td id="total_${cartDetail.id}"><fmt:formatNumber value="${cartDetail.price * cartDetail.quantity}" type="currency" currencyCode="USD" maxFractionDigits="1"/></td>
                    <td>
                        <input type="checkbox" id="checkbox_${cartDetail.id}" class="checkbox-large" value="${cartDetail.id}">
                        <button onclick="removeBookFromCart(${cartDetail.id})" class="danger-button" style="float: right;"><img class="remove-book" src="image\\delete.png"/></button>
                    </td>
                </tr>
            </p:forEach>

        </table>       
        <div id="check-out-button-container">
            <form action="CheckOutServlet" method="post" id="check-out-form">
                <input type="hidden" id="selectedProducts" name="selectedProducts">
                <button id="check-out-button" type="button" onclick="checkOut()">Buy</button>
            </form>
        </div>

    </body>
</html>
