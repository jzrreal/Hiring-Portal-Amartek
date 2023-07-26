import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Navigate } from "react-router-dom";
import axios from "axios";
import redirectManager from "../service/redirectManager";

export default function HumanResourceProtection () {

    const [tokenValidation, setTokenValidation] = useState(false)

    useEffect( () => {
        if(localStorage.getItem("authToken")) {
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
            .catch(() => {
                return <Navigate to={redirectManager(localStorage.getItem("role"))} />
            })
        }
    }, [])

    if(!localStorage.getItem("authToken") && !tokenValidation) {
        return <Navigate to="/login" />;
    }
    if(localStorage.getItem("role") !== "Human Resource") {
        return <Navigate to={redirectManager(localStorage.getItem("role"))} />
    }
    
    else {
        return (
            <div>
                <Outlet context={localStorage.getItem("authToken")}/>
            </div>
        )
    }
}