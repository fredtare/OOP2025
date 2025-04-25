import { useEffect, useRef, useState } from "react"
import { Product } from "../models/Product"
import { ToastContainer, toast } from "react-toastify";
import { Link } from "react-router-dom";

function ManageProducts() {
const [products, setProducts] = useState<Product[]>([]);
const [categories, setCategories] = useState<Category[]>([]);

useEffect(() => {
  fetch("http://localhost:8080/products")
  .then(res=>res.json())
  .then(json=>setProducts(json))
}, [])

useEffect(() => {
  fetch("http://localhost:8080/categories")
  .then(res=>res.json())
  .then(json=>setCategories(json))
}, [])

const deleteProduct = (id: number) => {
  fetch(`http://localhost:8080/products/${id}`, {
    method: "DELETE",
  }).then(() => 
  setProducts(products.filter(product => product.id !== id)));
}

//reffid ülemise osa jaoks
  const nameRef = useRef<HTMLInputElement>(null);
  const priceRef = useRef<HTMLInputElement>(null);
  const imageRef = useRef<HTMLInputElement>(null);
  const activeRef = useRef<HTMLInputElement>(null);
  const categoryRef = useRef<HTMLSelectElement>(null);
  const addProduct = () => {

  const newProduct = {
      name: nameRef.current?.value,
      price: Number(priceRef.current?.value), ///numb v''rtuse peaksin number ümber panema.
      image: imageRef.current?.value,
      active: activeRef.current?.checked, //value checkboxile ei sobi, kuna see annab "on"
      //kuna category on eraldi tabeel siis paneme vöörvõtmena selle sisse.
      category: {"id": Number(categoryRef.current?.value)}
   }

      //postib ära ja ütleb, lisab juurde newproduct bodyina ja converdib selle jsoni ja annab ka app tüübi.
      //kaldkriips lõpus tekitab CORS errorit!!!
  fetch(`http://localhost:8080/products`, {
    method: "POST",
        body: JSON.stringify(newProduct),
        headers: {"Content-Type": "application/json"}

        //tagastus:
      })
      .then(res=>res.json())
      .then(json=>{
        //see paneb veateate ekraanile mitte ei ürita produkti panna.
        if(json.message === undefined && json.timstamp === undefined && json.status === undefined){
          setProducts(json)
          toast.success("Uus toode lisatud!")
        } else {
          toast.error(json.message)
        }

      })

  }

  return (
    <div>

      <h2>ManageProducts</h2>

      <label>name</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label>price</label> <br />
      <input ref={priceRef} type="number" /> <br />
      <label>image</label> <br />
      <input ref={imageRef} type="text" /> <br />
      <label>active</label> <br />
      <input ref={activeRef} type="checkbox" /> <br />
      <label>category</label> <br />
      <select ref={categoryRef} >
        {categories.map(category => <option value={category.id}>{category.name}</option>)}
      </select>

      <button onClick={() => addProduct()}>Add Product</button>

      <br />

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nimi</th>
            <th>Hind</th>
            <th>Pilt</th>
            <th>Kategooria</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>{product.image}</td>
              <td>{product.category?.name}</td>
              <td>
                <button onClick={() => deleteProduct(product.id)}> Delet!</button>
              </td>
              <td>
                <Link to={"/admin/editproduct/" + product.id}>
                <button>Muuda Toodet</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <ToastContainer />
    </div>
  )
}

export default ManageProducts