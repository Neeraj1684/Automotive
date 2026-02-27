import { useEffect, useState } from "react";
import { 
  getAppointments,
  cancelAppointment,
  updateAppointment
} from "../../services/appointmentService";

const AppointmentList = () => {
  const [appointments, setAppointments] = useState([]);
  const [editId, setEditId] = useState(null);
  const [newDate, setNewDate] = useState("");

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    try {
      const res = await getAppointments();
      setAppointments(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const handleCancel = async (id) => {
    if (!window.confirm("Cancel this appointment?")) return;

    try {
      await cancelAppointment(id);
      fetchAppointments();
    } catch (err) {
      console.error(err);
    }
  };

  const handleUpdate = async (id) => {
    try {
      await updateAppointment(id, {
        appointmentDate: newDate
      });
      setEditId(null);
      fetchAppointments();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="container mt-4">
      <h2>My Appointments</h2>

      <table className="table table-bordered mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Vehicle</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {appointments.length > 0 ? (
            appointments.map((a) => (
              <tr key={a.id}>
                <td>{a.id}</td>
                <td>
                  {a.vehicle?.vehicleNumber} - {a.vehicle?.model}
                </td>

                {/* Date / Edit */}
                <td>
                  {editId === a.id ? (
                    <input
                      type="date"
                      value={newDate}
                      onChange={(e) => setNewDate(e.target.value)}
                    />
                  ) : (
                    a.appointmentDate
                  )}
                </td>

                <td>{a.status}</td>

                <td>
                  {a.status === "BOOKED" && (
                    <>
                      {editId === a.id ? (
                        <>
                          <button
                            className="btn btn-success btn-sm me-2"
                            onClick={() => handleUpdate(a.id)}
                          >
                            Save
                          </button>
                          <button
                            className="btn btn-secondary btn-sm"
                            onClick={() => setEditId(null)}
                          >
                            Cancel
                          </button>
                        </>
                      ) : (
                        <>
                          <button
                            className="btn btn-warning btn-sm me-2"
                            onClick={() => {
                              setEditId(a.id);
                              setNewDate(a.appointmentDate);
                            }}
                          >
                            Edit
                          </button>

                          <button
                            className="btn btn-danger btn-sm"
                            onClick={() => handleCancel(a.id)}
                          >
                            Cancel
                          </button>
                        </>
                      )}
                    </>
                  )}
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">
                No appointments found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default AppointmentList;