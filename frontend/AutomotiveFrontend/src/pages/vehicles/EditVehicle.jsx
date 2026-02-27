import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getVehicles, updateVehicle } from "../../services/vehicleService";

function EditVehicle() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    vehicleNumber: "",
    brand: "",
    model: "",
    year: "",
  });

  useEffect(() => {
    loadVehicle();
  }, []);

  const loadVehicle = async () => {
    const res = await getVehicles();
    const vehicle = res.data.find((v) => v.id === parseInt(id));
    if (vehicle) setForm(vehicle);
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await updateVehicle(id, form);
    navigate("/vehicles");
  };

  return (
    <div>
      <h2>Edit Vehicle</h2>

      <form onSubmit={handleSubmit}>
        <input name="vehicleNumber" value={form.vehicleNumber} onChange={handleChange} required />
        <input name="brand" value={form.brand} onChange={handleChange} required />
        <input name="model" value={form.model} onChange={handleChange} required />
        <input name="year" type="number" value={form.year} onChange={handleChange} required />

        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default EditVehicle;