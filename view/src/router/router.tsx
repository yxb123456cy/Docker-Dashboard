// src/router/index.jsx
import {createBrowserRouter} from "react-router";
import {DashboardLayout} from "../layout/main/DashboardLayout.tsx";
import {Login} from "../views/login/Login.tsx";
import {Home} from "../views/home/Home.tsx";
import {Container} from "../views/app/Container.tsx";
import {Images} from "../views/images/Images.tsx";
import {Volumes} from "../views/volumes/Volumes.tsx";
import {Network} from "../views/network/Network.tsx";
import {System} from "../views/system/System.tsx";


const router = createBrowserRouter(
    [
    {
        path: "/Dashboard", element: <DashboardLayout/>,
        children: [
            { index: true, element: <Home/>},
            { path:"containers" ,element: <Container/>},
            { path:"images" ,element: <Images/>},
            { path:"volumes" ,element: <Volumes/>},
            { path:"networks" ,element: <Network/>},
            { path:"settings" ,element: <System/>},

        ]
    },
    {path: "/login", element: <Login/>},
])

export default router