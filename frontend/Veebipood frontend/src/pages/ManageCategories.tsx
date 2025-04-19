import { useEffect, useRef, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

function ManageCategories() {
  const [products, setProducts] = useState<Product[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);

  
  useEffect(() => {
    fetch("http://localhost:8080/categories")
    .then(res=>res.json())
    .then(json=>setCategories(json))
  }, [])
  
  const deleteCategory = (id: number) => {
    fetch(`http://localhost:8080/categories/${id}`, {
      method: "DELETE",
    })
      .then(res => res.json())
      .then(json =>{

      if(json.message === undefined && json.timstamp === undefined && json.status === undefined){
        setCategories(json);
        toast.success("Kategooria kadus!?")
      } else {
        toast.error(json.message)
      }
    })
  }
  
  //reffid ülemise osa jaoks
  const nameRef = useRef<HTMLInputElement>(null);
  const activeRef = useRef<HTMLInputElement>(null);
    const newCategory = {
        name: nameRef.current?.value
     }
  
        //postib ära ja ütleb, lisab juurde newproduct bodyina ja converdib selle jsoni ja annab ka app tüübi.
        //kaldkriips lõpus tekitab CORS errorit!!!
    fetch(`http://localhost:8080/categories`, {
      method: "POST",
          body: JSON.stringify(newCategory),
          headers: {"Content-Type": "application/json"}
  
          //tagastus:
        })
        .then(res=>res.json())
        .then(json=>{
          //see paneb veateate ekraanile mitte ei ürita produkti panna.
          if(json.message === undefined && json.timstamp === undefined && json.status === undefined){
            setProducts(json)
            toast.success("Uus Kategooria lisatud!")
            if (nameRef.current && activeRef.current){
              nameRef.current.value = "";
              activeRef.current.checked = false;
            }

          } else {
            toast.error(json.message)
          }
  
        }) 

    return (

      <div>
  
        <h2>Manage Categories</h2>
  
        <label>name</label> <br />
        <input ref={nameRef} type="text" /> <br />
        <label>active</label> <br />
        <input ref={activeRef} type="checkbox" /> <br />
  
        <button onClick={() => addCategory()}>Add category</button>
  
        <br />
  
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nimi</th>
            </tr>
          </thead>
          <tbody>
            {categories.map((category) => (
              <tr key={category.id}>
                <td>{category.id}</td>
                <td>{category.name}</td>
                <td>{category.actve}</td>
                <td>
                <button onClick={() => deleteCategory(category.id)}> Delet!</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
  
        <ToastContainer />
      </div>
    )
}

export default ManageCategories