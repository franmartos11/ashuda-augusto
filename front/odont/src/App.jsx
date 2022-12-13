import React from 'react'
import  Home  from './page/Home'
import Patient  from './page/Patient'
import Dentist from './page/Dentist'
import Appointment from './page/Appointment'
import {Route,Routes} from 'react-router-dom'

const App = () => {
  return (
    <div>
      <Routes>
        <Route  path='/' element={<Home/>}>
          <Route path='patients' element={<Patient/>}></Route>
          <Route path='/dentists' element={<Dentist/>}></Route>
          <Route path='/appointments' element={<Appointment/>}></Route>
        </Route>
      </Routes>
      </div>
  )
}
export default App

