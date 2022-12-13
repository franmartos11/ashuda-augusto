import React from 'react'
import {Link} from 'react-router-dom'

const Header = () => {
    
  return (
    
    <nav>
      <Link to='/'><h3>HOME</h3></Link>
      <Link to='/patients'><h3>PATIENTS</h3></Link>
      <Link to='/dentists'><h3>DENTISTS</h3></Link>
      <Link to='/appointments'><h3>APPOINTMENTS</h3></Link>
    </nav> 
          
  )
}

export default Header