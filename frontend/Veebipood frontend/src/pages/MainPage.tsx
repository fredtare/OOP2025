//import React from 'react'   see välja kommida kuna tekitab problemi
//vaja importida moodul, terminaliga cd kausta ja npm i react-router-dom

import { useCallback, useEffect, useRef, useState } from "react";
import { Category } from "../models/Category";
import { Product } from "../models/Product";
import {Link, Route, Routes} from 'react-router-dom'






function MainPage() {
      //const t'hendab et otse ei saa muuta ainult läbi setteri
  const [kategooriad, setKategooriad] = useState<Category[]>([]);
  const [produktid, setProduktid] = useState<Product[]>([]);
  const [totalProducts, setTotalProducts] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  //algselt oli lihtsalt const productsByPage aga kuna tahame muuta, paneme kandilised suludinskid ümberr ja teeme setteri juurde. 
  //Juurde ka useState ja algv''rtus sulgu
  const [productsByPage, setProductsByPage] = useState(2); 
  const [page, setPage] = useState(0);
  const [activeCategory, setActiveCategory] = useState(-1);
  const productsByPageRef = useRef<HTMLSelectElement>(null); //htmli inputiga sidumiseks(select on input)

//) nagu -> onload funktsioon. Läheb kohe Käima alguses ja sinna saab ka dep arrayd kasutada (need [] sulud lõpus)
  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then(res=>res.json()) //kogu tagastus, headers, status code  json tähendab mis kujul see tuleb. Kui tagastab stringe pane text vms jne
      .then(json=> setKategooriad (json)) // body e sisu mida backend tagastab meile
  }, []);



  //usega algavad asjad on reacti v'rgid mida vaja importida!
  const showByCategory =  useCallback((categoryId: number, currentPage: number) => {
    setActiveCategory(categoryId);
    setPage(currentPage);
    fetch("http://localhost:8080/category-products?categoryId=" + categoryId + "&size=" + productsByPage + "&page=" + currentPage)
      .then(res=>res.json()) 
      .then(json=> {
        setProduktid(json.content);
        setTotalProducts(json.totalElements);
        setTotalPages(json.totalPages);
      }) 

  }, [productsByPage])

  useEffect(() => {
    showByCategory(-1, 0);
    //alla kandilistesse saab panna nt page, et kui lehekülg muutuja muutub, läheb süsteem uuesti käima. Hetkel kategooria muutuja triggeriks. 
    //Aga ta tahab hooki. Ehk peab k'ima funktsioonist allpool. Vt showbycatehgoryt. 14 aprl tund.
  }, [showByCategory]);

  function updatePage(newPage: number){
    showByCategory(activeCategory, newPage);

  }



  return (
    
    //SEE div ON SEE PEA ELMEENT MIDA EI TOHI YLE KIRJUTADA EGA VäLJAPOOLE MINNA
    //.current?  tähendab typescript näeb et ref on alguses null
    //ehk ta on 2 väärtusevõimalust. Lõppväärtus tuleb alles siit productsByPageRef inputist. 
    <div> 

      <select ref={productsByPageRef} onChange={() => setProductsByPage(Number(productsByPageRef.current?.value))}>
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>

      <button onClick={() => showByCategory(-1, 0)}>Kõik kategooriad</button>
      {kategooriad.map(kategooria =>
       <button key={kategooria.id} onClick={() => showByCategory(kategooria.id, page)}>
        {kategooria.name}
        </button>)}
        <br></br>
        <div>Kokku Tooteid: {totalProducts}</div>
        <br></br>

      { produktid.map(produkt => <div>
        <div>{produkt.name} </div>
        <div>{produkt.price}</div>

        </div> )}
        <br></br>
      <button disabled={page === 0} onClick={() => updatePage(page -1)}>eelmine</button>
      <span>{page + 1}</span>
      <button disabled={page >= totalPages -1} onClick={() => updatePage(page +1)}>järgmine</button>
    </div>
    //SEE div ON SEE PEA ELMEENT MIDA EI TOHI YLE KIRJUTADA EGA VäLJAPOOLE MINNA
    
  )
}

export default MainPage