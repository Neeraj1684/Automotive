import { Link } from "react-router-dom";

function Dashboard() {
  // Dummy data for now (later we’ll connect APIs)
  const totalVehicles = 24;
  const upcomingAppointments = 5;

  return (
    <div className="min-h-screen bg-slate-50 font-sans">
      
      {/* Header */}
      <div className="bg-slate-900 text-white px-6 py-4 shadow">
        <h1 className="text-2xl font-semibold">
          Welcome to Automotive
        </h1>
        <p className="text-slate-300 text-sm">
          Service Management Dashboard
        </p>
      </div>

      {/* Content */}
      <div className="p-6 grid gap-6 md:grid-cols-2">

        {/* Total Vehicles Card */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-slate-200">
          <h2 className="text-slate-500 text-sm font-medium">
            Total Vehicles
          </h2>

          <p className="text-4xl font-bold text-blue-600 mt-2 font-mono">
            {totalVehicles}
          </p>

          <p className="text-slate-400 text-sm mt-2">
            Registered in system
          </p>
        </div>

        {/* Upcoming Appointments Card */}
        <div className="bg-white rounded-xl shadow-md p-6 border border-slate-200">
          <h2 className="text-slate-500 text-sm font-medium">
            Upcoming Appointments
          </h2>

          <p className="text-4xl font-bold text-blue-600 mt-2 font-mono">
            {upcomingAppointments}
          </p>

          <div className="mt-3 inline-block bg-amber-500 text-white text-xs px-3 py-1 rounded-full">
            Next 3 days
          </div>
        </div>

      </div>
    </div>
  );
}

export default Dashboard;