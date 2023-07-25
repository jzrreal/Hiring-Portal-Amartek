import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Navigate } from "react-router-dom";
import axios from "axios";

export default function HumanResource () {

    const [tokenValidation, setTokenValidation] = useState(false)

    useEffect( () => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/tokens",
            headers: {
                Authorization: "Bearer " + localStorage.getItem("authToken")
            }
        })
        .then(response => {
            if(response.data.status === 200) {
                setTokenValidation(true)
            }
        })
        .catch(err => console.log(err))
    }, [])

    if(!localStorage.getItem("authToken") && tokenValidation) {
        return <Navigate to="/login" />;
    }
    
    else {
        return (
            <div>
                <Outlet context={localStorage.getItem("authToken")}/>
            </div>
        )
    }
}