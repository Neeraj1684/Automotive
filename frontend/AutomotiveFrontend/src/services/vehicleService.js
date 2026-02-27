import api from "./api";

// Get all vehicles of logged-in user
export const getVehicles = () => {
  return api.get("/vehicles");
};

// Get single vehicle by id (for Edit page)
export const getVehicleById = async (id) => {
  const res = await api.get("/vehicles");
  return res.data.find((v) => v.id === parseInt(id));
};

// Add vehicle
export const addVehicle = (vehicle) => {
  return api.post("/vehicles", vehicle);
};

// Update vehicle
export const updateVehicle = (id, vehicle) => {
  return api.put(`/vehicles/${id}`, vehicle);
};

// Delete vehicle
export const deleteVehicle = (id) => {
  return api.delete(`/vehicles/${id}`);
};