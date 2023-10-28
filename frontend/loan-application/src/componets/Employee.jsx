import { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

export function EmployeeLogin() {

    const [employeeId, setEmployeeId] = useState('');
    const [password, setPassword] = useState('');
    const [edata, setEdata] = useState('')

    const navigate = useNavigate();

    useEffect(() => {
        if (edata !== '') {
            navigate('/employee-dashboard', { state: edata });
        }
    }, [edata, navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (employeeId === '' || password === '') {
            return alert("Fields are empty!");
        }

        try {
            const response = await axios.post("http://localhost:9090/employee/login", {
                employee_id: employeeId,
                password: password
            });

            setEdata(response.data);

        } catch (error) {
            alert(error.response.data.error);
        }
    }

    return (
        <div className="d-flex align-items-center justify-content-center min-vh-100">
            <div className="text-center">
                <h1 className="mb-4">Employee Login</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Employee Id"
                            value={employeeId}
                            onChange={(e) => setEmployeeId(e.target.value)}
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="password"
                            className="form-control"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary mb-3 mx-5">Login</button>
                </form>
                <Link to="/">Back</Link>
            </div>
        </div>
    )
}

export function EmployeeDashboard() {

    const location = useLocation();
    const [stateData, setStateData] = useState(location.state);

    return (
        <div className="container mt-5">
            <Link to="/">Back</Link>
            <div className="row">
                <div className="col-md-5">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">User Details</h5>
                            <p className="card-text">Name: {stateData.employee_name}</p>
                            <p className="card-text">Email: {stateData.email_id}</p>
                        </div>
                    </div>
                </div>

                <div className="col-md-7">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Applications</h5>
                            <hr />

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
