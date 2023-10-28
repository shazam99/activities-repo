import { Link } from "react-router-dom";


export function Welcome() {
    return (
        <div className="d-flex align-items-center justify-content-center min-vh-100">
            <div className="text-center">
                <h1 className="mb-4">Loan Application</h1>
                <Link to='/customerLogin' className="btn btn-primary me-2">Customer Login</Link>
                <Link to='/employeeLogin' className="btn btn-secondary">Employee Login</Link>
            </div>
        </div>
    );
}



