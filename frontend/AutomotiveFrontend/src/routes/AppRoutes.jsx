import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import Dashboard from "../pages/dashboard/Dashboard";
import Profile from "../pages/profile/Profile";
import VehicleList from "../pages/vehicles/VehicleList";
import AddVehicle from "../pages/vehicles/AddVehicle";
import EditVehicle from "../pages/vehicles/EditVehicle";
import AppointmentList from "../pages/appointments/AppointmentList";
import BookAppointment from "../pages/appointments/BookAppointment";
import ProtectedRoute from "../components/ProtectedRoute";

function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Protected */}
        <Route element={<ProtectedRoute />}>
          <Route path="/" element={<Dashboard />} />
          <Route path="/profile" element={<Profile />} />

          <Route path="/vehicles" element={<VehicleList />} />
          <Route path="/vehicles/add" element={<AddVehicle />} />
          <Route path="/vehicles/edit/:id" element={<EditVehicle />} />
          
          <Route path="/book-appointment" element={<BookAppointment />} />
          <Route path="/appointments" element={<AppointmentList />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default AppRoutes;