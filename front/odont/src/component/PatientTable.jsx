import React from 'react'

const PatientTable = ({patient}) => {
  return (
    <tr>
        <td>{patient.id}</td>
        <td>{patient.name}</td>
        <td>{patient.lastName}</td>
        <td>{patient.dni}</td>
        <td>{patient.address}</td>
        <td>{patient.dischargeDate}</td>
    </tr>
  )
}

export default PatientTable