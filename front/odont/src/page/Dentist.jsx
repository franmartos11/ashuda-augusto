import React, { useEffect, useState } from 'react'
import DentistTable from '../component/DentistTable'
import axios from 'axios';

const Dentist = () => {

  const [dentists,setDentists]= useState([])
    useEffect(()=>{
      const options = {method: 'GET', url: 'http://localhost:8080/dentists/all'};

      axios.request(options)
        .then(function (response){
          setDentists(response.data)})
        .catch(function (error){
           console.error(error);});
    },[])

  return (
    <div class="table">

        <thead>
          <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>LAST NAME</th>
            <th>REGISTER</th>
          </tr>
        </thead>

        <tbody>
          {dentists.map(dentist => <DentistTable key={dentist.id} dentist={dentist}/>)} 
        </tbody>

    </div>
      
  )
}

export default Dentist