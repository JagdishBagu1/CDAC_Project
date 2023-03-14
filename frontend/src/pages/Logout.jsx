import React from 'react'
import { useNavigate } from 'react-router-dom'

function Logout() {
    const navigate = useNavigate()

    localStorage.removeItem('token')
    navigate('/login')
}

export default Logout