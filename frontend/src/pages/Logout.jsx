import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Logout() {
  const navigate = useNavigate();

  useEffect(() => {
    logout();
  }, []);

  const logout = () => {
    console.log("in logout");
    const token = localStorage.getItem("token");
    console.log(token);

    axios
      .get("http://localhost:9095/api/auth/logout", {
        headers: { Authorization: `Bearer ${token}`},
      })
      .then((res) => {
        console.log(res.data);
        localStorage.removeItem("token");
        navigate("/signIn");
      })
      .catch((err) => {
        console.log("Error ", err.response);
      });
  };
}

export default Logout;
