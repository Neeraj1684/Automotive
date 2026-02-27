import { useEffect, useState } from "react";
import { getProfile, updateProfile } from "../../services/profileService";

function Profile() {
  const [profile, setProfile] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
  });

  const [loading, setLoading] = useState(true);

  // Fetch profile on load
  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const res = await getProfile();
      setProfile(res.data);
      setLoading(false);
    } catch (err) {
      console.error("Failed to load profile", err);
    }
  };

  const handleChange = (e) => {
    setProfile({ ...profile, [e.target.name]: e.target.value });
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await updateProfile(profile);
      alert("Profile updated successfully");
    } catch (err) {
      alert("Update failed");
    }
  };

  if (loading) return <p>Loading profile...</p>;

  return (
    <div>
      <h2>My Profile</h2>

      <form onSubmit={handleUpdate}>
        <input
          name="firstName"
          value={profile.firstName}
          onChange={handleChange}
          placeholder="First Name"
        />

        <input
          name="lastName"
          value={profile.lastName}
          onChange={handleChange}
          placeholder="Last Name"
        />

        <input
          name="email"
          value={profile.email}
          disabled
        />

        <input
          name="phone"
          value={profile.phone}
          onChange={handleChange}
          placeholder="Phone"
        />

        <button type="submit">Update Profile</button>
      </form>
    </div>
  );
}

export default Profile;