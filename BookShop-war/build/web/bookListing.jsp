<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

            <title>Book shop - product</title>

            <style>
            /* Style the cart icon to be positioned at the top right corner */
            .cart-icon {
                position: fixed;
                top: 20px;
                right: 20px;
                display: flex;
                align-items: center;
            }
            .cart-icon img {
                width: 30px;
                height: auto;
                margin-right: 5px;
            }
            .item-count {
                background-color: #ff5733;
                color: white;
                border-radius: 50%;
                padding: 5px 10px;
                font-size: 14px;
            }
            .card-container {
                display: flex;
                flex-wrap: wrap;
            }
            .card-title {
                display: -webkit-box;
                -webkit-line-clamp: 2; /* Số dòng tối đa muốn hiển thị */
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .card-img {
                width: 100%;
                height: 250px;
                object-fit: cover;
            }
            .card {
                flex-basis: calc(25% - 15px); /* Đặt kích thước của mỗi card là 25% của hàng với khoảng cách giữa các card là 15px */
                margin-bottom: 15px; /* Khoảng cách dưới của mỗi card */
            }
            .book-card {
                display: flex;

                margin-right: 10px;
            }
            .card-footer {
                display: flex;
                justify-content: space-between;
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                border-radius: 5px;
            }
            button:hover {
                background-color: #45a049;
            }
            .container {
                display: flex;
                justify-content: space-between;
            }
        </style>
           <script>
            function addToCart(bookId) {
                var bookName = document.getElementById("book-name-" + bookId).innerHTML;
                var enteredQuantity = prompt("Please enter the number of " + bookName +" you want to buy :", "");
                
                if (enteredQuantity === null) {
                    return; // Không làm gì nếu người dùng ấn nút "Cancel"
                }
                if (enteredQuantity === "") {
                    alert("Please enter a quantity."); // Hiển thị cảnh báo nếu người dùng không nhập gì
                    return;
                }
                if (!/^\d+$/.test(enteredQuantity)) {
                    alert("Invalid input! Please enter a valid number.");
                    addToCart(bookId, quantity); // Yêu cầu nhập lại nếu nhập không phải là số
                    return;
                }
                var parsedQuantity = parseInt(enteredQuantity);
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "AddToCartServlet", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            var itemCount = xhr.responseText;
                            document.getElementById("item-count").innerText = itemCount;
                            setTimeout(function () {
                                alert("The product has been added to cart");
                            }, 100);
                        } else if (xhr.status === 400) {
                            alert("The number of products you selected exceeds the total product quantity");
                        }
                    }
                };
                xhr.send("bookId=" + encodeURIComponent(bookId) + "&quantity=" + encodeURIComponent(parsedQuantity));
            }

            // Đảm bảo phần tử có id "cart-icon" được tìm thấy trước khi thêm sự kiện lắng nghe vào đó
//            document.addEventListener('DOMContentLoaded', function () {
//                var cartIcon = document.getElementById('cart-icon');
//                if (cartIcon) {
//                    cartIcon.addEventListener('click', function () {
//                        window.location.href = 'cart.jsp';
//                    });
//                }
//            });
        </script>

          </head>
      <body>
        <div> 

            <h1>List of book</h1>
            <a href="AllDataServlet">All data</a>

            <a href="CartServlet">
                <div id="cart-icon" class = "cart-icon">

                    <img src="image/carts.png" alt="Cart">
                    <span id="item-count">${itemCount}</span>

                </div>
            </a>



        </div>

        <div class="container">
            <div class="card-container">
                <p:forEach items="${books}" var="book">
                    <div class="card book-card" style="width: 18rem;">
                        <img src="${book.img}" class="card-img-top card-img" alt="...">
                        <div class="card-body">

                            <h5 class="card-title" id="book-name-${book.id}">${book.name}</h5>
                            <h6>Available: ${book.quantity}</h6>
                            <div class="card-footer">
                                <p><fmt:formatNumber value="${book.price}" type="currency" currencyCode="USD" /></p>
                                <a onclick="addToCart(${book.id})" class="btn btn-primary">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </p:forEach>
            </div>
        </div>

    </body>
</html>
