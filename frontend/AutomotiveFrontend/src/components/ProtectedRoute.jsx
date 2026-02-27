import { useContext } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import Navbar from "./Navbar";

function ProtectedRoute() {
  const { isAuthenticated } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  return (
    <>
      <Navbar />
      <Outlet />
    </>
  );
}

export default ProtectedRoute;