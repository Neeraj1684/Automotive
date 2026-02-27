import api from "./api";

// Get logged-in customer profile
export const getProfile = () => {
  return api.get("/customer/profile");
};

// Update profile
export const updateProfile = (data) => {
  return api.put("/customer/profile", data);
};