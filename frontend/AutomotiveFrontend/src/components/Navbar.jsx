import { Link, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { useAuth } from "../context/AuthContext";

function Navbar() {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
      <Link to="/" style={{ marginRight: "15px" }}>Dashboard</Link>
      <Link to="/profile" style={{ marginRight: "15px" }}>Profile</Link>
      <Link to="/vehicles" style={{ marginRight: "15px" }}>Vehicles</Link>
      <Link to="/appointments" style={{ marginRight: "15px" }}>Appointments</Link>
      <Link to="/book-appointment" style={{ marginRight: "15px" }}>Book Service</Link>

      <button onClick={handleLogout} style={{ float: "right" }}>
        Logout
      </button>
    </div>
  );
}

export default Navbar;