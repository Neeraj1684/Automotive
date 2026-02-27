import { useState } from "react";
import { addVehicle } from "../../services/vehicleService";
import { useNavigate } from "react-router-dom";

function AddVehicle() {
  const [form, setForm] = useState({
    vehicleNumber: "",
    brand: "",
    model: "",
    year: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addVehicle(form);
    navigate("/vehicles");
  };

  return (
    <div>
      <h2>Add Vehicle</h2>

      <form onSubmit={handleSubmit}>
        <input name="vehicleNumber" placeholder="Vehicle Number" onChange={handleChange} required />
        <input name="brand" placeholder="Brand" onChange={handleChange} required />
        <input name="model" placeholder="Model" onChange={handleChange} required />
        <input name="year" type="number" placeholder="Year" onChange={handleChange} required />

        <button type="submit">Save</button>
      </form>
    </div>
  );
}

export default AddVehicle;