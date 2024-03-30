<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
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
        </style>
           <script>

        </script>

          </head>
      <body>


        <h1>Books</h1>
        <a href="BookServlet">Add more book</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <p:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td>${book.type}</td>
                    <td>${book.quantity}</td>
                    <td>${book.price}</td>
                </tr>
            </p:forEach>
        </table>
        <br/>
        <h1>Cart details</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Book name</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
            <p:forEach items="${cartDetails}" var="cartDetail">
                <tr>
                    <td>${cartDetail.id}</td>
                    <td>${cartDetail.name}</td>
                    <td>${cartDetail.type}</td>
                    <td>${cartDetail.quantity}</td>
                    <td>${cartDetail.price}</td>
                    <td>${cartDetail.quantity.intValue() * cartDetail.price.intValue()} </td>
                </tr>
            </p:forEach>
        </table>
        <h2>Orders</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Create Date</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <p:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.createDate}</td>
                        <td>${order.name}</td>
                        <td>${order.address}</td>
                        <td>${order.phone}</td>
                        <td>${order.status}</td>
                    </tr>
                </p:forEach>
            </tbody>
        </table>

        <h2>Order Details</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Order ID</th>
                    <th>Book ID</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <p:forEach items="${orderDetails}" var="orderDetail">
                    <tr>
                        <td>${orderDetail.id}</td>
                        <td>${orderDetail.orderId}</td>
                        <td>${orderDetail.bookId}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.price}</td>
                    </tr>
                </p:forEach>
            </tbody>
        </table>

        <h2>Receipts</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Order ID</th>
                    <th>Create Date</th>
                    <th>Price</th>
                    <th>Payment Type</th>
                </tr>
            </thead>
            <tbody>
                <p:forEach items="${receipts}" var="receipt">
                    <tr>
                        <td>${receipt.id}</td>
                        <td>${receipt.orderId}</td>
                        <td>${receipt.createDate}</td>
                        <td>${receipt.price}</td>
                        <td>${receipt.paymentType}</td>
                    </tr>
                </p:forEach>
            </tbody>
        </table>
          </body>
</html>
