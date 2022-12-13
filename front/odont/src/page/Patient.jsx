import React ,{ useState,useEffect } from 'react'
import axios from 'axios';
import PatientTable from '../component/PatientTable';

const Patient = () => {
    const [patients,setPatients]= useState([])

    useEffect(()=>{
      
      const options = {method: 'GET', url: 'http://localhost:8080/patients/all'};

      axios.request(options)
        .then(function (response){
          setPatients(response.data)})
        .catch(function (error){
           console.error(error);});
    },[])
  

    return (
      <div class="table">

        <thead>
          <tr>
            <th>Id</th>
            <th>FIRS NAME</th>
            <th>LAST NAME</th>
            <th>DNI</th>
            <th>ADDRESS</th>
            <th>DISCHARGE DATE</th>
          </tr>
        </thead>

        <tbody>
          {patients.map(patient => <PatientTable key={patient.id} patient={patient}/>)} 
        </tbody>

      </div>
    )
}

export default Patient
