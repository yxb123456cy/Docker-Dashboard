import {useEffect, useState} from "react";
export const System = () => {
    const [data, setData] = useState("Hello,系统设置")
    useEffect(() => {
        console.log("hello,React");
    }, []);
    return (
        <>
            <h1>学习{data}</h1>
            <button onClick={() => {
                setData("Vue")
            }}>
                点击我学习Vue;
            </button>
        </>
    )
}