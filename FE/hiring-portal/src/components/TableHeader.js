export default function TableHeader ( {responseData} ) {
    const headerMapping = (header) => {
        return (
            <th>{header}</th>
        )
    }
    return (
            <>
            {Object.keys(responseData[0]).map(headerMapping)}
            </>
    )
}