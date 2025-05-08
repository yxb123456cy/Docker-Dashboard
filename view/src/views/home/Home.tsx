import {useEffect, useState} from "react";
import {Button} from "antd";
export const Home = () => {
    const [data, setData] = useState("Hello,系统首页")
    useEffect(() => {
        console.log("hello,React");
    }, []);
    return (
        <>
            <h1>学习{data}</h1>
            <Button onClick={() => {
                setData("Vue")
            }}>
                点击我学习Vue;
            </Button>
        </>
    )
}