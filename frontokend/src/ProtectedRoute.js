// ProtectedRoute.js
import React, { useState } from "react";
import { Navigate } from "react-router-dom";
import axios from "axios";

const ProtectedRoute = ({ element: Component, ...rest }) => {
  const [message, setMessage] = useState(""); // Stan do przechowywania odpowiedzi
  const token = localStorage.getItem("token"); // Pobierz token z localStorage

  // Sprawdź, czy token istnieje, aby określić, czy użytkownik jest uwierzytelniony
  const isAuthenticated = token !== null;

  // Funkcja obsługująca kliknięcie przycisku
  const handleButtonClick = async () => {
    try {
      const response = await axios.get("http://localhost:8080/messages", {
        headers: {
          Authorization: `Bearer ${token}`, // Dodajemy token do nagłówka
        },
      });
      setMessage(response.data); // Ustaw odpowiedź w stanie
    } catch (error) {
      setMessage("Błąd: nie udało się pobrać wiadomości.");
    }
  };

  // Jeśli użytkownik nie jest uwierzytelniony, przekieruj go do strony logowania
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return (
    <div>
      <Component {...rest} />
      <button onClick={handleButtonClick}>Pobierz Wiadomości</button>
      <div>{message}</div>
    </div>
  );
};

export default ProtectedRoute;
