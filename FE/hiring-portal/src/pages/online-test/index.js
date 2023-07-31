import React, {useEffect, useState} from 'react'
import axios from 'axios';
import {useNavigate} from "react-router-dom";

export default function Index() {
    document.title = "Online Test";

    const urlParameter = new URLSearchParams(window.location.search);
    const navigate = useNavigate();

    const [testToken, setTestToken] = useState(urlParameter.get("token"));
    const [valid, setValid] = useState(false);
    const [message, setMessage] = useState("");

    useEffect(() => {
        const validateToken = axios({
            method: "GET",
            url: `${process.env.REACT_APP_API_URL}/api/online-tests/check?token=${testToken}`
        });

        validateToken.then(response => {
            setValid(true);
        }).catch(error => {
            const data = error.response.data;
            setMessage(error.response.data.message);
        })

    });

    const navigateToTest = () => {
        navigate(`/online-test/start?token=${testToken}`);
    }

    if (!valid) {
        return (
            <div className="lockscreen-wrapper">
                <div className="lockscreen-logo">
                    <img src="https://www.amartek.id/i/logo/weblogo-amartek.png"/>
                </div>
                <div className="help-block text-center my-2">
                    <h1 className="font-weight-bold">{message}</h1>
                </div>
                <div className="lockscreen-footer text-center">
                    <strong>Copyright &copy; 2023 <a href="https://www.amartek.id/">Bumi Amartha Teknologi
                        Mandiri</a></strong>
                    <div>All rights reserved.</div>
                </div>
            </div>
        )
    } else {

        return (
            <div className="lockscreen-wrapper">
                <div className="lockscreen-logo">
                    <img src="https://www.amartek.id/i/logo/weblogo-amartek.png"/>
                </div>
                <div className="help-block text-center my-2">
                    <h1 className="font-weight-bold">Please Prepare Yourself</h1>
                </div>
                <div className="help-block">
                    <ul>
                        <li>You have time 30 minute to finish all question</li>
                        <li>Questions contains 3 segment :</li>
                        <ul>
                            <li>Database : 5 Question</li>
                            <li>Logika Matematika : 5 Question</li>
                            <li>Basic Programming : 5 Question</li>
                        </ul>
                        <li>Answer the questions you can first</li>
                        <li>You can go to previous question</li>
                        <li>The countdown start when you click the button below</li>
                        <li>Test will automatically finish when times out</li>
                    </ul>
                </div>
                <div className="help-block text-center">
                    <a onClick={navigateToTest} className="btn btn-primary mt-3">Start Test</a>
                </div>
                <div className="lockscreen-footer text-center">
                    <strong>Copyright &copy; 2023 <a href="https://www.amartek.id/">Bumi Amartha Teknologi
                        Mandiri</a></strong>
                    <div>All rights reserved.</div>
                </div>
            </div>
        )
    }
}
