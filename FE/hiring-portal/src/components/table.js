import { useEffect, useState } from "react"
import axios from "axios"
import TableHeader from "./TableHeader";
import TableData from "./TableData";

export default function Table (  ) {

    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-levels",
            headers: {
                Authorization: "Bearer " + localStorage.getItem("authToken")
            }
        })
        .then(response => {
            // console.log(response.data.data);
            setTableData(response.data.data);
        })
    }, [])
    
    const [tableData, setTableData] = useState([{}])
    const [Id, setId] = useState()
    const getId = (newGetId) => {
        setId(newGetId)
    }

    const clickHandler = (e) => {
        // e.preventDefault()

        console.log(e.target.data.id);
    }

    return (
        <table className="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <TableHeader responseData = {tableData}/>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {/* <tr> */}
                    <TableData responseData={tableData} getId = {getId} addColumn={
                        [
                            <td>
                                <button onClick={(e) => console.log(e.nativeEvent)}>Click 1</button>
                            </td>,
                            <td>
                                <button onClick={e => console.log(Id)}>Click 2</button>
                            </td>
                        ]} />
                {/* </tr> */}
            </tbody>
        </table>
    )
}