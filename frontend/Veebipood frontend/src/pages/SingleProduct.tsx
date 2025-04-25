import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Product } from "../models/Product";

function SingleProduct() {
    //productid tuleb app.tsxist 
    //usega algavad asjad on reacti v'rk
    const {productId} = useParams();
    const [product, setProduct] = useState<Product>();

    //useeffect on backendi p'ring siis kui lõpus mingi array sees olev asi tahab muutuda. Takistab asjade korduva käivituse.
    useEffect(() =>{
        fetch("http://localhost:8080/products/" + productId)
        //res on lihtsalt lühend @responsist
        .then(res => res.json())
        .then(json => setProduct(json));
    }, [productId]);
  return (
    <div>
        <br />
        <h3>toote {product?.name} üksikasjad</h3>
        <div> Hind:  {product?.price}</div>
        <div> Kategooria:  {product?.category?.name}</div>
        <img src={product?.image} alt=""></img>

    </div>
  )
}

export default SingleProduct