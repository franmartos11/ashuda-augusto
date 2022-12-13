import React, { useEffect, useState } from 'react'
import AppointmentTable from '../component/AppointmentTable'
import axios from 'axios';

const Appointment = () => {
  const [appointments,setAppointments]= useState([])

    useEffect(()=>{
      const options = {method: 'GET', url:'http://localhost:8080/appointments/all'};

      axios.request(options)
        .then(function (response){
          setAppointments(response.data)})
        .catch(function (error){
           console.error(error);});
    },[])

    console.log(appointments);

  return (
    <div class="table">

      <thead>
        <tr>
          <th>ID</th>
          <th>DATE AND TIME</th>
          <th>DENTIST</th>
          <th>PACIENT</th>
        </tr>
      </thead>

      <tbody>
        {appointments.map(appointment => <AppointmentTable key={appointment.id} appointment={appointment}/>)} 
      </tbody>
    
    </div>
  )
}

export default Appointment
