import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

export function CustomerLogin() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cdata, setCdata] = useState('')

    const navigate = useNavigate();

    useEffect(() => {
        if (cdata !== '') {
            navigate('/customer-dashboard', { state: cdata });
        }
    }, [cdata, navigate]);


    const handleSubmit = async (e) => {
        e.preventDefault();

        if (email === '' || password === '') {
            return alert("Fields are empty!");
        }

        try {
            const response = await axios.post("http://localhost:9090/customer/login", {
                emailId: email,
                password: password
            });
            setCdata(response.data);

        } catch (error) {
            alert(error.response.data.error);
        }
    }

    return (
        <div className="d-flex align-items-center justify-content-center min-vh-100">
            <div className="text-center">
                <h1 className="mb-4">Customer Login</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
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
                    <Link to='/customerRegister' type="submit" className="btn btn-secondary mb-3 mx-5">Go to Register</Link>
                </form>
                <Link to="/">Back</Link>
            </div>
        </div>
    )
}

export function CustomerRegister() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phone, setPhone] = useState('');
    const [pan, setPan] = useState('');

    const [apiData, setApiData] = useState('')

    const navigate = useNavigate();

    useEffect(() => {
        if (apiData !== '') {
            navigate('/customerLogin');
        }
    }, [apiData, navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:9090/customer/register", {
                "firstName": firstName,
                "lastName": lastName,
                "emailId": email,
                "password": password,
                "phone": phone,
                "pan": pan
            });

            setApiData(response.data);
        } catch (error) {
            alert(error.response.data.error);
        }
    }

    return (
        <div className="d-flex align-items-center justify-content-center min-vh-100">
            <div className="text-center">
                <h1 className="mb-4">Register Customer</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="First Name"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Last Name"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="email"
                            className="form-control"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="password"
                            className="form-control"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Phone"
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="PAN"
                            value={pan}
                            onChange={(e) => setPan(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary mb-3 mx-5">Register</button>
                    <Link to='/customerLogin' className="btn btn-secondary mb-3 mx-5">Go to Login</Link>
                </form>
                <Link to="/">Back</Link>
            </div>
        </div>
    )
}

export function CustomerDashboard() {
    const location = useLocation();
    const [stateData, setStateData] = useState(location.state);

    const [loanCategories, setLoanCategories] = useState([])
    const [applicationList, setApplicationList] = useState([])

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:9090/loan/fetchAll');
                setLoanCategories(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData();

        const fetchloans = async () => {
            try {
                const response = await axios.get('http://localhost:9090/customer/getloans/' + stateData.customerId);
                setApplicationList(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchloans();
    }, []);


    const handleLoan = async () => {
        const selectedLoanCategory = document.getElementById('loanCategory').value;
        try {
            const response = await axios.post('http://localhost:9090/customer/apply-loan', {
                customerId: stateData.customerId,
                loan_id: selectedLoanCategory
            });
            setApplicationList((prevList) => [...prevList, response.data])
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

    return (
        <div className="container mt-5">
            <Link to="/">Back</Link>
            <div className="row">
                <div className="col-md-5">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">User Details</h5>
                            <p className="card-text">Name: {stateData.firstName + " " + stateData.lastName}</p>
                            <p className="card-text">Email: {stateData.emailId}</p>
                            <p className="card-text">Phone: {stateData.phone}</p>
                            <p className="card-text">PAN: {stateData.pan}</p>
                        </div>
                    </div>
                    <hr />
                    <div className="card">
                        <div className="card-body">
                            <h6>Apply for Loan here!</h6>
                            <select id="loanCategory" className="form-select" aria-label="Loan Category">
                                {loanCategories.map((category, index) => (
                                    <option key={index} value={category.loan_id}>
                                        {category.loan_type}
                                    </option>
                                ))}
                            </select>

                            <button className="btn btn-primary mt-3" onClick={handleLoan}>Apply loan</button>
                        </div>
                    </div>
                </div>

                <div className="col-md-7">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Applications</h5>
                            <hr />
                            <ul>
                                {applicationList && applicationList.map((application, index) => (
                                    <li key={index}>
                                        <b>Application ID : </b> {application.applicationId},
                                        <b> Loan :</b> {application.loan.loan_type},
                                        <b>Status :</b> {application.status}
                                    </li>
                                ))}
                                {applicationList.length === 0 && (<p>No Loans Applied yet</p>)}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
