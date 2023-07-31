import './CountdownTimer.css';
import {useEffect, useState} from "react";
import dayjs from "dayjs";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const defaultRemainingTime = {
    minutes: '00',
    seconds: '00'
}

function getRemainingTimeFromTimestamp(timestamp) {
    const timestampDayJs = dayjs(timestamp);
    const nowDayjs = dayjs();
    return {
        minutes: getRemainingMinutes(nowDayjs, timestampDayJs),
        seconds: getRemainingSecond(nowDayjs, timestampDayJs)
    }
}

function validateTheTime(timestamp) {
    const timestampDayJs = dayjs(timestamp);
    const nowDayjs = dayjs();
    return !timestampDayJs.isBefore(nowDayjs);
}

function getRemainingMinutes(nowDayjs, timestampDayjs) {
    const minutes = timestampDayjs.diff(nowDayjs, 'minutes');
    return padWithZero(minutes % 60);
}

function getRemainingSecond(nowDayjs, timestampDayjs) {
    const seconds = timestampDayjs.diff(nowDayjs, 'seconds');
    return padWithZero(seconds % 60);
}

function padWithZero(number) {
    const numberString = number.toString();
    if (numberString.length >= 2) return numberString;
    return `0${numberString}`;
}


const CountdownTimer = ({timestamp, token}) => {
    const navigate = useNavigate();

    const [remainingTime, setRemainingTime] = useState(defaultRemainingTime);


    const endTestOrFinishTest = testToken => {
        axios({
            method: "PUT",
            url: `${process.env.REACT_APP_API_URL}/api/online-tests/${testToken}`
        }).then(response => {
            console.log(response);
            navigate(`/online-test?token=${testToken}`);
        }).catch(error => {
            console.log(error);
            navigate(`/online-test?token=${testToken}`);
        })
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            if (timestamp) {
                updateRemainingTime(timestamp);
            }
        }, 1000);
        return () => clearInterval(intervalId);
    }, [timestamp])

    function updateRemainingTime(countdown) {
        setRemainingTime(getRemainingTimeFromTimestamp(countdown));
        if (!validateTheTime(countdown)) endTestOrFinishTest(token);
    }

    return (
        <div className='countdown-timer'>
            <span>{remainingTime.minutes}</span>
            <span>:</span>
            <span>{remainingTime.seconds}</span>
        </div>
    );
}

export default CountdownTimer;

