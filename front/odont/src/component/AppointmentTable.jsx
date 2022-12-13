import React from 'react'

const AppointmentTable = ({appointment}) => {
  return (
    <tr>
        <td>{appointment.id}</td>
        <td>{appointment.dayTime}</td>
        <td>{appointment.dentist.name} {appointment.dentist.lastName}</td>
        <td>{appointment.patient.name} {appointment.patient.lastName}</td>
    </tr>
  )
}

export default AppointmentTable