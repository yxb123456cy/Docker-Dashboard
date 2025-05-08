import './App.css'
import {RouterProvider} from "react-router";
import router from "./router/router.tsx";


function App() {


    return (
        <>
            <div style={{fontFamily: "Helvetica, Arial, sans-serif"}}
            ><RouterProvider router={router}/>
            </div>
        </>
    )
}

export default App
