import React, { useState } from 'react'
import ReactDataTable, { ReactTableCheckBox } from '@sifatkabir/reactdatatable';

function Index() {
    const columns = [
        {
            title: 'Firstname',
            render: (item) => {
                return (
                    <b>{item.firstname}</b>
                )
            },
            sort: true,
            key: 'firstname'
        },
        {
            title: 'Lastname',
            render: (item) => {
                return (
                    <b>{item.lastname}</b>
                )
            },
            sort: true,
            key: 'lastname'
        },
        {
            title: 'Country code',
            key: 'countrycode',
            sort: true
        }
    ];

    const data = [
        {
            "userId": "56615",
            "firstname": "Kaelyn",
            "lastname": "Hane",
            "countrycode": "CL"
        },
        {
            "userId": "66680",
            "firstname": "Jovani",
            "lastname": "Mosciski",
            "countrycode": "AT"
        },
    ];

    const option = {
        pagination: { perPage: 5 }
    };

    return (
        <ReactDataTable columns={columns} data={data} option={option} theme="bootstrap" />
    );
}

export default Index