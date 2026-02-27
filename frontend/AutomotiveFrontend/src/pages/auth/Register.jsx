import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../services/authService";
import { useAuth } from "../../context/AuthContext";

function Register() {
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phone: "",
  });

  const navigate = useNavigate();
  const { login } = useAuth();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await registerUser(form);

      // Backend returns token
      const token = res.data.token || res.data;

      login(token);   // store token
      navigate("/");

    } catch (error) {
      alert(error.response?.data || "Registration failed");
    }
  };

  return (
    <div>
      <h2>Register</h2>

      <form onSubmit={handleSubmit}>
        <input name="firstName" placeholder="First Name" onChange={handleChange} required />
        <input name="lastName" placeholder="Last Name" onChange={handleChange} required />
        <input name="email" type="email" placeholder="Email" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <input name="phone" placeholder="Phone" onChange={handleChange} required />

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;