import { Route, Routes } from 'react-router-dom';
import './App.css';
import { Welcome } from './componets/Login';
import { CustomerDashboard, CustomerLogin, CustomerRegister } from './componets/Customer';
import { EmployeeDashboard, EmployeeLogin } from './componets/Employee';

function App() {
  return (
    <div className="App">
      <div>
        <Routes>
          <Route path='' element={<Welcome />} />
          <Route path='/welcome' element={<Welcome />} />
          <Route path="/customerLogin" element={<CustomerLogin />} />
          <Route path="/customerRegister" element={<CustomerRegister />} />
          <Route path="/employeeLogin" element={<EmployeeLogin />} />
          <Route path="/employee-dashboard/*" element={<EmployeeDashboard />} />
          <Route path="/customer-dashboard/*" element={<CustomerDashboard />} />

        </Routes>
      </div>
    </div>
  );
}

export default App;
