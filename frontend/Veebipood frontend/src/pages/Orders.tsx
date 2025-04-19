import { useEffect, useState } from "react"
import { WebshopOrder } from "../models/WebshopOrder";



function Orders() {

    const [orders, setOrders] = useState<WebshopOrder[]>([]);

useEffect(() => {
    fetch("http://localhost:8080/orders")
    .then(res=>res.json()) 
    .then(json=> setOrders (json))
}, []);



  return (
    <div>Orders
            {orders.map(orderid => <div key={orderid.id}> Orderer:<div>{orderid.name} Order date: ({orderid.created}), total sum: {orderid.totalSum} ordered by {orderid.webshopUser.firstName}</div>
            <div id="div">{orderid.products.map(produkt => <div>{produkt.name}, {produkt.price}</div>)}</div> </div>)}
    </div>
  )
}

export default Orders