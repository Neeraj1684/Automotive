import { useEffect, useState } from "react";
import { getVehicles, deleteVehicle } from "../../services/vehicleService";
import { useNavigate } from "react-router-dom";

function VehicleList() {
  const [vehicles, setVehicles] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    const res = await getVehicles();
    setVehicles(res.data);
  };

  const handleDelete = async (id) => {
    await deleteVehicle(id);
    fetchVehicles();
  };

  return (
    <div>
      <h2>My Vehicles</h2>

      <button onClick={() => navigate("/vehicles/add")}>
        Add Vehicle
      </button>

      <table border="1">
        <thead>
          <tr>
            <th>Number</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Year</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {vehicles.map((v) => (
            <tr key={v.id}>
              <td>{v.vehicleNumber}</td>
              <td>{v.brand}</td>
              <td>{v.model}</td>
              <td>{v.year}</td>
              <td>
                <button onClick={() => navigate(`/vehicles/edit/${v.id}`)}>
                  Edit
                </button>
                <button onClick={() => handleDelete(v.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default VehicleList;