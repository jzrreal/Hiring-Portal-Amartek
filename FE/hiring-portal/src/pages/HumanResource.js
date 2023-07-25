import { Outlet } from "react-router-dom";
import { Navigate } from "react-router-dom";

export default function HumanResource () {



    if(!localStorage.getItem("authToken")) {
        return <Navigate to="/login" />;
    }
    
    else {
        return (
            <div>
                <Outlet />
            </div>
        )
    }
}