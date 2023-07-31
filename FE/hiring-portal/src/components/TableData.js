export default function Table ( {responseData, addColumn} ) {

    const dataMapping = (data) => {
        return (
            <tr>
                {Object.keys(data).map((value) => {
                    return (
                        <td id={data[0]}>{data[value]}</td>
                    )
                })}
                {addColumn}
            </tr>
        )
    }
    return (
        <>
        {responseData.map(dataMapping)}
        </>
    )
}