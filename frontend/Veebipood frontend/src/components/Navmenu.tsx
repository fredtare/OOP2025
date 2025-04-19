import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link } from 'react-router-dom';


//siin saab muuta k]iki menüü elemente. See tehti ise componendiga ja copipasteeriti
//hrefi me ei kasuta kuna see teeb refreshi. Hrefi asemel tuleb as={Link} to=
function Navmenu() {
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container fluid>
        <Navbar.Brand href="#">Õpime Feebibood</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
            navbarScroll
          >
            <Nav.Link as={Link} to="/">Home</Nav.Link>
            <Nav.Link as={Link} to="/cart">Cart</Nav.Link>
            <Nav.Link as={Link} to="/orders">Orders</Nav.Link>
            <Nav.Link as={Link} to="/login">Login</Nav.Link>
            <NavDropdown title="Adminn" id="navbarScrollingDropdown">
              <NavDropdown.Item as={Link} to="/manage/persons">Persons</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/manage/products">
                produktid
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={Link} to="/manage/categories">
                Kategooriad
              </NavDropdown.Item>
            </NavDropdown>
            <Nav.Link as={Link} to="/login" disabled>
                Arraid (resaikel binnushkas)
            </Nav.Link>
          </Nav>
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navmenu;