import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import CountdownTimer from "./CountdownTimer";

import {render} from "@testing-library/react";
import login from "../login";

export default function Test() {
    document.title = "Online Test";
    const navigate = useNavigate();

    const urlParameter = new URLSearchParams(window.location.search);
    const pathName = window.location.pathname;

    const [testToken, setTestToken] = useState(urlParameter.get('token'));
    const [currentNumber, setCurrentNumber] =
        useState(urlParameter.get('number') ? urlParameter.get('number') : 1);
    const [valid, setValid] = useState(true);
    const [data, setData] = useState({});
    const [totalNumber, setTotalNumber] = useState(0);
    const [choices, setChoices] = useState([]);
    const [questionId, setQuestionId] = useState(0);
    const [testQuestionId, setTestQuestionId] = useState(0);
    const [choiceId, setChoiceId] = useState(0);

    const validateToken = () => axios({
        method: "GET",
        url: `${process.env.REACT_APP_API_URL}/api/online-tests/check?token=${testToken}`
    });

    const getQuestion = () => axios({
        method: "GET",
        url: `${process.env.REACT_APP_API_URL}/api/online-tests/pagination`,
        params: {
            token: testToken,
            number: currentNumber
        }
    });

    const updateAnswer = () => axios({
        method: "PUT",
        url: `${process.env.REACT_APP_API_URL}/api/online-tests/test-questions/${testQuestionId}/questions/${questionId}/choices/${choiceId}`
    });

    const endTestOrFinishTest = () => axios({
        method: "PUT",
        url: `${process.env.REACT_APP_API_URL}/api/online-tests/${testToken}`
    });

    useEffect(() => {
        validateToken()
            .then(response => {
                setValid(true);
            }).catch(error => {
            navigate(`/online-test?token=${testToken}`);
        })
    }, [])

    useEffect(() => {
        getQuestion()
            .then(response => {
                setData(response.data.data.result);
                setChoices(response.data.data.result.choices);
                setTotalNumber(response.data.data.page);
                setTestQuestionId(response.data.data.result.test_question_id);
                setQuestionId(response.data.data.result.question_id);

                if (response.data.data.result.answer !== null) {
                    setChoiceId(response.data.data.result.answer);
                }
            }).catch(error => {
            navigate(`/online-test?token=${testToken}`);
        })
    }, [currentNumber, testToken])

    useEffect(() => {
        if (choiceId !== 0) {
            updateAnswer();
        }
    }, [choiceId]);

    const finishTest = () => {
        endTestOrFinishTest();
        window.location.replace('http://localhost:3000/online-test?token=' + testToken);
        // navigate(`/online-test?token=${testToken}`);
    }

    //For navigate to another question
    const changeNumber = ({button}) => {
        urlParameter.set('number', button);
        navigate(`${pathName}?${urlParameter.toString()}`);
        setCurrentNumber(button);
    }

    const changeAnswer = e => {
        setChoiceId(Number(e.target.value));
    }

    const style1 = {
        backgroundColor: 'lightblue'
    }
    const style2 = {
        backgroundColor: 'pink'
    }
    const buttonStyle = {
        margin: '2px'
    }

    const active = {
        margin: '2px',
        backgroundColor: 'lightyellow'
    }

    const listButton = [];

    for (let i = 1; i <= totalNumber; i++) {
        listButton.push(i);
    }


    return (
        <div className='container-fluid pt-4'>
            <div className="row justify-content-end">
                <div className="col-2" style={{backgroundColor: 'lightgreen'}}>
                    <CountdownTimer timestamp={new Date(data.expired_time).getTime()} token={testToken}/>
                </div>
            </div>
            <div className="row justify-content-center">
                <div className="col-2">
                    <div className="card-body">
                        <div className="row justify-content-center" style={style1}>
                            {listButton.map(button =>
                                <button
                                    style={Number(currentNumber) === button ? active : buttonStyle}
                                    key={button}
                                    onClick={() => changeNumber({button})}>
                                    {button}
                                </button>
                            )}
                        </div>
                    </div>
                </div>
                <div className="col-6" style={style2}>
                    <div className="card-body">
                        <div className="row">
                            <h3>{currentNumber}. {data.question}</h3>
                        </div>
                        {choices.map(choice => {
                            return (
                                <>
                                    <div className="row mb-0 pb-0">
                                        <div className="col-1">
                                            <input
                                                name="inlineRadioOptions"
                                                type="radio"
                                                value={choice.choiceId}
                                                checked={choiceId === choice.choiceId}
                                                onChange={changeAnswer}
                                            />
                                            <label className="form-check-label"></label>
                                        </div>
                                        <div className="col-11">
                                            <p key={choice.choice}>{choice.choice}</p>
                                        </div>
                                    </div>
                                </>
                            );
                        })}
                        {currentNumber == 9 ?
                            <div className="row justify-content-end">
                                <button
                                    className="btn btn-info"
                                    onClick={finishTest}
                                >Finish
                                </button>
                            </div>
                            :
                            <></>
                        }
                    </div>
                </div>
            </div>
            <h1>Hallo Ges, Token mu = {testToken}. Kamu ada di number {currentNumber}</h1>
            <div className="lockscreen-footer text-center">
                <strong>Copyright &copy; 2023 <a href="https://www.amartek.id/">Bumi Amartha Teknologi
                    Mandiri</a></strong>
                <div>All rights reserved.</div>
            </div>
        </div>
    );
}


