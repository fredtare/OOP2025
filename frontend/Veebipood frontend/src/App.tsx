
import './App.css'
import {Route, Routes} from 'react-router-dom'
import MainPage from './pages/MainPage';
import ManageProducts from './pages/ManageProducts';
import ManageCategories from './pages/ManageCategories';
import ManagePersons from './pages/ManagePersons';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Orders from './pages/Orders';
import Arrayd from './pages/Arrayd';
import Navmenu from './components/Navmenu';
import Cart from './pages/Cart';
import SingleProduct from './pages/SingleProduct';
import EditProduct from './pages/EditProduct';

function App() {



  return (
    <>
      <Navmenu />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/product/:productId" element={<SingleProduct/>} />
        <Route path="/admin/editproduct/:productId" element={<EditProduct/>} />
        <Route path="/manage/products" element={<ManageProducts />} />
        <Route path="/manage/categories" element={<ManageCategories />} />
        <Route path="/manage/persons" element={<ManagePersons />} />
        <Route path="/login" element={<Login />} />
        <Route path="/login/signup" element={<Signup />} />
        <Route path="/manage/orders" element={<Orders />} />
        <Route path="/arrayd" element={<Arrayd />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/*" element={<div>Not Found</div>} />
        
      </Routes>
    </>
  );
}
//key={}
//react soovib koodi memoriseerida.  Kui toimuvad re renderused, siis ta jätab kõik mällu v.a. tsükli ja array sisud. Sest tal pole aimu mille järgi
//seda meelde j'tte. Array meeldeJätmiseks kasutame key={}
export default App
