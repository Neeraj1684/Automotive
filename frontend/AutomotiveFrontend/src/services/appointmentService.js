import api from "./api";

// Get all appointments of logged-in user
export const getAppointments = () => {
  return api.get("/appointments");
};

// Book appointment
export const bookAppointment = (data) => {
  return api.post("/appointments", data);
};

// Update appointment (reschedule or change vehicle)
export const updateAppointment = (id, data) => {
  return api.put(`/appointments/${id}`, data);
};

// Cancel appointment
export const cancelAppointment = (id) => {
  return api.delete(`/appointments/${id}`);
};