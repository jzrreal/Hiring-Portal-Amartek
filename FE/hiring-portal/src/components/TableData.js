export default function Table ( {responseData, getId, addColumn} ) {

    const dataMapping = (data) => {
        return (
            <tr>
                {Object.keys(data).map((value) => {
                    return (
                        <td id={data[0]}>{data[value]}</td>
                    )
                })}
                {addColumn.map((column) => {
                    getId(data.id)
                    return(column)
                })}
            </tr>
        )
    }
    return (
        <>
        {responseData.map(dataMapping)}
        </>
    )
}