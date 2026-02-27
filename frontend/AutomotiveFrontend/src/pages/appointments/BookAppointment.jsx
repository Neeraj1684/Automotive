import { useEffect, useState } from "react";
import { bookAppointment } from "../../services/appointmentService";
import { getVehicles } from "../../services/vehicleService";
import { useNavigate } from "react-router-dom";

const BookAppointment = () => {
  const [vehicles, setVehicles] = useState([]);
  const [vehicleId, setVehicleId] = useState("");
  const [appointmentDate, setAppointmentDate] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    try {
      const res = await getVehicles();
      setVehicles(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!vehicleId || !appointmentDate) {
      alert("Please fill all fields");
      return;
    }

    try {
      await bookAppointment({
        vehicleId: vehicleId,
        appointmentDate: appointmentDate
      });

      alert("Appointment booked!");
      navigate("/appointments");
    } catch (err) {
      console.error(err);
      alert("Booking failed");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Book Appointment</h2>

      <form onSubmit={handleSubmit} className="mt-3">

        {/* Vehicle */}
        <div className="mb-3">
          <label className="form-label">Select Vehicle</label>
          <select
            className="form-control"
            value={vehicleId}
            onChange={(e) => setVehicleId(e.target.value)}
          >
            <option value="">-- Select Vehicle --</option>
            {vehicles.map((v) => (
              <option key={v.id} value={v.id}>
                {v.vehicleNumber} - {v.model}
              </option>
            ))}
          </select>
        </div>

        {/* Date */}
        <div className="mb-3">
          <label className="form-label">Appointment Date</label>
          <input
            type="date"
            className="form-control"
            value={appointmentDate}
            onChange={(e) => setAppointmentDate(e.target.value)}
          />
        </div>

        <button className="btn btn-primary">
          Book Appointment
        </button>
      </form>
    </div>
  );
};

export default BookAppointment;