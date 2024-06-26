// Dashboard.js
import React from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  // Funkcja obsługująca kliknięcie przycisku "Logout"
  const handleLogout = () => {
    localStorage.removeItem("token"); // Usuwamy token z localStorage
    navigate("/login"); // Przekierowujemy użytkownika na stronę logowania
  };

  return (
    <div>
      <h1>Witamy na Dashboard</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Dashboard;
