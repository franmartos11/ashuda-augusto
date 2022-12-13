import React from 'react'

const RowDentist = ({dentist}) => {
  return (
    <tr>
        <td>{dentist.id}</td>
        <td>{dentist.name}</td>
        <td>{dentist.lastName}</td>
        <td>{dentist.registration}</td>
    </tr>
  )
}

export default RowDentist