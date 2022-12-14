import React, {useEffect, useState} from 'react';
import './App.css';
import axios, {AxiosRequestConfig} from "axios";

function App() {

    const [user, setUser] = useState<string>()
    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    useEffect(() => {
        checkIfLogin()
    }, [])

    const config: AxiosRequestConfig = {
        headers: {
            Authorization: "Basic " + btoa(username + ":" + password)
        }
    }

    const checkIfLogin = () => {
        axios.get("/api/users/me")
            .then((response) => {
                setUser(response.data)
            })
            .catch(() => {
                setUser(undefined)
            })
    }

    const login = () => {
        axios.get("/api/users/login", config)
            .then((response) => {
                setUser(response.data)
            })
    }

    const logout = () => {
        axios.get("/api/users/logout")
            .then((response) => {
                setUser(undefined)
                checkIfLogin()
            })
    }

    const get = () => {
        axios.get("/api/hello")
            .then((response) => {
                console.log(response.data)
            })
            .catch((error) => {
                console.error(error.message)
            })
    }

    return (
        <div className="App">
            {user}
            <input value={username} onChange={(event) => setUsername(event.target.value)}/>
            <input value={password} onChange={(event) => setPassword(event.target.value)}/>
            <button onClick={login}>Login</button>
            <button onClick={logout}>Logout</button>

            <button onClick={get}>GET</button>
        </div>
    );
}

export default App;
